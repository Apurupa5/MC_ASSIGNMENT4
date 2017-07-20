package algos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import database.DatabaseHelper;
import utils.Provider;

public class BandwidthGreedy implements Algo {

	public BandwidthGreedy(JSONObject query) {
		this.query=query;
	}
	
	JSONObject query;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("USING BANDWIDTH GREEDY ALGORITHM");

			int minProviders=query.getInt("min");
			String queryID=query.getString("queryNo");

			String sensorName=query.getString("dataReqd");
			String powerSensor="";

			if(sensorName.equalsIgnoreCase("GPS"))
			{
				powerSensor="GPSPower";
			}
			if(sensorName.equalsIgnoreCase("Accelerometer"))
			{
				powerSensor="AccPower";
			}
			if(sensorName.equalsIgnoreCase("Gyroscope"))
			{
				powerSensor="GyrPower";
			}
			if(sensorName.equalsIgnoreCase("Microphone"))
			{
				powerSensor="MicPower";
			}

			// add condition for selecting providers with that sensor available
			String selectNodes="select sum(providerMode= ? and "+powerSensor+">0) from nodes;";
			//PreparedStatement checkNodes=connect.prepareStatement("select sum(providerMode=1 and "+powerSensor+">0) from nodes;");
			PreparedStatement checkNodes=connect.prepareStatement(selectNodes);
			checkNodes.setString(1,"1");
				
			ResultSet nodeCount=checkNodes.executeQuery();
			nodeCount.next();
			int availableCount=nodeCount.getInt(1);
			System.out.println("Available count: "+availableCount);
			nodeCount.close();
			checkNodes.close();

			if(availableCount>=minProviders) {
				List<String> selectedProviders=new ArrayList<String>();
				List<Provider> availableProviders=new ArrayList<>();
				List<Provider> allProviders=new ArrayList<Provider>();
				
				PreparedStatement allProv=connect.prepareStatement("select DeviceID from nodes where ProviderMode=1 and "+powerSensor+">0;");
				ResultSet availableProvs=allProv.executeQuery();
				while(availableProvs.next()){
					allProviders.add(new Provider(availableProvs.getString(1),0, 0,0,0,0, 0, 0, 0, false));
				}
				allProv.close();
				
				
				//------code for greedy bandwidth allocation
			
				List<Provider> result=new ArrayList<>();
				// pick the required devices with highest linkspeed levels from the MySQL database 
				String state="select DeviceID from nodes where ProviderMode=1 order by linkspeed desc limit "+minProviders;
				ResultSet valueResult = DatabaseHelper.dbOperation(state);
				while(valueResult.next()){
					//add the provider to the list of selectedProviders
					String device=valueResult.getString(1);
					selectedProviders.add(device);
					result.add(allProviders.stream().filter(p1->p1.getDeviceId().equals(device)).findFirst().get());
				}
				
				System.out.println("All: "+allProviders);
		
				int numProviders = result.size();
	
				System.out.println("selected Provider :" + selectedProviders );
		
     			if(result.size()!=minProviders)
					System.err.println("Some problem selecting providers ");
			
				// if count is > 0 add the providers to selected providers list
				if(numProviders > 0) {
					for(Provider p: result) {
						//if device is selected, write it to DeviceSelection.log with last field as 1
					//	selectedProviders.add(p.deviceId);
						if(!availableProviders.contains(p)) {
							availableProviders.add(p);
						}
					} 
				}
				//-----------------------------algo ends here------------------------------
				System.out.println("After Algorithm");
				System.out.println("selected Provider :::" + selectedProviders );
				for(int i=0;i<selectedProviders.size();i++)
				{
					BaseMethods.sendQuery(query, selectedProviders.get(i));
				}
			}else {
				System.out.println("Min providers not available");
				String selectionType=query.getString("selection");
				String setServiced="insert into queries(queryID, providerID, QueryAllocation, serviced) values ('"+queryID+"','unavailable','"+selectionType+"',2)";
				PreparedStatement set=connect.prepareStatement(setServiced);
				set.executeUpdate();
				set.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}



}

package algos;

import org.json.JSONObject;

public class AlgoFactory {

	//	public static HashMap<String, String> newAlgo;

	public static Algo getRequestedAlgo(String type, JSONObject query){
		System.out.println("In Algo Factory");
		//to select BandwidthGreedy class
		if("Bandwidth".equalsIgnoreCase(type)){ 
			Class<?> t = null;
			try{
			t = Class.forName("algos.BandwidthGreedy");
			return (Algo) t.getConstructor(JSONObject.class).newInstance(query);
			} catch (Exception e) {
			e.printStackTrace();
			}
			return null;
			}
		if("greedy".equalsIgnoreCase(type)){ 
			Class<?> t = null;
			try{
				t = Class.forName("algos.BatteryGreedy");
				return (Algo) t.getConstructor(JSONObject.class).newInstance(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}if("broadcast".equalsIgnoreCase(type)){ 
			Class<?> t = null;
			try{
				t = Class.forName("algos.Broadcast");
				return (Algo) t.getConstructor(JSONObject.class).newInstance(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}if("nvalues".equalsIgnoreCase(type)){ 
			Class<?> t = null;
			try{
				t = Class.forName("algos.NValues");
				return (Algo) t.getConstructor(JSONObject.class).newInstance(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}if("minAST".equalsIgnoreCase(type)){ 
			Class<?> t = null;
			try{
				t = Class.forName("algos.MinAST");
				return (Algo) t.getConstructor(JSONObject.class).newInstance(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		if("random".equalsIgnoreCase(type)){ 
			Class<?> t = null;
			try{
				t = Class.forName("algos.RandomAllocation");
				return (Algo) t.getConstructor(JSONObject.class).newInstance(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		return null;
	}
}

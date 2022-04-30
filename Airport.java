import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * Airport
 */
public class Airport {

	private Queue<String> Land;
	private Queue<String> takeOff;
	private ArrayList<String> log;


	public Airport() {
		Land = new LinkedList<String>();
		takeOff = new LinkedList<String>();
		log = new ArrayList<String>();
	}

	public void addTakeOff(String fnumber) {
		takeOff.add(fnumber);
	}

	public void addLanding(String fnumber) {
		Land.add(fnumber);
	}

	public String handleNextAction() {
		String s;
		if (!Land.isEmpty()) {
			s = Land.remove();
			log.add("Flight " + s + " landed.");
			return "Flight " + s + " is landing.";
		}else if(!takeOff.isEmpty()){
			s = takeOff.remove();
			log.add("Flight " + s + " taken-off." );
			return "Flight " + s + " is taking off.";
		} 
		return "No plane is waiting to land or take-off.";
	}

	public String waitingPlanes(){
		String st = "";
		if (!Land.isEmpty() || !takeOff.isEmpty()) {
			if (!Land.isEmpty()) {
				st += "Planes waiting to land:\n";
				st += "---------------------------\n";
				for (String string : Land) {
					st += string + "\n";
				}
			}

			if (!takeOff.isEmpty()) {
				st += "Planes waiting to take-off:\n";
				st += "---------------------------\n";
				for (String string : takeOff) {
					st += string + "\n";
				}
			}
		}else{
			return "No plane is in the landing and take-off queues.";
		}
		return st;
	}


	
	public String log() {
		String str = "";
		if (log.isEmpty()) {
			return str += "no activity exists";
		}
		str += "List of the landing/take-off activities\n" +  "---------------------------------------\n";
		for (String string : log) {
			str += string + "\n";
		}
		return str;
	}

	public void log(String name){
		FileOutputStream file = null;
		
		System.out.println("Writing the airport log to the file...");
		
		//for (String string : log) {
			//of.writeUTF(string);
		//}
		//of.close();
		try {
			file = new FileOutputStream(name);
		} catch (FileNotFoundException e) {
			//TODO: handle exception
			e.printStackTrace();
		}
		DataOutputStream of = new DataOutputStream(file);
		for (String string : log) {
			try {
				of.writeUTF(string);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Done.");
		
		}
	
	
	
}
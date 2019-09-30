import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class PassengerDataBase {
	Passenger passenger = new Passenger();
	private ArrayList<Trip> trips = new ArrayList<>();
	private ArrayList<Passenger> passengerList = new ArrayList<>();
	public ArrayList<Passenger> readPassengerInfo() {
		
		try {
			FileReader fr = new FileReader("passengerinfo.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			
			while ((line = br.readLine()) != null) {
				Passenger passenger = new Passenger();
				String tmp[] = line.split("\t");
				passenger.setFirstName(tmp[0]);
				passenger.setLastName((tmp[1]));
				passenger.setUsername(tmp[2]);
				passenger.setPassword(tmp[3]);
				passenger.setAge(Integer.valueOf(tmp[4]));
				passengerList.add(passenger);
			}
		} catch (IOException e) {
		}
		
		return passengerList;
	}

	public ArrayList<Trip> passengerTable(Passenger passenger) {

		try {

			FileReader fr = new FileReader("passengerTrips.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = null;

			while ((line = br.readLine()) != null) {

				Trip tripObj = new Trip();
				String tmp[] = line.split("\t");
				tripObj.setTripNo(tmp[0]);
				tripObj.setSource(tmp[1]);
				tripObj.setDestination(tmp[2]);
				tripObj.setNoOfTickets(Integer.valueOf(tmp[3]));
				tripObj.setDepTime(tmp[4]);
				tripObj.setArrTime(tmp[5]);
				tripObj.setStops(Integer.parseInt(tmp[6]));
				tripObj.setType(tmp[7]);
				tripObj.setPrice(Double.parseDouble(tmp[8]));
				tripObj.setVehicle(tmp[9]);
				trips.add(tripObj);
			}
		} catch (IOException e) {
			System.out.println("error");
		}
		return trips;
	}

	public void writePassengerFile(Passenger passenger, ArrayList<Trip> x) {

		File file = new File(
				("passengerTrips.txt"));
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			BufferedWriter buff = new BufferedWriter(writer);
			for (int i = 0; i < x.size(); i++) {
				buff.write(x.get(i).getTripNo());
				buff.write("\t");
				buff.write(x.get(i).getSource());
				buff.write("\t");
				buff.write(x.get(i).getDestination());
				buff.write("\t");
				buff.write(String.valueOf(x.get(i).getNoOfTickets()));
				buff.write("\t");
				buff.write(x.get(i).getDepTime());
				buff.write("\t");
				buff.write(x.get(i).getArrTime());
				buff.write("\t");
				buff.write(String.valueOf(x.get(i).getStops()));
				buff.write("\t");
				buff.write(x.get(i).getType());
				buff.write("\t");
				buff.write(String.valueOf(x.get(i).getPrice()));
				buff.write("\t");
				buff.write(x.get(i).getVehicle());
				buff.newLine();
			}
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Boolean confirmPassengerInfo(String username, String password) {
		Passenger passenger = new Passenger();
		ArrayList<Passenger>x=new ArrayList<>();
		x= readPassengerInfo();
		for(int i=0;i<x.size();i++){
			if (passenger.getUsername().equals(username)
					&& passenger.getPassword().equals(password)) 
				return true;
			}
		return false;
	}

	public void writeLoginInfo(Passenger passenger) {

		File file = new File(
				("login.txt"));
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			BufferedWriter buff = new BufferedWriter(writer);
			buff.write(passenger.getUsername());
			buff.write("\t");
			buff.write(passenger.getPassword());
			buff.newLine();

			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Passenger readLoginInfo() {
		Passenger passenger = new Passenger();
		try {
			FileReader fr = new FileReader("login.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {		
				String tmp[] = line.split("\t");
				passenger.setUsername(tmp[0]);
				passenger.setPassword(tmp[1]);
			}
		} catch (IOException e) {
			System.out.println("error");
		}
		return passenger;
	}
	

}

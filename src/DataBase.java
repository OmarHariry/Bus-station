import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBase {

	private ArrayList<Driver> driverList = new ArrayList<>();
	private ArrayList<Trip> trips = new ArrayList<>();
	private ArrayList<Passenger> passengerList = new ArrayList<>();
	public ArrayList<Trip> readTripFile() {

		try {

			FileReader fr = new FileReader("trips.txt");
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
				tripObj.setStops(Integer.valueOf(tmp[6]));
				tripObj.setType(tmp[7]);
				tripObj.setDriver(tmp[8]);
				tripObj.setPrice(Double.valueOf(tmp[9]));
				tripObj.setVehicle(tmp[10]);
				trips.add(tripObj);
			}
		} catch (IOException e) {
			System.out.println("error");
		}
		return trips;
	}

	public void writeFile(ArrayList<Trip> list) {

		File file = new File("trips.txt");
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
			for (int i = 0; i < list.size(); i++) {
				int j = i + 1;
				buff.write(j + "");
				buff.write("\t");

				buff.write(list.get(i).getSource());
				// writes the first token then leaves a tab
				buff.write("\t");

				buff.write(list.get(i).getDestination());
				buff.write("\t");

				buff.write(Integer.toString(list.get(i).getNoOfTickets()));
				buff.write("\t");

				buff.write(list.get(i).getDepTime());
				buff.write("\t");

				buff.write(list.get(i).getArrTime());
				buff.write("\t");
				// after writing the whole trip leave a new line
				buff.write(Integer.toString(list.get(i).getStops()));
				buff.write("\t");

				buff.write(list.get(i).getType());
				buff.write("\t");

				buff.write(list.get(i).getDriver());
				buff.write("\t");

				buff.write(Double.toString(list.get(i).getPrice()));
				buff.write("\t");

				buff.write(list.get(i).getVehicle());
				buff.newLine();
			}
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writePassengerFile(Passenger passenger, ArrayList<Trip> x) {

		File file = new File("passengerTrips.txt");
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

	public ArrayList<Trip> readPassengerFile(Passenger passenger) {

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
	public ArrayList<Passenger> readPassengerInfo() {

		try {

			FileReader fr = new FileReader("passengerinfo.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				Passenger passenger = new Passenger();
				String tmp[] = line.split("\t");
				passenger.setFirstName(tmp[0]);
				passenger.setLastName(tmp[1]);
				passenger.setUsername(tmp[2]);
				passenger.setPassword(tmp[3]);
				passenger.setAge(Integer.valueOf(tmp[4]));
				passengerList.add(passenger);
			}
		} catch (IOException e) {
			System.out.println("error");
		}
		return passengerList;
	}
	public ArrayList<Driver> readDriverFile() {
		try {
			FileReader fr = new FileReader("drivers.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				Driver driver = new Driver();
				String tmp[] = line.split("\t");
				driver.setID(Integer.valueOf(tmp[0]));
				driver.setFirstName(tmp[1]);
				driver.setLastName(tmp[2]);
				driver.setUsername(tmp[3]);
				driver.setPassword(tmp[4]);
				driver.setAge(Integer.valueOf(tmp[5]));
				driverList.add(driver);
			}
		} catch (IOException e) {
			System.out.println("error");
		}
		return driverList;
	}
	public Boolean confirmLogin(String username, String password) {
		DataBase data = new DataBase();
		ArrayList<Driver> x = new ArrayList<>();
		x = data.readDriverFile();
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i).getUsername().toString().equals(username)
					&& x.get(i).getPassword().toString().equals(password)) {
				return true;
			}
		}
		return false;
	}
	public Boolean confirmPassengerInfo(String username, String password) {
		DataBase data = new DataBase();
		ArrayList<Passenger> x = new ArrayList<>();
		x = data.readPassengerInfo();
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i).getUsername().equals(username)
					&& x.get(i).getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}public String whichDriver(String username, String password, Driver driver) {
		if (username.equals("Paul6")) {
			driver.setFirstName("Paul");
		}
		if (username.equals("Mark99")) {
			driver.setFirstName("Mark");
		}
		if (username.equals("JohnWWE")) {
			driver.setFirstName("John");
		}
		return driver.getFirstName();
	}
	public ArrayList<Driver> getDriverList() {
		return driverList;
	}
	public void setDriverList(ArrayList<Driver> driverList) {
		this.driverList = driverList;
	}
	public ArrayList<Trip> getTrips() {
		return trips;
	}

	public void setTrips(ArrayList<Trip> trips) {
		this.trips = trips;
	}

	public ArrayList<Passenger> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(ArrayList<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	
}
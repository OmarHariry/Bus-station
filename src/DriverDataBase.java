//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class DriverDataBase {
//	Driver driver = new Driver();
//	ArrayList<Trip> trips = new ArrayList<>();
//	ArrayList<Driver>driverList=new ArrayList<>();
//	public void writeDriverInfo(Driver driver) {
//
//		File file = new File((driver.getUsername())
//				+ driver.getPassword() + ".txt");
//		if (!file.exists())
//			try {
//				file.createNewFile();
//			} catch (IOException e1) {
//
//				e1.printStackTrace();
//			}FileWriter writer;
//			try {
//				writer = new FileWriter(file);
//				BufferedWriter buff = new BufferedWriter(writer);
//				buff.write(Integer.toString(driver.getID()));
//				buff.write("\t");
//				
//				buff.write(driver.getFirstName());
//				buff.write("\t");
//
//				buff.write(driver.getLastName());
//				buff.write("\t");
//
//				buff.write(driver.getUsername());
//				buff.write("\t");
//
//				buff.write(driver.getPassword());
//				buff.write("\t");
//				
//				buff.write(Integer.toString(driver.getAge()));
//				buff.write("\t");
//				
//				buff.newLine();
//				buff.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	public Driver readDriverInfo(String username,String password) {
//
//		try {
//
//			FileReader fr = new FileReader(username+password+".txt");
//			BufferedReader br = new BufferedReader(fr);
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				Driver driver = new Driver();
//				String tmp[] = line.split("\t");
//				driver.setID(Integer.valueOf(tmp[0]));
//				driver.setFirstName(tmp[1]);
//				driver.setLastName(tmp[2]);
//				driver.setUsername(tmp[3]);
//				driver.setPassword(tmp[4]);
//				driver.setAge(Integer.valueOf(tmp[5]));
//				this.driver=driver;
//			}
//		} catch (IOException e) {
//			System.out.println("error");
//			System.out.println("Error in readDriverInfo");
//		}
//		return driver;
//	}
//	public void writeDriverFile(Driver driver, ArrayList<Trip> x) {
//
//		File file = new File(
//				(driver.getFirstName() + driver.getLastName() + ".txt"));
//		if (!file.exists())
//			try {
//				file.createNewFile();
//			} catch (IOException e1) {
//
//				e1.printStackTrace();
//			}
//		FileWriter writer;
//		try {
//			writer = new FileWriter(file);
//			BufferedWriter buff = new BufferedWriter(writer);
//			for (int i = 0; i < x.size(); i++) {
//				buff.write(x.get(i).getTripNo());
//				buff.write("\t");
//				buff.write(x.get(i).getSource());
//				buff.write("\t");
//				buff.write(x.get(i).getDestination());
//				buff.write("\t");
//				buff.write(String.valueOf(x.get(i).getNoOfTickets()));
//				buff.write("\t");
//				buff.write(x.get(i).getDepTime());
//				buff.write("\t");
//				buff.write(x.get(i).getArrTime());
//				buff.write("\t");
//				buff.write(String.valueOf(x.get(i).getStops()));
//				buff.write("\t");
//				buff.write(x.get(i).getType());
//				buff.write("\t");
//				buff.write(String.valueOf(x.get(i).getPrice()));
//				buff.write("\t");
//				buff.write(x.get(i).getVehicle());
//				buff.newLine();
//			}
//			buff.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	public ArrayList<Trip> driverTable(Driver driver) {
//
//		try {
//
//			FileReader fr = new FileReader(driver.getFirstName()
//					+ driver.getLastName() + ".txt");
//			BufferedReader br = new BufferedReader(fr);
//			String line = null;
//
//			while ((line = br.readLine()) != null) {
//
//				Trip tripObj = new Trip();
//				String tmp[] = line.split("\t");
//				tripObj.setTripNo(tmp[0]);
//				tripObj.setSource(tmp[1]);
//				tripObj.setDestination(tmp[2]);
//				tripObj.setNoOfTickets(Integer.valueOf(tmp[3]));
//				tripObj.setDepTime(tmp[4]);
//				tripObj.setArrTime(tmp[5]);
//				tripObj.setStops(Integer.parseInt(tmp[6]));
//				tripObj.setType(tmp[7]);
//				tripObj.setPrice(Double.parseDouble(tmp[8]));
//				tripObj.setVehicle(tmp[9]);
//				trips.add(tripObj);
//			}
//		} catch (IOException e) {
//			System.out.println("error");
//			System.out.println("Error in driverTable");
//		}
//		return trips;
//	}
//	
//	public Boolean confirmDriverInfo(String username, String password) {
//		Driver driver = new Driver();
//		driver= readDriverInfo(username, password);
//		if (driver.getUsername().equals(username)
//				&& driver.getPassword().equals(password)) {
//			return true;
//		}
//		return false;
//	}
//
//	public void writeLoginInfo(Driver driver) {
//
//		File file = new File(
//				("loginDriver.txt"));
//		if (!file.exists())
//			try {
//				file.createNewFile();
//			} catch (IOException e1) {
//
//				e1.printStackTrace();
//			}
//		FileWriter writer;
//		try {
//			writer = new FileWriter(file);
//			BufferedWriter buff = new BufferedWriter(writer);
//			buff.write(driver.getUsername());
//			buff.write("\t");
//			buff.write(driver.getPassword());
//			buff.newLine();
//
//			buff.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("TEST");
//			System.out.println("Error in writeDriverLogin");
//		}
//	}
//
//	public Driver readLoginInfo() {
//		Driver driver = new Driver();
//		try {
//			FileReader fr = new FileReader("loginDriver.txt");
//			System.out.println("FileReader sha8al 3ady");
//			BufferedReader br = new BufferedReader(fr);
//			System.out.println("BUFFEREDREADER sha8al 3ady");
//			String line = null;
//			while ((line = br.readLine()) != null) {		
//				String tmp[] = line.split("\t");
//				driver.setUsername(tmp[0]);
//				driver.setPassword(tmp[1]);
//				System.out.println("LOOOOOOP sha8al 3ady");
//			}
//		} catch (IOException e) {
//			System.out.println("error");
//			System.out.println("Error in readDriverLogin");
//		}
//		return driver;
//	}
//	public ArrayList<Driver> readDriverFile() {
//
//		try {
//
//			FileReader fr = new FileReader("drivers.txt");
//			BufferedReader br = new BufferedReader(fr);
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				Driver driver = new Driver();
//				String tmp[] = line.split("\t");
//				driver.setID(Integer.valueOf(tmp[0]));
//				driver.setFirstName(tmp[1]);
//				driver.setLastName(tmp[2]);
//				driver.setUsername(tmp[3]);
//				driver.setPassword(tmp[4]);
//				driver.setAge(Integer.valueOf(tmp[5]));
//				driverList.add(driver);
//			}
//		} catch (IOException e) {
//			System.out.println("error");
//		}
//		return driverList;
//	}
//	public String whichDriver(String username, String password) {
//		if (username.equals("Paul6")) {
//			driver.setFirstName("Paul");
//		}
//		if (username.equals("Mark99")) {
//			driver.setFirstName("Mark");
//		}
//		if (username.equals("JohnWWE")) {
//			driver.setFirstName("John");
//		}
//		return driver.getFirstName();
//	}
//}

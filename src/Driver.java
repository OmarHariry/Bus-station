import java.util.ArrayList;

public class Driver extends Person{
private int ID;
private int age;
private String firstName;
private String lastName;
private String username;
private String password;
private ArrayList<Trip> driverTrips;
public ArrayList<Trip> getDriverTrips() {
	return driverTrips;
}
public void setDriverTrips(ArrayList<Trip> driverTrips) {
	this.driverTrips = driverTrips;
}
public int getID() {
	return ID;
}
public void setID(int iD) {
	ID = iD;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

}
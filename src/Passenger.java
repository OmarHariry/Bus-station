import java.util.ArrayList;

public class Passenger extends Person{
private String firstName;
private String lastName;
private String password;
private String username;
private int age ;
private ArrayList<Trip> x = new ArrayList<Trip>();
public ArrayList<Trip> addTrip(Trip trip, int noOfTickets) {
	trip.setNoOfTickets(noOfTickets);
	x.add(trip);
	return x;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
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

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public ArrayList<Trip> getTrips() {
	return x;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public void setTrips(ArrayList<Trip> x) {
	this.x = x;
}

}

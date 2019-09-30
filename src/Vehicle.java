
public class Vehicle {
   private String vehName;
   private Double ticketPrice;
public String getVehName() {
	return vehName;
}
public void setVehName(String vehName) {
	this.vehName = vehName;
}
public Double getTicketPrice() {
	return ticketPrice;
}
public void setTicketPrice(Double ticketPrice) {
	this.ticketPrice = ticketPrice;
}
public Double getPrice(String vehicleName,String type) {
	if(type=="roundtrip"){
	if(vehicleName=="Bus")
		return ticketPrice*0.85;
	else if(vehicleName == "Limousine")
		return ticketPrice*2*0.85;
	else if (vehicleName=="Micro-Bus")
		return ticketPrice*0.7*0.85;
	}else if(type == "onewaytrip")
	{
		if(vehicleName=="Bus")
			return ticketPrice;
		else if(vehicleName == "Limousine")
			return ticketPrice*2;
		else if (vehicleName=="Micro-Bus")
			return ticketPrice*0.7;
	}
	return 0.0;
}
}

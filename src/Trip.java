public class Trip {
	public  String tripNo;
	private String source;
	private String destination;
	private double distance;
	private double duration;
	private String type;
	private String depTime;
	private String arrTime;
	private int noOfTickets;
	private Double price;
	private String driver;
	private int stops;
	private String vehicle;
	public int getStops() {
		return stops;
	}
	public void setStops(int stops) {
		this.stops = stops;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public Trip() {

	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public double getDistance() {
		return distance;
	}

	public double getDuration() {
		return duration;
	}

	public String getType() {
		return type;
	}

	public String getDepTime() {
		return depTime;
	}

	public String getArrTime() {
		return arrTime;
	}
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getVehicle() {
		return vehicle;
	}


	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getTripNo() {
		return tripNo;
	}
	public void setTripNo(String tripNo) {
		this.tripNo = tripNo;
	}
	public Double getPrice(String vehicleName,String type,int noOfStpops) {
		if(type=="Round Trip"){
		if(vehicleName=="Bus")
			return price*0.85*(1-0.05*noOfStpops);
		else if(vehicleName == "Limousine")
			return price*2*0.85*(1-0.05*noOfStpops);
		else if (vehicleName=="Micro-Bus")
			return price*0.7*0.85*(1-0.05*noOfStpops);
		}else if(type == "One-Way Trip")
		{
			if(vehicleName=="Bus")
				return price*(1-0.05*noOfStpops);
			else if(vehicleName == "Limousine")
				return price*2*(1-0.05*noOfStpops);
			else if (vehicleName=="Micro-Bus")
				return price*0.7*(1-0.05*noOfStpops);
		}
		return 0.0;
	}

}
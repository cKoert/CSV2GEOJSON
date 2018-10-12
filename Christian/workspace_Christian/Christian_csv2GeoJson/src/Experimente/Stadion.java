package Experimente;

public abstract class Stadion {
	private String team;
	private String fDCOUK;
	private String city;
	private int Capacity;
	private double latitude;
	private double longitude;
	private String country;
	
	public Stadion(String team, String fDCOUK, String city, int capacity, double latitude, double longitude,
			String country) {
		super();
		this.team = team;
		this.fDCOUK = fDCOUK;
		this.city = city;
		Capacity = capacity;
		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getfDCOUK() {
		return fDCOUK;
	}

	public void setfDCOUK(String fDCOUK) {
		this.fDCOUK = fDCOUK;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int capacity) {
		Capacity = capacity;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	

}

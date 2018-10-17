package CSV2GeoJSON;

public class Stadion {
	private String team;
	private String fDCOUK;
	private String city;
	private String stadium;
	private String capacity;
	private String latitude;
	private String longitude;
	private String country;
	
	public Stadion(String team, String fDCOUK, String city, String stadium, String capacity, String latitude,
			String longitude, String country) {
		super();
		this.team = team;
		this.fDCOUK = fDCOUK;
		this.city = city;
		this.stadium = stadium;
		this.capacity = capacity;
		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Stadion [team=" + team + ", fDCOUK=" + fDCOUK + ", city=" + city + ", stadium=" + stadium
				+ ", capacity=" + capacity + ", latitude=" + latitude + ", longitude=" + longitude + ", country="
				+ country + "]";
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

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
}

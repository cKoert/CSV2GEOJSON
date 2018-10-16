package BibliothekenTest;

public class Stadion {

	// Attribute
	private String team;
	private String fdcouk;
	private String city;
	private String capacity;
	private String lat;
	private String lon;
	private String country;

	// Konstruktor
	public Stadion(String team, String fdcouk, String city, String capacity, String lat, String lon, String country) {
		super();
		this.team = team;
		this.fdcouk = fdcouk;
		this.city = city;
		this.capacity = capacity;
		this.lat = lat;
		this.lon = lon;
		this.country = country;
	}

	// getter + setter

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getFdcouk() {
		return fdcouk;
	}

	public void setFdcouk(String fdcouk) {
		this.fdcouk = fdcouk;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}



public class Person {
	
	private int id;
	private String firstName;
	private String name;
	private String city;
	private String address;
	private String birthday;
	private String gender;

	public Person(int id, String firstName, String name, String city,
			String address, String birthday, String gender) {
		this.id = id;
		this.firstName = firstName;
		this.name = name;
		this.city = city;
		this.address = address;
		this.birthday = convertBirthday(birthday);
		this.gender = gender;
	}
	
	public String convertBirthday(String b) {
		String birthday = "";
		if (b.contains(".")) {
			birthday = b.substring(6) + "-" + b.substring(3, 5) + "-" + b.substring(0, 2);
		} else {			
			birthday = b.substring(8) + "." + b.substring(5, 7) + "." + b.substring(0, 4);
		}
		return birthday;
	}
	
	public int getID() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getGender() {
		return gender;
	}
	
}

package main.frontend.backend.orders;

public class Customer {
	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private int id;
	private String email; // Assuming "mail" was changed to "email"
	private String fullName;
	private String gender; // Assuming "male" boolean was replaced with a gender String field
	private boolean enabled;

	public Customer() {
	}

	public Customer(int id, String email, String fullName, String gender, boolean enabled) {
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.gender = gender;
		this.enabled = enabled;
	}

	public Customer(Customer other) {
		this(other.id, other.email, other.fullName, other.gender, other.enabled);
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return fullName;
	}

	public String getGender() {
		return gender;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void changeInfo(int id, String email, String fullName, String gender, boolean enabled) {
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.gender = gender;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		String idStr = "\tID: " + String.valueOf(id) + "\n";
		String emailStr = "\tEmail: " + email + "\n";
		String fullnameStr = "\tFull name: " + fullName + "\n";
		String genderStr = "\tGender: " + gender + "\n";
		String stsStr = "\tStatus: " + (enabled ? "enabled" : "disabled") + "\n";

		return idStr + emailStr + fullnameStr + genderStr + stsStr;
	}
}

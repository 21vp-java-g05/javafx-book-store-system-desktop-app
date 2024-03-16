package main.backend.orders;

public class Customer {
	private String id, mail, fullname, phone;
	private boolean male, enabled;

	public Customer(String id, String mail, String fullname, String phone, boolean male, boolean enabled) {
		this.id = id;
		this.mail = mail;
		this.fullname = fullname;
		this.phone = phone;
		this.male = male;
		this.enabled = enabled;
	}
	public Customer(String id, String mail, String fullname, String phone, boolean male) {
		this(id, mail, fullname, phone, male, true);
	}
	public Customer(Customer other) {
		this(other.id, other.mail, other.fullname, other.phone, other.male, other.enabled);
	}

	public String getId() { return id; }
	public String getMail() { return mail; }
	public String getFullname() { return fullname; }
	public String getPhone() { return phone; }
	public boolean isMale() { return male; }

	public void setId(String id) { this.id = id; }
	public void setMail(String mail) { this.mail = mail; }
	public void setFullname(String fullname) { this.fullname = fullname; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setGender(boolean male) { this.male = male; }
	public void setEnable(boolean enabled) { this.enabled = enabled; }

	@Override
	public String toString() {
		String idStr = "\tID: " + id + "\n";
		String mailStr = "\tMail: " + mail + "\n";
		String fullnameStr = "\tFull name: " + fullname + "\n";
		String phoneStr = "\tPhone: " + phone + "\n";
		String gender = "\tGender: " + (male ? "male" : "female") + "\n";
		String stsStr = "\tStatus: " + (enabled ? "enable" : "disable") + "\n";
		
		return idStr + mailStr + fullnameStr + phoneStr + gender + stsStr;
	}
}

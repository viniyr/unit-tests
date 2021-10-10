package entities;


public class Contact {

	private String firstName;
	private String lastName;
	private String phone;
	
	public Contact() { 
	}

	public Contact(String firstName, String lastName, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void validateFirstName() { 
		if (this.firstName.isBlank())
			throw new RuntimeException("FN cannot be null or empty");
	}
	
	public void validateLastName() { 
		if (this.lastName.isBlank())
			throw new RuntimeException("LN cannot be null or empty");
	}

	public void validatePhoneNumber() { 
		if (this.phone.isBlank()) { 
			throw new RuntimeException("phone cannot be null or empty");
		}
	
		if (!this.phone.startsWith("0")) {
			throw new RuntimeException("should start w/ 0");
			}
		
		if (this.phone.length() != 10) { 
			throw new RuntimeException("should contain 10 digits");
		}
		
		if (!this.phone.matches("\\d+")) { 
			throw new RuntimeException("only digits");
		}
		
		
		}

	
	
	
}

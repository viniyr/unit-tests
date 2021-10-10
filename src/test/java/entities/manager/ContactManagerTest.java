package entities.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContactManagerTest {

	@Test
	public void creationTest() {
		ContactManager cm = new ContactManager();
		cm.addContact("Vini", "Yone", "0990262852");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
		Assertions.assertTrue(cm
				.getAllContacts().stream().filter(contact -> contact.getFirstName().equals("Vini")
						&& contact.getLastName().equals("Yone") && contact.getPhone().equals("0990262852"))
				.findAny().isPresent());

	}

	@Test
	@DisplayName("null first name error")
	public void errorIfFirstNameIsNull() { 
		ContactManager cm = new ContactManager();
		Assertions.assertThrows(RuntimeException.class, () -> {
			cm.addContact(null, "yone", "0990262852");
		});
	}
	
	@Test
	@DisplayName("null last name error")
	public void errorIfLastNameIsNull() { 
		ContactManager cm = new ContactManager();
		Assertions.assertThrows(RuntimeException.class, () -> {
			cm.addContact("vini", null, "0990262852");
		});
	}
	
	@Test
	@DisplayName("null phone number")
	public void errorIfPhoneIsNull() {
		ContactManager cm = new ContactManager();
		Assertions.assertThrows(RuntimeException.class, () -> {
			cm.addContact("vini", "yone", null);
		});

	}
}

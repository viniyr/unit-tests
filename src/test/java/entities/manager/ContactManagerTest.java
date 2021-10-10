package entities.manager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


@TestInstance(Lifecycle.PER_CLASS) // connections, large files
class ContactManagerTest {

	ContactManager cm;
	@BeforeAll
	public void testBf() { 
		System.out.println("b4 all tests");
	}
	
	@BeforeEach
	public void setup() { 
		cm = new ContactManager();
	}
	
	@Test
	public void creationTest() {
		cm.addContact("Vini", "Yone", "0990262852");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
		Assertions.assertTrue(cm
				.getAllContacts().stream().filter(contact -> contact.getFirstName().equals("Vini")
						&& contact.getLastName().equals("Yone") && contact.getPhone().equals("0990262852"))
				.findAny()
				.isPresent());

	}

	@Test
	@DisplayName("null first name error")
	public void errorIfFirstNameIsNull() { 
		Assertions.assertThrows(RuntimeException.class, () -> {
			cm.addContact(null, "yone", "0990262852");
		});
	}
	
	@Test
	@DisplayName("null last name error")
	public void errorIfLastNameIsNull() { 
		Assertions.assertThrows(RuntimeException.class, () -> {
			cm.addContact("vini", null, "0990262852");
		});
	}
	
	@Test
	@DisplayName("null phone number")
	public void errorIfPhoneIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			cm.addContact("vini", "yone", null);
		});
	}
	
	@AfterEach
	public void afterEach() { 
		System.out.println("after each test");
	}
	
	@AfterAll
	public void afterAll() { 
		System.out.println("exec after all tests");
	}
	
	@Test
	@DisplayName("should only create on macos")
	@EnabledOnOs(value = OS.MAC, disabledReason = "Only on mac OS")
	public void createOnlyOnMac() { 
		cm.addContact("vini", "yone", "0990262852");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
		Assertions.assertTrue(cm
				.getAllContacts().stream()
				.filter(contact -> contact.getFirstName().equals("Vini")
						&& contact.getLastName().equals("Yone") 
						&& contact.getPhone().equals("0990262852"))
				.findAny()
				.isPresent());
	}
	
	@Test
	@DisplayName("test with its on develepor machine")
	public void dev() { 
		Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
		cm.addContact("Vini", "Yone", "0990262852");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
	}
	
}

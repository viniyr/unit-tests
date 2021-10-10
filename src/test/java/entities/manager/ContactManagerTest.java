package entities.manager;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactManagerTest {

	@Test
	public void test() {
		ContactManager cm = new ContactManager();
		cm.addContact("Vini", "Yone", "0990262852");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
	}

}

package entities.manager;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import entities.Contact;

public class ContactManager {

	Map<String, Contact> contactList = new ConcurrentHashMap<String, Contact>();
	
	
	
	public void addContact(String firstName, String lastName, String phone) { 
		Contact contact = new Contact(firstName, lastName, phone);
		validateContact(contact);
		checkIfContactExist(contact);
		contactList.put(generateKey(contact), contact);
	}

	public void checkIfContactExist(Contact contact) {
		if(contactList.containsKey(generateKey(contact)));
	}

	private String generateKey(Contact contact) {
		return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
	}

	public Collection<Contact> getAllContacts() { 
		return contactList.values();
	}

	public void validateContact(Contact contact) {
		contact.validateFirstName();
		contact.validateLastName();
		contact.validatePhoneNumber();
	}


	
	
}

package entities.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
				.findAny().isPresent());

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
	@DisplayName("null phone number error")
	public void errorIfPhoneIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			cm.addContact("vini", "yone", null);
		});
	}

	@Test
	@DisplayName("should only create on macos")
	@EnabledOnOs(value = OS.MAC, disabledReason = "Only on mac OS")
	public void createOnlyOnMac() {
		cm.addContact("vini", "yone", "0990262852");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
		Assertions.assertTrue(cm
				.getAllContacts().stream().filter(contact -> contact.getFirstName().equals("Vini")
						&& contact.getLastName().equals("Yone") && contact.getPhone().equals("0990262852"))
				.findAny().isPresent());
	}

	@Test
	@DisplayName("test with its on develepor machine")
	public void dev() {
		Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
		cm.addContact("Vini", "Yone", "0990262852");
		Assertions.assertFalse(cm.getAllContacts().isEmpty());
		Assertions.assertEquals(1, cm.getAllContacts().size());
	}

	@Nested
	class RepeatedTests {
		@DisplayName("repeated test")
		@RepeatedTest(value = 4, name = "r: {currentRepetition} of {totalRepetitions}")
		public void repeated() {
			cm.addContact("Vini", "Yone", "0990262852");
			Assertions.assertFalse(cm.getAllContacts().isEmpty());
			Assertions.assertEquals(1, cm.getAllContacts().size());
		}
	}

	@Nested
	class ParametrizedTests {
		@DisplayName("valuesource parameterized")
		@ParameterizedTest
		@ValueSource(strings = { "0990262852", "0990262852", "0990262852" })
		public void valueSourceTest(String phone) {
			cm.addContact("Vini", "Yone", phone);
			Assertions.assertFalse(cm.getAllContacts().isEmpty());
			Assertions.assertEquals(1, cm.getAllContacts().size());
		}

		@DisplayName("csv - phone should match format")
		@ParameterizedTest
		@CsvSource({ "0990262852", "0990262852", "0990262852" })
		public void shouldTestPhoneNumberFormatUsingCSVSource(String phoneNumber) {
			cm.addContact("John", "Doe", phoneNumber);
			assertFalse(cm.getAllContacts().isEmpty());
			assertEquals(1, cm.getAllContacts().size());
		}

		@DisplayName("csv source - phone required format")
		@ParameterizedTest
		@CsvFileSource(resources = "/data.csv")
		public void csvSourceTest(String phone) {
			cm.addContact("Vini", "Yone", phone);
			Assertions.assertFalse(cm.getAllContacts().isEmpty());
			Assertions.assertEquals(1, cm.getAllContacts().size());
		}
	}
	
		@DisplayName("methodsource parameterized")
		@ParameterizedTest
		@MethodSource("phoneList")
		public void methodSourceTest(String phone) {
			cm.addContact("Vini", "Yone", phone);
			Assertions.assertFalse(cm.getAllContacts().isEmpty());
			Assertions.assertEquals(1, cm.getAllContacts().size());
		}
	
		private static List<String> phoneList() {
			return Arrays.asList("0990262852", "0990262852", "0990262852");
		}
		
		@Test
		@DisplayName("disabled test")
		@Disabled
		public void testDisabled() { 
			throw new RuntimeException("should not be executed");
		}
		
		@AfterEach
		public void afterEach() {
			System.out.println("after each test");
		}

		@AfterAll
		public void afterAll() {
			System.out.println("exec after all tests");
		}

}

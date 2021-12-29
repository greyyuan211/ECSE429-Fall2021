package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.Month;

public class InputData {

	// Pet ids

	final static int petId1 = 1;

	final static int petId2 = 2;

	// LocalDate

	final static LocalDate validDate = LocalDate.of(2021, Month.NOVEMBER, 03);

	final static LocalDate validDate2 = LocalDate.of(2021, Month.OCTOBER, 04);

	final static LocalDate validDate3 = LocalDate.of(2022, Month.JANUARY, 04);

	final static int invalidDay = 33;

	final static int invalidMonth = 23;

	final static int invalidYear = -1;

	// Descriptions

	final static String validDescription = "test description";

	final static String validDescription2 = "a new description";

	// Person first names

	final static String firstName = "Frida";

	final static String firstName2 = "Magdalena";

	// Person last names
	final static String lastName = "Kahlo";

	final static String lastName2 = "Rivera";

	// Owner address

	final static String validAddress = "4999 mcgill college";

	final static String validAddress2 = "5000 mcgill college";

	// Owner city

	final static String validCity = "Montreal";

	final static String validCity2 = "Toronto";

	// Owner telephone

	final static String validTelephone = "5148889999";

	final static String validTelephone2 = "5148890000";

	// Specialty

	final static String specialty = "surgeon";

	final static String specialty2 = "dentist";

	final static String specialty3 = "groomer";

	// Pet Types

	final static String petType = "dog";

	final static String petType2 = "cat";

}

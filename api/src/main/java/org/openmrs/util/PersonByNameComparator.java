package org.openmrs.util;

import java.util.Comparator;

import org.openmrs.Person;
import org.openmrs.PersonName;

/**
 * A simple person comparator for sorting persons by name. Sorts names based on the following
 * precedence: FamilyName, FamilyName2, GivenName, MiddleName, FamilyNamePrefix, FamilyNameSuffix
 */
public class PersonByNameComparator implements Comparator<Person> {
	
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Person person1, Person person2) {
		
		// test for null cases (sorting them to be last in a list)
		if(person1 == null || person1.getPersonName() == null) {
			return 1;
		} else if (person2 == null || person2.getPersonName() == null) {
			return -1;
		}
		
		// if neither are null, do the actual comparison
		PersonName name1 = person1.getPersonName();
		PersonName name2 = person2.getPersonName();
		
		int ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyName(), name2.getFamilyName());
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyName2(), name2.getFamilyName2());
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getGivenName(), name2.getGivenName());
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getMiddleName(), name2.getMiddleName());
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyNamePrefix(), name2.getFamilyNamePrefix());
		}
		
		if (ret == 0) {
			ret = OpenmrsUtil.compareWithNullAsGreatest(name1.getFamilyNameSuffix(), name2.getFamilyNameSuffix());
		}
		
		return ret;
	}
}

package com.assessments.availity.csv_formatting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InsuranceGroupTests {
	
	InsuranceGroup allstate = new InsuranceGroup("allstate");
	
	@Test
	public void duplicateUserIdTakesHighestVersion() {
		allstate.enroll(mockEnrolleeWithVersion(1));
		allstate.enroll(mockEnrolleeWithVersion(9));
		allstate.enroll(mockEnrolleeWithVersion(5));

		assertEquals(1, allstate.getEnrolled().size());
		assertEquals(9, allstate.getEnrolled().get(0).getVersion().intValue());
	}
	
	private Enrollee mockEnrolleeWithVersion(Integer version) {
		return mockEnrolleeWithVersionAndName(version, "");
	}
	
	@Test
	public void enrolleesAreSortedByFullName() {
		allstate.enroll(mockEnrolleeWithVersionAndName(1, "Zeta"));
		allstate.enroll(mockEnrolleeWithVersionAndName(1, "Beta"));
		allstate.enroll(mockEnrolleeWithVersionAndName(1, "Alpha"));
		
		assertEquals(3, allstate.getEnrolled().size());
		assertEquals("Alpha", allstate.getEnrolled().get(0).getFullName());
		assertEquals("Zeta", allstate.getEnrolled().get(2).getFullName());
	}
	
	private Enrollee mockEnrolleeWithVersionAndName(Integer version, String name) {
		Enrollee e = new Enrollee();
		e.setUserId(name);
		e.setFullName(name);
		e.setVersion(version);
		
		return e;
	}
	
}

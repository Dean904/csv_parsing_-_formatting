package com.assessments.availity.csv_formatting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsuranceGroup {

	public final String companyName;
	Map<String, Enrollee> idToEnrolleeMap = new HashMap<>();

	public InsuranceGroup(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * If there are duplicate User Ids for the same Insurance Company, then only the
	 * record with the highest version should be included.
	 * 
	 */
	public void enroll(Enrollee e) {
		if (idToEnrolleeMap.containsKey(e.getUserId())) {
			Enrollee existingRecord = idToEnrolleeMap.get(e.getUserId());
			if (existingRecord.getVersion() < e.getVersion()) {
				idToEnrolleeMap.put(e.getUserId(), e);
			}
		} else {
			idToEnrolleeMap.put(e.getUserId(), e);
		}
	}

	/**
	 * Sort the contents of each file by last and first name (ascending).
	 * 
	 */
	public List<Enrollee> getEnrolled() {
		// Create new list and sort so the map is not exposed
		List<Enrollee> enrollees = new ArrayList<>(idToEnrolleeMap.values());
		Collections.sort(enrollees, new Comparator<Enrollee>() {
			@Override
			public int compare(Enrollee e1, Enrollee e2) {
				return e1.getFullName().compareTo(e2.getFullName());
			}

		});

		return enrollees;
	}

}

package com.assessments.availity.csv_formatting.marshallers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.assessments.availity.csv_formatting.Enrollee;

public class CsvUnmarshaller {

	private final File csv;
	private List<Enrollee> enrollees = null;

	public CsvUnmarshaller(File csvFile) {
		this.csv = csvFile;
	}

	public List<Enrollee> getEnrollees() {
		if (null == enrollees) {
			enrollees = scrapeEnrollees();
		}

		return enrollees;
	}

	private List<Enrollee> scrapeEnrollees() {
		List<Enrollee> out = new ArrayList<>();

		try (Scanner sc = new Scanner(csv)) {
			while (sc.hasNextLine()) {
				String[] userData = sc.nextLine().split(",");
				out.add(makeEnrolleeFromUserData(userData));
			}
			
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		
		return out;
	}

	private Enrollee makeEnrolleeFromUserData(String[] userData) {
		Enrollee e = new Enrollee();
		e.setUserId(userData[0]);
		e.setFullName(userData[1]);
		e.setVersion(Integer.parseInt(userData[2]));
		e.setInsuranceCompany(userData[3]);

		System.out.println("Created " + e);
		return e;
	}

}

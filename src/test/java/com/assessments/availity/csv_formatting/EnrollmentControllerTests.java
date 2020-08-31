package com.assessments.availity.csv_formatting;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.assessments.availity.csv_formatting.marshallers.CsvUnmarshaller;

public class EnrollmentControllerTests {
	
	private CsvUnmarshaller unmarshaller;
	
	@Before
	public void beforeClass() throws URISyntaxException {
		URL res = getClass().getClassLoader().getResource("resources/example.csv");
		File csvFile = Paths.get(res.toURI()).toFile();
		unmarshaller = new CsvUnmarshaller(csvFile);
	}
	
	@Test
	public void insuranceGroupsAreReturnedForEachUniqueCompany() {
		Collection<InsuranceGroup> companies = EnrollmentController.organizeEnrollees(unmarshaller.getEnrollees());
		
		assertEquals(5, companies.size());
	}
	
	@Test
	public void exportingToCsvCreates5Files() throws IOException {
		Collection<InsuranceGroup> companies = EnrollmentController.organizeEnrollees(unmarshaller.getEnrollees());
		assertEquals(5, companies.size());

		List<File> csvFiles = EnrollmentController.exportInsuranceGroupsToCsvFiles(companies);
		assertEquals(5, csvFiles.size());
	}
	
}

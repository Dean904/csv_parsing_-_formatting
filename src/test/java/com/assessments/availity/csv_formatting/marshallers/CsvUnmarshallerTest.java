package com.assessments.availity.csv_formatting.marshallers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import com.assessments.availity.csv_formatting.Enrollee;
import com.assessments.availity.csv_formatting.marshallers.CsvUnmarshaller;

public class CsvUnmarshallerTest {
	
	private File csvFile;
	
	@Before
	public void before() throws URISyntaxException {
		URL res = getClass().getClassLoader().getResource("resources/example.csv");
		csvFile = Paths.get(res.toURI()).toFile();
	}

	
	@Test
	public void csvFormatterReturnsEnrollees() throws URISyntaxException {
		CsvUnmarshaller unmarshaller = new CsvUnmarshaller(csvFile);
		List<Enrollee> enrollees = unmarshaller.getEnrollees();
		
		assertTrue(CollectionUtils.isNotEmpty(enrollees));
	}
	
	@Test
	public void testEnrolleesAreCreatedProperly() throws URISyntaxException {		
		CsvUnmarshaller unmarshaller = new CsvUnmarshaller(csvFile);
		Enrollee e = unmarshaller.getEnrollees().get(0);
		
		assertEquals("34v2v2", e.getUserId());
		assertEquals("Gardner Minshew", e.getFullName());
		assertEquals(1, e.getVersion().intValue());
		assertEquals("Progressive", e.getInsuranceCompany());
	}
	
}

package com.assessments.availity.csv_formatting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

public class EnrollmentController {

	public static Collection<InsuranceGroup> organizeEnrollees(List<Enrollee> enrollees) {
		Map<String, InsuranceGroup> insuranceCompanyMap = new HashMap<>();

		for (Enrollee e : enrollees) {
			String key = e.getInsuranceCompany();
			if (!insuranceCompanyMap.containsKey(key)) {
				insuranceCompanyMap.put(key, new InsuranceGroup(key));
			}

			insuranceCompanyMap.get(key).enroll(e);
		}

		return insuranceCompanyMap.values();
	}

	public static List<File> exportInsuranceGroupsToCsvFiles(Collection<InsuranceGroup> companies) throws IOException {
		List<File> files = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(companies)) {
			for (InsuranceGroup i : companies) {
				File file = new File("src/main/resources/" + i.companyName + ".csv");
				
				try (FileWriter writer = new FileWriter(file);) {
					for (Enrollee e : i.getEnrolled()) {
						writer.append(buildEnrolleeData(e));
					}
				}
				files.add(file);
			}
		}
		return files;
	}

	private static String buildEnrolleeData(Enrollee e) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.getUserId());
		sb.append(",");
		sb.append(e.getFullName());
		sb.append(",");
		sb.append(e.getVersion());
		sb.append(",");
		sb.append(e.getInsuranceCompany());
		sb.append(System.lineSeparator());

		return sb.toString();
	}

}

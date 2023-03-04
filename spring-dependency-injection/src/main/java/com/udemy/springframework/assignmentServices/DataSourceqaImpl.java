package com.udemy.springframework.assignmentServices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service
public class DataSourceqaImpl implements DataSource {

	@Override
	public String datasource() {
		return "Data Source for Quality assurance";
	}

}

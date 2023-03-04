package com.udemy.springframework.assignmentServices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class DataSourceDevImpl implements DataSource {

	@Override
	public String datasource() {
		return "Datasource for development";
	}
	
}

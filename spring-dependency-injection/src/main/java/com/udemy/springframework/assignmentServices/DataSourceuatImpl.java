package com.udemy.springframework.assignmentServices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("uat")
@Service
public class DataSourceuatImpl implements DataSource{

	@Override
	public String datasource() {
		return "Datasource for User Acceptance Testing";
	}
	
}

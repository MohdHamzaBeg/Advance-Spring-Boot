package com.udemy.springframework.assignmentServices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class DataSourceprodImpl implements DataSource{

	@Override
	public String datasource() {
		return "Datasource for Production";
	}

}

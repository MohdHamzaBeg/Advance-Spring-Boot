package com.udemy.springframework.controllerss;

import org.springframework.stereotype.Controller;

import com.udemy.springframework.assignmentServices.DataSource;

@Controller
public class fauxController {
	private final DataSource dataSource;

	public fauxController(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public String getDatasource() {
		return dataSource.datasource();
	}
	
}

package com.udemy.springframework.models;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerCSV {
	
	@CsvBindByName //This annotation maps the column with the same name column of the csv file if now external name is provided
	private Integer row;
	@CsvBindByName(column = "count.x")// Here, actual column name for count in csv file is different from this variable name
	private Integer count;
	@CsvBindByName
	private String abv;
	@CsvBindByName
	private String ibu;
	@CsvBindByName
	private String id;
	@CsvBindByName
	private String beer;
	@CsvBindByName
	private String style;
	@CsvBindByName(column = "brewery_id")
	private Integer breweryID;
	@CsvBindByName
	private Float ounces;
	@CsvBindByName
	private String style2;
	@CsvBindByName(column = "count.y")
	private String count_y;
	@CsvBindByName
	private String city;
	@CsvBindByName
	private String state;
	@CsvBindByName
	private String label;
}

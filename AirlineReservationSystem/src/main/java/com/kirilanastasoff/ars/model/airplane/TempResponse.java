package com.kirilanastasoff.ars.model.airplane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TempResponse {

	private Long aaId;
	private Long aId;
	private String aaCode;
	private String name;
	private String make;
	private int capacity;
	private String details;

}

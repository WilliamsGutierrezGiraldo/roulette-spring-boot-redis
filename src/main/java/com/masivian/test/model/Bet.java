package com.masivian.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bet {
	
	private Long userId;
	private String rouletteId;
	private Long number;
	private String color;
	private Double value;

}

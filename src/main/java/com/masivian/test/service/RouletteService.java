package com.masivian.test.service;

import java.util.List;

import com.masivian.test.model.Roulette;

public interface RouletteService {
	
	public String save();

	public List<Roulette> getAllRouletes();

	boolean openARoulette(String rouletteId);

}

package com.masivian.test.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.test.model.Roulette;
import com.masivian.test.repository.RouletteDao;
import com.masivian.test.service.RouletteService;

@Service
public class RouletteServiceImpl implements RouletteService {
	
	@Autowired
	RouletteDao dao;

	@Override
	public String save() {
		String result = "";
		UUID uuid = UUID.randomUUID();
		if(dao.save(new Roulette(uuid.toString(), BigInteger.ZERO.longValue()))) {
			result = uuid.toString();
		}
		
		return result;
	}

	@Override
	public List<Roulette> getAllRouletes() {
		return dao.getAllRoulettes();
	}
	
	

}

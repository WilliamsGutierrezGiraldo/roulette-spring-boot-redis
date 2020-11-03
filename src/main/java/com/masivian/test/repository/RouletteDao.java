package com.masivian.test.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.test.model.Roulette;

@Repository
public class RouletteDao {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	private static final String KEY = "ROULETTE";
	
	@SuppressWarnings("unchecked")
	public boolean save(Roulette roulette) {
		try {
			redisTemplate.opsForHash().put(KEY, roulette.getId(), roulette);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Roulette> getAllRoulettes() {
		return redisTemplate.opsForHash().values(KEY);
	}

	@SuppressWarnings("unchecked")
	public Roulette findById(String rouletteId) {
		Roulette roulette = null;
		try {
			roulette = (Roulette) redisTemplate.opsForHash().get(KEY, rouletteId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roulette;
	}

	@SuppressWarnings("unchecked")
	public boolean openAroulette(String rouletteId) {
		Roulette roulette = findById(rouletteId);
		boolean result;
		if(Objects.nonNull(roulette)) {
			roulette.setStatus(BigInteger.ONE.longValue());
			redisTemplate.opsForHash().put(KEY, roulette.getId(), roulette);
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}

}

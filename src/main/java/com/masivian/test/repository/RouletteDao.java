package com.masivian.test.repository;

import java.util.List;

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

}

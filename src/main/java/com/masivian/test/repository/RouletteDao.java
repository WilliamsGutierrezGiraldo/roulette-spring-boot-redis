package com.masivian.test.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.test.model.Bet;
import com.masivian.test.model.Roulette;

@Repository
public class RouletteDao {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	private static final String KEY = "ROULETTE";
	
	private static final String BET_KEY = "BET";
	
	
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
	public boolean openRoulette(String rouletteId) {
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

	@SuppressWarnings("unchecked")
	public boolean saveBet(Bet bet) {
		try {
			if (Objects.nonNull(findById(bet.getRouletteId()))) {
				redisTemplate.opsForHash().put(BET_KEY, bet.getRouletteId(), bet);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

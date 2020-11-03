package com.masivian.test.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.masivian.test.model.Bet;
import com.masivian.test.model.Roulette;
import com.masivian.test.repository.RouletteDao;
import com.masivian.test.service.RouletteService;

@Service
public class RouletteServiceImpl implements RouletteService {
	
	@Autowired
	RouletteDao dao;
	
	private static final String RED = "red";
	
	private static final String BLACK = "black";

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

	@Override
	public boolean openRoulette(String rouletteId) {
		return dao.openRoulette(rouletteId);
	}

	@Override
	public boolean saveBet(Bet bet) {
		return dao.saveBet(bet);
	}

	public String validateRouletteAndBets(String rouletteId) {
		String result = "";
		if (Objects.nonNull(dao.findById(rouletteId))) {
			List<Bet> bets = dao.findBetsByRouletteId(rouletteId);
			if (!CollectionUtils.isEmpty(bets)) {
				result = getWinnerAnPrice(bets);
			} else {
				result = "Ops! There is no bets for this roulette...";
			}
		} else {
			result = "Ops! There is no roulette...";
		}
		
		return result;
	}
	
	public String getWinnerAnPrice(List<Bet> bets) {
		StringBuilder sb = new StringBuilder();
		int number = (int) Math.floor(Math.random()*(0-36)+36);
		int colorNumber = (int) Math.floor(Math.random()*(0-1)+1);
		String color = colorNumber == 1 ? RED : BLACK;
		for (Bet bet : bets) {
			if ( bet.getNumber().intValue() == number && bet.getColor() == color  ) {
				double prize = bet.getValue() * 5 + bet.getValue() * 1.8;
				sb.append("The winning user is: ");
				sb.append(bet.getUserId());
				sb.append(" and the prize they will receive is: $US ");
				sb.append(prize);
				sb.append("\n");
				
			}
		}
		
		return StringUtils.isEmpty(sb.toString()) ?  "There are no winners around here" : sb.toString();
	}
	
	
	

}

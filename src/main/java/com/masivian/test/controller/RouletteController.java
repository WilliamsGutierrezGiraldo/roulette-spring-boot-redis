package com.masivian.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.test.model.Bet;
import com.masivian.test.model.Roulette;
import com.masivian.test.service.RouletteService;

@RestController
public class RouletteController {
	
	@Autowired
	private RouletteService rouletteService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<String> createRoulette() {
		ResponseEntity<String> responseEntity;
		String result = rouletteService.save();
		responseEntity = StringUtils.isEmpty(result) ? 
				ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error creating a roulette") : 
				ResponseEntity.ok("Roulette is successfully created, Id:" + result);
		
		return responseEntity;
	}
	
	@PostMapping(path = "/open/{rouletteId}")
	public ResponseEntity<String> openRoulette(@PathVariable(value = "rouletteId") String rouletteId) {
		ResponseEntity<String> responseEntity;
		responseEntity = rouletteService.openRoulette(rouletteId) ? ResponseEntity.ok("Roulette is open") :
				ResponseEntity.status(HttpStatus.NOT_FOUND).body("Roulette does not exists"); 
				
		return responseEntity;
	} 
	
	@PostMapping(path = "/makeBet")
	public ResponseEntity<String> makeBet(HttpServletRequest request, @RequestBody Bet bet) {
		bet.setUserId(Long.parseLong(request.getHeader("userId")));
		ResponseEntity<String> responseEntity;
		responseEntity = rouletteService.saveBet(bet) ? 
				ResponseEntity.ok("Bet received for user: " + request.getHeader("userId")) : 
				ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Roulette does not exists");
		
		return responseEntity;
	}
	
	@PostMapping(path = "/close/{rouletteId}")
	public ResponseEntity<String> closeRouletteById(@PathVariable(value = "rouletteId") String rouletteId) {
		return ResponseEntity.ok(rouletteService.validateRouletteAndBets(rouletteId));
	}
	
	@GetMapping("/roulettes")
	public ResponseEntity<List<Roulette>>  getAllRoulettes() {
		return ResponseEntity.ok(rouletteService.getAllRouletes());
	}

}

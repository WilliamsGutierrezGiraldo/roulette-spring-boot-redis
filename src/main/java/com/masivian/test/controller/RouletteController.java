package com.masivian.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouletteController {
	
	@PostMapping(path = "/create")
	public ResponseEntity<String> createRoulette() {
		return ResponseEntity.ok("Create roulette works");
	}
	
	@PostMapping(path = "/open/{rouletteId}")
	public ResponseEntity<String> openRoulette(@PathVariable(value = "rouletteId") String rouletteId) {
		return ResponseEntity.ok("Roulette open with id: " + rouletteId);
	} 
	
	@PostMapping(path = "/makeABet")
	public ResponseEntity<String> makeABet(HttpServletRequest request) {
		return ResponseEntity.ok("Bet received for user: " + request.getHeader("userId").toString());	
	}
	
	@PostMapping(path = "/close/{rouletteId}")
	public ResponseEntity<String> closeRouletteById(@PathVariable(value = "rouletteId") String rouletteId) {
		return ResponseEntity.ok("Roulette closed, id:" + rouletteId);
	}
	
	@GetMapping("/roulettes")
	public ResponseEntity<String> getAllRoulettes() {
		return ResponseEntity.ok("Getting list of roulettes...");
	}

}

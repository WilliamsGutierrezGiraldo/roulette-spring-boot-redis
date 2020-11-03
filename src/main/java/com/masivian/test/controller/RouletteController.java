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
import org.springframework.web.bind.annotation.RestController;

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
		return ResponseEntity.ok("Roulette open with id: " + rouletteId);
	} 
	
	@PostMapping(path = "/makeBet")
	public ResponseEntity<String> makeBet(HttpServletRequest request) {
		return ResponseEntity.ok("Bet received for user: " + request.getHeader("userId").toString());	
	}
	
	@PostMapping(path = "/close/{rouletteId}")
	public ResponseEntity<String> closeRouletteById(@PathVariable(value = "rouletteId") String rouletteId) {
		return ResponseEntity.ok("Roulette closed, id:" + rouletteId);
	}
	
	@GetMapping("/roulettes")
	public ResponseEntity<List<Roulette>>  getAllRoulettes() {
		return ResponseEntity.ok(rouletteService.getAllRouletes());
	}

}

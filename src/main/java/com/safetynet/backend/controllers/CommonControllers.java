/**
 * 
 */
package com.safetynet.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.backend.services.CommonService;

/**
 * @author tonys
 *
 */
@RestController
public class CommonControllers {
	
	private CommonService commonService;
	
	public CommonControllers (CommonService commonService) {
		this.commonService = commonService;
	}

	@GetMapping("communityEmail")
	public ResponseEntity<List<String>> getAllEmailOfCity(@RequestParam String city){
		return ResponseEntity.ok(commonService.getAllEmailsInCity(city));
	}
}

/**
 * 
 */
package com.safetynet.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.backend.dto.ChildInfoDto;
import com.safetynet.backend.dto.FloodStationsDto;
import com.safetynet.backend.dto.PersonInfoDto;
import com.safetynet.backend.dto.PersonInfoWithFireStationDto;
import com.safetynet.backend.dto.PersonsInfoWithChildAndAdultCount;
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
	
	@GetMapping("firestation")
	public ResponseEntity<PersonsInfoWithChildAndAdultCount> getChildrenByAddress(@RequestParam int stationNumber){
		return ResponseEntity.ok(commonService.getPersonsWithChilAndAdultCount(stationNumber));
	}
	
	@GetMapping("childAlert")
	public ResponseEntity<List<ChildInfoDto>> getChildrenByAddress(@RequestParam String address){
		return ResponseEntity.ok(commonService.getChildrenByAdress(address));
	}

	@GetMapping("phoneAlert")
	public ResponseEntity<List<String>> getPhoneByStation(@RequestParam int firestation){
		return ResponseEntity.ok(commonService.getPhoneListWithStationNumber(firestation));
	}
	
	@GetMapping("fire")
	public ResponseEntity<PersonInfoWithFireStationDto> getPersonsByStationAdress(@RequestParam String address){
		return ResponseEntity.ok(commonService.getPersonInfoByAdress(address));
	}

	@GetMapping("/flood/stations")
	public ResponseEntity<List<FloodStationsDto>> getFloodStations(@RequestParam List<String> stations){
		return ResponseEntity.ok(commonService.getFloodStations(stations));
	}
	
	
	@GetMapping("personInfo")
	public ResponseEntity<List<PersonInfoDto>> getPersonInfo(
			@RequestParam(defaultValue = "") String firstName,
			@RequestParam(defaultValue = "") String lastName){
		return ResponseEntity.ok(commonService.getPersonInfoList(firstName, lastName));
	}
	
	@GetMapping("communityEmail")
	public ResponseEntity<List<String>> getAllEmailOfCity(@RequestParam(defaultValue = "") String city){
		return ResponseEntity.ok(commonService.getAllEmailsInCity(city));
	}
}

/**
 * 
 */
package com.safetynet.backend.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger LOG = LogManager.getLogger();
	
	private CommonService commonService;
	
	public CommonControllers (CommonService commonService) {
		this.commonService = commonService;
	}
	
	@GetMapping("firestation")
	public ResponseEntity<PersonsInfoWithChildAndAdultCount> getPersonByStation(@RequestParam int stationNumber){
		LOG.debug("[ GET /firestation?stationNumber={} ]",stationNumber);
		final PersonsInfoWithChildAndAdultCount result = commonService.getPersonsWithChilAndAdultCount(stationNumber);
		LOG.info("{}",result);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("childAlert")
	public ResponseEntity<List<ChildInfoDto>> getChildrenByAddress(@RequestParam String address){
		LOG.debug("[ GET /childAlert?address={} ]",address);
		final List<ChildInfoDto> result = commonService.getChildrenByAdress(address);
		LOG.info("{}",result);
		return ResponseEntity.ok(result);
	}

	@GetMapping("phoneAlert")
	public ResponseEntity<List<String>> getPhoneByStation(@RequestParam int firestation){
		LOG.debug("[ GET /phoneAlert?firestation={} ]",firestation);
		final List<String> result = commonService.getPhoneListWithStationNumber(firestation);
		LOG.info("{}",result);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("fire")
	public ResponseEntity<PersonInfoWithFireStationDto> getPersonsByStationAdress(@RequestParam String address){
		LOG.debug("[ GET /fire?address={} ]",address);
		final PersonInfoWithFireStationDto result = commonService.getPersonInfoByAdress(address);
		LOG.info("{}",result);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/flood/stations")
	public ResponseEntity<List<FloodStationsDto>> getFloodStations(@RequestParam List<String> stations){
		LOG.debug("[ GET /flood/stations?stations={} ]",stations);
		final List<FloodStationsDto> result = commonService.getFloodStations(stations);
		LOG.info("{}",result);
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping("personInfo")
	public ResponseEntity<List<PersonInfoDto>> getPersonInfo(
			@RequestParam(defaultValue = "") String firstName,
			@RequestParam(defaultValue = "") String lastName){
		LOG.debug("[ GET /personInfo?firstName={}&lastName={} ]",firstName,lastName);
		final List<PersonInfoDto> result = commonService.getPersonInfoList(firstName, lastName);
		LOG.info("{}",result);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("communityEmail")
	public ResponseEntity<List<String>> getAllEmailOfCity(@RequestParam(defaultValue = "") String city){
		LOG.debug("[ GET /communityEmail?city={} ]",city);
		final List<String> result = commonService.getAllEmailsInCity(city);
		LOG.info("{}",result);
		return ResponseEntity.ok(result);
	}
}

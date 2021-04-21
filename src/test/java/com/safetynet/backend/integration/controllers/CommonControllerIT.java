/**
 * 
 */
package com.safetynet.backend.integration.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.backend.controllers.CommonControllers;
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
@WebMvcTest(controllers = { CommonControllers.class })
class CommonControllerIT {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private CommonService commonService;

	/**
	 * Test method for
	 * {@link com.safetynet.backend.controllers.CommonControllers#getAllEmailOfCity(java.lang.String)}.
	 */
	@Test
	void testGetAllEmailOfCity() throws Exception {
		// arrange
		final String city = "culver";
		final String email = "email@domain.com";
		final List<String> emailList = new ArrayList<>();
		emailList.add(email);

		when(commonService.getAllEmailsInCity(city)).thenReturn(emailList);
		// act
		mockMvc.perform(get("/communityEmail").queryParam("city", city)).andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$[0]", is(email)));
	}

	@Test
	void testGetPersonInfo() throws Exception {
		// arrange
		final String firstName = "Dany";
		final String lastName = "Simons";
		final List<PersonInfoDto> personList = new ArrayList<>();
		final PersonInfoDto person = new PersonInfoDto();
		person.setFirstName(firstName);
		personList.add(person);

		when(commonService.getPersonInfoList(firstName, lastName)).thenReturn(personList);
		// act
		mockMvc.perform(get("/personInfo").queryParam("firstName", firstName).queryParam("lastName", lastName))
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0].firstName", is(person.getFirstName())));
	}

	@Test
	void testGetPhoneByStation() throws Exception {
		// arrange
		final String phone = "237-69878-098";
		final int stationNumber = 2;
		final List<String> phoneList = new ArrayList<>();
		phoneList.add(phone);

		when(commonService.getPhoneListWithStationNumber(stationNumber)).thenReturn(phoneList);
		// act
		mockMvc.perform(get("/phoneAlert").queryParam("firestation", String.valueOf(stationNumber)))
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0]", is(phone)));
	}

	@Test
	void testGetPersonByStation() throws Exception {
		// arrange
		final PersonsInfoWithChildAndAdultCount personsInfoWithChildAndAdultCount = new PersonsInfoWithChildAndAdultCount();
		personsInfoWithChildAndAdultCount.setChildren(0);
		int stationNumber = 6;
		when(commonService.getPersonsWithChilAndAdultCount(stationNumber))
				.thenReturn(personsInfoWithChildAndAdultCount);

		// act
		mockMvc.perform(get("/firestation").queryParam("stationNumber", String.valueOf(stationNumber)))
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.children", is(0)));
	}

	@Test
	void testGetChildrenByAdress() throws Exception {
		// arrange
		final String address = "adress";
		final ChildInfoDto childDto = new ChildInfoDto();
		childDto.setAge(6);
		final List<ChildInfoDto> list = new ArrayList<>();
		list.add(childDto);
		when(commonService.getChildrenByAdress(address)).thenReturn(list);

		// act
		mockMvc.perform(get("/childAlert").queryParam("address", String.valueOf(address)))
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0].age", is(6)));
	}

	@Test
	void testGetPersonsByStationAdress() throws Exception {
		// arrange
		final String address = "address";
		final PersonInfoWithFireStationDto personInfo = new PersonInfoWithFireStationDto();
		personInfo.setFireStationNumber(2);
		when(commonService.getPersonInfoByAdress(address)).thenReturn(personInfo);

		// act
		mockMvc.perform(get("/fire").queryParam("address", String.valueOf(address)))
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.fireStationNumber", is(2)));
	}

	@Test
	void testGetFloodStation() throws Exception {
		// arrange
		final FloodStationsDto floodDto = new FloodStationsDto();
		floodDto.setStationNumber(1);
		final List<FloodStationsDto> list = new ArrayList<>();
		list.add(floodDto);
		List<String> stations = new ArrayList<>();
		stations.add("1");
		when(commonService.getFloodStations(stations)).thenReturn(list);

		// act
		mockMvc.perform(get("/flood/stations").queryParam("stations", "1"))
				.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0].stationNumber", is(1)));
	}
}

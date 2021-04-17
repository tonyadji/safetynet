/**
 * 
 */
package com.safetynet.backend.mapper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author tonys
 *
 */
class PersonMapperTest {

	private PersonMapper personMapper = new PersonMapper();
	
	@Test
	void given_NullBirthDate_whenGettingAgeBasedOnBirthDate_ThenTrowException() {
		//arrange
		
		//act
		final Exception ex = assertThrows(IllegalArgumentException.class, 
				()-> personMapper.getAgeFromBirthDate(null));
		
		//assert
		assertThat(ex.getMessage()).isEqualTo("Cannot determine age based on [null] date");
	}
	
	@Test
	void given_WrongBirthDateFormat_whenGettingAgeBasedOnBirthDate_ThenTrowException() {
		//arrange
		
		//act
		final Exception ex = assertThrows(IllegalArgumentException.class, 
				()-> personMapper.getAgeFromBirthDate("0101/2000"));
		
		//assert
		assertThat(ex.getMessage()).isEqualTo("Date format should be dd/MM/yyyy");
	}
	
	@Test
	void given_WrongYearInBirthDate_whenGettingAgeBasedOnBirthDate_ThenTrowException() {
		//arrange
		
		//act
		final Exception ex = assertThrows(Exception.class, 
				()-> personMapper.getAgeFromBirthDate("01/01/year"));
		
		//assert
		assertThat(ex.getClass()).isEqualTo(NumberFormatException.class);
	}
	
	@Test
	void given_BirthDate_whenGettingAgeBasedOnBirthDate_ThenReturnAge() {
		//arrange
		
		//act
		final int age = personMapper.getAgeFromBirthDate("01/01/1990");
		
		//assert
		assertThat(age).isEqualTo(31);
	}
}

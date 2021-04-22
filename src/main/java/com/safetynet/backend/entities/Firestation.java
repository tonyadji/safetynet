/**
 * 
 */
package com.safetynet.backend.entities;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Getter @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
public class Firestation {

	@NotEmpty
	private String address;
	private int station;
}

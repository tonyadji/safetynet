/**
 * 
 */
package com.safetynet.backend.entities;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tonys
 *
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class Firestation {

	@NotEmpty
	private String address;
	private int station;
}

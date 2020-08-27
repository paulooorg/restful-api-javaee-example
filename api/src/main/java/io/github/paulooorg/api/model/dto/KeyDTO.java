package io.github.paulooorg.api.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class KeyDTO {
	@NotNull
	@NotBlank
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}

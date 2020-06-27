package io.github.paulooorg.api.util;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class CnpjValidator implements DocumentValidator {
	@Override
	public boolean isValid(String document) {
		try {
			new CNPJValidator().assertValid(document);
			return true;
		} catch (InvalidStateException e) {
			return false;
		}
	}
}

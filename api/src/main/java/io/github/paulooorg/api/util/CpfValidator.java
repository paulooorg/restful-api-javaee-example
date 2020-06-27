package io.github.paulooorg.api.util;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class CpfValidator implements DocumentValidator {
	@Override
	public boolean isValid(String document) {
		try {
			new CPFValidator().assertValid(document);
			return true;
		} catch (InvalidStateException e) {
			return false;
		}
	}
}

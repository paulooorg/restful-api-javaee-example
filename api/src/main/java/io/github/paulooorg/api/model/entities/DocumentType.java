package io.github.paulooorg.api.model.entities;

import io.github.paulooorg.api.util.DocumentValidator;
import io.github.paulooorg.api.util.CpfValidator;
import io.github.paulooorg.api.util.CnpjValidator;

public enum DocumentType {
    CPF(new CpfValidator()), CNPJ(new CnpjValidator());
	
	private DocumentValidator validator;

	private DocumentType(DocumentValidator validator) {
		this.validator = validator;
	}

	public DocumentValidator getValidator() {
		return validator;
	}
}

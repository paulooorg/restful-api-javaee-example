package io.github.paulooorg.api.infrastructure.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String i18n;
	
	private Object[] params;
	
	public BusinessException(String i18n, Object[] params) {
		this.i18n = i18n;
		this.params = params;
	}

	public String getI18n() {
		return i18n;
	}

	public Object[] getParams() {
		return params;
	}
}

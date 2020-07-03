package io.github.paulooorg.api.infrastructure.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String i18n;
	
	private Object[] params;
	
	private String errorCode = ErrorCodes.GENERIC_ERROR;
	
	public BusinessException(String i18n, Object[] params) {
		this.i18n = i18n;
		this.params = params;
	}

	public BusinessException(String i18n, Object[] params, String errorCode) {
		this.i18n = i18n;
		this.params = params;
		this.errorCode = errorCode;
	}
	
	public String getI18n() {
		return i18n;
	}

	public Object[] getParams() {
		return params;
	}

	public String getErrorCode() {
		return errorCode;
	}
}

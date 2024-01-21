package com.pcalouche.springelixir.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class JsonExceptionResponse {

	private long timestamp;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String path;

	private int status;

	private String error;

	private String exception;

	private String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, String> validationMessages = null;

}

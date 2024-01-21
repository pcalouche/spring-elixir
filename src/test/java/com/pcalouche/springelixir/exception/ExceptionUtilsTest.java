package com.pcalouche.springelixir.exception;

import static org.assertj.core.api.Assertions.assertThat;

import com.pcalouche.springelixir.controlleradvice.ControllerAdviceProperties;
import com.pcalouche.springelixir.statusendpoint.StatusDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

public class ExceptionUtilsTest {

	@Test
	public void testBuildJsonErrorResponse() throws NoSuchMethodException {
		long currentTimeMillis = System.currentTimeMillis();
		MockHttpServletRequest request = MockMvcRequestBuilders.get("/some-endpoint")
			.buildRequest(new MockServletContext());

		StatusDto statusDto = new StatusDto("", "");
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(statusDto, "statusDto");
		errors.rejectValue("appName", "appName", "Required");

		MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(
				new MethodParameter(getClass().getDeclaredMethod("testBuildJsonErrorResponse"), -1), errors);

		// Test response that will include validation messages
		JsonExceptionResponse jsonExceptionResponse = ExceptionUtils
			.buildJsonErrorResponse(methodArgumentNotValidException, request);
		assertThat(jsonExceptionResponse.getTimestamp()).isGreaterThanOrEqualTo(currentTimeMillis);
		assertThat(jsonExceptionResponse.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
		assertThat(jsonExceptionResponse.getError()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
		assertThat(jsonExceptionResponse.getException())
			.isEqualTo(MethodArgumentNotValidException.class.getSimpleName());
		assertThat(jsonExceptionResponse.getMessage()).isEqualTo("See validation messages for more details.");
		assertThat(jsonExceptionResponse.getValidationMessages().get("appName")).isEqualTo("Required");

		// Test response that will not include validation messages
		jsonExceptionResponse = ExceptionUtils
			.buildJsonErrorResponse(new RestResourceForbiddenException("can touch this"), request);
		assertThat(jsonExceptionResponse.getTimestamp()).isGreaterThanOrEqualTo(currentTimeMillis);
		assertThat(jsonExceptionResponse.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
		assertThat(jsonExceptionResponse.getError()).isEqualTo(HttpStatus.FORBIDDEN.getReasonPhrase());
		assertThat(jsonExceptionResponse.getException())
			.isEqualTo(RestResourceForbiddenException.class.getSimpleName());
		assertThat(jsonExceptionResponse.getValidationMessages()).isNull();
	}

	@Test
	public void testGetHttpStatusForAuthenticationException() {
		HttpStatus httpStatus = ExceptionUtils.getHttpStatusForException(new BadCredentialsException("message"));
		assertThat(httpStatus).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

	@Test
	public void testGetHttpStatusForAccessDeniedException() {
		HttpStatus httpStatus = ExceptionUtils.getHttpStatusForException(new AccessDeniedException("message"));
		assertThat(httpStatus).isEqualTo(HttpStatus.FORBIDDEN);
	}

	@Test
	public void testGetHttpStatusForMethodHttpMessageConversionException() {
		HttpStatus httpStatus = ExceptionUtils.getHttpStatusForException(new HttpMessageConversionException("message"));
		assertThat(httpStatus).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void testGetHttpStatusForMethodArgumentNotValidException() throws NoSuchMethodException {
		MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(
				new MethodParameter(getClass().getDeclaredMethod("testGetHttpStatusForMethodArgumentNotValidException"),
						-1),
				new BindException(new ControllerAdviceProperties(), "controllerAdviceProperties"));
		HttpStatus httpStatus = ExceptionUtils.getHttpStatusForException(methodArgumentNotValidException);
		assertThat(httpStatus).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void testGetHttpStatusForMethodArgumentTypeMismatchException() throws NoSuchMethodException {
		MethodArgumentTypeMismatchException methodArgumentTypeMismatchException = new MethodArgumentTypeMismatchException(
				null, null, "name",
				new MethodParameter(
						getClass().getDeclaredMethod("testGetHttpStatusForMethodArgumentTypeMismatchException"), -1),
				new RuntimeException("bad argument"));
		HttpStatus httpStatus = ExceptionUtils.getHttpStatusForException(methodArgumentTypeMismatchException);
		assertThat(httpStatus).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void testGetHttpStatForMultipartException() {
		MultipartException multipartException = new MultipartException("File size to larger");
		HttpStatus httpStatus = ExceptionUtils.getHttpStatusForException(multipartException);
		assertThat(httpStatus).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void testGetHttpStatusForRestResourceNotFoundException() {
		HttpStatus httpStatus = ExceptionUtils.getHttpStatusForException(new RestResourceNotFoundException("message"));
		assertThat(httpStatus).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	public void testGetHttpStatusForRestResourceForbiddenException() {
		HttpStatus httpStatus = ExceptionUtils.getHttpStatusForException(new RestResourceForbiddenException("message"));
		assertThat(httpStatus).isEqualTo(HttpStatus.FORBIDDEN);
	}

	@Test
	public void testGetHttpStatusForAllOtherExceptions() {
		HttpStatus httpStatus = ExceptionUtils.getHttpStatusForException(new RuntimeException("message"));
		assertThat(httpStatus).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

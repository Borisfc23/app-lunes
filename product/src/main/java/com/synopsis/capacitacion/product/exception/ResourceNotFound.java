package com.synopsis.capacitacion.product.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFound(String mensaje) {
		super(mensaje);
	}
}

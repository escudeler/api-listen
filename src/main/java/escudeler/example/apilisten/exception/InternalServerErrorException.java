package escudeler.example.apilisten.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = -7311444711658270725L;

	private final HttpStatus status;

	public InternalServerErrorException() {
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public HttpStatus getStatus() {
		return status;
	}
}

package ch.hevs.exception;

public class CarException extends RuntimeException{

	public CarException() {
		super();
	}

	public CarException(String arg0) {
		super(arg0);
	}

	public CarException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CarException(Throwable arg0) {
		super(arg0);
	}
}

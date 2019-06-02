package ch.byebyecar.exception;

/* Students : Valentin Bornatici & Montaine Burger
 * Class : 606_3
 * Project : June 2019
 */


public class PlatformException extends RuntimeException {
	
	public PlatformException() {
		super();
	}
	
	public PlatformException(String arg0) {
		super(arg0);
	}
	
	public PlatformException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	public PlatformException(Throwable arg0) {
		super(arg0);
	}
}

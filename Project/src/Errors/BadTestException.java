package Errors;

/**
 * Exception thrown when data is tested in the wrong field.
 */
@SuppressWarnings("serial")
public class BadTestException extends TableException {

	/**
	 * Creates message explaining the error and data that failed.
	 */
	public BadTestException(String data, String type) {
		super("\"" + data + "\" is not a \"" + type + "\".");
	}
}

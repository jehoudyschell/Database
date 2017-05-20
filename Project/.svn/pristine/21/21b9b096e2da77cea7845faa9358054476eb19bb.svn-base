package Errors;

/**
 * Exception thrown when data is incorrectly written to a field.
 */
@SuppressWarnings("serial")
public class IncorrectInputException extends TableException {

	/**
	 * Creates message explaining the error and data that failed.
	 */
	public IncorrectInputException(String data, String field) {
		super("\"" + data + "\" does not belong in field \"" + field + "\".");
	}
}
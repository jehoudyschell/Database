package Errors;

/**
 * Exception thrown when a field is incorrectly instantiated.
 */
@SuppressWarnings("serial")
public class FieldUndefinedException extends TableException {

	/**
	 * Creates message explaining the error and field that failed instantiation.
	 */
	public FieldUndefinedException(String field) {
		super("Field \"" + field
				+ "\" has an unsupported or improper data type.");
	}
}
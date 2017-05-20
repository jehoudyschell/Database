package Errors;

/**
 * Exception thrown when an unavailable name is attempted to be used for a
 * Table.
 */
@SuppressWarnings("serial")
public class NameNotAvailibleException extends TableException {

	/**
	 * Creates message explaining the error.
	 * 
	 * @param type Type of object attempted to be created.
	 * @param name Name of object attempted to be created.
	 */
	public NameNotAvailibleException(String type, String name) {
		super(type + " name \"" + name + "\" is not valid or already used.");
	}
}
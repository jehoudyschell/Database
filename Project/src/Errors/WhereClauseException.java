package Errors;

/**
 * Exception thrown when an uninstantiated table is acted on.
 */
@SuppressWarnings("serial")
public class WhereClauseException extends TableException {

	/**
	 * Creates message explaining the error and uninstantiated table.
	 */
	public WhereClauseException(String where) {
		super("\"" + where + "\" is not a boolean statement."
				+ " If spaces weren't included, please include them.");
	}
}
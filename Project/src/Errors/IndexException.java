package Errors;

@SuppressWarnings("serial")
public class IndexException extends TableException {
	public IndexException(String field) {
		super("The field " + field + " already has a defined index.");
	}
}

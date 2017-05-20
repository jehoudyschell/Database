package Commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.TableException;
import Table.TableCollection;

/**
 * Creates a binary search tree for use with where clauses.
 */
public class DefineIndexCommand implements ICommand {

	private Pattern pattern = Pattern
			.compile(
					"\\s*define\\s+index\\s+on\\s+(\\S+)\\s*\\(\\s*(\\S+)\\s*\\)\\s*;\\s*",
					Pattern.CASE_INSENSITIVE);

	private String tableName;
	private String fieldName;

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			tableName = matcher.group(1);
			fieldName = matcher.group(2);
			return true;
		}
		return false;
	}

	/*
	 * @see Commands.ICommand#execute()
	 */
	@Override
	public void execute() throws IOException, TableException {
		TableCollection.get().defineIndex(tableName, fieldName);
	}
}
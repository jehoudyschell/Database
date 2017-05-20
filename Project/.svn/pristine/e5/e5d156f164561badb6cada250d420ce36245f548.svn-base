package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.TableNotDefinedException;
import Table.TableCollection;

/**
 * Removes a table and all data in the table from the database.
 */
public class DropTableCommand implements ICommand {

	private Pattern pattern = Pattern.compile(
			"\\s*drop\\s+table\\s+(\\S+)\\s*;\\s*", Pattern.CASE_INSENSITIVE);

	private String tableName;

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			tableName = matcher.group(1);
			return true;
		}
		return false;
	}

	/*
	 * @see Commands.ICommand#execute()
	 */
	@Override
	public void execute() throws TableNotDefinedException {
		TableCollection.get().drop(tableName);
	}
}
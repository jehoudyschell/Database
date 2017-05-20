package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.TableException;
import Table.TableCollection;

/**
 * Renames a specified table to a new user specified name.
 */
public class RenameTableCommand implements ICommand {

	private Pattern pattern = Pattern.compile(
			"\\s*rename\\s+table\\s+(\\S+)\\s+to\\s+(\\S+)\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

	private String tableName;
	private String newName;

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			tableName = matcher.group(1);
			newName = matcher.group(2);
			return true;
		}
		return false;
	}

	/*
	 * @see Commands.ICommand#execute()
	 */
	@Override
	public void execute() throws TableException {
		TableCollection.get().rename(tableName, newName);
	}
}
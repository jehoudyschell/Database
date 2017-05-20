package Commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.TableException;
import Table.TableCollection;

/**
 * Deletes either all or specified rows in a table.
 */
public class DeleteCommand implements ICommand {

	private Pattern pattern = Pattern.compile(
			"\\s*delete\\s+(\\S+)(\\s+where\\s+(.*))?\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

	private String tableName;
	private String whereClause = "";

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			tableName = matcher.group(1);
			if (matcher.group(3) != null) {
				whereClause = matcher.group(3);
			}
			return true;
		}
		return false;
	}

	/*
	 * @see Commands.ICommand#execute()
	 */
	@Override
	public void execute() throws TableException, IOException {
		TableCollection.get().deleteTableData(tableName, whereClause);
		whereClause = "";
	}
}
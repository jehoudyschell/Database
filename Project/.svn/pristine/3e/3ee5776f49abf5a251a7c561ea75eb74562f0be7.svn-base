package Commands;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.TableException;
import Table.TableCollection;

/**
 * Inserts a row of field values into the table data.
 */
public class InsertCommand implements ICommand {

	private Pattern pattern = Pattern.compile(
			"\\s*insert\\s*\\(\\s*(.+)\\s*\\)\\s*into\\s+(\\S+)\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

	private String valueList;
	private String tableName;

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			valueList = matcher.group(1);
			if (matcher.groupCount() > 1) {
				tableName = matcher.group(2);
				return true;
			}
		}
		return false;
	}

	/*
	 * @see Commands.ICommand#execute()
	 */
	@Override
	public void execute() throws IOException, ParseException, TableException {
		TableCollection.get().addTableData(valueList.split(","), tableName);
	}
}
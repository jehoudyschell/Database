package Commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.TableException;
import Table.Row;
import Table.TableCollection;

/**
 * Prints data from a specified table to the screen.
 */
public class PrintTableCommand implements ICommand {

	private Pattern pattern = Pattern.compile("\\s*print\\s+(\\S+)\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

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
	public void execute() throws IOException, TableException {
		System.out.println();
		if (tableName.equalsIgnoreCase("dictionary"))
			System.out.println(TableCollection.get());
		else {
			Row[] out = TableCollection.get().getTableData(tableName, "");
			for (Row row : out) {
				System.out.println(row);
			}
			System.out.println();
		}
	}
}

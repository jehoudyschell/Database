package Commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.TableException;
import Table.DataSet;
import Table.Row;
import Table.TableCollection;

/**
 * Displays specified information from a table that matches the condition in the
 * where clause if applicable.
 */
public class SelectCommand implements RecursiveCommand {

	private Pattern pattern = Pattern.compile(
			"\\s*select\\s+(\\S+)(\\s+where\\s+(.+))?\\s*;\\s*",
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
			if (matcher.group(3) != null)
				whereClause = matcher.group(3).trim();
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
		Row[] out = TableCollection.get().getTableData(tableName, whereClause);
		for (Row row : out) {
			if (row.toString() != "")
				System.out.println(row);
		}
		System.out.println();
		whereClause = "";
	}

	@Override
	public DataSet executeRecursive(HashMap<String, DataSet> data)
			throws IOException, TableException {
		DataSet out;
		if (TableCollection.get().contains(tableName))
			out = new DataSet(tableName);
		else
			out = new DataSet(data.get(tableName).getTableName());
		Row[] rows;
		if (!TableCollection.get().contains(tableName))
			rows = data.get(tableName).getData("");
		else
			rows = TableCollection.get().getTableData(tableName, whereClause);
		for (Row row : rows) {
			if (row.toString() != "")
				out.add(row);
		}
		return out;
	}
}
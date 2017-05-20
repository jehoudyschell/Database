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
 * Prints only table data from specified fields.
 */
public class ProjectCommand implements RecursiveCommand {

	private Pattern pattern = Pattern.compile(
			"\\s*project\\s+(.+)\\s+over\\s+(.+)\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

	private String tableName;
	private String fieldList;

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			tableName = matcher.group(1);
			if (matcher.groupCount() > 1) {
				fieldList = matcher.group(2);
				return true;
			}
		}
		return false;
	}

	/*
	 * @see Commands.ICommand#execute()
	 */
	@Override
	public void execute() throws IOException, TableException {
		System.out.println();
		Row[] out = TableCollection.get()
				.projectTableData(tableName, fieldList);
		for (Row row : out) {
			if (row.toString() != "")
				System.out.println(row);
		}
		System.out.println();
	}

	@Override
	public DataSet executeRecursive(HashMap<String, DataSet> data)
			throws IOException, TableException {
		DataSet out;
		if (TableCollection.get().contains(tableName))
			out = new DataSet(tableName, fieldList.split("\\s*,\\s*"));
		else
			out = new DataSet(data.get(tableName).getTableName(),
					fieldList.split("\\s*,\\s*"));
		Row[] rows;
		if (!TableCollection.get().contains(tableName))
			rows = data.get(tableName).projectData(fieldList);
		else
			rows = TableCollection.get().projectTableData(tableName, fieldList);
		for (Row row : rows) {
			if (row.toString() != "")
				out.add(row);
		}
		return out;
	}
}
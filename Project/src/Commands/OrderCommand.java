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
 * Displays table data in either ascending or descending order.
 */
public class OrderCommand implements RecursiveCommand {

	private Pattern pattern = Pattern.compile(
			"\\s*order\\s+(.+)\\s+by\\s+(\\S+)(\\s+descending)?\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

	private String tableName;
	private String fieldName;
	private boolean descend = false;

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			tableName = matcher.group(1);
			if (matcher.groupCount() > 1) {
				fieldName = matcher.group(2);
				if (matcher.group(3) != null)
					descend = true;
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
		Row[] out = TableCollection.get().orderTableData(tableName, fieldName,
				descend);
		for (Row row : out) {
			if (row.toString() != "")
				System.out.println(row);
		}
		System.out.println();
		descend = false;
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
			rows = data.get(tableName).orderData(fieldName, descend);
		else
			rows = TableCollection.get().orderTableData(tableName, fieldName,
					descend);
		for (Row row : rows) {
			if (row.toString() != "")
				out.add(row);
		}
		descend = false;
		return out;
	}
}
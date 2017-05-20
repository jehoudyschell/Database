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
 * Prints only table data contained in both tables.
 */
public class IntersectCommand implements RecursiveCommand {

	private Pattern pattern = Pattern.compile(
			"\\s*intersect\\s+(.+)\\s+and\\s+(.+)\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

	private String table1;
	private String table2;

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			table1 = matcher.group(1);
			if (matcher.groupCount() > 1) {
				table2 = matcher.group(2);
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
		Row[] rows1 = TableCollection.get().getTableData(table1, "");
		Row[] rows2 = TableCollection.get().getTableData(table2, "");
		for (Row row1 : rows1) {
			boolean matches = false;
			for (int i = 0; i < rows2.length && !matches; i++) {
				if (rows2[i].compareTo(row1) == 0) {
					System.out.println(row1);
					matches = true;
				}
			}
		}
		System.out.println();
		table1 = "";
		table2 = "";
	}

	@Override
	public DataSet executeRecursive(HashMap<String, DataSet> data)
			throws IOException, TableException {
		DataSet out;
		if (TableCollection.get().contains(table1))
			out = new DataSet(table1);
		else
			out = new DataSet(data.get(table1).getTableName());
		Row[] rows1;
		Row[] rows2;
		if (!TableCollection.get().contains(table1))
			rows1 = data.get(table1).getData("");
		else
			rows1 = TableCollection.get().getTableData(table1, "");
		if (!TableCollection.get().contains(table2))
			rows2 = data.get(table2).getData("");
		else
			rows2 = TableCollection.get().getTableData(table2, "");
		for (Row row1 : rows1) {
			boolean matches = false;
			for (int i = 0; i < rows2.length && !matches; i++) {
				if (rows2[i].compareTo(row1) == 0) {
					out.add(row1);
					matches = true;
				}
			}
		}
		table1 = "";
		table2 = "";
		return out;
	}
}
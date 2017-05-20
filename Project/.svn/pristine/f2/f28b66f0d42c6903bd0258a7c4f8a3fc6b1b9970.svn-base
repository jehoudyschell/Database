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
 * Prints table data from two tables, each row with each row.
 */
public class JoinCommand implements RecursiveCommand {

	private Pattern pattern = Pattern.compile(
			"\\s*join\\s+(.+)\\s+and\\s+(.+)\\s*;\\s*",
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
		for (Row row1 : rows1)
			for (Row row2 : rows2)
				System.out.println("" + row1 + row2);
		System.out.println();
		table1 = "";
		table2 = "";
	}

	@Override
	public DataSet executeRecursive(HashMap<String, DataSet> data)
			throws IOException, TableException {
		DataSet out;
		String[] fields1;
		String[] fields2;
		Row[] rows1;
		Row[] rows2;
		if (!TableCollection.get().contains(table1)) {
			rows1 = data.get(table1).getData("");
			fields1 = data.get(table1).getFieldNames();
		} else {
			rows1 = TableCollection.get().getTableData(table1, "");
			fields1 = TableCollection.get().getTableFieldNames(table1);
		}
		if (!TableCollection.get().contains(table2)) {
			rows2 = data.get(table2).getData("");
			fields2 = data.get(table1).getFieldNames();
		} else {
			rows2 = TableCollection.get().getTableData(table2, "");
			fields2 = TableCollection.get().getTableFieldNames(table2);
		}
		String[] fieldList = new String[fields1.length + fields2.length];
		for (int i = 0; i < fieldList.length; i++) {
			if (i >= fields1.length)
				fieldList[i] = fields2[i - fields1.length];
			else
				fieldList[i] = fields1[i];
		}
		if (TableCollection.get().contains(table1))
			out = new DataSet(table1, fieldList);
		else
			out = new DataSet(data.get(table1).getTableName(), fieldList);
		for (Row row1 : rows1)
			for (Row row2 : rows2) {
				Row row = new Row();
				row.addData(row1);
				row.addData(row2);
				out.add(row);
			}
		table1 = "";
		table2 = "";
		return out;
	}
}
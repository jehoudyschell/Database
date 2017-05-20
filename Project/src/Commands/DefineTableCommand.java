package Commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Errors.NameNotAvailibleException;
import Errors.TableException;
import Table.Table;
import Table.TableCollection;
import XML.XMLWriter;

/**
 * Creates a new table for data storing and organizing.
 */
public class DefineTableCommand implements ICommand {

	private Pattern pattern = Pattern
			.compile(
					"\\s*define\\s+table\\s+(\\S+)\\s+having\\s+fields\\s*\\(\\s*(.*)\\s*\\)\\s*;\\s*",
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
			fieldList = matcher.group(2);
			return true;
		}
		return false;
	}

	/*
	 * @see Commands.ICommand#execute()
	 */
	@Override
	public void execute() throws TableException, IOException {
		if (tableName.equalsIgnoreCase("dictionary")) {
			System.out.println("\"Dictionary\" is a reserved word.");
			throw new NameNotAvailibleException("Table", "dictionary");
		}
		TableCollection.get().add(tableName, new Table(tableName, fieldList));
		XMLWriter.save();
	}
}
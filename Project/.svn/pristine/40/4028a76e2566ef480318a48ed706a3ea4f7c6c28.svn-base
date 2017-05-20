package Commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.TableException;
import Table.TableCollection;

/**
 * The Class UpdateCommand. Represents and recognizes the UPDATE command.
 * 
 * Updates data in an already existing table that matches specific conditions.
 */
public class UpdateCommand implements ICommand {

	private Pattern pattern = Pattern
			.compile(
					"\\s*update\\s+(\\S+)\\s+set\\s+(\\S+\\s+=\\s+\\S+)\\s*(where\\s(.+))?\\s*;\\s*",
					Pattern.CASE_INSENSITIVE);

	private String tableName;
	private String fieldData;
	private String whereClause = "";

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			tableName = matcher.group(1);
			fieldData = matcher.group(2);
			if (matcher.group(4) != null) {
				whereClause = matcher.group(4);
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
		TableCollection.get()
				.updateTableData(tableName, fieldData, whereClause);
		whereClause = "";
	}
}
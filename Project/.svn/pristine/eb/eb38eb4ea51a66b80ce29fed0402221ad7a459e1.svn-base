package Commands;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.BackupNotFoundException;
import Errors.TableException;
import Table.TableCollection;

/**
 * The Class RestoreFromCommand. Represents and recognizes the RESTORE command.
 * 
 * Restores data from a backup file.
 */
public class RestoreFromCommand implements ICommand {

	private Pattern pattern = Pattern.compile(
			"\\s*restore\\s+from\\s*\'\\s*(\\S+)\\s*\'\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

	private String fileName;

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			fileName = matcher.group(1);
			return true;
		}
		return false;
	}

	/*
	 * @see Commands.ICommand#execute()
	 */
	@Override
	public void execute() throws IOException, ClassNotFoundException,
			TableException, ParseException {
		File file = new File("Backups/" + fileName + ".ser");
		if (!file.exists())
			throw new BackupNotFoundException(fileName);
		TableCollection.get().restore(file);
	}
}
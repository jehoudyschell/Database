package Commands;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Errors.TableException;
import Table.TableCollection;

/**
 * The Class BackupToCommand. Represents and recognizes the BACKUP TO command.
 * 
 * Backup data to a file with a user specified name.
 */
public class BackupToCommand implements ICommand {

	private Pattern pattern = Pattern.compile(
			"\\s*backup\\s+to\\s*\'\\s*([^']+)\\s*\'\\s*;\\s*",
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
	public void execute() throws IOException, TableException {
		if (!new File("Backups").exists())
			new File("Backups").mkdirs();
		File file = new File("Backups/" + fileName + ".ser");
		if (file.exists())
			;
		else
			;
		TableCollection.get().backup(file);
	}
}

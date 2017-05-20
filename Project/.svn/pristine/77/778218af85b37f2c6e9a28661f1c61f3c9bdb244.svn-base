package Commands;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Errors.TableException;

/**
 * Reads in command data from a specified file.
 */
public class ReadCommand implements ICommand {

	private Pattern pattern = Pattern.compile(
			"\\s*read\\s*\'\\s*([^']+)\\s*\'\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

	private String fileName;

	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		if (matcher.matches()) {
			fileName = matcher.group(1);
			return true;
		}
		return false;
	}

	@Override
	public void execute() throws TableException, IOException {
		Scanner in = new Scanner(new File(fileName));
		(new Driver()).readLines(in);
		in.close();
	}
}
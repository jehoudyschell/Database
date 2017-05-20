package Commands;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import XML.XMLWriter;

/**
 * Closes the driver and saves table data.
 */
public class ExitCommand implements ICommand {

	private Pattern pattern = Pattern.compile("\\s*exit\\s*;\\s*",
			Pattern.CASE_INSENSITIVE);

	/*
	 * @see Commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) {
		Matcher matcher = pattern.matcher(input.trim());
		return matcher.matches();
	}

	/*
	 * @see Commands.ICommand#execute()
	 */
	@Override
	public void execute() throws FileNotFoundException {
		System.out.println("Goodbye.");
		XMLWriter.save();
		System.exit(0);
	}
}

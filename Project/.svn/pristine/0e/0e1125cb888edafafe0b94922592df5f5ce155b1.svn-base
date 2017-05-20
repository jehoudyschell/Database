package Commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import Errors.TableException;

/**
 * The ICommand Interface. Binds all commands to a set structure and relates
 * them through inheritance.
 */
public interface ICommand {

	/**
	 * Checks if the command input by the user is the command being checked
	 * against.
	 * 
	 * @param input
	 *            the command input by the user
	 * @return true, if input matches the pattern
	 */
	boolean matches(String input);

	/**
	 * Executes the command.
	 * 
	 * @throws TableException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 */
	void execute() throws TableException, FileNotFoundException, IOException,
			ParseException, ClassNotFoundException;
}
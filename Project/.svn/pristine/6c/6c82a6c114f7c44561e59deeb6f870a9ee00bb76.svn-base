package Commands;

import java.io.IOException;
import java.util.HashMap;

import Errors.TableException;
import Table.DataSet;

/**
 * Binds all commands that can be used recursively.
 */
public interface RecursiveCommand extends ICommand {

	/**
	 * Returns the DataSet that execute would print to the screen.
	 * 
	 * @param data
	 *            Collection of non-table Data Sets.
	 * @return processed table data.
	 * @throws TableException
	 * @throws IOException
	 */
	DataSet executeRecursive(HashMap<String, DataSet> data) throws IOException,
			TableException;

}

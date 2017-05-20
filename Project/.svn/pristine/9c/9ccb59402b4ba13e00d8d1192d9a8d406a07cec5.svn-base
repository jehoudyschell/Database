package Table;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.text.ParseException;

import Values.AbstractValue;
import Errors.BadTestException;
import Errors.IncorrectInputException;
import Errors.IndexException;

/**
 * A field for use in a table.
 */
@SuppressWarnings("serial")
public abstract class Field implements Serializable {
	protected String name;
	protected String type;
	protected File index = null;
	protected BinarySearchTree tree;

	/**
	 * Creates a new Field for a table.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 */
	protected Field(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Adds data to a binary file.
	 * 
	 * @param data
	 *            Data to be added.
	 * @param file
	 *            Output file
	 * @throws IncorrectInputException
	 * @throws IOException
	 */
	public abstract void addData(String data, RandomAccessFile file)
			throws IncorrectInputException, IOException;

	protected void delete() {
		if (isIndex())
			tree.delete();
	}

	/**
	 * Prints the field's data.
	 * 
	 * @param file
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public abstract AbstractValue getData(RandomAccessFile file)
			throws IOException;

	public void defineIndex(File file, int indexNum, String tableName, long l)
			throws IOException, IndexException {
		if (isIndex())
			throw new IndexException(name);
		index = file;
		tree = new BinarySearchTree(file, new File("TableData/" + tableName
				+ "_" + name + "_index.bin"), indexNum, this, l);
	}

	public File getIndex() {
		return index;
	}

	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the type.
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	@SuppressWarnings("rawtypes")
	public abstract AbstractValue makeTestVal(String testVal)
			throws BadTestException;

	/**
	 * Prints info on the field.
	 * 
	 * @return String name and type.
	 */
	public String toString() {
		String out = name + ": " + type;
		return out;
	}

	public abstract void restoreData(String string, RandomAccessFile dataFile)
			throws IOException, ParseException;

	public boolean isIndex() {
		if (index != null)
			return true;
		else
			return false;
	}

	public void delete(long pos) throws IOException {
		tree.delete(tree.select(pos));
	}
}
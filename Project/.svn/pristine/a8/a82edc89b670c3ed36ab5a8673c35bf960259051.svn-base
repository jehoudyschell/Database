package Table;

import java.io.IOException;
import java.io.RandomAccessFile;

import Values.AbstractValue;
import Values.IntegerValue;

import Errors.BadTestException;
import Errors.IncorrectInputException;

/**
 * Field using integers.
 */
@SuppressWarnings("serial")
public class IntegerField extends Field {

	/**
	 * Instantiates a new field that uses integers.
	 * 
	 * @param name
	 *            field name
	 */
	protected IntegerField(String name) {
		super(name, "integer");
	}

	/**
	 * Adds data to a binary file.
	 * 
	 * @param data
	 *            Integer to be added.
	 * @param file
	 *            Output file
	 * @throws IncorrectInputException
	 * @throws IOException
	 */
	public void addData(String data, RandomAccessFile file)
			throws IncorrectInputException, IOException {
		long index = file.getFilePointer();
		boolean adding = (index == file.length());
		try {
			Integer in = new Integer(data);
			file.writeInt(in);
		} catch (NumberFormatException | IOException e) {
			throw new IncorrectInputException(data, name);
		}
		if (adding && isIndex())
			tree.insert(index);
	}

	/*
	 * @see Table.Field#getData(java.io.RandomAccessFile)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public AbstractValue getData(RandomAccessFile file) throws IOException {
		return new IntegerValue(file.readInt());
	}

	/*
	 * @see Table.Field#getData(java.io.RandomAccessFile)
	 */
	@SuppressWarnings("rawtypes")
	public AbstractValue makeTestVal(String testVal) throws BadTestException {
		return new IntegerValue(testVal);
	}

	public void restoreData(String string, RandomAccessFile dataFile)
			throws IOException {
		dataFile.writeInt(new Integer(string));
	}
}
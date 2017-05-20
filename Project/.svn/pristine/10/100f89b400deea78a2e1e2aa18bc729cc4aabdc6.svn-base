package Table;

import java.io.IOException;
import java.io.RandomAccessFile;

import Values.AbstractValue;
import Values.BooleanValue;

import Errors.BadTestException;
import Errors.IncorrectInputException;

/**
 * Field using booleans.
 */
@SuppressWarnings("serial")
public class BooleanField extends Field {

	/**
	 * Instantiates a new field that uses booleans.
	 * 
	 * @param name
	 *            field name
	 */
	public BooleanField(String name) {
		super(name, "boolean");
	}

	/**
	 * Adds data to a binary file.
	 * 
	 * @param data
	 *            Boolean to be added.
	 * @param file
	 *            Output file
	 * @throws IncorrectInputException
	 * @throws IOException
	 */
	public void addData(String data, RandomAccessFile file)
			throws IncorrectInputException, IOException {
		verify(data);
		long index = file.getFilePointer();
		boolean adding = (index == file.length());
		Boolean in = new Boolean(data);
		try {
			file.writeBoolean(in);
		} catch (IOException e) {
			throw new IncorrectInputException(data, name);
		}
		if (adding && isIndex())
			tree.insert(index);
	}

	/*
	 * @see Table.Field#getData(java.io.RandomAccessFile)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public AbstractValue getData(RandomAccessFile file) throws IOException {
		return new BooleanValue(file.readBoolean());
	}

	/*
	 * @see Table.Field#getData(java.io.RandomAccessFile)
	 */
	@SuppressWarnings("rawtypes")
	public AbstractValue makeTestVal(String testVal) throws BadTestException {
		return new BooleanValue(testVal);
	}

	public void restoreData(String string, RandomAccessFile dataFile)
			throws IOException {
		dataFile.writeBoolean(new Boolean(string));
	}

	/**
	 * Verifies the data is of the correct type.
	 * 
	 * @param data
	 * @throws IncorrectInputException
	 */
	private void verify(String data) throws IncorrectInputException {
		if (!data.equalsIgnoreCase("true") && !data.equalsIgnoreCase("false"))
			throw new IncorrectInputException(data, name);
	}
}
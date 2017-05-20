package Table;

import java.io.IOException;
import java.io.RandomAccessFile;

import Values.AbstractValue;
import Values.RealValue;

import Errors.BadTestException;
import Errors.IncorrectInputException;

/**
 * Field using reals (doubles).
 */
@SuppressWarnings("serial")
public class RealField extends Field {

	/**
	 * Instantiates a new field that uses reals (doubles).
	 * 
	 * @param name
	 *            field name
	 */
	protected RealField(String name) {
		super(name, "real");
	}

	/**
	 * Adds data to a binary file.
	 * 
	 * @param data
	 *            Real to be added.
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
			Double in = new Double(data);
			file.writeDouble(in);
		} catch (IOException | NumberFormatException e) {
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
		return new RealValue(file.readDouble());
	}

	/*
	 * @see Table.Field#getData(java.io.RandomAccessFile)
	 */
	@SuppressWarnings("rawtypes")
	public AbstractValue makeTestVal(String testVal) throws BadTestException {
		return new RealValue(testVal);
	}

	public void restoreData(String string, RandomAccessFile dataFile)
			throws IOException {
		dataFile.writeDouble(new Double(string));
	}
}
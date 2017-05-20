package Table;

import java.io.IOException;
import java.io.RandomAccessFile;

import Values.AbstractValue;
import Values.CharValue;
import Errors.BadTestException;
import Errors.IncorrectInputException;

/**
 * Field using character sequences of set length.
 */
@SuppressWarnings("serial")
public class CharField extends Field {

	private int length;

	/**
	 * Instantiates a new field that uses character sequences of set length.
	 * 
	 * @param lengh
	 *            required character sequence length
	 * @param name
	 *            field name
	 */
	protected CharField(String name, int length) {
		super(name, "char");
		this.length = length;
	}

	/**
	 * Adds data to a binary file.
	 * 
	 * @param data
	 *            Characters to be added.
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
		data = data.substring(1, data.length() - 1).trim();
		try {
			file.writeChars(data);
		} catch (IOException e) {
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
		String out = "";
		for (int i = 0; i < length; i++) {
			out += file.readChar();
		}
		return new CharValue(out);
	}

	/**
	 * Returns the length.
	 * 
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/*
	 * @see Table.Field#getData(java.io.RandomAccessFile)
	 */
	@SuppressWarnings("rawtypes")
	public AbstractValue makeTestVal(String testVal) throws BadTestException {
		return new CharValue(testVal, length);
	}

	public void restoreData(String string, RandomAccessFile dataFile)
			throws IOException {
		dataFile.writeChars(string);
	}

	/*
	 * @see Table.Field#toString()
	 */
	@Override
	public String toString() {
		return name + ": " + type + "(" + length + ")";
	}

	/**
	 * Verifies the data is of the correct type.
	 * 
	 * @param data
	 * @throws IncorrectInputException
	 */
	private void verify(String data) throws IncorrectInputException {
		if (data.indexOf("'") != 0
				|| data.lastIndexOf("'") != data.length() - 1)
			throw new IncorrectInputException(data, type);
		data = data.substring(1, data.length() - 1).trim();
		if (!(data.length() == length))
			throw new IncorrectInputException(data, name);
	}
}
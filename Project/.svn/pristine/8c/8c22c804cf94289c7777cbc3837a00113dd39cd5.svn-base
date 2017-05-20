package Table;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Values.AbstractValue;
import Values.DateValue;

import Errors.BadTestException;
import Errors.IncorrectInputException;

/**
 * Field using dates.
 */
@SuppressWarnings("serial")
public class DateField extends Field {

	/**
	 * Instantiates a new field that uses dates.
	 * 
	 * @param name
	 *            field name
	 */
	protected DateField(String name) {
		super(name, "date");
	}

	/**
	 * Adds data to a binary file.
	 * 
	 * @param data
	 *            Date to be added.
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
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		long in;
		try {
			in = format.parse(data).getTime();
			file.writeLong(in);
		} catch (IOException | ParseException e) {
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
		return new DateValue(new Date(file.readLong()));
	}

	/*
	 * @see Table.Field#getData(java.io.RandomAccessFile)
	 */
	@SuppressWarnings("rawtypes")
	public AbstractValue makeTestVal(String testVal) throws BadTestException {
		return new DateValue(testVal);
	}

	public void restoreData(String string, RandomAccessFile dataFile)
			throws IOException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		long in;
		in = format.parse(string).getTime();
		dataFile.writeLong(in);
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
			throw new IncorrectInputException(data, name);
	}
}
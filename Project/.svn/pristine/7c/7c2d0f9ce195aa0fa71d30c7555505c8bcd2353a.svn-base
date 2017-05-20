package Table;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import Values.AbstractValue;
import Values.VarcharValue;

import Errors.BadTestException;
import Errors.IncorrectInputException;

/**
 * Field using variable length character sequences.
 */
@SuppressWarnings("serial")
public class VarcharField extends Field {
	File varcharFile;
	private transient RandomAccessFile stringFile;

	/**
	 * Instantiates a new field that uses variable length character sequences.
	 * 
	 * @param name
	 *            field name
	 * @throws IOException
	 */
	protected VarcharField(String name) throws IOException {
		super(name, "varchar");
	}

	/**
	 * Adds data to a binary file.
	 * 
	 * @param data
	 *            String to be added.
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
			stringFile.seek(stringFile.length());
			file.writeLong(stringFile.length());
			stringFile.writeUTF(data);
		} catch (IOException e) {
			throw new IncorrectInputException(data, name);
		}
		if (adding && isIndex())
			tree.insert(index);
	}

	/**
	 * Creates the .bin file for this field and RAF for reading/writing.
	 * 
	 * @param tableName
	 * @throws IOException
	 */
	protected void createRAF(String tableName) throws IOException {
		varcharFile = new File("TableData/" + tableName + "_" + name
				+ "_Varchar.bin");
		varcharFile.createNewFile();
		stringFile = new RandomAccessFile(varcharFile, "rw");
	}

	/**
	 * Deletes the .bin file when the field is deleted.
	 */
	protected void delete() {
		varcharFile.delete();
		super.delete();
	}

	/*
	 * @see Table.Field#getData(java.io.RandomAccessFile)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public AbstractValue getData(RandomAccessFile file) throws IOException {
		stringFile.seek(file.readLong());
		return new VarcharValue(stringFile.readUTF());
	}

	/*
	 * @see Table.Field#getData(java.io.RandomAccessFile)
	 */
	@SuppressWarnings("rawtypes")
	public AbstractValue makeTestVal(String testVal) throws BadTestException {
		return new VarcharValue(testVal);
	}

	public void restoreData(String string, RandomAccessFile dataFile)
			throws IOException {
		stringFile.seek(stringFile.length());
		dataFile.writeLong(stringFile.getFilePointer());
		stringFile.writeUTF(string);
	}

	/**
	 * Verifies the data is of the correct type.
	 * 
	 * @param data
	 * @throws IncorrectInputException
	 */
	private void verify(String data) throws IncorrectInputException {
		if (data.indexOf("'") != 0
				|| data.lastIndexOf("'") != (data.length() - 1))
			throw new IncorrectInputException(data, name);
	}
}
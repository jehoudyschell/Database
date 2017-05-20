package Table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;

import Errors.NameNotAvailibleException;
import Errors.NullFieldException;
import Errors.TableException;
import Errors.TableNotDefinedException;

/**
 * Stores all created Tables.
 */

@SuppressWarnings("serial")
public class TableCollection implements Serializable {

	private static TableCollection tc;
	private HashMap<String, Table> tables = new HashMap<String, Table>(0);
	private transient boolean debug = false;

	/**
	 * Creates an empty TableCollection.
	 */
	private TableCollection() {
	}

	/**
	 * Returns the TableCollection object. Creates a new one if one has not been
	 * instantiated.
	 * 
	 * @return TableCollection table collection.
	 */
	public static TableCollection get() {
		if (tc == null)
			tc = new TableCollection();
		return tc;
	}

	/**
	 * Adds a new table to the TableCollection.
	 * 
	 * @param key
	 *            table name
	 * @param table
	 *            Table object
	 * @throws NameNotAvailibleException
	 */
	public void add(String key, Table table) throws NameNotAvailibleException {
		if (tables.containsKey(key) == false)
			tables.put(key, table);
		else
			throw new NameNotAvailibleException("table", key);
	}

	/**
	 * Creates and writes data to .bin file.
	 * 
	 * @throws IOException
	 * @throws TableException
	 * @throws ParseException
	 */
	public void addTableData(String[] data, String tableName)
			throws TableException, IOException, ParseException {
		if (tables.containsKey(tableName) == false)
			throw new TableNotDefinedException(tableName);
		tables.get(tableName).addData(data);
	}

	public void backup(File file) throws IOException, TableException {
		for (Object table : tables.values().toArray())
			((Table) table).backup();
		FileOutputStream fileOut = new FileOutputStream(file);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(tables);
		objectOut.close();
	}

	/**
	 * Determines if a table exists that is named by the input.
	 * 
	 * @param table1
	 * @return if table exists
	 */
	public boolean contains(String table1) {
		return tables.containsKey(table1);
	}

	public boolean debug() {
		return debug;
	}

	public void defineIndex(String tableName, String fieldName)
			throws IOException, TableException {
		tables.get(tableName).defineIndex(fieldName);
	}

	/**
	 * Deletes rows in the specified table.
	 * 
	 * @param tableName
	 * @param whereClause
	 * @throws TableException
	 * @throws IOException
	 */
	public void deleteTableData(String tableName, String whereClause)
			throws TableException, IOException {
		if (!tables.containsKey(tableName))
			throw new TableNotDefinedException(tableName);
		tables.get(tableName).deleteRow(whereClause);
	}

	/**
	 * Removes the specified table from the HashMap.
	 * 
	 * @param tableName
	 *            table name
	 * @throws TableNotDefinedException
	 */
	public void drop(String tableName) throws TableNotDefinedException {
		if (!tables.containsKey(tableName))
			throw new TableNotDefinedException(tableName);
		Table temp = tables.remove(tableName);
		temp.delete();
	}

	/**
	 * Find the table to print data from.
	 * 
	 * @param tableName
	 * @param whereClause
	 * @return Table data.
	 * @throws IOException
	 * @throws TableException
	 */
	public Row[] getTableData(String tableName, String whereClause)
			throws IOException, TableException {
		if (!tables.containsKey(tableName))
			throw new TableNotDefinedException(tableName);
		return tables.get(tableName).getData(whereClause);
	}

	public int getTableFieldIndex(String tableName, String fieldName)
			throws NullFieldException {
		return tables.get(tableName).getFieldIndex(fieldName);
	}

	public String[] getTableFieldNames(String tableName) {
		return tables.get(tableName).getFieldNames();
	}

	/**
	 * Returns ordered table data.
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param descend
	 * @return
	 * @throws IOException
	 * @throws TableException
	 */
	public Row[] orderTableData(String tableName, String fieldName,
			boolean descend) throws IOException, TableException {
		if (!tables.containsKey(tableName))
			throw new TableNotDefinedException(tableName);
		return tables.get(tableName).orderData(fieldName, descend);
	}

	/**
	 * 
	 * @param tableName
	 * @param fieldList
	 * @return Table data.
	 * @throws IOException
	 * @throws TableException
	 */
	public Row[] projectTableData(String tableName, String fieldList)
			throws IOException, TableException {
		if (!tables.containsKey(tableName))
			throw new TableNotDefinedException(tableName);
		return tables.get(tableName).projectData(fieldList);
	}

	/**
	 * Renames the Table to the new name.
	 * 
	 * @param tableName
	 *            old name
	 * @param newName
	 *            new name
	 * @throws TableException
	 */
	public void rename(String tableName, String newName) throws TableException {
		if (!tables.containsKey(tableName))
			throw new TableNotDefinedException(tableName);
		else if (tables.containsKey(newName))
			throw new NameNotAvailibleException("Table", newName);
		else {
			Table table = tables.remove(tableName);
			table.setName(newName);
			tables.put(newName, table);
		}
	}

	@SuppressWarnings("unchecked")
	public void restore(File file) throws IOException, TableException,
			ClassNotFoundException, ParseException {
		FileInputStream fileIn = new FileInputStream(file);
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		tables = (HashMap<String, Table>) objectIn.readObject();
		objectIn.close();
		restoreTables();
	}

	public void restoreTables() throws IOException, TableException,
			ParseException {
		for (Table table : tables.values())
			table.restore();
	}

	public void setDebug() {
		debug = true;
	}

	/**
	 * Prints basic information on all current tables.
	 * 
	 * @return String table name and info on fields for all tables.
	 */
	public String toString() {
		String out = "";
		for (Object table : tables.values().toArray())
			out += ((Table) table);
		return out;
	}

	/**
	 * Returns information on all current tables in a format suitable for XML
	 * file creation.
	 * 
	 * @return ArrayList<ArrayList<String>> table name and info on fields for
	 *         all tables.
	 */
	public String toXML() {
		String out = "";
		for (Object table : tables.values().toArray()) {
			out += ((Table) table).toXML();
		}
		return out;
	}

	/**
	 * Updates selected data in the table.
	 * 
	 * @param tableName
	 * @param fieldName
	 * @param whereClause
	 * @throws IOException
	 * @throws TableException
	 */
	public void updateTableData(String tableName, String fieldData,
			String whereClause) throws TableException, IOException {
		if (!tables.containsKey(tableName))
			throw new TableNotDefinedException(tableName);
		tables.get(tableName).update(fieldData, whereClause);
	}
}
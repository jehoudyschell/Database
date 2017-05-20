package Table;

import java.io.IOException;

import Errors.NullFieldException;
import Errors.TableException;

public interface IData {

	/**
	 * Prints the data in the table. Incorrect or inappropriate data is handled
	 * by each individual field.
	 * 
	 * @return table data
	 * 
	 * @throws IOException
	 * @throws TableException
	 */
	public abstract Row[] getData(String whereClause) throws IOException,
			TableException;

	/**
	 * Returns organized data sorted by a given field.
	 * 
	 * @param fieldName
	 * @param descend
	 * @return Table data.
	 * @throws IOException
	 * @throws TableException
	 */
	public abstract Row[] orderData(String fieldName, boolean descend)
			throws IOException, TableException;

	/**
	 * Prints the data in the table from the chosen fields.
	 * 
	 * @return Table data.
	 * 
	 * @throws IOException
	 * @throws NullFieldException 
	 * @throws TableException
	 */
	public abstract Row[] projectData(String fieldList) throws IOException, NullFieldException;

	/**
	 * Prints basic information on the table.
	 * 
	 * @return String table name and info on fields.
	 */
	public String toString();
}
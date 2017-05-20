package Table;

import java.io.Serializable;
import java.util.ArrayList;

import Values.AbstractValue;

/**
 * Contains requested table data for processing.
 */
@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class Row implements Comparable<Row>, Serializable {
	private ArrayList<AbstractValue> data = new ArrayList<AbstractValue>(0);

	/**
	 * Creates an empty table data row.
	 */
	public Row() {
	}

	/**
	 * Adds data to the row.
	 * 
	 * @param in
	 *            Incoming data
	 */
	protected void addData(AbstractValue in) {
		data.add(in);
	}

	/**
	 * Adds data from a pre-existing row.
	 * 
	 * @param row
	 */
	public void addData(Row row) {
		for (AbstractValue val : row.data)
			data.add(val);
	}

	/**
	 * Compares two rows.
	 * 
	 * @param row
	 * @return -1, 0, or 1
	 */
	public int compareTo(Row row) {
		int out = 0;
		int i = 0;
		while (out == 0 && i < data.size()) {
			out = data.get(i).compareTo(row.data.get(i));
			i++;
		}
		return out;
	}

	/**
	 * Compares two rows on a given field.
	 * 
	 * @param row
	 * @return -1, 0, or 1
	 */
	public int compareOnField(Row row, int index) {
		return data.get(index).compareTo(row.data.get(index));
	}

	/**
	 * Finds data from a certain field.
	 * 
	 * @param index
	 * @return
	 */
	public AbstractValue getFieldData(int index) {
		return data.get(index);
	}

	/**
	 * Returns string of data in row.
	 * 
	 * @return Row data.
	 */
	public String toString() {
		String out = "";
		for (AbstractValue val : data)
			out += val.toString();
		return out;
	}
}

package Table;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import Errors.NullFieldException;
import Errors.TableException;

@SuppressWarnings("serial")
public class DataSet implements IData, Serializable {
	ArrayList<Row> data = new ArrayList<Row>(0);
	private String tableName;
	private String[] fields;

	/**
	 * Creates an empty DataSet.
	 */
	public DataSet(String ref) {
		tableName = ref;
		fields = TableCollection.get().getTableFieldNames(ref);
	}

	/**
	 * Creates an empty DataSet with specified fields.
	 */
	public DataSet(String ref, String[] fieldList) {
		tableName = ref;
		fields = fieldList;
	}

	public void add(Row row) {
		data.add(row);
	}

	/*
	 * @see Table.IData#getData(java.lang.String)
	 */
	public Row[] getData(String whereClause) throws IOException, TableException {
		Row[] rows = new Row[data.size()];
		for (int i = 0; i < data.size(); i++)
			rows[i] = data.get(i);
		return rows;
	}

	public String getTableName() {
		return tableName;
	}

	/*
	 * @see Table.IData#orderData(java.lang.String, boolean)
	 */
	public Row[] orderData(String fieldName, boolean descend)
			throws IOException, TableException {
		Row[] rows = new Row[data.size()];
		for (int i = 0; i < data.size(); i++)
			rows[i] = data.get(i);
		int index = Arrays.binarySearch(fields, fieldName);
		Row row1;
		Row row2;
		for (int i = 0; i < data.size(); i++) {
			row1 = rows[i];
			for (int j = i; j < data.size(); j++) {
				row2 = rows[j];
				if ((descend && row1.compareOnField(row2, index) < 0)
						|| (!descend && row1.compareOnField(row2, index) > 0)) {
					row1 = row2;
					row2 = rows[i];
					rows[i] = rows[j];
					rows[j] = row2;
				}
			}
		}
		return rows;
	}

	/*
	 * @see Table.IData#projectData(java.lang.String)
	 */
	public Row[] projectData(String fieldList) throws NullFieldException {
		String[] fields = fieldList.split(",");
		Row[] rows = new Row[data.size()];
		int[] indexes = new int[fields.length];
		for (int i = 0; i < fields.length; i++) {
			indexes[i] = Arrays.binarySearch(this.fields, fields[i].trim());
		}
		for (int i = 0; i < rows.length; i++) {
			rows[i] = new Row();
		}
		for (int i = 0; i < rows.length; i++)
			for (int index : indexes)
				rows[i].addData(data.get(i).getFieldData(index));
		return rows;
	}

	/*
	 * @see Table.IData#toString()
	 */
	public String toString() {
		String out = "";
		for (Row row : data)
			out += row + "\n";
		return out;
	}

	public String[] getFieldNames() {
		return fields;
	}
}
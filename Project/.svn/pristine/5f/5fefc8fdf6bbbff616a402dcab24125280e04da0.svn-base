package Table;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

import Values.AbstractValue;
import Errors.IncorrectInputException;
import Errors.NameNotAvailibleException;
import Errors.NullFieldException;
import Errors.TableException;
import Errors.FieldUndefinedException;
import Errors.WhereClauseException;

/**
 * Stores Fields and organized data.
 */
@SuppressWarnings("serial")
public class Table implements IData, Serializable {

	private String name;
	private ArrayList<Field> fields = new ArrayList<Field>(0);
	private transient RandomAccessFile dataFile;
	private int rowLength = 0;
	private DataSet data;

	/**
	 * Creates a new Table with no fields.
	 * 
	 * @param name
	 *            table name
	 * @throws IOException
	 */
	public Table(String name) throws IOException {
		this.name = name;
		if (!(new File("TableData").exists()))
			new File("TableData").mkdir();
		File binFile = new File("TableData/" + name + ".bin");
		binFile.createNewFile();
		dataFile = new RandomAccessFile(binFile, "rw");
	}

	/**
	 * Creates a new Table with specified fields.
	 * 
	 * @param name
	 *            table name
	 * @param fieldData
	 *            field descriptions.
	 * @throws TableException
	 * @throws IOException
	 */
	public Table(String name, String fieldData) throws TableException,
			IOException {
		this(name);
		String[] fieldList = fieldData.split(",");
		for (String field : fieldList) {
			field = field.trim();
			String[] data = field.split("\\s+", 2);
			addField(data);
		}
	}

	/**
	 * Adds data to the .bin file associated with this table.
	 * 
	 * @throws IOException
	 * @throws IncorrectInputException
	 */
	public void addData(String[] data) throws IncorrectInputException,
			IOException {
		long lengthBin = dataFile.length();
		dataFile.seek(lengthBin);
		try {
			for (int i = 0; i < fields.size(); i++)
				fields.get(i).addData(data[i].trim(), dataFile);
		} catch (IncorrectInputException e) {
			dataFile.setLength(lengthBin);
			throw e;
		}
	}

	/**
	 * Creates a new field in the Table.
	 * 
	 * @param data
	 *            field name and type
	 * @throws TableException
	 * @throws IOException
	 */
	public void addField(String[] data) throws TableException, IOException {
		for (Field f : fields)
			if (f.type.equalsIgnoreCase(data[0]))
				throw new NameNotAvailibleException("Field", data[0]);
		if (data[1].equalsIgnoreCase("boolean")) {
			fields.add(new BooleanField(data[0]));
			rowLength++;
			if (data.length > 2 && data[3].equalsIgnoreCase("true"))
				defineIndex(data[0]);
		} else if (data[1].toLowerCase().matches("char\\s*\\(\\s*\\d+\\s*\\)")
				&& !data[1].toLowerCase().matches("char\\s*\\(\\s*0\\s*\\)")) {
			int length = new Integer(data[1].replaceAll("char\\s*\\(", "")
					.replace(")", "").trim());
			fields.add(new CharField(data[0], length));
			rowLength = rowLength + 2 * length;
			if (data.length > 2 && data[3].equalsIgnoreCase("true"))
				defineIndex(data[0]);
		} else if (data[1].equalsIgnoreCase("char")) {
			int length = new Integer(data[2]);
			fields.add(new CharField(data[0], length));
			rowLength = rowLength + 2 * length;
			if (data.length > 2 && data[3].equalsIgnoreCase("true"))
				defineIndex(data[0]);
		} else if (data[1].equalsIgnoreCase("date")) {
			fields.add(new DateField(data[0]));
			rowLength = rowLength + 8;
			if (data.length > 2 && data[3].equalsIgnoreCase("true"))
				defineIndex(data[0]);
		} else if (data[1].equalsIgnoreCase("integer")) {
			fields.add(new IntegerField(data[0]));
			rowLength = rowLength + 4;
			if (data.length > 2 && data[3].equalsIgnoreCase("true"))
				defineIndex(data[0]);
		} else if (data[1].equalsIgnoreCase("real")) {
			fields.add(new RealField(data[0]));
			rowLength = rowLength + 8;
			if (data.length > 2 && data[3].equalsIgnoreCase("true"))
				defineIndex(data[0]);
		} else if (data[1].equalsIgnoreCase("varchar")) {
			VarcharField field = new VarcharField(data[0]);
			fields.add(field);
			field.createRAF(name);
			rowLength = rowLength + 8;
			if (data.length > 2 && data[3].equalsIgnoreCase("true"))
				defineIndex(data[0]);
		} else
			throw new FieldUndefinedException(data[0]);
	}

	public void backup() throws IOException, TableException {
		Row[] rows = getData("");
		data = new DataSet(name);
		for (Row row : rows)
			data.add(row);
	}

	public void defineIndex(String fieldName) throws IOException,
			NullFieldException, TableException {
		File indexFile = new File("TableData/" + name + ".bin");
		indexFile.createNewFile();
		int index = getFieldIndex(fieldName);
		Field field = fields.get(index);
		field.defineIndex(indexFile, index, name, rowLength);
		for (long i = 0; i < getNumberRows(); i++)
			field.tree.insert(i * rowLength + getFieldPos(fieldName));
	}

	/**
	 * Requests that the .bin file is deleted.
	 */
	public void delete() {
		new File("TableData/" + name + ".bin").delete();
		for (Field field : fields) {
			if (field.type.equalsIgnoreCase("varchar"))
				((VarcharField) field).delete();
		}
	}

	/**
	 * Deletes all rows in table matching the criteria.
	 * 
	 * @param whereClause
	 * @throws TableException
	 * @throws IOException
	 */
	public void deleteRow(String whereClause) throws TableException,
			IOException {
		WhereClause where;
		if (whereClause == "") {
			where = new NoWhere();
		} else
			where = new Where(whereClause);
		dataFile.seek(0);
		int row = 0;
		int skip = 0;
		byte[] bytes = new byte[rowLength];
		while (dataFile.getFilePointer() < rowLength * getNumberRows()) {
			if (where.matches(row + skip)) {
				skip++;
				for (Field field : fields)
					if (field.isIndex())
						field.delete((row + skip) * rowLength
								+ getFieldPos(field.getName()));
			} else {
				dataFile.seek(rowLength * (row + skip));
				dataFile.readFully(bytes);
				dataFile.seek(rowLength * row);
				dataFile.write(bytes);
				row++;
			}
			dataFile.seek(rowLength * (row + skip));
		}
		dataFile.setLength(row * rowLength);
	}

	/*
	 * @see Table.IData#getData(java.lang.String)
	 */
	public Row[] getData(String whereClause) throws IOException, TableException {
		WhereClause where;
		if (whereClause == "" || whereClause == null)
			where = new NoWhere();
		else
			where = new Where(whereClause);
		if (whereClause != ""
				&& getField(((Where) where).getFieldName()).isIndex())
			return getDataIndex(where);
		else
			return getDataNoIndex(where);
	}

	public Row[] getDataIndex(WhereClause where) throws IOException,
			NullFieldException {
		dataFile.seek(0);
		Long[] vals = ((Where) where).getDataIndex();
		Row[] rows = new Row[vals.length];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = new Row();
			dataFile.seek(vals[i]);
			for (Field field : fields)
				rows[i].addData(field.getData(dataFile));
		}
		return rows;
	}

	private Row[] getDataNoIndex(WhereClause where) throws IOException,
			TableException {
		dataFile.seek(0);
		int row = 0;
		Row[] rows = new Row[(int) getNumberRows()];
		for (int i = 0; i < getNumberRows(); i++)
			rows[i] = new Row();
		while (dataFile.getFilePointer() < rowLength * getNumberRows()) {
			if (where.matches(row)) {
				dataFile.seek(rowLength * row);
				for (Field field : fields) {
					rows[row].addData(field.getData(dataFile));
				}
			}
			row++;
			dataFile.seek(rowLength * row);
		}
		return rows;
	}

	/**
	 * Finds a field in this table.
	 * 
	 * @param name
	 *            Field name.
	 * @return The field.
	 * @throws NullFieldException
	 */
	private Field getField(String name) throws NullFieldException {
		Field field = null;
		int i = 0;
		while (i < fields.size() && field == null) {
			if (fields.get(i).getName().equals(name))
				field = fields.get(i);
			i++;
		}
		if (field == null)
			throw new NullFieldException(name);
		return field;
	}

	/**
	 * Finds a field in this table.
	 * 
	 * @param name
	 *            Field name.
	 * @return The field.
	 * @throws NullFieldException
	 */
	public int getFieldIndex(String name) throws NullFieldException {
		int index = 0;
		while (index < fields.size()
				&& !fields.get(index).getName().equals(name))
			index++;
		if (index == fields.size())
			throw new NullFieldException(name);
		return index;
	}

	public String[] getFieldNames() {
		String[] out = new String[fields.size()];
		for (int i = 0; i < fields.size(); i++)
			out[i] = fields.get(i).getName();
		return out;
	}

	/**
	 * Calculates the position of the field in bytes in a row.
	 * 
	 * @param fieldName
	 * @return Byte position of field in row.
	 */
	public long getFieldPos(String fieldName) {
		long pos = 0;
		int i = 0;
		while (i < fields.size() && !fields.get(i).getName().equals(fieldName)) {
			if (fields.get(i).type.equalsIgnoreCase("boolean"))
				pos += 1;
			else if (fields.get(i).type.equalsIgnoreCase("char"))
				pos += 2 * ((CharField) fields.get(i)).getLength();
			else if (fields.get(i).type.equalsIgnoreCase("date"))
				pos += 8;
			else if (fields.get(i).type.equalsIgnoreCase("integer"))
				pos += 4;
			else if (fields.get(i).type.equalsIgnoreCase("real"))
				pos += 8;
			else
				pos += 8;
			i++;
		}
		return pos;
	}

	/**
	 * Returns the table's name.
	 * 
	 * @return String name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Calculates number of rows.
	 * 
	 * @return number of rows.
	 * @throws IOException
	 */
	private long getNumberRows() throws IOException {
		return dataFile.length() / rowLength;
	}

	/*
	 * @see Table.IData#orderData(java.lang.String, boolean)
	 */
	public Row[] orderData(String fieldName, boolean descend)
			throws IOException, TableException {
		if (getField(fieldName).index != null)
			return orderIndex(descend, getField(fieldName).tree);
		System.out.println("No index defined for " + fieldName);
		return orderNoIndex(fieldName, descend);
	}

	private Row[] orderIndex(boolean descend, BinarySearchTree tree)
			throws IOException {
		Long[] indexes;
		if (!descend)
			indexes = tree.inOrderWalk(null);
		else
			indexes = tree.reverseInOrderWalk(null);
		Row[] rows = new Row[indexes.length];
		for (int i = 0; i < indexes.length; i++) {
			dataFile.seek(indexes[i]);
			rows[i] = new Row();
			for (Field field : fields)
				rows[i].addData(field.getData(dataFile));
		}
		return rows;
	}

	private Row[] orderNoIndex(String fieldName, boolean descend)
			throws IOException, TableException, NullFieldException {
		dataFile.seek(0);
		Row[] rows = getData("");
		int index = fields.indexOf(getField(fieldName));
		Row row1;
		Row row2;
		for (int i = 0; i < getNumberRows(); i++) {
			row1 = rows[i];
			for (int j = i; j < getNumberRows(); j++) {
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
	public Row[] projectData(String fieldList) throws IOException {
		String[] fieldSelection = fieldList.split(",");
		dataFile.seek(0);
		int row = 0;
		Row[] rows = new Row[(int) getNumberRows()];
		for (int i = 0; i < getNumberRows(); i++)
			rows[i] = new Row();
		while (dataFile.getFilePointer() < rowLength * getNumberRows()) {
			dataFile.seek(rowLength * row);
			for (String fieldName : fieldSelection) {
				for (Field field : fields) {
					if (field.getName().equals(fieldName.trim())) {
						dataFile.seek(rowLength * row
								+ getFieldPos(fieldName.trim()));
						rows[row].addData(field.getData(dataFile));
					}
				}
			}
			row++;
			dataFile.seek(rowLength * row);
		}
		return rows;
	}

	public void restore() throws IOException, TableException, ParseException {
		if (!(new File("TableData").exists()))
			new File("TableData").mkdir();
		File binFile = new File("TableData/" + name + ".bin");
		binFile.createNewFile();
		dataFile = new RandomAccessFile(binFile, "rw");
		for (Field field : fields)
			if (field.getType().equals("varchar"))
				((VarcharField) field).createRAF(name);
		restoreData();
		data = null;
	}

	public void restoreData() throws IOException, TableException,
			ParseException {
		String[] rowData = data.toString().split("\n");
		String[][] binData = new String[rowData.length][];
		for (int i = 0; i < rowData.length; i++)
			binData[i] = rowData[i].split("\t");
		dataFile.seek(0);
		for (int i = 0; i < rowData.length; i++)
			for (int j = 0; j < binData[i].length; j++)
				fields.get(j).restoreData(binData[i][j], dataFile);
		dataFile.setLength(rowLength * rowData.length);
	}

	/**
	 * Changes the table name to the new name.
	 * 
	 * @param name
	 *            new name
	 */
	public void setName(String name) {
		this.name = name;
		new File("TableData/" + name + ".bin").renameTo(new File("TableData/"
				+ name + ".bin"));
		for (Field field : fields) {
			if (field.type.equalsIgnoreCase("varchar"))
				new File("TableData/" + name + "_" + field + ".bin")
						.renameTo(new File("TableData/" + name + "_Varchar.bin"));
		}
	}

	/*
	 * @see Table.IData#toString()
	 */
	public String toString() {
		String out = "\nTable name: " + name + "\n";
		out += "Fields: \n";
		for (Field field : fields)
			out += field + "\n";
		return out;
	}

	/**
	 * Returns the table's field info for XML creation.
	 * 
	 * @return ArrayList<String> Field names and types.
	 */
	public String toXML() {
		String out = "\t<TABLE ID=\"" + name + "\">";
		for (Field field : fields) {
			out += "\t\t<FIELD ID=\"" + field.getName() + "\"";
			out += " TYPE=\"";
			if (!field.getType().equalsIgnoreCase("char"))
				out += field.getType() + "\"";
			else
				out += field.getType() + "\" LENGTH=\""
						+ ((CharField) field).getLength() + "\"";
			out += " INDEX=\"" + field.isIndex() + "\"";
			out += "></FIELD>";
		}
		out += "</TABLE>";
		return out;
	}

	/**
	 * Updates selected data in the table.
	 * 
	 * @param whereClause
	 * @param whereClause
	 * @throws TableException
	 * @throws IOException
	 */
	public void update(String fieldData, String whereClause)
			throws TableException, IOException {
		WhereClause where;
		String[] data = fieldData.split("\\s+", 3);
		if (whereClause == "") {
			where = new NoWhere();
		} else
			where = new Where(whereClause);
		dataFile.seek(0);
		int row = 0;
		while (dataFile.getFilePointer() < rowLength * getNumberRows()) {
			if (where.matches(row)) {
				long pos = (row * rowLength) + getFieldPos(data[0]);
				dataFile.seek(pos);
				Field field = getField(data[0]);
				field.delete(pos);
				field.addData(data[2], dataFile);
			}
			row++;
			dataFile.seek(rowLength * row);
		}
	}

	/**
	 * Compares table values for where clauses.
	 */
	@SuppressWarnings("rawtypes")
	class Where implements WhereClause {
		String fieldName;
		String operator;
		String testVal;
		AbstractValue test;

		/**
		 * Creates a where clause object.
		 * 
		 * @param fieldName
		 * @param operator
		 * @param testVal
		 * @throws TableException
		 */
		private Where(String where) throws TableException {
			String[] data = where.split("\\s+", 3);
			if (data.length < 3)
				throw new WhereClauseException(where);
			this.fieldName = data[0];
			this.operator = data[1];
			this.testVal = data[2];
			Field field = getField(fieldName);
			test = field.makeTestVal(testVal);
		}

		public Long[] getDataIndex() throws NullFieldException, IOException {
			return getField(fieldName).tree.getData(operator, test, null);
		}

		public String getFieldName() {
			return fieldName;
		}

		/**
		 * Tests the values against the operator.
		 * 
		 * @param rowNum
		 *            Row number being used.
		 * @return If data in the row matches the where clause.
		 */
		@SuppressWarnings("unchecked")
		public boolean matches(int rowNum) throws TableException, IOException {
			dataFile.seek((rowNum * rowLength) + getFieldPos(fieldName));
			AbstractValue val = getField(fieldName).getData(dataFile);
			boolean matches = false;
			int result = val.compareTo(test);
			if (operator.equals("!=") && result != 0)
				matches = true;
			else if (operator.contains("=") && !operator.contains("!")
					&& result == 0)
				matches = true;
			else if (operator.contains(">") && result == 1)
				matches = true;
			else if (operator.contains("<") && result == -1)
				matches = true;
			return matches;
		}
	}
}
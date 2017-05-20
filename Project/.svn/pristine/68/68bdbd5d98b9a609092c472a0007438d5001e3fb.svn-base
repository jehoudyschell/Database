package XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import Table.TableCollection;

/**
 * Responsible for writing the XML file to save table data.
 */
public class XMLWriter {

	/**
	 * Creates an XMLWriter for creating the XML document.
	 */
	public XMLWriter() {
		;
	}

	/**
	 * Writes table collection information to an XML file for data storage.
	 * 
	 * @param file
	 *            file to write data to.
	 * @throws FileNotFoundException
	 */
	public void write(String file) throws FileNotFoundException {
		String ENCODING = "UTF-8";
		PrintWriter out = new PrintWriter(new FileOutputStream(file));
		out.println("<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>");
		// out.println("<!DOCTYPE TABLECOLLECTION SYSTEM \"tables.dtd\">");
		out.println("<COLLECTION>" + TableCollection.get().toXML()
				+ "</COLLECTION>");
		out.close();
	}

	/**
	 * Creates .dtd file if necessary, then writes table collection information
	 * to an XML file for data storage.
	 * 
	 * @throws FileNotFoundException
	 */
	public static void save() throws FileNotFoundException {
		if (!(new File("TableData").exists()))
			new File("TableData").mkdir();
		String file = "TableData/tables.xml";
		if (!(new File("TableData/tables.dtd").exists()))
			dtdWrite();
		new XMLWriter().write(file);
	}

	/**
	 * Creates .dtd file.
	 * 
	 * @throws FileNotFoundException
	 */
	private static void dtdWrite() throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new FileOutputStream(
				"TableData/tables.dtd"));
		out.println("<xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!ELEMENT COLLECTION (TABLE+)>");
		out.println("<!ELEMENT TABLE (FIELD+)>");
		out.println("<!ELEMENT FIELD>\n");
		out.println("<!ATTLIST TABLE\nID #REQUIRED>");
		out.println("<!ATTLIST FIELD\nID #REQUIRED");
		out.println("TYPE #REQUIRED");
		out.print("LENGTH #IMPLIED>");
		out.close();
	}
}
package XML;

import java.io.IOException;
import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import Errors.NameNotAvailibleException;
import Errors.TableException;
import Table.Table;
import Table.TableCollection;

/**
 * The SAXReader responsible for reading and processing XML data.
 */
public class SAXReader {

	/**
	 * Handles XML file reading and processing.
	 * 
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerConfigurationException
	 */
	public static void loadTables() throws SAXException, IOException,
			TransformerConfigurationException {
		new SAXReader().saxReader();
	}

	/**
	 * Creates a new saxReader for XML file reading and processing.
	 * 
	 * @throws SAXException
	 * @throws IOException
	 */
	public void saxReader() throws SAXException, IOException {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new ExampleContentHandler());
		reader.parse("TableData/tables.xml");
	}

	/**
	 * Responsible for processing information from the XML file.
	 */
	private class ExampleContentHandler extends DefaultHandler {

		private boolean isTable = false;
		private boolean isField = false;
		private String tableName;
		private String[] fieldList = new String[3];
		private Table tempTable;

		/**
		 * Initiated when XMLReader encounters a <>. Processes data between
		 * pairs of <>.
		 */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			fieldList = new String[4];
			if (qName.equalsIgnoreCase("TABLE")) {
				tableName = attributes.getValue(0);
				try {
					tempTable = new Table(attributes.getValue(0));
					TableCollection.get().add(tableName, tempTable);
				} catch (NameNotAvailibleException e) {
					System.out.println(e);
				} catch (IOException e) {
					System.out.println(e);
				}
				isTable = true;
			} else if (qName.equalsIgnoreCase("FIELD")) {
				isTable = false;
				isField = true;
				fieldList[0] = attributes.getValue(0);
				fieldList[1] = attributes.getValue(1);
				if (fieldList[1].equalsIgnoreCase("char")) {
					fieldList[2] = attributes.getValue(2);
					fieldList[3] = attributes.getValue(3);
				} else {
					fieldList[3] = attributes.getValue(2);
				}
				try {
					tempTable.addField(fieldList);
				} catch (TableException | IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * Method used when "</>" is encountered.
		 * 
		 * @param uri
		 * @param localName
		 * @param qName
		 */
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			if (isTable) {
				System.out.println();
				isTable = false;
			}
			if (isField) {
				isField = false;
			}
		}
	}
}
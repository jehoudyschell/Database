package XML;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.transform.TransformerConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import Table.TableCollection;

public class XMLWriterTest {

	@Test
	public void test() {
		try {
			SAXReader.loadTables();
		} catch (TransformerConfigurationException | SAXException | IOException e) {
			System.out.println(e);
		}
		try {
			XMLWriter.save();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		try {
			SAXReader.loadTables();
		} catch (TransformerConfigurationException | SAXException | IOException e) {
			System.out.println(e);
		}
		assertTrue(!TableCollection.get().toString().equals(""));
	}
}

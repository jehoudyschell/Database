package XML;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.transform.TransformerConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import Table.TableCollection;

public class SAXReaderTest {

	@Test
	public void test() {
		TableCollection tc = TableCollection.get();
		assertTrue(tc.toString().equals(""));
		try {
			SAXReader.loadTables();
		} catch (TransformerConfigurationException | SAXException | IOException e) {
			System.out.println(e);
		}
		assertFalse(tc.toString().equals(""));
	}
}

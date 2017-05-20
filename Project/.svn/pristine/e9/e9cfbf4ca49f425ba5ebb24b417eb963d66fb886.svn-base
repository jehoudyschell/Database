package Table;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class TableTest {
	String[] tests = { "test", "table1", "emp" };
	Table table;
	File file;

	@Test
	public void testConstruct() {
		try {
			for (String test : tests) {
				table = new Table(test);
				file = new File("TableData/" + test + ".bin");
				assertTrue(file.exists());
				table.delete();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

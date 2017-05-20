package Table;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import Errors.TableException;

public class TableCollectionTest {
	TableCollection tc = TableCollection.get();
	String test = " true    boolean , state   char ( 2 ), bday date";

	@Test
	public void testConstruct() {
		try {
			assertTrue(("" + tc).equals(""));
			tc.add("test", new Table("test", test));
			assertTrue(("" + tc).contains("test"));
			tc.rename("test", "table1");
			assertTrue(("" + tc).contains("table1"));
			tc.drop("table1");
			assertTrue(("" + tc).equals(""));
		} catch (TableException e) {
			System.out.println(e);
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
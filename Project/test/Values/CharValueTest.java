package Values;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Errors.BadTestException;

public class CharValueTest {
	String[] good = { "'dog'", "'cat'", "'pen'" };
	String[] bad = { "'potato'", "'false'", "cat" };
	@SuppressWarnings("rawtypes")
	ArrayList<AbstractValue> array = new ArrayList<AbstractValue>(0);

	@Test
	public void test() {
		try {
			for (String test : good) {
				array.add(new CharValue(test, 3));
			}
		} catch (BadTestException e) {
			System.out.println(e);
			assertTrue(false);
		}
		try {
			for (String test : bad) {
				array.add(new CharValue(test, 3));
			}
		} catch (BadTestException e) {
			assertTrue(true);
		}
	}
}

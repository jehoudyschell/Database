package Values;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Errors.BadTestException;

public class BooleanValueTest {
	String[] good = { "true", "false", "TruE" };
	String[] bad = { "potato", "PoTaTo" };
	@SuppressWarnings("rawtypes")
	ArrayList<AbstractValue> array = new ArrayList<AbstractValue>(0);

	@Test
	public void test() {
		try {
			for (String test : good) {
				array.add(new BooleanValue(test));
			}
		} catch (BadTestException e) {
			System.out.println(e);
			assertTrue(false);
		}
		try {
			for (String test : bad) {
				array.add(new BooleanValue(test));
			}
		} catch (BadTestException e) {
			assertTrue(true);
		}
	}
}

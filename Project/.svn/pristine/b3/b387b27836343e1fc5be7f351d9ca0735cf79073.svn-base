package Values;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Errors.BadTestException;

public class RealValueTest {
	String[] good = { "12", "15.8", "3.14159" };
	String[] bad = { "potato", "'1777'" };
	@SuppressWarnings("rawtypes")
	ArrayList<AbstractValue> array = new ArrayList<AbstractValue>(0);

	@Test
	public void test() {
		try {
			for (String test : good) {
				array.add(new RealValue(test));
			}
		} catch (BadTestException e) {
			System.out.println(e);
			assertTrue(false);
		}
		try {
			for (String test : bad) {
				array.add(new RealValue(test));
			}
		} catch (BadTestException e) {
			assertTrue(true);
		}
	}
}

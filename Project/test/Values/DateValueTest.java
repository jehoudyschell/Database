package Values;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Errors.BadTestException;

public class DateValueTest {
	String[] good = { "'11/12/1994'", "'01/10/1997'", "'08/27/1966'" };
	String[] bad = { "11/12/1994", "'12/25/00'", "'2/29/2000'" };
	@SuppressWarnings("rawtypes")
	ArrayList<AbstractValue> array = new ArrayList<AbstractValue>(0);

	@Test
	public void test() {
		try {
			for (String test : good) {
				array.add(new DateValue(test));
			}
		} catch (BadTestException e) {
			System.out.println(e);
			assertTrue(false);
		}
		try {
			for (String test : bad) {
				array.add(new DateValue(test));
			}
		} catch (BadTestException e) {
			assertTrue(true);
		}
	}
}

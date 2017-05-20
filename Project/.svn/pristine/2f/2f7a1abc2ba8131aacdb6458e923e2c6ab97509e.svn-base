package Values;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class VarcharValueTest {
	String[] good = { "'true'", "'false'", "'word'" };
	@SuppressWarnings("rawtypes")
	ArrayList<AbstractValue> array = new ArrayList<AbstractValue>(0);

	@Test
	public void test() {
		for (String test : good) {
			array.add(new VarcharValue(test));
		}
		if (array.size() != 3)
			assertTrue(false);
	}
}

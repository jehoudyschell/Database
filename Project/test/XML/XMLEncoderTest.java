package XML;

import static org.junit.Assert.*;

import org.junit.Test;

public class XMLEncoderTest {

	@Test
	public void test() {
		assertTrue((XMLEncoder.encode("&")).equals("&amp;"));
		assertTrue((XMLEncoder.encode("'")).equals("&apos;"));
		assertTrue((XMLEncoder.encode("<")).equals("&lt;"));
		assertTrue((XMLEncoder.encode(">")).equals("&gt;"));
		assertTrue((XMLEncoder.encode("\"")).equals("&quot;"));
		assertTrue((XMLEncoder.encode("a")).equals("a"));
		assertTrue((XMLEncoder.encode(";")).equals(";"));
	}

}

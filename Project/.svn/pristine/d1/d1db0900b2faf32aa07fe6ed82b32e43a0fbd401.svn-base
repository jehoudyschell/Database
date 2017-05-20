package Commands;

import org.junit.Before;

public class MinusCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new MinusCommand();
		good = new String[] { "minus ename sal and ename ename;",
				"minus ename sal, ename and dept deptnum, location;",
				"minus ename sal and ename deptnum;" };
		bad = new String[] { "minus sal andename;",
				"minusename and ename sal;",
				"minus ename sal, ename, deptnum;",
				"minus ename sal ename ename;" };
	}
}

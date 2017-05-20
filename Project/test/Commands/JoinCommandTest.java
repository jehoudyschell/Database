package Commands;

import org.junit.Before;

public class JoinCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new JoinCommand();
		good = new String[] { "join ename sal and ename ename;",
				"join ename sal, ename and dept deptnum, location;",
				"join ename sal and ename deptnum;" };
		bad = new String[] { "join sal andename;", "joinename and ename sal;",
				"join ename sal, ename, deptnum;",
				"join ename sal ename ename;" };
	}
}

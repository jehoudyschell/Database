package Commands;

import org.junit.Before;

public class IntersectCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new IntersectCommand();
		good = new String[] { "intersect ename sal and ename ename;",
				"intersect ename sal, ename and dept deptnum, location;",
				"intersect ename sal and ename deptnum;" };
		bad = new String[] { "intersect sal andename;",
				"intersectename and ename sal;",
				"intersect ename sal, ename, deptnum;",
				"intersect ename sal ename ename;" };
	}
}
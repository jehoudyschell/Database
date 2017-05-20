package Commands;

import org.junit.Before;

public class OrderCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new OrderCommand();
		good = new String[] { "order ename  by ename;",
				"order ename by deptnum;", "order ename by sal;" };
		bad = new String[] { "order sal byename;", "orderename by ename sal;",
				"order ename sal, ename, deptnum;",
				"order ename sal ename ename;" };
	}
}

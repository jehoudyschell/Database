package Commands;

import org.junit.Before;

public class InsertCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new InsertCommand();
		good = new String[] { "insert(ename)into emp;",
				"insert (name, sal, deptnum) into emp;",
				"insert(location)into dept;" };
		bad = new String[] { "insert into emp;",
				"insert (ename) into emp, dept;",
				"insert ename in emp;",
				"insert ename into;" };
	}
}
package Commands;

import org.junit.Before;

public class DefineIndexCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new DefineIndexCommand();
		good = new String[] { "define index on emp (ename);",
				"define index on emp (dept);", "define index on dept(deptnum);" };
		bad = new String[] { "define index emp ename;",
				"define index on (emp);", "define index (emp) on ename;",
				"define index on (ename);" };
	}
}
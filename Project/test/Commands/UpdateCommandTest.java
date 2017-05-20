package Commands;

import org.junit.Before;

public class UpdateCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new UpdateCommand();
		good = new String[] { "update emp set emp = 'Tom';",
				"update emp set emp = 'Tom' where sal > 2500;",
				"update emp set sal = 3800 where emp = 'Bob';" };
		bad = new String[] { "update emp;", "update set emp = 'Tom';",
				"update emp set emp;", "update emp = 'Tom';" };
	}
}
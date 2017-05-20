package Commands;

import org.junit.Before;

public class DeleteCommandTest extends AbstractCommandTest {


	@Before
	public void testMatches() throws Exception {
		command = new DeleteCommand();
		good = new String[] { "delete emp;", "delete emp where sal > 3500;",
				"delete emp where emane = 'John';",
				"delete dept where deptnum = 1;" };
		bad = new String[] { "delete emp wheresal;", "delete emp sal > 3500;",
				"delete where sal > 3500;", "delete emp where;" };
	}
}
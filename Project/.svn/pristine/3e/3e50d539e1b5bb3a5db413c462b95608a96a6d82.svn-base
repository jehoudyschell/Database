package Commands;

import org.junit.Before;

public class RenameTableCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new RenameTableCommand();

		good = new String[] { "rename table emp to employee;",
				"rename table dept to department;", "rename table emp to dept;" };
		bad = new String[] { "renametable emp to employee;",
				"rename table emp employee;", "rename table emp to ;",
				"rename emp to employee;", "rename table to employee;" };
	}
}
package Commands;

import org.junit.Before;

public class DropTableCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new DropTableCommand();
		good = new String[] { "drop table emp;", "drop table dept;",
				"drop table table1;" };
		bad = new String[] { "drop table;", "drop emp;", "droptable emp;" };
	}
}
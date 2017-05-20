package Commands;

import org.junit.Before;

public class SelectCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new SelectCommand();
		good = new String[] { "select emp;", "select emp where sal > 1000;",
				"select emp where name = 'Tom';" };
		bad = new String[] { "selectemp;", "select empwhere sal > 1000;" };
	}
}

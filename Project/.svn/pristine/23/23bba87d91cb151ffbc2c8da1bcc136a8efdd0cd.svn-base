package Commands;

import org.junit.Before;

public class UnionCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new UnionCommand();
		good = new String[] { "union emp and dept;", "union emp and comp;",
				"union dept and comp;" };
		bad = new String[] { "union emp;", "union emp and;", "union and emp;" };
	}
}
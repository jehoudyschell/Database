package Commands;

import org.junit.Before;

public class PrintTableCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new PrintTableCommand();
		good = new String[] { "print ename;",
				"print       ename;",
				"print\nename;" };
		bad = new String[] { "printsal;",
				"print;",
				"print ename, dept;"};
	}
}


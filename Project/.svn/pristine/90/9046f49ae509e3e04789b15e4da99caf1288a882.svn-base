package Commands;

import org.junit.Before;

public class ExitCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new ExitCommand();
		good = new String[] { "exit;", "Exit;", "ExIt;", "EXIT;" };
		bad = new String[] { "stop;", "end;", "kill;", "terminate;", ";" };
	}
}
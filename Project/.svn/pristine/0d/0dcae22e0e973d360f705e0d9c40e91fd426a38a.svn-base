package Commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public abstract class AbstractCommandTest {

	protected ICommand command;

	public abstract void testMatches() throws Exception;

	protected String[] good;
	protected String[] bad;

	public AbstractCommandTest() {
		super();
	}

	@Test
	public void testExecute() {
		System.out.println(command);
		for (String s : good) {
			System.out.println(s);
			assertTrue(command.matches(s));
		}
		for (String s : bad) {
			System.out.println(s);
			assertFalse(command.matches(s));
		}
	}
}
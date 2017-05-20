package Commands;

import org.junit.Before;

public class RestoreFromCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new RestoreFromCommand();
		good = new String[] { "restore from file;", "restore from text.txt;",
				"restore from sound.mp3;", "restore from image.jpg;" };
		bad = new String[] { "restorefrom text.txt;", "restore from;",
				"restore fromtext.txt;" };
	}
}
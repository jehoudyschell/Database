package Commands;

import org.junit.Before;

public class ReadCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new ReadCommand();
		good = new String[] { "read 'file';", "read 'text.txt   ';",
				"read '  sound.mp3';", "read     'image.jpg';" };
		bad = new String[] { "read 'text' '.txt';", "read;", "readtext.txt';" };
	}
}
package Commands;

import org.junit.Before;

public class BackupToCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new BackupToCommand();
		good = new String[] { "backup to 'file';", "backup to 'text.txt';",
				"backup to 'sound.mp3';", "backup to 'image.jpg';" };
		bad = new String[] { "backup 'text.txt';", "backupto 'text.txt';",
				"backup totext.txt;", "backup to text.txt;" };
	}
}
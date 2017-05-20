package Commands;

import org.junit.Before;

public class ProjectCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new ProjectCommand();
		good = new String[] { "project ename sal over ename ename;",
				"project ename sal, ename over dept deptnum, location;",
				"project ename sal over ename deptnum;" };
		bad = new String[] { "project sal overename;",
				"projectename over ename sal;",
				"project ename sal, ename, deptnum;",
				"project ename sal ename ename;" };
	}
}

package Commands;

import org.junit.Before;

public class DefineTableCommandTest extends AbstractCommandTest {

	@Before
	public void testMatches() throws Exception {
		command = new DefineTableCommand();
		good = new String[] { "define table emp having fields (ename);",
				"define table emp having fields(ename, dept);",
				"define table dept having fields (deptnum);" };
		bad = new String[] { "define table emp;",
				"define table having fields ename, dept;",
				"define table emp having (ename, dept);",
				"definetable emp havingfields (ename, dept);" };
	}
}
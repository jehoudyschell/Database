package Commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Table.DataSet;
import Errors.RecursionException;
import Errors.TableException;

public class RecursionHandler {
	RecursiveCommand[] commands = { new IntersectCommand(), new JoinCommand(),
			new MinusCommand(), new OrderCommand(), new ProjectCommand(),
			new SelectCommand(), new UnionCommand() };
	HashMap<String, DataSet> dataSets = new HashMap<String, DataSet>(0);
	Pattern pattern = Pattern.compile("(\\([^)^(]*?\\))");

	public boolean matches(String input) {
		Matcher m = pattern.matcher(input);
		return m.find();
	}

	public void parse(String input, boolean print, int index)
			throws FileNotFoundException, TableException, IOException,
			ParseException {
		Matcher m = pattern.matcher(input);
		while (m.find()) {
			String inside = m.group(1);
			parse(inside.replace("(", "").replace(")", "") + ";", false, index);
			input = input.replace(inside, ("dictionary" + index));
			m = pattern.matcher(input);
			index++;
		}
		String i = index + "";
		boolean exc = false;
		for (RecursiveCommand c : commands) {
			exc = (c.matches(input) || exc);
			if (c.matches(input))
				dataSets.put(("dictionary" + i), c.executeRecursive(dataSets));
		}
		if (!exc)
			throw new RecursionException();
		if (print)
			System.out.println("\n" + dataSets.get("dictionary" + i));
	}
}
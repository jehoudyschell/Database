package Commands;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;

import Table.TableCollection;
import XML.SAXReader;
import XML.XMLWriter;
import Commands.ICommand;
import Commands.SelectCommand;
import Errors.TableException;

/**
 * Main hub of program. Runs the UI.
 */
public class Driver {

	private ICommand[] commands = new ICommand[] { new BackupToCommand(),
			new DefineIndexCommand(), new DefineTableCommand(),
			new DeleteCommand(), new DropTableCommand(), new ExitCommand(),
			new InsertCommand(), new PrintTableCommand(), new ReadCommand(),
			new RenameTableCommand(), new RestoreFromCommand(),
			new UpdateCommand() };

	RecursiveCommand[] recursiveCommands = { new IntersectCommand(),
			new JoinCommand(), new MinusCommand(), new OrderCommand(),
			new ProjectCommand(), new SelectCommand(), new UnionCommand() };

	private RecursionHandler recursion = new RecursionHandler();

	/**
	 * Starts the Driver.
	 */
	public void run() {
		Scanner in = new Scanner(System.in);
		readLines(in);
	}

	/**
	 * Displays UI and runs the Driver. All commands are sent from here.
	 */
	public void readLines(Scanner in) {
		LABLE: while (true) {
			try {
				String input = "";

				while (input.lastIndexOf(";") == -1) {
					System.out.print("-> ");
					if (in.hasNextLine())
						input += in.nextLine() + " ";
					else {
						System.out.print("End of file.\n");
						return;
					}
				}

				for (ICommand command : commands) {
					if (command.matches(input)) {
						command.execute();
						continue LABLE;
					}
				}

				if (recursion.matches(input)) {
					new RecursionHandler().parse(input, true, 0);
					continue LABLE;
				}

				for (RecursiveCommand command : recursiveCommands) {
					if (command.matches(input)) {
						command.execute();
						continue LABLE;
					}
				}

				if (input.toLowerCase().contains("debug")) {
					TableCollection.get().setDebug();
					continue LABLE;
				}

				if (input.lastIndexOf(";") != -1) {
					System.out
							.println("Error: Improper or incorrect command. Please check syntax.");
				}
			} catch (TableException e) {
				System.out.println(e);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Where program starts. Initiates the driver method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			if (!(new File("TableData/tables.xml").exists()))
				XMLWriter.save();
			SAXReader.loadTables();
		} catch (TransformerConfigurationException | SAXException | IOException e) {
			System.out.println(e);
		}
		System.out
				.println("Welcome. Please type your commands below using proper syntax.");
		new Driver().run();
	}
}
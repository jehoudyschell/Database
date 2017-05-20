package XML;

import java.util.HashMap;

/**
 * Responsible for encoding certain characters in the XML document.
 */
public class XMLEncoder {
	private static HashMap<String, String> codes;

	/**
	 * Initializes class variables.
	 */
	static {
		codes = new HashMap<String, String>();
		codes.put("&", "&amp;");
		codes.put("\"", "&quot;");
		codes.put("'", "&apos;");
		codes.put("<", "&lt;");
		codes.put(">", "&gt;");
	}

	/**
	 * Creates an XMLEncoder for use in this class only.
	 */
	private XMLEncoder() {
	}

	/**
	 * Returns encoded characters.
	 * 
	 * @param input
	 * @return encoded character.
	 */
	public static String encode(String input) {
		// Ampersands must be checked for first, otherwise they can replace
		// ampersands from the codes.
		input = input.replace("&", codes.get("&"));
		for (String x : codes.keySet()) {
			if (!x.equals("&"))
				input = input.replace(x, codes.get(x));
		}
		return input;
	}
}
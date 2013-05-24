package passwordApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class PasswordFile {

	/*
	 * Takes in a text file
	 * Checks if the file is a legal password file
	 * Once the check is passed,
	 *  adds Entries based on file
	 *  
	 *  A legal password file is a text file that
	 *  * contains three operators (T, U, P) that
	 *  	signify title, username, and password
	 *  * has the operators on a single line
	 *  	by themselves
	 *  * has Strings immediately following each
	 *  	operator on a new line, which is taken
	 *  	to mean that each field of an Entry can
	 *  	be filled by the String following the
	 *  	corresponding operator
	 *  
	 *  File Format:
	 *  	T
	 *  	Facebook
	 *  	U
	 *  	user@hotmail.com
	 *  	P
	 *  	FBpassword
	 */

	public boolean legal(File in) {
		boolean retVal = true;
		return retVal;
	}

	public static void main(String[] args) {
		Map<String, Entry> test = new HashMap<String, Entry>();
		test.put("Awesome.com", new Entry("Awesome.com", "Kevin113", "password123"));
		test.put("matrix.com", new Entry("matrix.com", "klo411newman", "helloWorld"));
		test.put("blogness.com", new Entry("blogness.com", "kevinhasadream", "12345"));
		save(test, "passwords.txt");
		Map<String, Entry> entries = load(new File("passwords.txt"));
		if (!entries.isEmpty()) {
			for (Entry e : entries.values()) {
				System.out.println(e);
			}
		} else {
			System.out.println("No entries.");
		}
	}

	public Map<String, Entry> load(File in) {
		Map<String, Entry> m = new HashMap<String, Entry>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(in));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {			
			String line = br.readLine();
			Entry e = new Entry();
			while (line != null) {
				if (line.startsWith("T")) {
					e.setTitle(line.substring(1));
				} else if (line.startsWith("U")) {
					e.setUsername(line.substring(1));
				} else if (line.startsWith("P")) {
					e.setPassword(line.substring(1));
				} else {
					if (e.hasFullFields()) {
						m.put(e.getTitle(), e);
						e = new Entry();
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Loaded successfully");
		return m;
	}

	public void save(Map<String, Entry> m, String fileName) {
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter(fileName);
			if (!m.isEmpty()) {
				for (Entry e : m.values()) {
					outFile.println("T" + e.title + "\n" + "U" +
							e.username + "\n" + "P" + e.password + "\n");
				}
			}
			if (outFile != null) outFile.close();
		} catch (FileNotFoundException /* | IOException */ e) {
			e.printStackTrace();
		}
		System.out.println("Saved successfully");
	}
}

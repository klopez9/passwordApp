package passwordApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Console version of password application.
 * 
 * There is a Map of saved entries. Entries consist
 * of the name of the website or application, the username, and the
 * password; possible other fields (to be added in the future)
 * might be date added, notes or comments, etc.
 * 
 * The program asks the user for input. The user can choose from several options:
 * * Add an entry
 * * Change an entry
 * * View the list of entries
 * * Delete an entry
 * * etc. (to be added)
 */
public class Terminal {
	
	static String c = "y";
	static Map<String, Entry> entries = new HashMap<String, Entry>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the Password App");
		Scanner input = new Scanner(System.in);
		boolean run = true;
		while (run) {
			System.out.print("1. Add or change an entry\n2. View entries\n" +
					"3. Delete an entry\n4. Quit: ");
			String option = input.nextLine().trim();
			
			while (option.equals("1")) {
				// ask user for title, username, and password
				System.out.print("Please enter the title of the entry: ");
				String title = input.nextLine();
				c = "y";
				while (entries.containsKey(title) && confirm(c)) {
					System.out.println("Entry already exists. Continue with overwrite? y/n: ");
					c = input.nextLine().toLowerCase();
					if (confirm(c)) {
						System.out.print("Please enter the title of the entry: ");
						title = input.nextLine();
						c = "y";
					}
				}
				System.out.print("Please enter this entry's username: ");
				String username = input.nextLine();
				System.out.print("Please enter this entry's password: ");
				String password = input.nextLine();
				// check to make sure same Entry is not already entered; otherwise, overwrite
				// add the entry
				entries.put(title, new Entry(title, username, password));
				System.out.print("Entry added: \n" + entries.get(title) +
						"\nPress 1 to add another entry, or\n" +
						"any other key to exit to main menu: ");
				option = input.nextLine();
			}

			while (option.equals("2")) {
				// iterate over the values of entries and print each element
				if (!entries.isEmpty()) {
					for (Entry e : entries.values()) {
						System.out.println(e);
					}
				} else {
					System.out.println("No entries.");
				}
				System.out.print("Press 2 to view entries again, or\n" +
						"any other key to exit to main menu: ");
				option = input.nextLine();
			}

			while (option.equals("3")) {
				// allow user to specify entry; display entry; ask if user is sure; delete
				String title = "";
				c = "n";
				while (!confirm(c)) {
					System.out.print("Please enter the title of the entry you wish to delete: ");
					title = input.nextLine();
					if (entries.get(title) != null) {
						System.out.print(entries.get(title) + "\nDelete this entry? y/n: ");
						c = input.nextLine().toLowerCase();
					}
				}
				entries.remove(title);
				System.out.print("Entry deleted\nPress 3 to delete another entry, or\n" +
						"any other key to exit to main menu: ");
				option = input.nextLine();
			}

			while (option.equals("4")) {
				c = "n";
				System.out.print("Are you sure you want to quit? y/n: ");
				c = input.nextLine().toLowerCase();
				if (confirm(c)) {
					System.out.println("Do you want to save your current entries? y/n: ");
					if (confirm(c)) {
						System.out.println("Enter a text file name: ");
						String name = input.nextLine();
						if (name.substring(name.length()-4, name.length()-1) != ".txt")
							name = name + ".txt";
						save(entries, name);
					}
				}
				option = "0";
			}

		}
		input.close();
	}

	private static void save(Map<String, Entry> entries2, String name) {
		// TODO Auto-generated method stub
		
	}

	public static boolean confirm(String s) {
		boolean retVal = false;
		if (s != null || s != "") {
			if (s.toLowerCase() == "y") {
				retVal = true;
			}
		}
		return retVal;
	}
	
	public Map<String, Entry> getMap() {
		return entries;
	}
	
}
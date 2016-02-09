package ca.bcit.comp2526.a1c.a1a;

import java.util.Scanner;

/**
 * Main.
 * 
 * @author Jia Qi Lee, Set 2A
 * @version 1.0
 */
public class Main {
    private String[] database;
    private Gui gui;
    Scanner input;

    /**
     * Default constructor for console version.
     */
    public Main() {
        database = new String[0];
        input = new Scanner(System.in);
    }
    
    /**
     * Constructor for gui version.
     * 
     * @param ui
     *            The graphic user interface
     */
    public Main(Gui ui) {
        database = new String[0];
        input = new Scanner(System.in);
        gui = ui;
    }

    /**
     * Reads a Person from the user interface 
     * and adds them to the database.
     * @param name the person to add 
     */
    public void add(final String name) {
        String[] temp = new String[database.length + 1];
        System.arraycopy(database, 0, temp, 0, database.length);
        temp[database.length] = name;
        database = temp;
    }

    /**
     * Reads a Person's name from the user interface and tries to find 
     * them in the database.
     * @param name the name of the person to find
     * @return the index of the person found or -1 if not found
     */
    public int search(final String name) {
        String name2;

        for (int pos = 0; pos < database.length; pos++) {
            Scanner extract = new Scanner(database[pos]);
            name2 = extract.next();
            extract.close();

            if (name.compareToIgnoreCase(name2) == 0) {
                return pos;
            }
        }
        return -1;
    }

    /**
     * Displays the requested person on the user interface.
     * @param pos the index position of the person to display
     */
    public void display(int pos) {
        String name;
        String phone;
        Scanner extract = new Scanner(database[pos]);
        name = extract.next();
        phone = extract.next();
        extract.close();
        System.out.printf("%-20s%-15s\n", name, phone);
    }
    
    /**
     * Displays the requested person on the user interface. Gui version.
     * @param pos the index position of the person to display
     */
    public void displayGui(int pos) {
        String person;
        String heading1 = "Name";
        String heading2 = "Phone";
        person = String.format("%-20s%-15s\n", heading1, heading2);
        person += database[pos];
        gui.display(person);
    }

    /**
     * <p>Displays the database table heading on the user interface.</p>
     */
    public void displayHeading() {
        String heading1 = "Name";
        String heading2 = "Phone";
        System.out.printf("%-20s%-15s\n", heading1, heading2);
    }

    /**
     * <p>Displays all people on the user interface.</p>
     */
    public void displayAll() {
        displayHeading();
        for (int i = 0; i < database.length; i++) {
            display(i);
        }
    }
    
    /**
     * <p>Displays all people on the user interface.Gui version</p>
     */
    public void displayAllGui() {
        String heading1 = "Name";
        String heading2 = "Phone";
        String persons;
        persons = String.format("%-20s%-15s\n", heading1, heading2);
        for (int i = 0; i < database.length; i++) {
            persons += database[i];
        }
        gui.display(persons);
    }

    /**
     * <p>Finds the Person's name from the user interface and tries to 
     * delete them from the database using the index position.</p>
     * @param name the name of person to remove
     * @return true if removed or false if failed
     */
    public boolean remove(final String name) {
        int pos = search(name);
        if (pos >= 0) {
            String[] temp = new String[database.length - 1];
            System.arraycopy(database, 0, temp, 0, pos);
            System.arraycopy(database, pos + 1, temp, pos, database.length - pos - 1);
            database = temp;
            return true;
        }
        return false;
    }

    /**
     * <p>Displays the text menu on the console.</p>
     */
    public void displayMenu() {
        System.out.println("\n\n\n1) Add");
        System.out.println("2) Delete");
        System.out.println("3) Search");
        System.out.println("4) Display All");
        System.out.println("5) Exit\n");
    }

    /**
     * <p>Awaits menu choice input from user and 
     * validates the input.</p>
     * @return the choice that the user has input
     */
    public int getChoice() {
        int choice = 4;// default
        boolean done = false;
        while (!done) {
            System.out.print("choice? ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                throw new IllegalArgumentException("Illegal input");
            }
            if (choice > 0 && choice <= 5) {
                done = true;
            } else {
                System.out.println("\nYour choice is incorrect, please try again");
            }
        }
        return choice;
    }

    /**
     * <p>Reads a Person from the user interface 
     * and adds them to the database.</p>
     */
    public void addPerson() {
        String name = "";
        String phone = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.print("\nEnter the persons phone number ");
            phone = input.next();
            System.out.println("");
        } catch (Exception e) {
            System.out.println("\nYour input is incorrect, please try again");
            return;
        }
        add(name + " " + phone);
    }
    
    /**
     * <p>Reads a Person from the graphic user interface 
     * and adds them to the database.</p>
     */
    public void addPersonGui() {
        add(gui.readPerson());
    }

    /**
     * <p>Removes a person from the database.</p>
     */
    public void deletePerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal input");
        }
        if (!remove(name)) {
            System.out.println("Could not delete " + name);
        } else {
            System.out.println(name + " was deleted successfully");
        }
    }
    
    /**
     * <p>Removes a person from the database for the Gui version.</p>
     */
    public void deletePersonGui() {
        String name = gui.readName();
        if (!remove(name)) {
            gui.displayErrorMsg("Could not delete \"" + name + "\"");
        } else {
            gui.displayMsg("\"" + name + "\"" + " was deleted successfully");
        }
    }

    /**
     * <p>Searches database for a person with input name.</p>
     */
    public void findPerson() {
        String name = "";
        try {
            System.out.print("Enter the persons name ");
            name = input.next();
            System.out.println("");
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal input!");
        }
        int pos = search(name);
        if (pos >= 0) {
            displayHeading();
            display(pos);
        } else {
            System.out.println("No such person!");
        }
    }
    /**
     * <p>Searches database for a person with input name. Gui Version.</p>
     */
    public void findPersonGui() {
        String name = gui.readName();
        int pos = search(name);
        if (pos >= 0) {
            displayGui(pos);
        } else {
            gui.displayMsg("No such person!");
        }
    }

    /**
     * <p>Processes the input and executes methods that 
     * correspond to the option.</p>
     */
    public void run() {
        int choice = 0;
        do {
            displayMenu();
            choice = getChoice();
            switch (choice) {
              case 1:
                  addPerson();
                  break;
              case 2:
                  deletePerson();
                  break;
              case 3:
                  findPerson();
                  break;
              case 4:
                  displayAll();
                  break;
              default:
                // should not get here
            }

        } while (choice != 5);
    }

    /**
     * The main method accepts command line arguments and drives the program.
     * 
     * @param args
     *            specify choice of console or gui
     */
    public static void main(String[] args) {       
        if (args.length > 0 && args[0].compareToIgnoreCase("gui") == 0) {
            Gui ui = new Gui();
            Main main = new Main(ui);
            ui.run(main);  
        } else {
            new Main().run();
        }    
    }
}

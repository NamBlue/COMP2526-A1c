package ca.bcit.comp2526.a1c.a1b;

/**
 * The Address Book.
 * 
 * @author Jia Qi Lee, Set 2A
 * @version 1.0
 */
public class AddressBook {
    private final Database database;
    private final UserInterface userInterface;

    /**
     * Constructor for objects of type AddressBook.
     * 
     * @param userInterface
     *            The user interface
     */
    public AddressBook(final UserInterface userInterface) {
        this.userInterface = userInterface;
        database = new Database();
    }

    /**
     * <p>Reads a Person from the user interface 
     * and adds them to the database.</p>
     */
    public void addPerson() {
        final Person person;

        person = userInterface.readPerson();
        if (person != null) {
            database.add(person);
        }
    }

    /**
     * <p>Finds the Person's name from the user interface and tries to 
     * delete them from the database.</p>
     */
    public void deletePerson() {
        final String name;

        name = userInterface.readName();

        if (!remove(name)) {
            userInterface.displayErrorMsg("Could not delete \"" + name + "\"");
        } else {
            userInterface.displayMsg("\"" + name + "\"" + " was deleted successfully");
        }
    }

    /**
     * <p>Reads a Person's name from the user interface and tries to find 
     * them in the database.</p>
     */
    public void findPerson() {
        final String name;
        final Person person;

        name = userInterface.readName();
        person = search(name);

        if (person != null) {
            display(person);
        } else {
            userInterface.displayErrorMsg("No such person");
        }
    }

    private boolean remove(final String name) {
        return (database.removeByName(name) != null);
    }

    private Person search(final String name) {
        return (database.findByName(name));
    }

    /**
     * <p>Displays all people on the user interface.</p>
     */
    public void displayAll() {
        userInterface.display(database.getAll());
    }

    private void display(final Person person) {
        userInterface.display(person);
    }
}

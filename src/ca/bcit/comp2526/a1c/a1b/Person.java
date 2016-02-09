package ca.bcit.comp2526.a1c.a1b;

/**
 * Person stores a name and phone number.
 * 
 * @author Jia Qi Lee, Set 2A
 * @version 1.0
 */
public class Person {
    private final String name;
    private final String phone;

    /**
     * Constructor for objects of type Person.
     * @param name A String
     * @param phone A String
     */
    public Person(final String name, final String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Returns name as a String.
     * @return the name of this Person
     */
    public String getName() {
        return (name);
    }

    /**
     * Returns phone number as a String.
     * @return the phone number of this Person
     */
    public String getPhoneNumber() {
        return (phone);
    }
}

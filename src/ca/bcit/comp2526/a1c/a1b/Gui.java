package ca.bcit.comp2526.a1c.a1b;

import static java.io.File.separator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Graphic User Interface.
 * 
 * @author Jia Qi Lee, Set 2A
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Gui extends JFrame implements UserInterface {
    private AddressBook addressBook;// interface to database
    private int choice;// users choice 1-5
    private Image bg = Toolkit.getDefaultToolkit().createImage("src" + separator + "ca"
            + separator + "bcit" + separator + "comp2526" + separator + "a1c"
            + separator + "a1b" + separator + "bg.jpg"); //The Background Image
    private JPanel menu;
    private JButton addb;
    private JButton delb;
    private JButton serb;
    private JButton allb;
    private JButton exitb;
    private final Color purple = new Color(210, 157, 199);
    private final int titlex = 30;//constant for title text position x
    private final int titley = 45;//constant for title text position y
    private final int title2x = 20;//constant for title text position x
    private final int title2y = 460;//constant for title text position y
    

    /**
     * Constructor for objects of type GUI. Constructs the GUI and adds a
     * keyboard listener to capture the user's choices from the menu.
     */
    public Gui() {
        super("Database ver.1.0");
        setContentPane(new DatabasePanel());
        setSize(900, 570);// fix window size
        setVisible(true);// make window visible
        setLayout(new BorderLayout());
        setResizable(false);
        
        menu = new ButtonMenu();      
        add(menu, BorderLayout.SOUTH);
        menu.setOpaque(false);
        addKeyListener(new KeyBoardInput());// listen to keyboard input
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }
    
    /**
     * Represents the panel for the Database display.
     */
    private final class DatabasePanel extends JPanel {
        /**
         * Displays the GUI when window requires repainting.
         * 
         * @param grapic
         *            Graphics - device context for the window to draw on
         */
        public void paintComponent(Graphics graphic) {
            displayMenu(graphic);
        }
        
        /**
         * Clears and draws the main menu on the window.
         * 
         * @param graphic
         *            Graphics - device context to allow drawing on this window
         */
        private void displayMenu(Graphics graphic) {
            graphic.drawImage(bg, 0, 0, this);
            graphic.setColor(purple);// set colour to draw with now to black
            graphic.setFont(new Font("TimesRoman", Font.BOLD, 30));
            graphic.drawString("Welcome to my Database!", titlex, titley);
            graphic.setFont(new Font("TimesRoman", Font.BOLD, 20));
            graphic.drawString("Please select an option or", title2x, title2y);
            graphic.drawString("press the corresponding number key:", title2x, title2y + 20);
        }
    }
    
    /**
     * Represents the panel for the Menu Buttons Group.
     */
    private class ButtonMenu extends JPanel {
        
        /**
         * Constructs the panel and adds Listeners to buttons.
         */
        private ButtonMenu() {
            addb = new JButton("1) Add Person");            
            addb.setBackground(purple);
            addb.setForeground(Color.white);
            addb.setFocusable(false);
            add(addb);
            addb.addActionListener(new ButtonListener());
            
            delb = new JButton("2) Delete Person");            
            delb.setBackground(purple);
            delb.setForeground(Color.white);
            delb.setFocusable(false);
            add(delb);
            delb.addActionListener(new ButtonListener());
            
            serb = new JButton("3) Search Person");            
            serb.setBackground(purple);
            serb.setForeground(Color.white);
            serb.setFocusable(false);
            add(serb);
            serb.addActionListener(new ButtonListener());
            
            allb = new JButton("4) Display All");            
            allb.setBackground(purple);
            allb.setForeground(Color.white);
            allb.setFocusable(false);
            add(allb);
            allb.addActionListener(new ButtonListener());
            
            exitb = new JButton("5) Exit Program");            
            exitb.setBackground(purple);
            exitb.setForeground(Color.white);
            exitb.setFocusable(false);
            add(exitb);
            exitb.addActionListener(new ButtonListener());
        }
        
        /**
         * Listener that executes methods corresponding to the button pressed.
         */
        private class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent pressed) {
                if (pressed.getSource() == addb) {
                    choice = 1;
                    evaluateChoice();
                } else if (pressed.getSource() == delb) {
                    choice = 2;
                    evaluateChoice();
                } else if (pressed.getSource() == serb) {
                    choice = 3;
                    evaluateChoice();
                } else if (pressed.getSource() == allb) {
                    choice = 4;
                    evaluateChoice();
                } else if (pressed.getSource() == exitb) {
                    choice = 5;
                    evaluateChoice();
                }       
            }
        }      
    }   

    /**
     * Displays the people records passed in.
     * 
     * @param people
     *            Person[] - records of people (from address book)
     */
    public void display(Person... people) {
        String msg = "";
        // create a string of all the enteries in the address book
        // no formating of the data - chose to keep it simple
        String heading1 = "Name";
        String heading2 = "Phone";
        String name = "";
        String phone = "";
        msg = String.format("%-20s%-15s\n", heading1, heading2);
        for (Person p : people) {
            name = p.getName();
            phone = p.getPhoneNumber();
            msg += String.format("%-20s%-15s\n", name, phone);
        }
        JOptionPane.showMessageDialog(this, msg, 
                "Address book entries", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Reads a Person's name using a dialog box.
     * 
     * @return String - name read in
     */
    public String readName() {

        final String name = JOptionPane.showInputDialog("Enter the persons name");

        return (name);

    }

    /**
     * Reads in a Person's data (name/phone) using two dialog boxes and creates
     * a Person object with the data.
     * 
     * @return Person - person data record
     */
    public Person readPerson() {
        final Person person;
        final String name;
        final String phone;
        // since we have a method to read the name already
        name = readName();
        phone = JOptionPane.showInputDialog("Enter the persons phone number");
        // make sure we have data to create a person
        if (name == null || phone == null) {
                                          
            return null;
        }
        person = new Person(name, phone);

        return (person);
    }

    /**
     * Sets the AddressBook to use so the GUI can communicate with it. Note that
     * since a GUI is event driven unlike the Console this method has limited
     * use here.
     * 
     * @param book
     *            AddressBook - interface to the database of Person records
     */
    public void run(AddressBook book) {
        addressBook = book;
    }

    /**
     * Invokes the appropriate method on the addressBook. When the user makes
     * their selection the Keyboard listener stores the selection value in data
     * member "choice" and then calls this method.
     */
    private void evaluateChoice() {

        switch (choice) {
          case 1:
              addressBook.addPerson();
              break;
          case 2:
              addressBook.deletePerson();
              break;
          case 3:
              addressBook.findPerson();
              break;
          case 4:
              addressBook.displayAll();
              break;
          case 5:
              System.exit(0);
              break;
          default:
            // should not get here
        }

    }

    /**
     * Displays a message on the title bar of the window.
     * 
     * @param msg
     *            String - non-error message to display
     */
    public void displayMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays an error message in a dialog box.
     * 
     * @param msg
     *            String - error msg to display
     */
    public void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /*
     * KeyBoardInput.
     *
     * A private (no one else needs access to this class) inner class (this
     * class needs access to the GUI to handle user selections) that listens for
     * keys pressed.
     *
     */
    private class KeyBoardInput extends KeyAdapter {

        /**
         * Responds when a key is pressed on the keyboard.
         * 
         * @param event
         *            KeyEvent - key pressed and other information
         */
        public void keyTyped(KeyEvent event) {
            // set the "choice" data member of the outer class GUI
            // to get the integer value, get the character value of the key
            // pressed, make it a string and ask the Integer class to parse it
            try {
                choice = Integer.parseInt("" + event.getKeyChar());
                // if it wasn't an integer key pressed then make an invalid
                // choice
            } catch (Exception except) {
                choice = -1;// this will result in nothing happening
            }
            evaluateChoice(); // GUI method to call the addressBook to perform
                              // task
        }
    }
}

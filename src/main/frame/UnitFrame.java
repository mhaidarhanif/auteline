/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

package main.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.java.BalanceInquiry;
import main.java.BankDatabase;
import main.java.CashDispenser;
import main.java.Deposit;
import main.java.DepositSlot;
import main.java.Keypad;
import main.java.Screen;
import main.java.Transaction;
import main.java.Withdrawal;

public class UnitFrame extends CommonFrame implements ActionListener {

  // panels on frame
  KeypadPanel panelKeypad = new KeypadPanel();
  JPanel panelScreen = new JPanel();
  JPanel panelCashDispenser = new JPanel();
  JPanel panelDepositSlot = new JPanel();

  // input components


  // variables corresponding user's conditions and actions
  private boolean userAuthenticated; // whether user is authenticated
  private int currentAccountNumber;  // current user's account number
  private Screen screen; // ATM's screen
  private Keypad keypad; // ATM's keypad
  private CashDispenser cashDispenser; // ATM's cash dispenser
  private DepositSlot depositSlot;     // ATM's deposit slot
  private BankDatabase bankDatabase;   // account information database

  // constants corresponding to main menu options
  private static final int BALANCE_INQUIRY = 1;
  private static final int WITHDRAWAL = 2;
  private static final int DEPOSIT = 3;
  private static final int EXIT = 4;

  // define unit interface with starting point
  public UnitFrame() {
    super("Unit Interface");
    userAuthenticated = false; // user is not authenticated to start
    currentAccountNumber = 0;  // no current account number to start
    screen = new Screen();     // create screen
    keypad = new Keypad();     // create keypad
    cashDispenser = new CashDispenser(); // create cash dispenser
    depositSlot = new DepositSlot();     // create deposit slot
    bankDatabase = new BankDatabase();   // create acct info database
  }

  // start ATM unit
  // welcome and authenticate user; then perform transactions
  public void run() {
    // loop while user is not yet authenticated
    while (!userAuthenticated) {
      screen.displayMessageLine("[i] Welcome to Auteline Bank ATM!");
      authenticateUser();
    }
    performTransactions();     // user is now authenticated
    userAuthenticated = false; // reset before next ATM session
    currentAccountNumber = 0;  // reset before next ATM session
    screen.displayMessageLine("[i] Thank you for banking with Auteline Bank!");
  }

  // attempts to authenticate user against database
  private void authenticateUser() {
    screen.displayMessageLine("[?] Please enter your account number: ");
    int accountNumber = keypad.getInput(); // input account number
    screen.displayMessageLine("[?] Enter your PIN: "); // prompt for PIN
    int pin = keypad.getInput(); // input PIN

    // set userAuthenticated to boolean value returned by database
    userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
    // check whether authentication succeeded
    if (userAuthenticated) {
      currentAccountNumber = accountNumber;
    } else {
      screen.displayMessage("[!] Invalid account number or PIN. Please try again.");
    }
  }

  // display the main menu and perform transactions
  private void performTransactions() {

    // local variable to store transaction currently being processed
    Transaction currentTransaction;
    boolean userExited = false; // user has not chosen to exit

    // loop while user has not chosen option to exit system
    while (!userExited) {
      // show main menu and get user selection
      int mainMenuSelection = displayMainMenu();
      // decide how to proceed based on user's menu selection
      switch (mainMenuSelection) {
        // user chose to perform one of three transaction types
        case BALANCE_INQUIRY:
        case WITHDRAWAL:
        case DEPOSIT: // initialize as new object of chosen type
          currentTransaction = createTransaction(mainMenuSelection);
          currentTransaction.execute(); // execute transaction
          break;
        case EXIT: // user chose to terminate session
          screen.displayMessageLine("[~] Exiting the system...");
          userExited = true; // end ATM session
          break;
        default: // user did not enter an integer from 1-4
          screen.displayMessageLine("[!] You did not enter a valid selection! Please try again.");
          break;
      }
    }
  }

  // display the main menu and return an input selection
  private int displayMainMenu() {
    screen.displayMessageLine("\n[Main menu]");
    screen.displayMessageLine("1 - View my balance");
    screen.displayMessageLine("2 - Withdraw cash");
    screen.displayMessageLine("3 - Deposit funds");
    screen.displayMessageLine("4 - Exit");
    screen.displayMessage("[?] Enter a choice: ");
    return keypad.getInput();
  }

  // return object of specified Transaction subclass
  private Transaction createTransaction(int type) {
    Transaction temp = null; // temporary Transaction variable
    // determine which type of Transaction to create
    switch (type) {
      case BALANCE_INQUIRY:
        temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
        break;
      case WITHDRAWAL:
        temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
        break;
      case DEPOSIT:
        temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
        break;
    }
    return temp; // return the newly created object
  }

  // call listener when there is an event
  @Override
  public void actionPerformed(ActionEvent event) {
    System.out.println("AutelineApp: event: " + event.getActionCommand()); // event logger
    // logic when specific action is performed
    repaint();
  }

}

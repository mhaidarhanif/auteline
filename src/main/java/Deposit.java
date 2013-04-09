/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// Deposit.java
// Represents a deposit ATM transaction

package main.java;

public class Deposit extends Transaction {

  private double amount; // amount to deposit
  private Keypad keypad; // reference to keypad
  private DepositSlot depositSlot; // reference to deposit slot
  private final static int CANCELED = 0; // constant for cancel option

  // Deposit constructor
  public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,
                 Keypad atmKeypad, DepositSlot atmDepositSlot) {
    // initialize superclass variables
    super(userAccountNumber, atmScreen, atmBankDatabase);
    // initialize references to keypad and deposit slot
    keypad = atmKeypad;
    depositSlot = atmDepositSlot;
  }

  // perform transaction
  public void execute() {
    BankDatabase bankDatabase = getBankDatabase(); // get reference
    Screen screen = getScreen(); // get reference
    amount = promptForDepositAmount(); // get deposit amount from user
    // check whether user entered a deposit amount or canceled
    if (amount != CANCELED) {
      // request deposit envelope containing specified amount
      screen.displayMessage("[?] Please insert a deposit envelope containing ");
      screen.displayDollarAmount(amount);
      screen.displayMessageLine(".");
      // receive deposit envelope
      boolean envelopeReceived = depositSlot.isEnvelopeReceived();
      // check whether deposit envelope was received
      if (envelopeReceived) {
        screen.displayMessageLine("[i] Your envelope has been received.\n" +
                                  "    NOTE: The money just deposited will not\n" +
                                  "    be available until we verify the amount of any\n" +
                                  "    enclosed cash and your checks clear.");
        // credit account to reflect the deposit
        bankDatabase.credit(getAccountNumber(), amount);
      } else { // deposit envelope not received
        screen.displayMessageLine("[~] You did not insert an envelope,\n" +
                                  "    so the ATM has canceled your transaction.");
      }
    } else {
      screen.displayMessageLine("[~] Canceling transaction...");
    }
  }

  // prompt user to enter a deposit amount in cents
  private double promptForDepositAmount() {
    Screen screen = getScreen(); // get reference to screen
    // display the prompt
    screen.displayMessage("[?] Please enter a deposit amount in\n" +
                          "    CENTS (or 0 to cancel): ");
    int input = keypad.getInput(); // receive input of deposit amount
    // check whether the user canceled or entered a valid amount
    if (input == CANCELED) {
      return CANCELED;
    } else {
      return (double) input / 100; // return dollar amount
    }
  }

}

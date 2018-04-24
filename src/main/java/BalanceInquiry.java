/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// BalanceInquiry.java
// Represents a balance inquiry ATM transaction

package main.java;

public class BalanceInquiry extends Transaction {

  // BalanceInquiry constructor
  public BalanceInquiry(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
    super(userAccountNumber, atmScreen, atmBankDatabase);
  }

  // performs the transaction
  public void execute() {
    // get references to bank database and screen
    BankDatabase bankDatabase = getBankDatabase();
    Screen screen = getScreen();
    // get the available balance for the account involved
    double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
    // get the total balance for the account involved
    double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
    // display the balance information on the screen
    screen.displayMessageLine("\n[i] Balance Information:");
    screen.displayMessage(" |  Available balance: ");
    screen.displayDollarAmount(availableBalance);
    screen.displayMessage("\n |  Total balance: ");
    screen.displayDollarAmount(totalBalance);
    screen.displayMessageLine("");
  }

}

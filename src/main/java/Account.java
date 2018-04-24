/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// Account.java
// Represents a bank account

package main.java;

public class Account {

  private int accountNumber; // account number
  private int pin; // PIN for authentication
  private double availableBalance; // funds available for withdrawal
  private double totalBalance; // funds available + pending deposits

  // Account constructor initializes attributes
  public Account(int theAccountNumber, int thePIN,
                 double theAvailableBalance, double theTotalBalance) {
    accountNumber = theAccountNumber;
    pin = thePIN;
    availableBalance = theAvailableBalance;
    totalBalance = theTotalBalance;
  }

  // determines whether a user-specified PIN matches PIN in Account
  public boolean validatePIN(int userPIN) {
    return userPIN == pin ? true : false;
  }

  // returns available balance
  public double getAvailableBalance() {
    return availableBalance;
  }

  // returns the total balance
  public double getTotalBalance() {
    return totalBalance;
  }

  // credits an amount to the account
  public void credit(double amount) {
    totalBalance += amount;
  }

  // debits an amount from the account
  public void debit(double amount) {
    availableBalance -= amount;
    totalBalance -= amount;
  }

  // returns account number
  public int getAccountNumber() {
    return accountNumber;
  }

}

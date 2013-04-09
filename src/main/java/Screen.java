/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// Screen.java
// Represents the screen of the ATM

package main.java;

public class Screen {

  // display a message without a carriage return
  public void displayMessage(String message) {
    System.out.print(message);
  }

  // display a message with a carriage return
  public void displayMessageLine(String message) {
    System.out.println(message);
  }

  // displays a dollar amount
  public void displayDollarAmount(double amount) {
    System.out.printf("$%,.2f", amount);
  }

  // get message with a carriage return
  public String getMessage(String message) {
    return message;
  }

  // get dollar amount
  public String getDollarAmount(double amount) {
    return String.format("$%,.2f", amount);
  }

}

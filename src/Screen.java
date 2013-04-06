/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// Screen.java
// Represents the screen of the ATM

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

}

/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// Keypad.java
// Represents the keypad of the ATM

package main.java;

import java.util.Scanner;

public class Keypad {

  private Scanner input; // reads data from the command line

  // no-argument constructor initializes the Scanner
  public Keypad() {
    input = new Scanner(System.in);
  }

  // return an integer value entered by user
  public int getInput() {
    return input.nextInt(); // we assume that user enters an integer
  }

}

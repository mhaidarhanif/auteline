/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

// ATMTest.java
// Driver program to test ATM program

package auteline;

public class ATMTest {

  // main method creates and runs the ATM
  public static void main(String[] args) {
    Screen screen = new Screen();
    Keypad keypad = new Keypad();
    CashDispenser cashDispenser = new CashDispenser();
    DepositSlot depositSlot = new DepositSlot();
    BankDatabase bankDatabase = new BankDatabase();

    ATM mobileATM = new ATM(screen, keypad, cashDispenser, 
                            depositSlot, bankDatabase);
    mobileATM.run();
  }

}

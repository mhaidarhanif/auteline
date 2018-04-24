/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

package main.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class KeypadPanel extends JPanel {

  public KeypadPanel() {
    setLayout(new BorderLayout());

    // add display field
    display = new JTextField();
    add(display, "North");

    // create button panel
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(4, 3));

    // add digit buttons
    ActionListener listener = new DigitButtonListener();
    addButton("7", listener);
    addButton("8", listener);
    addButton("9", listener);
    addButton("4", listener);
    addButton("5", listener);
    addButton("6", listener);
    addButton("1", listener);
    addButton("2", listener);
    addButton("3", listener);
    addButton("0", listener);
    addButton(".", listener);

    // add clear entry button
    clearButton = new JButton("CE");
    buttonPanel.add(clearButton);
    clearButton.addActionListener
        (new ClearButtonListener());
    add(buttonPanel, "Center");
  }

  /**
   * Gets the value that the user entered.
   * @return the value in the text field of the keypad
   */
  public double getValue() {// modified by JD to issue error dialog
    while (display.getText().equals("")) {
      String UserInput = JOptionPane.showInputDialog
          ("Please enter the required input value:");
      display.setText(UserInput);
    }
    return Double.parseDouble(display.getText());
  }

  /**
   * Clear the display.
   */
  public void clear() {
    display.setText("");
  }

  /**
   * Adds a button to the button panel
   * @param label    the button label
   * @param listener the button listener
   */
  public void addButton(String label, ActionListener listener) {
    JButton button = new JButton(label);
    buttonPanel.add(button);
    button.addActionListener(listener);
  }

  private JPanel buttonPanel;
  private JButton clearButton;
  private JTextField display;

  private class DigitButtonListener implements ActionListener {
    // Get the button label
    public void actionPerformed(ActionEvent event) {
      // it is a digit or decimal point
      JButton source = (JButton) event.getSource();
      String label = source.getText();
      // don't add two decimal points
      if (label.equals(".") && display.getText().indexOf(".") != -1) {
        return;
      }
      display.setText(display.getText() + label);
    }
  }

  private class ClearButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      clear();
    }
  }

}

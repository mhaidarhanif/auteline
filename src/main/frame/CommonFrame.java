package main.frame;

/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

import java.awt.*;
import java.util.Enumeration;

import javax.swing.*;

public abstract class CommonFrame extends JFrame {

  JPanel panelHeading = new JPanel();

  // define common settings for all frame
  public CommonFrame(String heading) {
    super("Auteline - " + heading);

    setSize(600, 400);
    setResizable(false);
    setLocationRelativeTo(null);
    setLayout(new FlowLayout());
    setVisible(true);

    JLabel labelHeading = new JLabel(heading);
    panelHeading.add(labelHeading);
    add(panelHeading);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  // custom method to get text of the selected button
  public String getSelectedButtonText(ButtonGroup buttonGroup) {
    Enumeration<AbstractButton> buttons;
    for (buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
      AbstractButton button = buttons.nextElement();
      if (button.isSelected())
        return button.getText();
    }
    return null;
  }

}

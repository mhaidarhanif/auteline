package main.frame;

/**
 * SMTI06, 54411850, M Haidar Hanif
 * Task Five: Automated Teller Machine
 * Auteline | Simple ATM simulator with basic features
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UnitFrame extends CommonFrame implements ActionListener {

  // panels on frame
  JPanel panelInput = new JPanel();
  JPanel panelType = new JPanel();
  JPanel panelMore = new JPanel();
  JPanel panelButton = new JPanel();
  JPanel panelOutput = new JPanel();

  // input components
  JLabel labelSSNInput = new JLabel("SSN:");
  JTextField textSSNInput = new JTextField(8);
  JLabel labelNameFirst = new JLabel("First Name:");
  JTextField textNameFirst = new JTextField(15);
  JLabel labelNameLast = new JLabel("Last Name:");
  JTextField textNameLast = new JTextField(15);

  JLabel labelType = new JLabel("Employee Type:");
  ButtonGroupListener cTypes = new ButtonGroupListener();
  String eSalaried = "Salaried";
  String eHourly = "Hourly";
  String eCommission = "Commission";
  String eCommissionPlus = "Commission Plus";
  String ePieceWorker = "Piece Worker";
  JRadioButton cSalaried = new JRadioButton(eSalaried, true);
  JRadioButton cHourly = new JRadioButton(eHourly, false);
  JRadioButton cCommission = new JRadioButton(eCommission, false);
  JRadioButton cCommissionPlus = new JRadioButton(eCommissionPlus, false);
  JRadioButton cPieceWorker = new JRadioButton(ePieceWorker, false);

  JLabel labelSalary = new JLabel("Weekly Salary ($):");
  JTextField textSalary = new JTextField();
  JLabel labelWage = new JLabel("Wage ($):");
  JTextField textWage = new JTextField();
  JLabel labelHours = new JLabel("Hours (0-168):");
  JTextField textHours = new JTextField();
  JLabel labelRate = new JLabel("Commission Rate (%):");
  JTextField textRate = new JTextField();
  JLabel labelSales = new JLabel("Gross Sales ($):");
  JTextField textSales = new JTextField();
  JLabel labelPieces = new JLabel("Pieces Produced (>0):");
  JTextField textPieces = new JTextField();

  // control components
  JButton buttonAddData = new JButton("Add");
  JButton buttonDeleteData = new JButton("Delete");
  JButton buttonClearData = new JButton("Clear");
  JButton buttonDisplayData = new JButton("Display");

  // output components
  JLabel labelSSNOutput = new JLabel("Registered SSN:");
  JTextField textSSNOutput = new JTextField(10);
  JLabel labelNameFull = new JLabel("Full Name:");
  JTextField textNameFull = new JTextField(31);
  JLabel labelTypeOutput = new JLabel("Employee Type:");
  JTextField textTypeOutput = new JTextField(16);
  JLabel labelEarningsOutput = new JLabel("Earnings ($):");
  JTextField textEarningsOutput = new JTextField(31);

  // reuse variables
  private String ssnInput;
  private String firstName;
  private String lastName;
  private String cType;
  private String eType;
  private double eEarnings;
  private String warnSSN = "Type SSN (Social Security Number)!";
  private String warnName = "Type first name with/without last name!";
  private String warnMore = "Type all required additional information!";

  double iSalary;
  double iWage;
  double iHours;
  double iRate;
  double iSales;
  int iPieces;

  // define dashboard for special frame
  public DashboardFrame() {
    super("Dashboard");

    // define event action listener
    cSalaried.addActionListener(this);
    cHourly.addActionListener(this);
    cCommission.addActionListener(this);
    cCommissionPlus.addActionListener(this);
    cPieceWorker.addActionListener(this);

    cSalaried.setActionCommand("Salaried");
    cHourly.setActionCommand("Hourly");
    cCommission.setActionCommand("Commission");
    cCommissionPlus.setActionCommand("Commission Plus");
    cPieceWorker.setActionCommand("Piece Worker");

    buttonAddData.addActionListener(this);
    buttonDeleteData.addActionListener(this);
    buttonClearData.addActionListener(this);

    buttonAddData.setActionCommand("addData");
    buttonDeleteData.setActionCommand("deleteData");
    buttonClearData.setActionCommand("clearData");
    buttonDisplayData.setActionCommand("displayData");
    buttonDisplayData.setEnabled(false); // temporary disabled

    // put components on panel
    panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.Y_AXIS));
    panelInput.add(labelSSNInput);
    panelInput.add(textSSNInput);
    panelInput.add(labelNameFirst);
    panelInput.add(textNameFirst);
    panelInput.add(labelNameLast);
    panelInput.add(textNameLast);

    panelType.setLayout(new BoxLayout(panelType, BoxLayout.Y_AXIS));
    panelType.add(labelType);
    cTypes.add(cSalaried);
    cTypes.add(cHourly);
    cTypes.add(cCommission);
    cTypes.add(cCommissionPlus);
    cTypes.add(cPieceWorker);
    panelType.add(cSalaried);
    panelType.add(cHourly);
    panelType.add(cCommission);
    panelType.add(cCommissionPlus);
    panelType.add(cPieceWorker);

    panelMore.setLayout(new BoxLayout(panelMore, BoxLayout.Y_AXIS));
    panelMore.add(labelSalary);
    panelMore.add(textSalary);
    panelMore.add(labelWage);
    panelMore.add(textWage);
    panelMore.add(labelHours);
    panelMore.add(textHours);
    panelMore.add(labelRate);
    panelMore.add(textRate);
    panelMore.add(labelSales);
    panelMore.add(textSales);
    panelMore.add(labelPieces);
    panelMore.add(textPieces);
    textSalary.setEditable(true);
    textWage.setEditable(false);
    textHours.setEditable(false);
    textRate.setEditable(false);
    textSales.setEditable(false);
    textPieces.setEditable(false);

    panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
    panelButton.add(buttonAddData);
    panelButton.add(buttonDeleteData);
    panelButton.add(buttonClearData);
    panelButton.add(buttonDisplayData);

    panelOutput.setLayout(new BoxLayout(panelOutput, BoxLayout.Y_AXIS));
    panelOutput.add(labelSSNOutput);
    panelOutput.add(textSSNOutput);
    panelOutput.add(labelNameFull);
    panelOutput.add(textNameFull);
    panelOutput.add(labelTypeOutput);
    panelOutput.add(textTypeOutput);
    panelOutput.add(labelEarningsOutput);
    panelOutput.add(textEarningsOutput);
    textSSNOutput.setEditable(false);
    textNameFull.setEditable(false);
    textTypeOutput.setEditable(false);
    textEarningsOutput.setEditable(false);

    // put panels on frame
    add(panelInput);
    add(panelType);
    add(panelMore);
    add(panelButton);
    add(panelOutput);
  }

  // get number input in text input
  // also define exception if input is not number
  private void getInputAll() {
    ssnInput = textSSNInput.getText();
    firstName = textNameFirst.getText();
    lastName = textNameLast.getText();
    eType = getSelectedButtonText(cTypes);
    eEarnings = getEarnings();
    try {
      textSSNOutput.setText(ssnInput);
      textNameFull.setText(firstName + " " + lastName);
      textTypeOutput.setText(eType);
      textEarningsOutput.setText(eEarnings + "");
    } catch (Exception ex) {
      warnAllInput();
    }
  }

  // give warning message in SSN output if SSN input is empty
  private void warnSSNInput() {
    textSSNOutput.setText(warnSSN);
  }

  // give warning message in full name output if first and/or last name is empty
  private void warnNameInput() {
    textNameFull.setText(warnName);
  }

  // give warning message in earnings output if more input is empty
  private void warnMoreInput() {
    textEarningsOutput.setText(warnMore);
  }

  // give all warning message
  private void warnAllInput() {
    warnSSNInput();
    warnNameInput();
    warnMoreInput();
  }

  // clear all input in text input
  private void clearInputAll() {
    textSSNInput.setText("");
    textNameFirst.setText("");
    textNameLast.setText("");
    textSalary.setText("");
    textWage.setText("");
    textHours.setText("");
    textRate.setText("");
    textSales.setText("");
    textPieces.setText("");
  }

  // clear all output in text output
  private void clearOutputAll() {
    textSSNOutput.setText("");
    textNameFull.setText("");
    textTypeOutput.setText("");
    textEarningsOutput.setText("");
  }

  // clear all input & output in text input & output
  private void clearInputOutputAll() {
    clearInputAll();
    clearOutputAll();
  }

  // get earnings based on employee type
  private double getEarnings() {
    try {
      if (eType == eSalaried) {
        iSalary = Double.parseDouble(textSalary.getText());
        Employee thisEmployee = new SalariedEmployee(ssnInput, firstName, lastName, iSalary);
        return thisEmployee.earnings();
      } else if (eType == eHourly) {
        iWage = Double.parseDouble(textWage.getText());
        iHours = Double.parseDouble(textHours.getText());
        Employee thisEmployee = new HourlyEmployee(ssnInput, firstName, lastName, iWage, iHours);
        return thisEmployee.earnings();
      } else if (eType == eCommission) {
        iSales = Double.parseDouble(textSales.getText());
        iRate = Double.parseDouble(textRate.getText());
        Employee
            thisEmployee =
            new CommissionEmployee(ssnInput, firstName, lastName, iSales, iRate);
        return thisEmployee.earnings();
      } else if (eType == eCommissionPlus) {
        iSales = Double.parseDouble(textSales.getText());
        iRate = Double.parseDouble(textRate.getText());
        iSalary = Double.parseDouble(textSalary.getText());
        Employee thisEmployee =
            new CommissionPlusEmployee(ssnInput, firstName, lastName, iSales, iRate, iSalary);
        return thisEmployee.earnings();
      } else if (eType == ePieceWorker) {
        iPieces = Integer.parseInt(textPieces.getText());
        iWage = Double.parseDouble(textWage.getText());
        Employee thisEmployee = new PieceWorker(ssnInput, firstName, lastName, iPieces, iWage);
        return thisEmployee.earnings();
      } else {
        return 1;
      }
    } catch (Exception ex) {
      return 0;
    }
  }

  // reset all additional information text field
  public void resetMoreEditable() {
    textSalary.setEditable(false);
    textWage.setEditable(false);
    textHours.setEditable(false);
    textSales.setEditable(false);
    textRate.setEditable(false);
    textPieces.setEditable(false);
  }

  // listen employee type radio button when chosen
  public void cTypesActionPerformed(ActionEvent event) {
    if (event.getActionCommand().equals("Salaried")) {
      resetMoreEditable();
      textSalary.setEditable(true);
    } else if (event.getActionCommand().equals("Hourly")) {
      resetMoreEditable();
      textWage.setEditable(true);
      textHours.setEditable(true);
    } else if (event.getActionCommand().equals("Commission")) {
      resetMoreEditable();
      textSales.setEditable(true);
      textRate.setEditable(true);
    } else if (event.getActionCommand().equals("Commission Plus")) {
      resetMoreEditable();
      textSales.setEditable(true);
      textRate.setEditable(true);
      textSalary.setEditable(true);
    } else if (event.getActionCommand().equals("Piece Worker")) {
      resetMoreEditable();
      textPieces.setEditable(true);
      textWage.setEditable(true);
    }
  }

  // define listener action when specific button is clicked
  public void buttonActionPerformed(ActionEvent event) {
    if (event.getActionCommand().equals("addData")) {
      clearOutputAll();
      ssnInput = textSSNInput.getText();
      firstName = textNameFirst.getText();
      eEarnings = getEarnings();
      if (ssnInput.equals("") || (firstName.equals("")) || eEarnings == 0) {
        if (!firstName.equals("") && (!ssnInput.equals(""))) {
          warnMoreInput();
        } else if ((!firstName.equals("")) && (eEarnings != 0)) {
          warnSSNInput();
        } else if (!ssnInput.equals("") && (eEarnings != 0)) {
          warnNameInput();
        } else {
          warnAllInput();
        }
      } else {
        getInputAll();
      }
    } else if (event.getActionCommand().equals("deleteData")) {
      clearOutputAll();
    } else if (event.getActionCommand().equals("clearData")) {
      clearInputOutputAll();
    }
  }

  // call listener when there is an event
  @Override
  public void actionPerformed(ActionEvent event) {
    System.out.println("EmployApp: event: " + event.getActionCommand()); // event logger
    cTypesActionPerformed(event);
    buttonActionPerformed(event);
    repaint();
  }

}

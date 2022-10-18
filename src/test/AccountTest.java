package test;
import main.java.*;
import org.junit.jupiter.api.Test;
import static org.testng.AssertJUnit.assertEquals;

class AccountTest {

    @Test
    public void T001_ValidatePin_54321_true(){
        int accountNumber = 12345;
        int pin = 54321;
        double availableBalance = 1000;
        double totalBalance = 1200;
        boolean expected = true;

        Account acc = new Account(accountNumber, pin, availableBalance, totalBalance);
        boolean actual = acc.validatePIN(54321);
        assertEquals(expected, actual);
    }

    @Test
    public void T002_ValidatePin_12345_false(){
        int accountNumber = 12345;
        int pin = 54321;
        double availableBalance = 1000;
        double totalBalance = 1200;
        boolean expected = false;

        Account acc = new Account(accountNumber, pin, availableBalance, totalBalance);
        boolean actual = acc.validatePIN(12345);
        assertEquals(expected, actual);
    }

    @Test
    public void T003_getAvailableBalance_1000_1000(){
        int accountNumber = 12345;
        int pin = 54321;
        double availableBalance = 1000;
        double totalBalance = 1200;
        double expected = 1000;

        Account acc = new Account(accountNumber, pin, availableBalance, totalBalance);
        double actual = acc.getAvailableBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void T004_getTotalBalance_1200_1200(){
        int accountNumber = 12345;
        int pin = 54321;
        double availableBalance = 1000;
        double totalBalance = 1200;
        double expected = 1200;

        Account acc = new Account(accountNumber, pin, availableBalance, totalBalance);
        double actual = acc.getTotalBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void T005_credit_totalBalanceChange_1000_1000(){
        int accountNumber = 12345;
        int pin = 54321;
        double availableBalance = 1000;
        double totalBalance = 1200;
        double expected = 2200;

        Account acc = new Account(accountNumber, pin, availableBalance, totalBalance);
        acc.credit(1000);
        double actual = acc.getTotalBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void T006_credit_availableBalanceChange_1000_1000(){
        int accountNumber = 12345;
        int pin = 54321;
        double availableBalance = 1000;
        double totalBalance = 1200;
        double expected = 1000;

        Account acc = new Account(accountNumber, pin, availableBalance, totalBalance);
        acc.credit(1000);
        double actual = acc.getAvailableBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void T007_debit_totalBalanceChange_1000_1000(){
        int accountNumber = 12345;
        int pin = 54321;
        double availableBalance = 1000;
        double totalBalance = 1200;
        double expected = 950;

        Account acc = new Account(accountNumber, pin, availableBalance, totalBalance);
        acc.debit(250);
        double actual = acc.getTotalBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void T008_debit_availableBalanceChange_1000_1000(){
        int accountNumber = 12345;
        int pin = 54321;
        double availableBalance = 1000;
        double totalBalance = 1200;
        double expected = 750;

        Account acc = new Account(accountNumber, pin, availableBalance, totalBalance);
        acc.debit(250);
        double actual = acc.getAvailableBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void T009_getAccountNumber_12345_12345(){
        int accountNumber = 12345;
        int pin = 54321;
        double availableBalance = 1000;
        double totalBalance = 1200;
        int expected = 12345;

        Account acc = new Account(accountNumber, pin, availableBalance, totalBalance);
        int actual = acc.getAccountNumber();
        assertEquals(expected, actual);
    }
}
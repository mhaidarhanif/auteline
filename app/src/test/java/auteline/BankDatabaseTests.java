package auteline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BankDatabaseTests {

    BankDatabase db = new BankDatabase();

    @Test 
    public void T001_authenticateUser_12345_54321_true(){
        boolean expected = true;
        int account = 12345;
        int pin = 54321;
        boolean actual = db.authenticateUser(account, pin);

        assertEquals(expected, actual);
    }

    @Test 
    public void T002_authenticateUser_11111_54321_false(){
        boolean expected = false;
        int account = 1111;
        int pin = 54321;
        boolean actual = db.authenticateUser(account, pin);

        assertEquals(expected, actual);
    }

    
    @Test 
    public void T003_authenticateUser_54321_11111_false(){
        boolean expected = false;
        int account = 12345;
        int pin = 11111;
        boolean actual = db.authenticateUser(account, pin);

        assertEquals(expected, actual);
    }

    @Test 
    public void T004_getAvaiableBalance_12345_1000(){
        double expected = 1000;
        int account = 12345;

        double actual = db.getAvailableBalance(account);

        assertEquals(expected, actual , 0.001);
    }

    @Test 
    public void T005_getTotalBalance_12345_1200(){
        double expected = 1200;
        int account = 12345;

        double actual = db.getTotalBalance(account);

        assertEquals(expected, actual , 0.001);
    }

    @Test
    public void T006_credit_12345_100_1300(){
        double expected = 1300;
        int account = 12345;
        double amount = 100;

        db.credit(account, amount);

        double actual = db.getTotalBalance(account);

        assertEquals(expected, actual , 0.001);

    }

    @Test
    public void T007_debit_12345_100_1100(){
        double expected = 1100;
        int account = 12345;
        double amount = 100;

        db.debit(account, amount);

        double actual = db.getTotalBalance(account);

        assertEquals(expected, actual , 0.001);

    }
    
}

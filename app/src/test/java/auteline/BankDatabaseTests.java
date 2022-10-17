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
    public void T002_authenticateUser_54321_11111_false(){
        boolean expected = false;
        int account = 12345;
        int pin = 11111;
        boolean actual = db.authenticateUser(account, pin);

        assertEquals(expected, actual);
    }
    
}

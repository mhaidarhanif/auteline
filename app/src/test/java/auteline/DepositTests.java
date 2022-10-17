package auteline;

import org.junit.Test;
import static org.junit.Assert.*;

public class DepositTests {
    
    Screen screen = new Screen();
    BankDatabase Bdb = new BankDatabase();
    DepositSlot slot = new DepositSlot();    
    Keypad keypad = new Keypad();

    @Test
    public void T001_getAccountNumber_12345_12345(){
        int expected = 12345;
        Deposit deposit = new Deposit(expected, screen, Bdb, keypad, slot); 

        int actual = deposit.getAccountNumber();

        assertEquals(expected, actual);
    }

    @Test
    public void T002_getScreen_screen_screen(){
        int account = 12345;
        Screen expected = screen;
        Deposit deposit = new Deposit(account, screen, Bdb, keypad, slot); 

        Screen actual = deposit.getScreen();

        assertEquals(expected, actual);
    }

    @Test
    public void T003_getBankDatabase_Bdb_Bdb(){
        int account = 12345;
        BankDatabase expected = Bdb;
        Deposit deposit = new Deposit(account, screen, Bdb, keypad, slot); 

        BankDatabase actual = deposit.getBankDatabase();

        assertEquals(expected, actual);
    }


}

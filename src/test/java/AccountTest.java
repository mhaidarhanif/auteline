package main.java;
import org.junit.Test;


import static org.junit.Assert.*;

public class AccountTest {
    Account account = new Account(11111,22222,45000.21,40000.35);
    @Test
    public void validatePIN() throws Exception {
        assertTrue( account.validatePIN(22222));
    }

    @Test
    public void getAvailableBalance() throws Exception {
        assertEquals(45000.21, account.getAvailableBalance(), 0.0000001);
    }

    @Test
    public void getTotalBalance() throws Exception {
        assertEquals(40000.35, account.getTotalBalance(), 0.0000001);
    }

    @Test
    public void credit() throws Exception {
        account.credit(50.00);
        assertEquals(40050.35, account.getTotalBalance(), 0.0000001);
    }

    @Test
    public void debit() throws Exception {
        account.debit(50.00);
        assertEquals(39950.35, account.getTotalBalance(), 0.0000001);
        assertEquals(44950.21, account.getAvailableBalance(), 0.0000001);
    }


}
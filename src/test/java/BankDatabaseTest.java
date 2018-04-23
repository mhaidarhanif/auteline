package main.java;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankDatabaseTest {
    private final BankDatabase db = new BankDatabase();
    @Test
    public void authenticateUser() throws Exception {

        assertEquals( db.authenticateUser(12345,54321),true);
    }

    @Test
    public void getAvailableBalance() throws Exception {
        assertEquals(db.getAvailableBalance(12345), 1000, 0.0000001);
    }

    @Test
    public void getTotalBalance() throws Exception {
    }

    @Test
    public void credit() throws Exception {
        db.credit(12345,500.5);
        assertEquals(1700.5,db.getTotalBalance(12345),0.00001);
    }

    @Test
    public void debit() throws Exception {
    }

}
package auteline;

import org.junit.Test;
import static org.junit.Assert.*;


public class BalanceInquiryTests {
    
    Screen screen = new Screen();
    BankDatabase db = new BankDatabase();
    
    @Test(expected = Test.None.class)
    public void T001_BalanceInquriy_12345_screen_db_NoExceptions(){
        int account = 12345;
        BalanceInquiry bi = new BalanceInquiry(account, screen, db);
    }

    @Test(expected = Test.None.class)
    public void T002_execute_NoExceptions(){
        int account = 12345;
        BalanceInquiry bi = new BalanceInquiry(account, screen, db);

        bi.execute();
    }
}

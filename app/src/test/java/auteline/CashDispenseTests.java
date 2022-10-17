package auteline;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class CashDispenseTests {

    CashDispenser cash = new CashDispenser();

    //TODO: Unsure on how to assert this test 
    
    @Test
    public void T001_dispenseCash_100_5(){
        int amount = 100;
        cash.dispenseCash(amount);

        assertEquals(true, true);
    }

@Test
    public void T002_isSufficentCashAvaliable_100_true(){
        int amount = 100;
        boolean expected = true;

        boolean actual = cash.isSufficientCashAvailable(amount);

        assertEquals(expected, actual);
    }
    
@Test
    public void T003_isSufficentCashAvaliable_10020_false(){
        int amount = 10020;
        boolean expected = false;

        boolean actual = cash.isSufficientCashAvailable(amount);

        assertEquals(expected, actual);
    }

}

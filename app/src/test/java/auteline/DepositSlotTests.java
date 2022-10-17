package auteline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DepositSlotTests {

    @Test
    public void T001_isEnvolpeReceived(){
        DepositSlot slot = new DepositSlot();
        boolean expected = true;

        boolean actual = slot.isEnvelopeReceived();

        assertEquals(expected, actual);

    }

    
    
}

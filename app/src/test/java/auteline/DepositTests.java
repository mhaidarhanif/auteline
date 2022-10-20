package auteline;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class DepositTests { 
    final int ACCOUNT_NUMBER = 12345; 
    Screen mockScreen = mock(Screen.class);
    BankDatabase mockDatabase = mock(BankDatabase.class);
    DepositSlot mockSlot = mock(DepositSlot.class); 
    Keypad mockKeypad = mock(Keypad.class);
    Deposit deposit;

    @Before
    public final void setUp() {
        deposit = new Deposit(ACCOUNT_NUMBER, mockScreen, mockDatabase, mockKeypad, mockSlot);
    }
    

    @Test
    public void T001_getAccountNumber_12345_12345(){
        int expected = ACCOUNT_NUMBER;

        int actual = deposit.getAccountNumber();

        assertEquals(expected, actual);
    }

    @Test
    public void T002_getScreen_screen_screen(){
        Screen expected = mockScreen;

        Screen actual = deposit.getScreen();

        assertEquals(expected, actual);
    }

    @Test
    public void T003_getBankDatabase_Bdb_Bdb(){
        BankDatabase expected = mockDatabase;

        BankDatabase actual = deposit.getBankDatabase();

        assertEquals(expected, actual);
    }

    @Test
    public void T004_execute_noEnvelope_noDatabaseCredit() {
        when(mockSlot.isEnvelopeReceived()).thenReturn(false);

        deposit.execute();

        verify(mockDatabase, times(0)).credit(anyInt(), anyInt());
    }


    @Test
    public void T005_execute_desposit300_deposits300() {
        final int CENTS_DEPOSITED = 30000;
        int dollars_deposited = CENTS_DEPOSITED / 100;
        when(mockKeypad.getInput()).thenReturn(CENTS_DEPOSITED);
        when(mockSlot.isEnvelopeReceived()).thenReturn(true);

        deposit.execute();

        verify(mockDatabase, times(1)).credit(ACCOUNT_NUMBER, dollars_deposited);
    }
}

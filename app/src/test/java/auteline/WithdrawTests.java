package auteline;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class WithdrawTests {
    final int USERACCOUNT = 0;
    
    Screen mockScreen = mock(Screen.class);
    BankDatabase mockDatabase = mock(BankDatabase.class);
    Keypad mockKeypad = mock(Keypad.class);
    CashDispenser mockCashDispenser = mock(CashDispenser.class);
    Withdrawal withdrawal;

    @Before
    public final void setUp() {
        withdrawal = new Withdrawal(USERACCOUNT, mockScreen, mockDatabase, mockKeypad, mockCashDispenser);
    }

    @Test
    public void T001_exitCommand_noDispenseCash(){
        int userInput = 6; // Cancel transaction

        when(mockKeypad.getInput()).thenReturn(userInput);
        when(mockDatabase.getAvailableBalance(USERACCOUNT)).thenReturn(0.0);

        withdrawal.execute();
        
        verify(mockCashDispenser, times(0)).dispenseCash(anyInt());
    }

    @Test
    public void T002_haveAndWithdraw200_dispense200() {
        int withdraw200Action = 5;
        int cancel_action = 6;

        when(mockKeypad.getInput()).thenReturn(withdraw200Action, cancel_action);
        when(mockDatabase.getAvailableBalance(USERACCOUNT)).thenReturn(500.0, 0.0);
        when(mockCashDispenser.isSufficientCashAvailable(anyInt())).thenReturn(true);

        withdrawal.execute();
        
        verify(mockDatabase, times(1)).debit(USERACCOUNT, 200.0);
        verify(mockCashDispenser, times(1)).dispenseCash(200);
    }

    @Test
    public void T003_insufficientATMCash_screenDisplaysMessage() {
        final String EXPECTED = "[!] Insufficient cash available in the ATM.\n" +
                                "    Please choose a smaller amount.";

        int withdraw200Action = 5;
        int cancel_action = 6;

        when(mockKeypad.getInput()).thenReturn(withdraw200Action, cancel_action);
        when(mockDatabase.getAvailableBalance(USERACCOUNT)).thenReturn(500.0, 0.0);
        when(mockCashDispenser.isSufficientCashAvailable(anyInt())).thenReturn(false);

        withdrawal.execute();
        
        verify(mockDatabase, times(0)).debit(anyInt(), anyInt());
        verify(mockCashDispenser, times(0)).dispenseCash(anyInt());
        verify(mockScreen, times(1)).displayMessageLine(EXPECTED);
    }

    @Test
    public void T004_insufficientAccountCash_screenDisplaysMessage() {
        final String EXPECTED = "[!] Insufficient funds in your account!\n" +
                                "    Please choose a smaller amount.";
        int withdraw200Action = 5;
        int cancel_action = 6;

        when(mockKeypad.getInput()).thenReturn(withdraw200Action, cancel_action);
        when(mockDatabase.getAvailableBalance(USERACCOUNT)).thenReturn(0.0);

        withdrawal.execute();
        
        verify(mockDatabase, times(0)).debit(anyInt(), anyInt());
        verify(mockCashDispenser, times(0)).dispenseCash(anyInt());
        verify(mockScreen, times(1)).displayMessageLine(EXPECTED);
    }
}

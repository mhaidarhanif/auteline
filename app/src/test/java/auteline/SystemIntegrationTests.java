package auteline;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class SystemIntegrationTests {
    Keypad mockKeypad = mock(Keypad.class);
    Screen mockScreen = mock(Screen.class);

    CashDispenser cashDispenser = new CashDispenser();
    DepositSlot depositSlot = new DepositSlot();
    BankDatabase bankDatabase = new BankDatabase();

    ATM atm;

    @Before
    public final void setUp() {
        atm = new ATM(mockScreen, mockKeypad, cashDispenser, depositSlot, bankDatabase);
    }

    @Test
    public void SIT001_getBalance() {
        // Arrange
        when(mockKeypad.getInput()).thenReturn(12345, 54321, 1, 4);

        // Act
        atm.run();

        // Assert
        verify(mockScreen, times(1)).displayDollarAmount(1000.0);
    }

    // TODO
    // @Test
    // public void SIT002_getBalanceWrongMenuInput() {
    //     // Arrange
    //     when(mockKeypad.getInput()).thenReturn(12345, 54321, 5, 4);

    //     // Act
    //     atm.run();

    //     // Assert
    //     verify(mockScreen, times(1)).displayMessage(null);(1000.0);
    // }

    @Test
    public void SIT003_withdraw100Dollars() {
        // Arrange
        when(mockKeypad.getInput()).thenReturn(12345, 54321, 2, 4, 1, 4);

        // Act
        atm.run();

        // Assert
        verify(mockScreen, times(1)).displayDollarAmount(900.0);
    }

    @Test
    public void SIT004_withdrawInsufficientFunds() {
        // Arrange
        when(mockKeypad.getInput()).thenReturn(12345, 54321, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 6, 4);

        // Act
        atm.run();

        // Assert
        verify(mockScreen, times(1))
            .displayMessageLine("[!] Insufficient funds in your account!\n" +
                                "    Please choose a smaller amount.");
    }
}

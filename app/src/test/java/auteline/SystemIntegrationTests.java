package auteline;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;

public class SystemIntegrationTests {
    // Mocked classes (ATM's dependencies)
    Keypad mockKeypad = mock(Keypad.class);
    Screen mockScreen = mock(Screen.class);

    // Real production classes (ATM's dependencies)
    CashDispenser cashDispenser = new CashDispenser();
    DepositSlot depositSlot = new DepositSlot();
    BankDatabase bankDatabase = new BankDatabase();

    // Classes that are indirectly tested
    // - Account (all tests)
    // - BalanceInquiry (SIT001 & SIT002)
    // - Transaction (SIT003 and SIT004)
    // - Withdrawal (SIT003 and SIT004)

    // Tested class (also production)
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


    @Test
    // Authenticating
    // Viewing balance (enter input, contact database)
    public void SIT002_getBalanceWrongMenuInput() {
        // Arrange
        when(mockKeypad.getInput())
            .thenReturn(12345, 54321)                 // Login
            .thenThrow(InputMismatchException.class)    // Simulates non-integer input
            .thenReturn(4);     // Exit command

        // Act
        atm.run();

        // Assert
        verify(mockScreen, times(1)).displayMessageLine("Please enter an integer number.");
    }

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

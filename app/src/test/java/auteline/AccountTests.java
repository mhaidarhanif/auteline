package auteline;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTests {
	@Test
	public void T001_Account_12345_1234_100_100_true() {
		int accountNumber = 12345;
		int pin = 1234;
		double avalBalance = 100.00;
		double totalBalance = 100.00;
		boolean expected = true;
		boolean actual = true;

		Account act = new Account(accountNumber, pin, avalBalance, totalBalance);

		if(act.getAccountNumber() != accountNumber){
			actual = false;
        }

		if(!act.validatePIN(pin)){
			actual = false;
        }

		if(act.getAvailableBalance() != avalBalance){
			actual = false;
        }

		if(act.getTotalBalance() != totalBalance){
			actual = false;
        }
		
		assertEquals(expected, actual);

	}

	@Test
	public void T002_validatePin_11111_true(){
		int accountNumber = 12345;
		int pin = 11111;
		double avalBalance = 100;
		double totalBalance = 100;
		boolean expected = true;

		Account act = new Account(accountNumber, pin, avalBalance, totalBalance);

		boolean actual = act.validatePIN(11111);

		assertEquals(expected, actual);

	}
	@Test
	public void T003_validatePin_0512_false(){
		int accountNumber = 12345;
		int pin = 11111;
		double avalBalance = 100;
		double totalBalance = 100;
		boolean expected = false;

		Account act = new Account(accountNumber, pin, avalBalance, totalBalance);

		boolean actual = act.validatePIN(0512);

		assertEquals(expected, actual);

	}

	@Test
	public void T004_getBalance_100_100(){
		int accountNumber = 12345;
		int pin = 11111;
		double avalBalance = 100;
		double totalBalance = 100;
		boolean expected = false;

		Account act = new Account(accountNumber, pin, avalBalance, totalBalance);

		boolean actual = act.validatePIN(0512);

		assertEquals(expected, actual);

	}

    @Test
    public void T005_getAvaliableBalance_1000_1000(){
        int accountNumber = 12345;
		int pin = 11111;
		double avalBalance = 1000;
		double totalBalance = 1000;
		double expected = 1000;

		Account act = new Account(accountNumber, pin, avalBalance, totalBalance);
  
        double actual = act.getAvailableBalance();

        assertEquals(expected, actual, 0.001);  // The delta is the change between expected and double as doubles often have a floating point value

    }

    @Test
    public void T006_getTotalBalance_1000_1000(){
        int accountNumber = 12345;
		int pin = 11111;
		double avalBalance = 500;
		double totalBalance = 1000;
		double expected = 1000;

		Account act = new Account(accountNumber, pin, avalBalance, totalBalance);
  
        double actual = act.getTotalBalance();

        assertEquals(expected, actual, 0.001);  // The delta is the change between expected and double as doubles often have a floating point value

    }

    @Test
    public void T007_credit_100_200(){
        int accountNumber = 12345;
		int pin = 11111;
		double avalBalance = 100;
		double totalBalance = 100;
		double expected = 200;

		Account act = new Account(accountNumber, pin, avalBalance, totalBalance);
  
        act.credit(100);
        double actual = act.getTotalBalance();

        assertEquals(expected, actual, 0.001);  // The delta is the change between expected and double as doubles often have a floating point value

    }

    
    @Test
    public void T008_debit_200_100(){
        int accountNumber = 12345;
		int pin = 11111;
		double avalBalance = 200;
		double totalBalance = 200;
		double expected = 100;

		Account act = new Account(accountNumber, pin, avalBalance, totalBalance);
  
        act.debit(100);
        double actual = act.getTotalBalance();

        assertEquals(expected, actual, 0.001);  // The delta is the change between expected and double as doubles often have a floating point value

    }

    @Test
    public void T009_getAccountNumber_12345_1235(){
        int accountNumber = 12345;
		int pin = 11111;
		double avalBalance = 200;
		double totalBalance = 200;
		int expected = 12345;

		Account act = new Account(accountNumber, pin, avalBalance, totalBalance);
  
        int actual = act.getAccountNumber();

        assertEquals(expected, actual);  // The delta is the change between expected and double as doubles often have a floating point value

    }





}

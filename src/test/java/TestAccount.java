package test.java;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import main.java.Account;

public class TestAccount {

	public TestAccount() {
		int x = 2 + 2;
	}

	@Test
	public void testOne() {
		assertEquals(2, 2);
	}

	@Test
	public void testTwo() {
		Account a = new Account(1234, 1234, 2.0, 50.0);
	}
}
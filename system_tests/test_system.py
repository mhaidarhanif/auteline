import pexpect 

def __helper_getTotalBalance() -> pexpect.spawn:
    # Spawn the test via gradle.
    child = pexpect.spawn('gradle run -q', timeout=360)

    child.expect_exact('[i] Welcome to Auteline Bank ATM!')
    print (child.after)

    child.expect_exact('[?] Please enter your account number: ')
    print (child.after)
    child.sendline('12345')

    child.expect_exact('Enter your PIN:')
    print (child.after)
    child.sendline('54321')

    child.expect_exact("[Main menu]\r\n\
1 - View my balance\r\n\
2 - Withdraw cash\r\n\
3 - Deposit funds\r\n\
4 - Exit")
    print (child.after)

    child.sendline('1')
    child.expect_exact('[i] Balance Information:\r\n |  Available balance: ')
    print (child.after)
    child.expect(r'\$\d\,\d\d\d\.\d\d')
    print (child.after)

    child.expect_exact(' |  Total balance: ')
    print (child.after)
    child.expect(r'\$\d\,\d\d\d\.\d\d')
    print (child.after)

    return child

def __helper_exit(child: pexpect.spawn) -> pexpect.spawn:
    child.expect_exact("[Main menu]\r\n\
1 - View my balance\r\n\
2 - Withdraw cash\r\n\
3 - Deposit funds\r\n\
4 - Exit")
    child.sendline('4')
    return child
    

def test_basic():
    child: pexpect.spawn = __helper_getTotalBalance()
    
    child.kill(0)
    assert child.exitstatus == None

def test_exit():
    child: pexpect.spawn = __helper_getTotalBalance()
    child = __helper_exit(child)

    assert child.exitstatus == None
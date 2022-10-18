import pexpect 

def test_systemTest():
    #Need to create jar file for this to run, 
    #having trouble creating one without META-INF/MANIFEST.MF
    #spent a long time trying to make jar from command line 
    #with commands like jar cfm test.jar manifest.mf ATMTest.class
    child = pexpect.spawn('java -jar auteline.jar')

    child.expect_exact('[i] Welcome to Auteline Bank ATM!')
    print (child.after)

    child.expect_exact('[?] Please enter your account number: ')
    print (child.after)
    child.sendline('12345')

    child.expect_exact('Enter your PIN:')
    print (child.after)
    child.sendline('54321')

    child.expect_exact('[Main menu]\r\n\
1 - View my balance\r\n\
2 - Withdraw cash\r\n\
3 - Deposit funds\r\n\
4 - Exit')
    print (child.after)

    child.sendline('1')
    child.expect_exact('[i] Balance Information:\r\n |  Available balance: ')
    print (child.after)
    child.expect('\$\d\,\d\d\d\.\d\d')
    print (child.after)

    child.expect_exact(' |  Total balance: ')
    print (child.after)
    child.expect('\$\d\,\d\d\d\.\d\d')
    print (child.after)

    child.close()
    assert child.exitstatus == 0


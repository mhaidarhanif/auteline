package auteline;

import org.junit.Test;
import static org.junit.Assert.*;

public class ScreenTests {
    
    Screen screen = new Screen();

    @Test
    public void T001_getMessage_HelloWorld_HelloWorld(){
        String expected = "Hello World";

        String actual = screen.getMessage(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void T002_getDollarAmount_100_Dollersign100Decimal00(){
        double amount = 100;
        String expected = "$100.00";

        String actual = screen.getDollarAmount(amount);

        assertEquals(expected, actual);
    }

    @Test
    public void T003_getDollarAmount_55Decimal255_Dollersign55Decimal26(){
        double amount = 55.255;
        String expected = "$55.26";

        String actual = screen.getDollarAmount(amount);

        assertEquals(expected, actual);
    }

    @Test(expected = Test.None.class)
    public void T005_displayMessage_NoExpection() throws Exception{

        screen.displayMessage("Hello World");

    }

    @Test(expected = Test.None.class)
    public void T006_displayMessageLine_NoExpection() throws Exception{

        screen.displayMessageLine("Hello World");

    }

    @Test(expected = Test.None.class)
    public void T007_displayDollarAmount_NoExpection() throws Exception{

        screen.displayDollarAmount(100.00);

    }


}

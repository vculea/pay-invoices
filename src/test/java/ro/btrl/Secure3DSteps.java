package ro.btrl;

import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.table.Cell;
import com.sdl.selenium.web.table.Table;
import com.sdl.selenium.web.utils.Utils;
import cucumber.api.java.en.Then;
import org.fasttrackit.util.BankCardDetails;
import org.fasttrackit.util.TestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Secure3DSteps extends TestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(Secure3DSteps.class);

    private Secure3DPassword secure3DPassword = new Secure3DPassword();

    @Then("^I type \"([^\"]*)\" into BT 3DSecure password$")
    public void enterPassword(String password) throws Throwable {
        secure3DPassword.setPassword(password);
    }

    @Then("^I enter BT 3DSecure password$")
    public void enterPassword() throws Throwable {
        Cell cell = new Table().getCell(2, new Cell(1, "Suma:", SearchType.DEEP_CHILD_NODE_OR_SELF));
        cell.setResultIdx(2);
        String text = cell.getText();
        if(text != null && "".equals(text)){
            cell.ready(10);
            Utils.sleep(2000);
            text = cell.getText();
        }
        String sum = text.split(" ")[0];
        System.setProperty("sum", sum);
        BankCardDetails card = new BankCardDetails();
        secure3DPassword.setPassword(card.getPassword());
    }

    @Then("^I finalize payment on BT 3DSecure$")
    public void I_finalize_payment_on_PayU_site() throws Throwable {
        secure3DPassword.clickContinue();
    }
}

package ro.mymoney;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fasttrackit.util.TestBase;

public class MyMoneySteps extends TestBase {
    private Login login = new Login();
    private View view = new View();

    @Then("^I login on MyVirtual using \"([^\"]*)\"/\"([^\"]*)\"$")
    public void login(String user, String pass) {
        login.login(user, pass);
    }

    @When("^I add new insert \"([^\"]*)\"/\"([^\"]*)\"/\"([^\"]*)\"$")
    public void iAddNewInsert(String denum, String categ, String sub) {
        view.addInsert(denum, categ, sub, System.getProperty("sum").replaceAll(",","."));
    }
}

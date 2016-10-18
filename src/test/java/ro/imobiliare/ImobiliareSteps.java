package ro.imobiliare;

import com.sdl.selenium.web.utils.Utils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.fasttrackit.util.TestBase;

public class ImobiliareSteps extends TestBase {
    private LoginView loginView = new LoginView();
    private SearchView searchView = new SearchView();
    private ListView listView = new ListView();

    @Then("^I login on Imobiliare using \"([^\"]*)\"/\"([^\"]*)\"$")
    public void login(String user, String pass) {
        loginView.login(user, pass);
        Utils.sleep(2);
    }

    @And("^I select my search$")
    public void iSelectMySearch() {
        searchView.mySearch();
    }

    @And("^I view anunturile$")
    public void iViewAnunturile(){
        listView.getData();
    }
}

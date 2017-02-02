package ro.jira;

import com.sdl.selenium.utils.config.WebDriverConfig;
import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.button.InputButton;
import com.sdl.selenium.web.form.ComboBox;
import com.sdl.selenium.web.form.MultipleSelect;
import com.sdl.selenium.web.form.TextField;
import com.sdl.selenium.web.link.WebLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BoardView extends WebLocator {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardView.class);

    public BoardView() {
        setClasses("issue-view");
    }

    private WebLink moreLink = new WebLink(this).setId("opsbar-operations_more");
    private WebLink createSubTask = new WebLink().setId("create-subtask");

    private WebLocator createSubtaskDialog = new WebLocator().setId("create-subtask-dialog");
    private TextField summary = new TextField(createSubtaskDialog).setId("summary");
    private TextField assignee = new TextField(createSubtaskDialog).setId("assignee-field");
    private ComboBox activityCombo = new ComboBox(createSubtaskDialog).setClasses("select", "cf-select");
    private TextField originalEstimate = new TextField(createSubtaskDialog).setId("timetracking_originalestimate");
    private MultipleSelect team = new MultipleSelect(createSubtaskDialog, "Team");
    private InputButton create = new InputButton(createSubtaskDialog, "Create");

    public void createTasks(List<Tasks> tasks) {
        for (Tasks task : tasks) {
            WebDriverConfig.getDriver().get("https://jira.sdl.com/browse/LTLC-" + task.getStoryId());
            ready();
            moreLink.click();
            createSubTask.click();
            createSubtaskDialog.ready();
            summary.setValue(task.getNameTask());
            assignee.setValue(task.getUser());
            activityCombo.select(task.getActivity());
            originalEstimate.setValue(task.getHours());
            scrollToWebLocator(team);
            team.selectRows(SearchType.DEEP_CHILD_NODE_OR_SELF, "None", task.getTeam());
            create.click();
        }
    }

    public static void scrollToWebLocator(WebLocator element) {
        if (element.isElementPresent()) {
            WebLocator.getExecutor().executeScript("arguments[0].scrollIntoView(true);", element.currentElement);
        }
    }
}

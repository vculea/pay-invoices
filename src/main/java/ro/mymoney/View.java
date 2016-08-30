package ro.mymoney;

import com.sdl.selenium.extjs4.form.ComboBox;
import com.sdl.selenium.extjs4.form.TextField;
import com.sdl.selenium.extjs4.window.Window;
import com.sdl.selenium.extjs6.button.Button;
import com.sdl.selenium.extjs6.grid.Grid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class View {
    private static final Logger LOGGER = LoggerFactory.getLogger(View.class);

    public View() {}

    Grid grid = new Grid().setId("userlist");
    Button add = new Button(grid).setAttribute("data-qtip","Adaugă o înregistrare").setVisibility(true);

    Window addNew = new Window("Add New Row");
    TextField denumireField = new TextField(addNew).setLabel("Denumire:").setLabelPosition("/..//following-sibling::*//");
    ComboBox category = new ComboBox(addNew).setLabel("Categorie:").setLabelPosition("/..//following-sibling::*//");
    ComboBox subCategory = new ComboBox(addNew).setLabel("Subcategorie:").setLabelPosition("/..//following-sibling::*//");
//    DateField dateField = new DateField(addNew).setLabel("Data:").setLabelPosition("/..//following-sibling::*//");
    TextField sumaField = new TextField(addNew).setLabel("Suma:").setLabelPosition("/..//following-sibling::*//");
    Button saveButton = new Button(addNew, "Save");

    public boolean addInsert(String denum, String cat, String sub, String sum){
        add.ready(10);
        add.click();
        denumireField.setValue(denum);
        category.select(cat);
        subCategory.select(sub);
        sumaField.setValue(sum);
        return saveButton.click();
    }

    public static void main(String[] args) {
        View login = new View();
        LOGGER.debug(login.category.getXPath());
    }
}

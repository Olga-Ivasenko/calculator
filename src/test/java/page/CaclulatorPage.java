package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class CaclulatorPage {

    public CaclulatorPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

   @FindBy(id="divide")
   public WebElement divideButton;

    @FindBy(css = "[value=\"C\"]")
    public WebElement clearButton;

    @FindBy(id="multiply")
    public WebElement multiplyButton;

    @FindBy(id="subtract")
    public WebElement subtractButton;

    @FindBy(id="add")
    public WebElement addButton;

    @FindBy(css = "[value=\"=\"]")
    public WebElement equalsButton;

    @FindBy(css ="[value=\".\"]")
    public WebElement flPointButton;

    @FindBy(xpath = "//div[@id=\"display\"]/div[1]")
    public WebElement displayField;

    public WebElement number(int number) {
       return Driver.getDriver().findElement(By.cssSelector("[value=\""+number+"\"]"));
    }

}

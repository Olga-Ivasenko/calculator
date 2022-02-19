package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.CaclulatorPage;
import utils.Driver;
import utils.PropertiesReader;

import java.util.concurrent.TimeUnit;

public class CalculatorFunctionalityTest {

    CaclulatorPage calcPage;

    @BeforeMethod
    public void initiate() {
        Driver.getDriver().get(PropertiesReader.getProperty("production"));
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        calcPage = new CaclulatorPage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }

    @DataProvider
    public Object[][] testData() {
        Object[][] td = {
                {1, 5, "6", "-4", "5", "0.2"},
                {2, 4, "6", "-2", "8", "0.5"},
                {9, 3, "12", "6", "27", "3"},
                {7, 8, "15", "-1", "56", "0.875"},
                {6, 0, "6", "6", "0", "Error"}};
        return td;
    }


    @Test(priority = 1, dataProvider = "testData")
    public void addTest(Integer operand1, Integer operand2, String sum,
                        String difference, String product, String quotient) {
        calcPage.number(operand1).click();
        calcPage.addButton.click();
        calcPage.number(operand2).click();
        calcPage.equalsButton.click();
        String result = calcPage.displayField.getText();
        Assert.assertEquals(result, sum);
    }

    @Test(priority = 2, dataProvider = "testData")
    public void subtractTest(Integer operand1, Integer operand2, String sum,
                             String difference, String product, String quotient) {
        calcPage.number(operand1).click();
        calcPage.subtractButton.click();
        calcPage.number(operand2).click();
        calcPage.equalsButton.click();
        String result = calcPage.displayField.getText();
        Assert.assertEquals(result, difference);
    }
    @Test(priority = 3, dataProvider = "testData")
    public void multiplyTest(Integer operand1, Integer operand2, String sum,
                             String difference, String product, String quotient) {
        calcPage.number(operand1).click();
        calcPage.multiplyButton.click();
        calcPage.number(operand2).click();
        calcPage.equalsButton.click();
        String result = calcPage.displayField.getText();
        Assert.assertEquals(result, product);
    }
    @Test(priority = 4, dataProvider = "testData")
    public void divideTest(Integer operand1, Integer operand2, String sum,
                             String difference, String product, String quotient) {
        calcPage.number(operand1).click();
        calcPage.divideButton.click();
        calcPage.number(operand2).click();
        calcPage.equalsButton.click();
        String result = calcPage.displayField.getText();
        Assert.assertEquals(result, quotient);
    }
    @Test(priority = 5)
    public void floatingPointTest(){
        calcPage.number(0).click();
        calcPage.number(1).click();
        calcPage.divideButton.click();
        calcPage.number(0).click();
        calcPage.flPointButton.click();
        calcPage.number(0).click();
        calcPage.number(1).click();
        calcPage.equalsButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(calcPage.displayField.getText(),"100");
    }
    @Test(priority = 5)
    public void changeActionTest(){
        calcPage.number(0).click();
        calcPage.number(1).click();
        calcPage.divideButton.click();
        calcPage.addButton.click();
        calcPage.number(0).click();
        calcPage.flPointButton.click();
        calcPage.number(0).click();
        calcPage.number(1).click();
        calcPage.equalsButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(calcPage.displayField.getText(),"1.01");
    }
    @Test(priority = 6)
    public void clearButtonTest() {
        SoftAssert  anAssert = new SoftAssert();
        for(int i = 1; i<10; i++) {
            calcPage.number(i).click();}
            String display = calcPage.displayField.getText();
            anAssert.assertEquals(display,"123456789");
            calcPage.clearButton.click();
            display = calcPage.displayField.getText();
            anAssert.assertEquals(display,"0");
            anAssert.assertAll();
    }
}
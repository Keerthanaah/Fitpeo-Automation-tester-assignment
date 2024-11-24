import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class testcases {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait mywait= new WebDriverWait(driver,Duration.ofSeconds(30));
        //1.Navigate to the FitPeo Homepage:
        driver.get("https://www.fitpeo.com/");
        driver.manage().window().maximize();
        Actions act = new Actions(driver);
        //2.Navigate to the Revenue Calculator Page:
        WebElement revenuecalculator_link = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Revenue Calculator")));
        revenuecalculator_link.click();
        Thread.sleep(3000);
        //3.Scroll Down to the Slider section:
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement ele = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiBox-root css-19zjbfs']")));
        js.executeScript("arguments[0].scrollIntoView();", ele);
        Thread.sleep(1000);
        //4.Adjust the Slider:
        WebElement slider = driver.findElement(By.xpath("//span[@class='MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary MuiSlider-thumb MuiSlider-thumbSizeMedium MuiSlider-thumbColorPrimary css-1sfugkh']"));
        System.out.println("Default location of the slider is" + slider.getLocation());
        act.dragAndDropBy(slider, 93, 0).perform();
        slider.click();
        for (int i = 1; i <= 3; i++) {
            act.keyDown(Keys.ARROW_RIGHT).keyUp(Keys.ARROW_RIGHT).perform();
        }
        System.out.println("Location of the slider after moving to 820 is" + slider.getLocation());
        //Validation of the text field whether it shows the value '820' after adjusting the slider:
        WebElement textbox = driver.findElement(By.xpath("//input[@type='number']"));
        String updatedvalue = textbox.getAttribute("value");
        if (updatedvalue.equals("820")) {
            System.out.println("Slider moved to desired location successfully, " + updatedvalue);
        } else {
            System.out.println("Slider failed to move to desired location ");
        }
        //5.Update the Text Field:
        textbox.click();
        act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
        Thread.sleep(1500);
        textbox.sendKeys("560");
        Thread.sleep(1500);
        System.out.println("Location of the slider after moving to 560 is" + slider.getLocation());
        String slidervalue=driver.findElement(By.xpath("//input[@type='range']")).getAttribute("value");
        //6.Validate Slider Value:
        if(slidervalue.equals("560"))
        {
            System.out.println("validation successful: slider value is updated to 560");
        }
        else
        {
            System.out.println("validation failed: slider value is " +slidervalue +",expected is 560.");
        }
        // Updating the slider back to 820
        act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
        Thread.sleep(1500);
        textbox.sendKeys("820");
        Thread.sleep(1500);
        //7. Select CPT Codes:
        List<WebElement> checkboxes= driver.findElements(By.xpath("//input[@type='checkbox']"));
        for(int i=0;i<3;i++)
        {
            checkboxes.get(i).click();
        }
        WebElement eleme= driver.findElement(By.xpath("//body/div[@class='MuiBox-root css-3f59le']/div[@class='MuiBox-root css-rfiegf']/div[@class='MuiBox-root css-1p19z09']/div[1]"));
        WebElement elem= driver.findElement(By.xpath("(//input[@type='checkbox'])[8]"));
        js.executeScript("arguments[0].scrollIntoView();", eleme);
        Thread.sleep(1000);
        elem.click();
        //Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.
        WebElement headervalue=driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'][normalize-space()='$110700']"));
        String expected_value="$110700";
        String actual_value= headervalue.getText();
        if (expected_value.equals(actual_value))
        {
            System.out.println("Validation successful: Header shows the correct value: " +actual_value);
        }
        else
        {
            System.out.println("validation failed: Expected value is "+expected_value+ "but got "+actual_value);
        }
        Thread.sleep(3000);
        driver.quit();
        }
}
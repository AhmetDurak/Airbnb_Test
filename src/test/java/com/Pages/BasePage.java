package com.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Utilities.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class BasePage {
    Actions action = new Actions(Driver.get());
    boolean check_inAlreadyClicked = false;

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);

    }

    // LOCATORS
    @FindBy(css = "button._148dgdpk")
    public WebElement consentCookies;

    @FindBy(css = "div.cs3bjhu.dir.dir-ltr>button:nth-of-type(1)")
    public WebElement selectionWWW;

    @FindBy(css = ".ffc0w66.dir.dir-ltr:first-of-type")
    public WebElement where;

    @FindBy(css = "div.d9jpnbz.dir.dir-ltr>div:nth-of-type(3)>div>button")
    public WebElement selectItaly;

    @FindBy(css = "div.v1ykbue4.dir.dir-ltr")
    public WebElement when;

    @FindBy(css = "div._1l1u7cl>div")
    public List<WebElement> dateList;

    @FindBy(css = "div.c2frgdd.crbzydf.dir.dir-ltr")
    public WebElement who;

    @FindBy(css = "div._14iponau:first-of-type>div>div>button:last-child")
    public WebElement adult;

    @FindBy(css = "div._14iponau:nth-of-type(2)>div>div>button:last-child")
    public WebElement kids;

    @FindBy(css = "div._14iponau:nth-of-type(3)>div>div>button:last-child")
    public WebElement baby;

    @FindBy(css = "div._14iponau:last-of-type>div>div>button:last-child")
    public WebElement pet;

    @FindBy(css = "button.b134py57.b14gupvm.dir.dir-ltr")
    public WebElement search;

    @FindBy(css = "button[aria-label=Filter]")
    public WebElement filter;

    @FindBy(css = "input[name=Privatzimmer]")
    public WebElement specialRoom;

    @FindBy(css = "div._10toigd>main#site-content>div")
    public List<WebElement> filterElement;

    @FindBy(id = "filterItem--936168351377325455-checkbox-languages-1-row-checkbox")
    public WebElement english;

    @FindBy(xpath = "(//input[contains(@id,'language')])[1]")
    public WebElement getEnglish;

    @FindBy(xpath = "(//input[contains(@id,'amenities')])[1]")
    public WebElement wlan;

    @FindBy(css = "span>input[type='checkbox']")
    public List<WebElement> filterCheckBox;

    @FindBy(css = "footer>a")
    public WebElement showResult;

    @FindBy(css = "div.giajdwt>div.c4mnd7m.dir.dir-ltr")
    public List<WebElement> results;

    @FindBy(xpath = "//div[@class='_p03egf']//div[@class='_e296pg']/button")
    public WebElement check_in_out;

    @FindBy(xpath = "(//div[@class='_1lds9wb'])[2]/div/table/tbody/tr/td")
    public List<WebElement> checkDateList;

    @FindBy (xpath = "(//button[@data-testid=\"homes-pdp-cta-btn\"])[2]")
    public WebElement reserve;


    public void setSelectionWWW(String www) {
        int index = 0;
        switch (www) {
            case "where":
                index = 1;
                System.out.println("Where");
                break;
            case "when":
                index = 2;
                System.out.println("When");
                break;
            case "who":
                index = 3;
                System.out.println("Who");
                break;
            default:
                System.out.println("Wrong input");
        }
        WebElement wwwSelect = Driver.get().findElement(By.cssSelector("div.cs3bjhu.dir.dir-ltr>button:nth-of-type(" + index + ")"));

            action.click(wwwSelect).perform();

    }

    public void setDate(String month) {
        month = month.replace("month.", "");
        for (WebElement eachMonth : dateList) {
            if (eachMonth.getAttribute("id").contains(month)) {
                eachMonth.click();
            }
        }
    }

    // you can choose any checkbox by its name in filter
    public void setFilterCheckBox(WebDriverWait wait, String nameCheckBoxList) {
        nameCheckBoxList = nameCheckBoxList.replace(", ", ",");
        String[] list = nameCheckBoxList.split(",");
        System.out.println(Arrays.toString(list));

        // check whether the locator of the selected Element is present and wait until it is present
        String locator = "span>input[name='Japanisch']";
        try {
            wait.withTimeout(Duration.ofSeconds(4))
                    .until(ExpectedConditions.presenceOfElementLocated(new By.ByCssSelector(locator)));
        } catch (TimeoutException ignored) {
            System.out.println("Locator failure");
        }

        int index = 0;              // helps to start searching CB from last index position
        boolean startOver = false; // helps to iterate only once.
        boolean isFound;

        for (int i = 0; i < list.length; i++) {
            int filterCB_length = filterCheckBox.size();
            isFound = false;

            for (int j = index + 1; j < filterCB_length; j++) {
                BrowserUtilities.scrollToElement(filterCheckBox.get(j));

                if (filterCheckBox.get(j).getAttribute("name").equalsIgnoreCase(list[i])) {
                    if (!(filterCheckBox.get(j).isSelected())) filterCheckBox.get(j).click();
                    //System.out.println(filterCheckBox.get(j).getAttribute("name") + " is clicked!");
                    index = j;
                    startOver = false;
                    isFound = true;
                    break;
                } else if ((j == filterCB_length - 1) && !startOver) {// search checkboxes from the beginning and do that only one time
                    filterCB_length = index;
                    j = 1;
                    startOver = true;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (isFound) {
                System.out.println(list[i] + " is found");
            } else System.out.println(list[i] + " couldn't be found");
        }
    }

    public void setCheckDateList(int day) {
        //BrowserUtilities.waitFor(4);
        if (!check_inAlreadyClicked) {
            BrowserUtilities.waitForVisibility(check_in_out, 10);
            check_in_out.click();
            check_inAlreadyClicked = true;

        }
        for (int i = 0; i < checkDateList.size(); i++) {
            if (checkDateList.get(i).getText().equals("" + day)) {
                checkDateList.get(i).click();
            }
        }
    }
}































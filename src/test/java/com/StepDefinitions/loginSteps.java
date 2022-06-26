package com.StepDefinitions;

import com.Pages.airbnbFunctionalities;
import com.Utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class loginSteps {

    airbnbFunctionalities login = new airbnbFunctionalities();
    WebDriverWait wait = new WebDriverWait(Driver.get(), 10);

    @Given("landing home page")
    public void landing_home_page() {
        login.consentCookies.click();
    }

    @Then("select {string}")
    public void select(String www) {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        login.setSelectionWWW(www);
    }

    @Then("click {string}")
    public void click(String input) {
        switch (input) {
            case "italy":
                login.selectItaly.click();
                break;
            case "filter":
                login.filter.click();
                break;
            case "search":
                login.search.click();
                break;
            case "show result":
                login.showResult.click();
                break;
            case "reserve":
                login.reserve.click();
                break;
        }
        if (input.contains("month")) login.setDate(input);

    }

    @Then("select checkboxes like {string}")
    public void selectCheckboxesLike(String checkbox) throws InterruptedException {
        Thread.sleep(3000);
        login.setFilterCheckBox(wait, checkbox);
    }

    @Then("select result {int}")
    public void selectResult(int result) throws InterruptedException {
        WebElement selected = login.results.get(result - 1);
        //wait.until(ExpectedConditions.visibilityOf(selected));
        Thread.sleep(3000);
        selected.click();
        ArrayList<String> tabs = new ArrayList<>(Driver.get().getWindowHandles());
        Driver.get().switchTo().window(tabs.get(1));
    }

    @Then("select date {int}")
    public void selectDate(int date) {
        login.setCheckDateList(date);

    }
}




















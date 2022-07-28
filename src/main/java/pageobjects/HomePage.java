package pageobjects;

import actions.HomePageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.Wait;

import java.time.Duration;

import static utilities.Constant.HOME_PAGE_TITLE;

public class HomePage {
    private final WebDriver webDriver;
    private final HomePageActions homePageActions;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        homePageActions = new HomePageActions(webDriver);
    }

    By acceptPrivacy = By.xpath("//button[@title='Yes, I’m happy']");

    By frameElement = By.xpath("//iframe[@title='Iframe title']");

    By firstNewsArticle = By.xpath("//span[@class='js-headline-text']");

    public boolean acceptPrivacyMessage() {
        Wait.untilPageReadyState(webDriver, Duration.ofSeconds(30));
        homePageActions.acceptPrivacyMessage(frameElement, acceptPrivacy);
        return true;
    }
    public void validateIAmOnGuardianPage() {
        Assert.assertEquals(homePageActions.validateSiteTitle(), HOME_PAGE_TITLE);
    }

    public String fetchFirstNewsArticle() {
        return webDriver.findElements(firstNewsArticle).get(0).getText();
    }
}

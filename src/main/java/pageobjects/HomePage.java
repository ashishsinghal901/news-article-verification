package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Wait;

import java.time.Duration;

public class HomePage {
    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    By acceptPrivacy = By.xpath("//button[@title='Yes, I’m happy']");

    By frameElement = By.xpath("//iframe[@title='Iframe title']");

    By firstNewsArticle = By.xpath("//span[@class='js-headline-text']");


    public boolean acceptPrivacyMessage() {
        Wait.untilPageReadyState(webDriver, Duration.ofSeconds(30));
        webDriver.switchTo().frame(webDriver.findElement(frameElement));
        webDriver.findElement(acceptPrivacy).click();
        webDriver.switchTo().defaultContent();
        return true;
    }

    public String validateSiteTitle() {
        return webDriver.getTitle();
    }

    public String fetchFirstNewsArticle() {
        return webDriver.findElements(firstNewsArticle).get(0).getText();
    }
}

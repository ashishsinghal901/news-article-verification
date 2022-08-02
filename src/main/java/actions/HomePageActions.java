package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageActions {
    private final WebDriver webDriver;

    public HomePageActions(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void acceptPrivacyMessage(By frameElement, By acceptPrivacy) {
        webDriver.switchTo().frame(webDriver.findElement(frameElement));
        clickOnPrivacyMessage(acceptPrivacy);
        webDriver.switchTo().defaultContent();
    }

    public void clickOnPrivacyMessage(By acceptPrivacy) {
        webDriver.findElement(acceptPrivacy).click();
    }

    public String validateSiteTitle() {
        return webDriver.getTitle();
    }
}

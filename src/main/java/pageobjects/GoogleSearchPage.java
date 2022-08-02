package pageobjects;

import actions.GoogleSearchPageActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.Wait;

import java.time.Duration;

import static utilities.Constant.GOOGLE_URL;

public class GoogleSearchPage {

    private final WebDriver webDriver;
    private final GoogleSearchPageActions googleSearchPageActions;

    public GoogleSearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        googleSearchPageActions = new GoogleSearchPageActions(webDriver);
    }

    By inputSearch = By.name("q");
    By linkNewsWebsite = By.xpath("//div[@class='TbwUpd NJjxre']//cite");
    By linkNewsUrl = By.xpath("//h3/..//cite");
    By linkMetaData = By.xpath("//div[@class='VwiC3b yXK7lf MUxGbd yDYNvb lyLwlc lEBKkf']");

    public void navigateToGoogle() {
        webDriver.get(GOOGLE_URL);
        Wait.untilPageReadyState(webDriver, Duration.ofSeconds(45));
    }

    public void searchNewsArticle(String newsArticle) {
        googleSearchPageActions.searchArticleOnGoogle(inputSearch, newsArticle);
    }

    public void validateNewsOnOtherSource(String newsArticle, int noOfSources) {
        boolean status = googleSearchPageActions.validateNewsOnOtherSource(newsArticle, noOfSources, linkNewsWebsite, linkMetaData, linkNewsUrl);
        Assert.assertTrue(status, "News is not authentic");
    }
}
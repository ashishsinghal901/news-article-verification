package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Wait;

import java.time.Duration;
import java.util.List;

import static utilities.Constant.GOOGLE_URL;
import static utilities.Constant.GUARDIAN_URL;

public class GoogleSearchPage {

    private final WebDriver webDriver;

    public GoogleSearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    By inputSearch = By.name("q");
    By linkNewsWebsite = By.xpath("//div[@class='TbwUpd NJjxre']//cite");
    By linkNewsUrl = By.xpath("//h3/..//cite");
    By linkMetaData = By.xpath("//div[@class='VwiC3b yXK7lf MUxGbd yDYNvb lyLwlc lEBKkf']");

    public void navigateToGoogle() {
        webDriver.get(GOOGLE_URL);
        Wait.untilPageReadyState(webDriver,Duration.ofSeconds(45));
    }

    public void searchNewsArticle(String newsArticle) {
        webDriver.findElement(inputSearch).click();
        webDriver.findElement(inputSearch).sendKeys(newsArticle);
        webDriver.findElement(inputSearch).sendKeys(Keys.ENTER);
        Wait.untilPageReadyState(webDriver,Duration.ofSeconds(45));
    }

    public boolean validateNewsOnOtherSource(String newsArticle, int noOfSources) throws InterruptedException {
        String[] newsDetails = newsArticle.split(" ");
        List<WebElement> newsSourceSite = webDriver.findElements(linkNewsWebsite);
        int wordMatch = 0;
        int noOfArticle = 0;
        for (int i = 0; i < newsSourceSite.size(); i++) {
            if (!newsSourceSite.get(i).getText().contains(GUARDIAN_URL)) {
                for (int j = 0; j < newsDetails.length; j++) {
                    String metadata = webDriver.findElements(linkMetaData).get(i).getText().toLowerCase();
                    if (metadata.contains(newsDetails[j].toLowerCase())) {
                        wordMatch++;
                    }
                }
                if ((newsDetails.length / wordMatch) * 100 > 90) {
                    if (noOfArticle == noOfSources)
                        break;
                    noOfArticle++;
                    System.out.println("News is genuine, Found similar news on following Website: " + webDriver.findElements(linkNewsUrl).get(i).getText());
                }
            }
            wordMatch = 0;
        }
        return true;
    }
}
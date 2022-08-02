package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Wait;

import java.time.Duration;
import java.util.List;

import static utilities.Constant.GUARDIAN_URL;

public class GoogleSearchPageActions {

    private final WebDriver webDriver;

    public GoogleSearchPageActions(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Author: Ashish Singhal
     * This method will search article on google.
     * @param inputSearch: Google search bar locator
     * @param newsArticle: Article to search on google
     *
     */
    public void searchArticleOnGoogle(By inputSearch, String newsArticle) {
        webDriver.findElement(inputSearch).click();
        webDriver.findElement(inputSearch).sendKeys(newsArticle);
        webDriver.findElement(inputSearch).sendKeys(Keys.ENTER);
        Wait.untilPageReadyState(webDriver, Duration.ofSeconds(45));
    }

    /**
     * This method is used to search article on the google search result screen,
     * It validates guardian's new article in the meta data of the search site.
     * @param newsArticle: Guardian Article
     * @param noOfSources: No. of sources on which we have to validate the result
     * @param linkNewsWebsite
     * @param linkMetaData
     * @param linkNewsUrl
     * @return
     */
    public boolean validateNewsOnOtherSource(String newsArticle, int noOfSources, By linkNewsWebsite, By linkMetaData, By linkNewsUrl) {
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


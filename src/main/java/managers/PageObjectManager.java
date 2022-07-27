package managers;

import org.openqa.selenium.WebDriver;
import pageobjects.GoogleSearchPage;
import pageobjects.HomePage;


public class PageObjectManager {

    private final WebDriver webDriver;
    private HomePage homePage;
    private GoogleSearchPage googleSearchPage;

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(webDriver) : homePage;
    }

    public GoogleSearchPage getGoogleSearch() {
        return (googleSearchPage == null) ? googleSearchPage = new GoogleSearchPage(webDriver) : googleSearchPage;
    }
}

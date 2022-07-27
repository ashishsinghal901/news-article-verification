package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.GoogleSearchPage;
import pageobjects.HomePage;
import utilities.TestContext;

import static utilities.Constant.HOME_PAGE_TITLE;


public class GuardianNewsSteps {

    TestContext testContext;
    HomePage homePage;
    static String newsArticle;
    GoogleSearchPage googleSearchPage;

    public GuardianNewsSteps(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
        googleSearchPage = testContext.getPageObjectManager().getGoogleSearch();
    }

    @Given("I am on the Guardian website homepage")
    public void IAmOnTheGuardianWebsiteHomepage() {
        Assert.assertEquals(homePage.validateSiteTitle(), HOME_PAGE_TITLE);
    }

    @And("I accept the manage cooking popup")
    public void iAcceptTheManageCookingPopup() {
        homePage.acceptPrivacyMessage();
    }

    @Given("I fetch first news article from Guardian home page")
    public void iFetchFirstNewsArticleFromGuardianHomePage() {
        newsArticle = homePage.fetchFirstNewsArticle();
    }

    @When("I navigate to google")
    public void iNavigateToGoogle() {
        googleSearchPage.navigateToGoogle();
    }

    @And("I searched same news on google")
    public void iSearchSameNewsOnGoogle() {
        googleSearchPage.searchNewsArticle(newsArticle);
        System.out.println("Latest News on the The Guardian is: " + newsArticle);
    }

    @Then("I went to {int} sources and validate news on Guardian is valid")
    public void iWentToSourcesAndValidateNewsOnGuardianIsValid(int noOfSources) throws InterruptedException {
        googleSearchPage.validateNewsOnOtherSource(newsArticle, noOfSources);
    }
}

@gui
Feature: Guardian News Test
  AS a user I validate that news article present in the Guardian Web side is valid or not,
  Same news should be available on 2 other sources as well.

  Background:
    Given I am on the Guardian website homepage
    And I accept the manage cooking popup

  Scenario Outline: As a user I validate first news on the Guardian home page is valid on <noOfResources> resource
    Given I fetch first news article from Guardian home page
    When I navigate to google
    And I searched same news on google
    Then I went to <noOfResources> sources and validate news on Guardian is valid
    Examples:
      | noOfResources |  |
      | 1             |  |
      | 2             |  |
      | 3             |  |
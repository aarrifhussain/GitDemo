@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecoomerce Page

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Loggesd in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> nd submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation

    Examples: 
      | name  				| password 				| productName|
      | Kumbh@g.com 	| Abhishek@1 			| ZARA COAT 3|
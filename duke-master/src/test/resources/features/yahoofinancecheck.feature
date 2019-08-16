Feature: opening yahoofinance.com and check stock prices and 

Scenario: I am going to finane.yahoo.com and check stock market prices 
	Given user is finance.yahoo.com
	When user checks Dowjones value for theday
	And user checks enters fb in to pricecheck
	Then user gets the price of the stock and print out value
	 
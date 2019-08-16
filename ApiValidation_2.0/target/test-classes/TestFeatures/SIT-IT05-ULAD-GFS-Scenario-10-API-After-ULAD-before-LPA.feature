@SIT-IT06-ULAD-GFS-Scenario-10 @GFS-Scenarios-ULAD @B-110539  @SIT-IT05 @SIT-ACEAPI20 @Regression_SIT
Feature: GFS Scenario-10-API after ULAD but before LPA and vice versa

Background: Clear previously posted GFS data from database
	Given Remove "GFS" MongoDB documents with the following values
		| addressLineText		| postalCode		|
		| 1 Fred Ave			| 44444				|
		| 2 Fred Ave			| 44444				|
		| 3 Fred Ave			| 44444				|

Scenario: GFS Scenario-10-API after ULAD but before LPA
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|987654		|LPAv2	|1234	|1		|1 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|987654		|LPAv2	|2019-06-05	|1234	|1		|1 Fred Ave	|250	|250	|Not Eligible	|Ineligible	|ApplicationProcessing	|100	|
	
	Given User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|ACEAPIID	|Address	|HVE	|ACEDecision	|
		|123456		|ACEAPI	|2019-06-06	|2			|1 Fred Ave	|275	|Eligible		|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|3		|1 Fred Ave	|
	#No grandfathered HVE data
	#GFS provides the PV from 6/5 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|PV 	|
		|Data Not Found		|100	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-08	|1234	|3		|1 Fred Ave	|300	|300	|Not Eligible	|Ineligible	|ApplicationProcessing	|110	|
		
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPA	|1234	|4		|1 Fred Ave	|
	#ACE GFS grandfathers the $275 HVE from 6/6 ACEAPI transaction
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|HVE 	|ErrorMessagePV	|
		|275	|Data Not Found	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPA	|2019-06-09	|1234	|4		|1 Fred Ave	|275	|300	|Not Eligible	|Ineligible	|ApplicationProcessing	|110	|

Scenario: GFS Scenario-10-API after ULAD but before LPA
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|987654		|LPA	|1234	|1		|1 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|987654		|LPA	|2019-06-05	|1234	|1		|1 Fred Ave	|250	|250	|Not Eligible	|Ineligible	|ApplicationProcessing	|100	|
	
	Given User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|ACEAPIID	|Address	|HVE	|ACEDecision	|
		|123456		|ACEAPI	|2019-06-06	|2			|1 Fred Ave	|275	|Eligible		|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPA	|1234	|3		|1 Fred Ave	|
	#No grandfathered HVE data
	#GFS provides the PV from 6/5 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|PV 	|
		|Data Not Found		|100	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPA	|2019-06-08	|1234	|3		|1 Fred Ave	|300	|300	|Not Eligible	|Ineligible	|ApplicationProcessing	|110	|
		
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|4		|1 Fred Ave	|
	#ACE GFS grandfathers the $275 HVE from 6/6 ACEAPI transaction
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|HVE 	|ErrorMessagePV	|
		|275	|Data Not Found	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-09	|1234	|4		|1 Fred Ave	|275	|300	|Not Eligible	|Ineligible	|ApplicationProcessing	|110	|

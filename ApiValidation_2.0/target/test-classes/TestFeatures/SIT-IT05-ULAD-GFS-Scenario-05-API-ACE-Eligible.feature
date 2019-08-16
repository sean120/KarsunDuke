@SIT-IT06-ULAD-GFS-Scenario-05 @GFS-Scenarios-ULAD @B-110539  @SIT-IT05 @SIT-ACEAPI20 @Regression_SIT
Feature: GFS Scenario-05-API ACE Eligible

Background: Clear previously posted GFS data from database
	Given Remove "GFS" MongoDB documents with the following values
		| addressLineText		| postalCode		|
		| 1 Fred Ave			| 44444				|
		| 2 Fred Ave			| 44444				|
		| 3 Fred Ave			| 44444				|

Scenario: GFS Scenario-05-API ACE Eligible
	Given User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|ACEAPIID	|Address	|HVE	|ACEDecision	|
		|123456		|ACEAPI	|2019-06-02	|1			|2 Fred Ave	|225	|Eligible		|

	Given User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|ACEAPIID	|Address	|HVE	|ACEDecision	|
		|123456		|ACEAPI	|2019-06-05	|2			|2 Fred Ave	|200	|Eligible		|

	Given User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|ACEAPIID	|Address	|HVE	|ACEDecision	|
		|987654		|ACEAPI	|2019-06-06	|3			|2 Fred Ave	|195	|Eligible		|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|4		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 6/5 ACEAPI transaction
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|HVE	|ErrorMessagePV |
		|200	|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-10	|1234	|4		|2 Fred Ave	|200	|250	|Eligible		|Reuse		|ApplicationProcessing	|150	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|5		|1 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-20	|1234	|5		|1 Fred Ave	|300	|300	|Not Eligible	|Ineligible	|ApplicationProcessing	|165	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|6		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 6/5 ACEAPI transaction
	#GFS provides the PV from 6/10 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|200	|150	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-25	|1234	|6		|2 Fred Ave	|200	|260	|Not Eligible	|Reuse		|ApplicationProcessing	|155	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|7		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 6/5 ACEAPI transaction
	#GFS provides the PV from 6/10 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|200	|150	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-11-02	|1234	|7		|2 Fred Ave	|200	|275	|Not Eligible	|Ineligible	|ApplicationProcessing	|160	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|8		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 6/5 ACEAPI transaction
	#GFS provides the PV from 6/10 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|200	|150	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-11-05	|1234	|8		|2 Fred Ave	|200	|260	|Not Eligible	|Ineligible	|ApplicationProcessing	|155	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|9		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 6/5 ACEAPI transaction
	#GFS provides the PV from 6/10 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|200	|150	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-11-10	|1234	|9		|2 Fred Ave	|200	|285	|Eligible		|Current	|ApplicationProcessing	|170	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|10		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 11/10 ACEAPI transaction
	#GFS provides the PV from 6/10 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|285	|150	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-11-12	|1234	|10		|2 Fred Ave	|285	|290	|Eligible		|Reuse		|ApplicationProcessing	|160	|

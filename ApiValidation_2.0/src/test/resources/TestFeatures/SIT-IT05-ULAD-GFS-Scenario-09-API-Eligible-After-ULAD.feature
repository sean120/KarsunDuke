@SIT-IT06-ULAD-GFS-Scenario-09 @GFS-Scenarios-ULAD @B-110539  @SIT-IT05 @SIT-ACEAPI20 @Regression_SIT
Feature: GFS Scenario-09-API After ULAD

Background: Clear previously posted GFS data from database
	Given Remove "GFS" MongoDB documents with the following values
		| addressLineText		| postalCode		|
		| 1 Fred Ave			| 44444				|
		| 2 Fred Ave			| 44444				|
		| 3 Fred Ave			| 44444				|

Scenario: GFS Scenario-09-API After ULAD
	Given User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|ACEAPIID	|Address	|HVE	|ACEDecision	|
		|123456		|ACEAPI	|2019-06-02	|1			|2 Fred Ave	|175	|Eligible		|

	Given User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|ACEAPIID	|Address	|HVE	|ACEDecision	|
		|123456		|ACEAPI	|2019-06-05	|2			|2 Fred Ave	|200	|Eligible		|

	Given User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|ACEAPIID	|Address	|HVE	|ACEDecision	|
		|123456		|ACEAPI	|2019-06-06	|3			|1 Fred Ave	|225	|Eligible		|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|4		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 6/5 ACEAPI transaction
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|HVE 	|ErrorMessagePV |
		|200	|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-10	|1234	|4		|2 Fred Ave	|200	|250	|Eligible		|Reuse		|PreQualification		|150	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|5		|3 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-20	|1234	|5		|3 Fred Ave	|300	|300	|Eligible		|Current	|ApplicationProcessing	|155	|

	Given User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|ACEAPIID	|Address	|HVE	|ACEDecision	|
		|123456		|ACEAPI	|2019-06-21	|6			|2 Fred Ave	|215	|Not Eligible	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|7		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 6/5 ACEAPI transaction
	#No Grandfathered PV data as 6/10 trans is PreQualification stage
	Then User gets GFS GET Response with the following values
		|HVE 	|ErrorMessagePV |
		|200	|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-25	|1234	|7		|2 Fred Ave	|200	|260	|Not Eligible	|Reuse		|ApplicationProcessing	|180	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|8		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 6/5 ACEAPI transaction
	#GFS provides the PV from 6/25 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|200	|180	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-30	|1234	|8		|2 Fred Ave	|200	|265	|Eligible		|Reuse		|ApplicationProcessing	|185	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|987654		|LPAv2	|1234	|9		|1 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|987654		|LPAv2	|2019-07-01	|1234	|9		|1 Fred Ave	|300	|300	|Not Eligible	|Ineligible	|ApplicationProcessing	|210	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|10		|1 Fred Ave	|
	#No grandfathered HVE data
	#GFS provides the PV from 7/1 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|PV 	|
		|Data Not Found		|210	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-07-02	|1234	|10		|1 Fred Ave	|215	|215	|Eligible		|Current	|ApplicationProcessing	|205	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|11		|1 Fred Ave	|
	#ACE GFS grandfathers the $215 HVE from 7/2  transaction
	#GFS provides the PV from 7/1 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|215	|210	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-07-03	|1234	|11		|1 Fred Ave	|215	|210	|Eligible		|Reuse		|ApplicationProcessing	|215	|

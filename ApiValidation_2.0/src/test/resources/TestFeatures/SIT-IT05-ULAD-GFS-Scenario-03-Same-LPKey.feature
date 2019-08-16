@SIT-IT06-ULAD-GFS-Scenario-03 @GFS-Scenarios-ULAD @B-110539  @SIT-IT05 @SIT-ACEAPI20 @Regression_SIT
Feature: GFS Scenario-03-Same LPKey in LPA and ULAD

Background: Clear previously posted GFS data from database
	Given Remove "GFS" MongoDB documents with the following values
		| addressLineText		| postalCode		|
		| 1 Fred Ave			| 44444				|
		| 2 Fred Ave			| 44444				|
		| 3 Fred Ave			| 44444				|

Scenario: GFS Scenario-03-Same LPKey in LPA and ULAD
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|1		|1 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-05	|1234	|1		|1 Fred Ave	|150	|150	|Eligible		|Current	|ApplicationProcessing	|100	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|2		|1 Fred Ave	|
	#ACE GFS grandfathers the $150 HVE from 6/5 transaction
	#GFS provides the PV from 6/5 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|150	|100	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-06	|1234	|2		|1 Fred Ave	|150	|175	|Eligible		|Reuse		|ApplicationProcessing	|155	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPA	|1234	|3		|1 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPA	|2019-06-08	|1234	|3		|1 Fred Ave	|200	|200	|Eligible		|Current	|ApplicationProcessing	|120	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPA	|1234	|4		|1 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 6/8 transaction
	#GFS provides the PV from 6/8 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|200	|120	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPA	|2019-06-10	|1234	|4		|1 Fred Ave	|200	|225	|Eligible		|Reuse		|ApplicationProcessing	|155	|
		
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPA	|1234	|5		|2 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPA	|2019-06-12	|1234	|5		|2 Fred Ave	|250	|250	|Eligible		|Current	|ApplicationProcessing	|140	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPA	|1234	|6		|2 Fred Ave	|
	#ACE GFS grandfathers the $250 HVE from 6/12 transaction
	#GFS provides the PV from 6/12 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|250	|140	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPA	|2019-06-14	|1234	|6		|2 Fred Ave	|250	|260	|Eligible		|Reuse		|ApplicationProcessing	|155	|

	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|7		|1 Fred Ave	|
	#ACE GFS grandfathers the $150 HVE from 6/5 transaction
	#GFS provides the PV from 6/5 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|150	|100	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-15	|1234	|7		|1 Fred Ave	|150	|175	|Eligible		|Reuse		|ApplicationProcessing	|160	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|8		|2 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-16	|1234	|8		|2 Fred Ave	|175	|175	|Eligible		|Current	|ApplicationProcessing	|110	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|9		|2 Fred Ave	|
	#ACE GFS grandfathers the $175 HVE from 6/16 transaction
	#GFS provides the PV from 6/16 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|175	|110	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-18	|1234	|9		|2 Fred Ave	|175	|180	|Eligible		|Current	|ApplicationProcessing	|115	|
	
	
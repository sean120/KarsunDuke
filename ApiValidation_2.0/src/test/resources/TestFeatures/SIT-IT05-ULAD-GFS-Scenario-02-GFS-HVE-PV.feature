@SIT-IT06-ULAD-GFS-Scenario-02 @GFS-Scenarios-ULAD @B-110539  @SIT-IT05 @SIT-ACEAPI20 @Regression_SIT
Feature: GFS Scenario-02-HVE and PV

Background: Clear previously posted GFS data from database
	Given Remove "GFS" MongoDB documents with the following values
		| addressLineText		| postalCode		|
		| 1 Fred Ave			| 44444				|
		| 2 Fred Ave			| 44444				|
		| 3 Fred Ave			| 44444				|

Scenario: GFS Scenario-02-HVE and PV
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
		|123456		|LPAv2	|2019-06-02	|1234	|1		|1 Fred Ave	|100	|100	|Not Eligible	|Ineligible	|ApplicationProcessing	|100	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|2		|1 Fred Ave	|
	#No grandfathered HVE data
	#GFS provides the PV from 6/2 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|PV 	|
		|Data Not Found		|100	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-05	|1234	|2		|1 Fred Ave	|150	|150	|Eligible		|Current	|ApplicationProcessing	|150	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|3		|1 Fred Ave	|
	#ACE GFS grandfathers the $150 HVE from 6/5 transaction
	#GFS provides the PV from 6/2 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|150	|100	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-06	|1234	|3		|1 Fred Ave	|150	|175	|Eligible		|Reuse		|ApplicationProcessing	|155	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|4		|1 Fred Ave	|
	#ACE GFS grandfathers the $150 HVE from 6/5 transaction
	#GFS provides the PV from 6/2 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|150	|100	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-06-07	|1234	|4		|1 Fred Ave	|150	|185	|Not Eligible	|Reuse		|ApplicationProcessing	|160	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|5		|2 Fred Ave	|
	#No grandfathered HVE data
	#No Grandfathered PV data
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|ErrorMessagePV |
		|Data Not Found		|Data Not Found |
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-07-10	|1234	|5		|2 Fred Ave	|200	|200	|Eligible		|Current	|ApplicationProcessing	|120	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|6		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 7/10 transaction 
	#GFS provides the PV from 7/10 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|200	|120	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-07-15	|1234	|6		|2 Fred Ave	|200	|300	|Eligible		|Reuse		|ApplicationProcessing	|250	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|7		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 7/10 transaction
	#GFS provides the PV from 7/10 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|200	|120	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456		|LPAv2	|2019-07-16	|1234	|7		|2 Fred Ave	|200	|325	|Not Eligible	|Reuse		|ApplicationProcessing	|260	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|987654		|LPAv2	|1234	|8		|1 Fred Ave	|
	#ACE GFS grandfathers the $150 HVE from 6/5 transaction
	#GFS provides the PV from 6/2 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|150	|100	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|987654		|LPAv2	|2019-10-12	|1234	|8		|1 Fred Ave	|150	|375	|Eligible		|Current	|ApplicationProcessing	|180	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|9		|1 Fred Ave	|
	#ACE GFS grandfathers the $375 HVE from 10/12 transaction
	#GFS provides the PV from 6/2 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|375	|100	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|123456	|LPAv2	|2019-10-15		|1234	|9		|1 Fred Ave	|375	|390	|Eligible		|Reuse		|ApplicationProcessing	|175	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|987654		|LPAv2	|1234	|10		|2 Fred Ave	|
	#ACE GFS grandfathers the $200 HVE from 7/10 transaction
	#GFS provides the PV from 7/10 trx as the Original PV
	Then User gets GFS GET Response with the following values
		|HVE 	|PV 	|
		|200	|120	|
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|Stage					|PV		|
		|987654		|LPAv2	|2019-10-16|1234	|10		|2 Fred Ave	|200	|250	|Eligible		|Reuse		|ApplicationProcessing	|125	|
	

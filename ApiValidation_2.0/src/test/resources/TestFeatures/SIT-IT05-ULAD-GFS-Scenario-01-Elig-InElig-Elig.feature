@SIT-IT06-ULAD-GFS-Scenario-01 @GFS-Scenarios-ULAD @B-110539 @SIT-IT05 @SIT-ACEAPI20 @Regression_SIT
Feature: GFS Scenario-01- Eligible - InEligible - Eligible

Background: Clear previously posted GFS data from database
	Given Remove "GFS" MongoDB documents with the following values
		| addressLineText		| postalCode		|
		| 1 Fred Ave			| 44444				|
		| 2 Fred Ave			| 44444				|
		| 3 Fred Ave			| 44444				|

Scenario: GFS Scenario-01-Eligible - InEligible - Eligible
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|1		|1 Fred Ave	|
	#No data to grandfather on the original submission. 
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE	|
		|Data Not Found 	|	
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|123456		|LPAv2	|2019-06-02	|1234	|1		|1 Fred Ave	|100	|100	|Not Eligible	|Ineligible	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|2		|1 Fred Ave	|	
	#No data to grandfather 
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|
		|Data Not Found 	|	
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date			|LPKey	|LPT		|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|123456		|LPAv2	|2019-06-05		|1234	|2			|1 Fred Ave	|150	|150	|Eligible		|Current	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|987654		|LPAv2	|1234	|3		|1 Fred Ave	|	
	#The $150 from 6/5 is grandfathered and is reused for the ACE decision.
	Then User gets GFS GET Response with the following values
		|HVE	|
		|150	|		
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|987654		|LPAv2	|2019-06-06	|1234	|3		|1 Fred Ave	|150	|175	|Eligible		|Reuse		|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|4		|1 Fred Ave	|	
	#The $150 from 6/5 is grandfathered and is reused for the ACE decision. 
	Then User gets GFS GET Response with the following values
		|HVE 	|
		|150	|		
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|123456		|LPAv2	|2019-06-07	|1234	|4		|1 Fred Ave	|150	|185	|Not Eligible	|Reuse	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|5		|1 Fred Ave	|	
	#Even if there is an ACE Not Eligible transaction after an Eligible transaction, the $150 from the 6/5 original ACE Eligible transaction is grandfathered 
	Then User gets GFS GET Response with the following values
		|HVE 		|
		|150		|	
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|123456		|LPAv2	|2019-06-08	|1234	|5		|1 Fred Ave	|150	|190	|Eligible	|Reuse	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|6		|2 Fred Ave	|	
	#Since the address is different than previous transactions	,  ACE GFS provide a response that there is no data to grandfather. 
	Then User gets GFS GET Response with the following values
		|ErrorMessageACE 	|
		|Data Not Found 	|		
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|123456		|LPAv2	|2019-07-10	|1234	|6		|2 Fred Ave	|200	|200	|Eligible		|Current	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|7		|2 Fred Ave	|	
	#The $200 from 7/10 is grandfathered and is reused for the ACE decision.  
	Then User gets GFS GET Response with the following values
		|HVE 	|
		|200	|	
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|123456		|LPAv2	|2019-07-15	|1234	|7		|2 Fred Ave	|200	|300	|Eligible		|Reuse		|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|987654		|LPAv2	|1234	|8		|1 Fred Ave	|	
	#The $150 from the original ACE Eligible transaction on 6/5 is grandfathered in container 1.  
	Then User gets GFS GET Response with the following values
		|HVE 	|
		|150	|
	#But the EDS ACE rules see that the date of this HVE information is over 120 days ago so it uses the current HVE data in container 2 for the ACE Eligible decision. So $375 is the new HVE amount that will be grandfathered for 1 Fred Ave address.		
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|987654		|LPAv2	|2019-10-12	|1234	|8		|1 Fred Ave	|150	|375	|Eligible		|Current	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|9		|1 Fred Ave	|	
	#The $375 from 10/12 transaction is the HVE info that will be grandfathered for 1 Fred Ave. 
	Then User gets GFS GET Response with the following values
		|HVE 	|
		|375	|	
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|123456		|LPAv2	|2019-10-14	|1234	|9		|1 Fred Ave	|375	|385	|Not Eligible	|Reuse	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|123456		|LPAv2	|1234	|10		|1 Fred Ave	|	
	#The $375 from 10/12 transaction is the HVE info that will be grandfathered for 1 Fred Ave. 
	Then User gets GFS GET Response with the following values
		|HVE 	|
		|375	|	
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|123456		|LPAv2	|2019-10-15	|1234	|10		|1 Fred Ave	|375	|390	|Eligible		|Reuse	|
	
	When User submits GFS GET request with the following values
		|SellerID	|Client	|LPKey	|LPT	|Address	|
		|987654		|LPAv2	|1234	|11		|2 Fred Ave	|	
	#The $200 from 7/10 transaction is the HVE info that will be grandfathered for 2 Fred Ave.
	Then User gets GFS GET Response with the following values
		|HVE 	|
		|200	|	
	And User submits GFS POST request with the following values
		|SellerID	|Client	|Date		|LPKey	|LPT	|Address	|HVE1	|HVE2	|ACEDecision	|AAStatus	|
		|987654		|LPAv2	|2019-10-16	|1234	|11		|2 Fred Ave	|200	|250	|Eligible		|Reuse	|
	

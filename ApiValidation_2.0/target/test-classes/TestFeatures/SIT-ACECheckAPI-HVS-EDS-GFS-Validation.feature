@SIT-ACECheckAPI-HVS-EDS-GFS-Validation @NoAPIGEE @SIT-ACEAPI20 @Regression_SIT
Feature: ACECheckAPI HVS/EDS/GFS validation

Scenario Outline: ACECheckAPI HVS/EDS/GFS validation
	When User submits ACE Check API (No APIGEE) request
	| partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| propertyEstimatedValueAmount		| addressLineText				| cityName			| postalCode	| stateCode		|
	| <partyRoleIdentifier>	| <loanPurposeType>	| <salesContractAmount>	| <propertyEstimatedValueAmount>	| <addressLineText>				| <cityName>		| <postalCode>	| <stateCode>	|
	Then ACE Check API returns HTTP status code 200
	And User verifies the ACE Check API response
	| messageCode1		| messageText1		|
	| <messageCode1>	| <messageText1>	|

	Then User retrieves the corresponding "ACEAPIRequest" from ACEAPI Database
	Then User verifies the "ACEAPIRequest" retrieved from ACEAPI Database
	
	Then User retrieves the corresponding "ACEAPIResponse" from ACEAPI Database
	Then User verifies the "ACEAPIResponse" retrieved from ACEAPI Database

	Then User retrieves the corresponding "HVERequest" from ACEAPI Database
	Then User verifies "HVERequest" wrt "ACEAPIRequest"
	Then User retrieves the corresponding "HVEResponse" from ACEAPI Database
	Then User retrieves the corresponding "AARequest" from ACEAPI Database
	Then User verifies "AARequest" wrt "HVEResponse"
	Then User retrieves the corresponding "AAResponse" from ACEAPI Database
	Then User verifies "ACEAPIResponse" wrt "AAResponse"

	Then User retrieves the corresponding "GFSPostData" from GFS Database
	And User verifies the "Address" data in "HVEResponse" is copied to "GFSPostData"
	And User verifies the "HVE1" data in "HVEResponse" is copied to "GFSPostData"
	And User verifies the "AA" data in "AAresponse" is copied to "GFSPostData"

Examples:
	| partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| propertyEstimatedValueAmount	| addressLineText				| cityName			| postalCode	| stateCode	| messageCode1	| messageText1	|
	| 000601				| Purchase			| 346585				| 								| 11931 BENTON LAKE RD			| BRISTOW			| 20136			| VA		| AWP0001		| Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.	|
	| 000601				| Refinance			| 						| 410000						| 23303 ANGELA CT				| SMITHSBURG		| 21783			| MD		| AWP0002		| Based on the submitted data, the subject property is not eligible for an appraisal waiver.	|
	| 000601				| Purchase			| 287671				| 								| 14576 BERKLEE DR				| ADDISON			| 75001			| LA		| AWP0001		| Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.	|
	| 000601				| Purchase			| 140949				| 								| 9204 MARSH MOUNTAIN RD		| PINSON			| 35126			| AL		| AWP0001		| Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.	|
	| 000601				| Purchase			| 173617				| 								| 1318 PRINCETON ST				| ABILENE			| 79602			| TX		| AWP0002		| Based on the submitted data, the subject property is not eligible for an appraisal waiver.	|
	| 000601				| Purchase			| 447246				| 								| 25264 WINDY BLUFF LN			| ARLINGTON			| 68002			| NE		| AWP0001		| Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.	|
	| 000601				| Purchase			| 320000				| 								| 37 PARK AVE					| LAKE RONKONKOMA	| 11779			| NY		| AWP0001		| Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.	|
	| 000601				| Purchase			| 395000				| 								| 3365 MORONEY DR				| RICHARDSON		| 75082			| TX		| AWP0001		| Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.	|
	| 000601				| Refinance			| 						| 575000						| 2140 DEMETRIUS WAY			| ROSEVILLE			| 95661			| CA		| AWP0001		| Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.	|
	| 000601				| Purchase			| 797945				| 								| 33 OAK KNOLL RD				| RIDGEFIELD		| 06877			| CT		| AWP0002		| Based on the submitted data, the subject property is not eligible for an appraisal waiver.	|
	| 000601				| Purchase			| 550000				| 								| 5901 COVINGTON CT				| MINNETONKA		| 55345			| MN		| AWP0001		| Based on the submitted data, the subject property is conditionally approved for an appraisal waiver. This approval is contingent upon the maximum loan amount, the expiration date, a complete assessment of the loan by Loan Product Advisor, and compliance with the eligibility requirements in the Freddie Mac Seller/Servicer Guide for the appraisal waiver.	|

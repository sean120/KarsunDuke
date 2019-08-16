@Iteration05_SIT @B-114986-SIT @Kostia @IT05 @SIT-ACEAPI20 @SIT-IT05 @Regression_SIT
Feature: Check ACE API User Role
Background:	
Given Remove "GFS" MongoDB documents with the following values
	|postalCode|
	|44444     |

Scenario Outline: User Sends ACEAPI request
                Given User submits new ACEAPI request       
	            | partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| propertyEstimatedValueAmount		| addressLineText				| cityName			| postalCode	| stateCode		|
	            | <partyRoleIdentifier>	| <loanPurposeType>	| <salesContractAmount>	| <propertyEstimatedValueAmount>	| <addressLineText>				| <cityName>		| <postalCode>	| <stateCode>	|  
	            When User restrieves "ED" "RESPONSE" from "serviceCallTraces"
	            Then User verify that "UserRoleName" is "ACE Check API Customer"
	            
Examples:
         | partyRoleIdentifier	| loanPurposeType	| salesContractAmount	| propertyEstimatedValueAmount| addressLineText		| cityName			| postalCode	| stateCode	|
	     | 000601			    | Purchase			| 346585				| 					          | 11931 BENTON LAKE RD| BRISTOW			|20136			| VA		|      
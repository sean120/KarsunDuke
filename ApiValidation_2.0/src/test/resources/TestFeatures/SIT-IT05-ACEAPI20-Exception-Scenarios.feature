@SIT-IT05-ACEAPI20-Exception-Scenarios @IT05 @B-119278-SIT @SIT-IT05 @SIT-ACEAPI20 @Regression_SIT
Feature: ACEAPI20 Exception Scenarios

Scenario Outline: ACEAPI20 Exception Scenarios
	Given ACEAPI2.0 request payload in the file "ACEAPI20Request-<Client>-<TMPL_ID>.json"
	And Update the ACEAPI2.0 request payload using the below Excel data
	| ExcelFileName   | ExcelSheetName   | ExcelRowNum   |
	| <ExcelFileName> | <ExcelSheetName> | <ExcelRowNum> |
	
	And Update the ACEAPI2.0 request payload using the below data
	| HVEOptionType		| <element1Name>	| <element2Name>	|
	| <HVEOptionType>	| <element1Value>	| <element2Value>	|
	
	When User submits ACEAPI2.0 request
	Then User gets ACEAPI2.0 response
	
	And User retrieves the corresponding "HVEResponse" from ACEAPI2.0 Database
	And User verifies there is "<HVSDataAvailability>" in the "HVEResponse"	
	
	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	And User verifies the "AARequest" values
	| homeValueExplorerOptionType	| HVE2homeValueExplorerOptionType		|
	|								| <HVEOptionType>						|

	And User verifies the "AARequest" "communicationFailure" values
	| rdsCommunicationFailureType	| craCommunicationFailureType	| hvsCommunicationFailureType	|
	| <rdsCommunicationFailureType>	| <craCommunicationFailureType>	| <hvsCommunicationFailureType>	|

	Then User retrieves the corresponding "AAResponse" from ACEAPI2.0 Database
	And User verifies the ACEAPI2.0 response values
	| alternateAppraisalEligibilityDecision		| alternateAppraisalReasonList		| messageCodeList	|
	| <alternateAppraisalEligibilityDecision>	| <alternateAppraisalReasonList>	| <messageCodeList>	|
	
Examples:
	| Client | TMPL_ID	| ExcelFileName        | ExcelSheetName   | ExcelRowNum   | HVEOptionType		| element1Name								| element1Value			| element2Name								| element2Value				| HVSDataAvailability	| rdsCommunicationFailureType	|	craCommunicationFailureType	| hvsCommunicationFailureType	| alternateAppraisalEligibilityDecision	| alternateAppraisalReasonList	| messageCodeList		|
	#Eligible
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | HVEFeedback			| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| HVSDataAvailable		| 								|								| 								| Eligible								| Eligible						| B1,WH					|
	#RDSDataNotAvailable
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | NoHVEFeedback		| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureType				| RDSDataNotAvailable		| HVSDataAvailable		| RDSDataNotAvailable			|								| 								| Not Eligible							| NoStdAddress					| L5,KT					|
	#CRADataNotAvailable
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | ReliefRefinance		| craCommunicationFailureType				| CRADataNotAvailable	| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| HVSDataAvailable		| 								| CRADataNotAvailable			| 								| Not Eligible							| NoCRADataAvail				| L5,KT					|
	#CRADataNotAvailable and RDSDataNotAvailable
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | __NULL_VALUE__		| craCommunicationFailureType				| CRADataNotAvailable	| rdsCommunicationFailureType				| RDSDataNotAvailable		| HVSDataAvailable		| RDSDataNotAvailable			| CRADataNotAvailable			| 								| Not Eligible							| NoStdAddress,NoCRADataAvail	| L5,KT					|
	#RDSServiceNotAvailable
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | __NO_ELEMENT__		| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureType				| RDSServiceNotAvailable	| HVSDataAvailable		| RDSServiceNotAvailable		|								| 								| Not Eligible							| RDSDown						| L5,KT					|
	#CRANotAvailable
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | HVEFeedback			| craCommunicationFailureType				| CRANotAvailable		| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| HVSDataAvailable		| 								| CRANotAvailable				| 								| Not Eligible							| CRADown						| L5,KT					|
	#CRANotAvailable and RDSServiceNotAvailable
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | HVEFeedback			| craCommunicationFailureType				| CRANotAvailable		| rdsCommunicationFailureType				| RDSServiceNotAvailable	| HVSDataAvailable		| RDSServiceNotAvailable		| CRANotAvailable				| 								| Not Eligible							| RDSDown,CRADown				| L5,KT					|
	#RDSServiceNotAvailable and CRADataNotAvailable
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | HVEFeedback			| craCommunicationFailureType				| CRADataNotAvailable	| rdsCommunicationFailureType				| RDSServiceNotAvailable	| HVSDataAvailable		| RDSServiceNotAvailable		| CRADataNotAvailable			| 								| Not Eligible							| RDSDown,NoCRADataAvail		| L5,KT					|
	#CRANotAvailable and RDSDataNotAvailable 
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 1             | HVEFeedback			| craCommunicationFailureType				| CRANotAvailable		| rdsCommunicationFailureType				| RDSDataNotAvailable		| HVSDataAvailable		| RDSDataNotAvailable			| CRANotAvailable				| 								| Not Eligible							| CRADown,NoStdAddress			| L5,KT					|
	#No HVS Data
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 5             | HVEFeedback			| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| NoHVSData				| 								|								| HVSDataNotAvailable			| Not Eligible							| NoHVEFDRAvail					| L5,KT					|
	#Disaster zone
	| ULAD   | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-ULAD | 8             | HVEFeedback			| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| HVSDataAvailable		| 								|								| 								| Not Eligible							| DisasterFlorence				| L5,KT					|
	#Eligible
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | HVEFeedback			| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| HVSDataAvailable		| 								|								| 								| Eligible								| Eligible						| B1,WH					|
	#RDSDataNotAvailable
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | NoHVEFeedback		| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureType				| RDSDataNotAvailable		| HVSDataAvailable		| RDSDataNotAvailable			|								| 								| Not Eligible							| NoStdAddress					| L5,KT					|
	#CRADataNotAvailable
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | ReliefRefinance		| craCommunicationFailureType				| CRADataNotAvailable	| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| HVSDataAvailable		| 								| CRADataNotAvailable			| 								| Not Eligible							| NoCRADataAvail				| L5,KT					|
	#CRADataNotAvailable and RDSDataNotAvailable
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | __NULL_VALUE__		| craCommunicationFailureType				| CRADataNotAvailable	| rdsCommunicationFailureType				| RDSDataNotAvailable		| HVSDataAvailable		| RDSDataNotAvailable			| CRADataNotAvailable			| 								| Not Eligible							| NoStdAddress,NoCRADataAvail	| L5,KT					|
	#RDSServiceNotAvailable
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | __NO_ELEMENT__		| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureType				| RDSServiceNotAvailable	| HVSDataAvailable		| RDSServiceNotAvailable		|								| 								| Not Eligible							| RDSDown						| L5,KT					|
	#CRANotAvailable
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | HVEFeedback			| craCommunicationFailureType				| CRANotAvailable		| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| HVSDataAvailable		| 								| CRANotAvailable				| 								| Not Eligible							| CRADown						| L5,KT					|
	#CRANotAvailable and RDSServiceNotAvailable
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | HVEFeedback			| craCommunicationFailureType				| CRANotAvailable		| rdsCommunicationFailureType				| RDSServiceNotAvailable	| HVSDataAvailable		| RDSServiceNotAvailable		| CRANotAvailable				| 								| Not Eligible							| RDSDown,CRADown				| L5,KT					|
	#RDSServiceNotAvailable and CRADataNotAvailable
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | HVEFeedback			| craCommunicationFailureType				| CRADataNotAvailable	| rdsCommunicationFailureType				| RDSServiceNotAvailable	| HVSDataAvailable		| RDSServiceNotAvailable		| CRADataNotAvailable			| 								| Not Eligible							| RDSDown,NoCRADataAvail		| L5,KT					|
	#CRANotAvailable and RDSDataNotAvailable 
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 1             | HVEFeedback			| craCommunicationFailureType				| CRANotAvailable		| rdsCommunicationFailureType				| RDSDataNotAvailable		| HVSDataAvailable		| RDSDataNotAvailable			| CRANotAvailable				| 								| Not Eligible							| CRADown,NoStdAddress			| L5,KT					|
	#No HVS Data
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 5             | HVEFeedback			| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| NoHVSData				| 								|								| HVSDataNotAvailable			| Not Eligible							| NoHVEFDRAvail					| L5,KT					|
	#Disaster zone
	| LQA    | 02-excep	| EDSTestData-02.xlsx  | EDSTestData-LQA  | 8             | HVEFeedback			| craCommunicationFailureDerivation_Object	| __NO_OBJECT__			| rdsCommunicationFailureDerivation_Object	| __NO_OBJECT__				| HVSDataAvailable		| 								|								| 								| Not Eligible							| DisasterFlorence				| L5,KT					|

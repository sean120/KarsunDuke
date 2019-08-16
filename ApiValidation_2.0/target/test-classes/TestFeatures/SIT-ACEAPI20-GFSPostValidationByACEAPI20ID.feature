@aceapi20-gfs-post-validation
Feature: ACE API 2.0 GFS POST validation

Scenario Outline: ACE API 2.0 GFS POST validation
	Given The ACEAPI2.0 RequestID is "<aceApi20Id>"
	
	Then User retrieves the corresponding "ACEAPI2.0Request" from ACEAPI2.0 Database
	And User creates the "ACEAPI2.0Request" using the ACEAPI2.0 DB document
	Then User retrieves the corresponding "ACEAPI2.0Response" from ACEAPI2.0 Database
	
	Then User retrieves the corresponding "HVEResponse" from ACEAPI2.0 Database
	Then User retrieves the corresponding "AARequest" from ACEAPI2.0 Database
	Then User retrieves the corresponding "AAResponse" from ACEAPI2.0 Database

	Then User retrieves the corresponding ACEAPI2.0 "GFSPostData" from GFS Database
	And User verifies GFS "GFSPostData" "Address" data matches "HVEResponse" "Address" data
	And User verifies GFS "GFSPostData" "HVE2" data matches "HVEResponse" "HVE" data
	And User verifies GFS "GFSPostData" "AA" data matches "AAResponse" "AA" data
	And User verifies GFS "GFSPostData" "HVE" data matches "AARequest" "HVE" data
	And User verifies GFS "GFSPostData" "PV" data matches "AARequest" "Subsequent PV" data
	
Examples:
	| aceApi20Id	|
	| 126264307082	|


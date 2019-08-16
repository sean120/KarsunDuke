@acecheckapi-gfs-post-validation
Feature: ACECheckAPI GFS POST validation

Scenario Outline: ACECheckAPI GFS POST validation
	Given The ACEAPI1.0 RequestID is "<aceApiId>"
	
	Then User retrieves the corresponding "ACEAPIRequest" from ACEAPI Database
	Then User retrieves the corresponding "ACEAPIResponse" from ACEAPI Database
	
	Then User retrieves the corresponding "HVEResponse" from ACEAPI Database
	Then User retrieves the corresponding "AARequest" from ACEAPI Database
	Then User retrieves the corresponding "AAResponse" from ACEAPI Database

	Then User retrieves the corresponding "GFSPostData" from GFS Database
	And User verifies the "Address" data in "HVEResponse" is copied to "GFSPostData"
	And User verifies the "HVE1" data in "HVEResponse" is copied to "GFSPostData"
	#And User verifies the "AA" data in "AAresponse" is copied to "GFSPostData"

Examples:
	| aceApiId		|
	| 6882977803 |
	| 478500796897 |
	| 206979818540 |
	| 835200444935 |
	| 371327587423 |
	| 260526339059 |
	| 608292542750 |
	| 663598014126 |
	| 108445043514 |
	| 635095518209 |
	| 395884509712 |
	| 574202964220 |
	| 544227427736 |
	| 725004461243 |
	| 967174353390 |
	| 975897578548 |
	| 426854000238 |
	| 878767189602 |
	| 844928455022 |
	| 199460176078 |
	| 388983018072 |
	| 163076959586 |
	| 253333321051 |
	| 968259977208 |
	| 624305844084 |
	| 918229567651 |
	| 91163886303 |
	| 757781767936 |
	| 327349493186 |
	| 239027674701 |
	| 648717575647 |
	| 467627016754 |
	| 782086520753 |
	| 932268766972 |
	| 551320181141 |
	| 502449991490 |
	| 899776528536 |
	| 188458985316 |
	| 442978552420 |
	| 134788601042 |
	| 834783408284 |
	| 595174629664 |
	| 289557976809 |
	| 905655966346 |
	| 189431463452 |
	| 906451165225 |
	| 758234963213 |
	| 951815683019 |
	| 826634643001 |
	| 12200117403 |
	| 186662995489 |
	| 859834236643 |
	| 517383444361 |
	| 62090584387 |
	| 869781409723 |
	| 486949565713 |
	| 513468664789 |
	| 14811101014 |
	| 425562011107 |
	| 429922807448 |
	| 749808295983 |
	| 828011694204 |
	| 405324288027 |
	| 180082779905 |
	| 194963920334 |
	| 487631854611 |
	| 844640467227 |
	| 202667915101 |
	| 355399932856 |
	| 361544264790 |
	| 54232317218 |
	| 528255819203 |
	| 58006177386 |
	| 993350158476 |
	| 872248589796 |
	| 336516452707 |
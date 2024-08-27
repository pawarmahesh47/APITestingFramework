Feature: Validating place APIs

@AddPlace
Scenario Outline: Vefiry that place is added successfully using AddPlaceAPI
	Given Add place Payload "<name>" "<language>"
	When User calls "addPlaceAPI" with "post" http request
	Then API call get success with status code 200
	And "status" response body is "OK"
	And "scope" response body is "APP"
	And verify place_id created map to "<name>" using "getPlaceAPI"
	
	Examples:
	
	|name    | language | 
	|AAhouse | French   |
	|BBHouse | Marathi  |

@DeletePlace	
Scenario: Verify delete place API functionality is working
		Given Delete place payload
		When User calls "deletePlaceAPI" with "Delete" http request
		Then API call get success with status code 200
		And "status" response body is "OK"
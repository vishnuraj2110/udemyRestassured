
Feature: Validationg Place APIs

@addPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" With "post" http Request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"
    
    Examples:
    | name | language| address|
    | aaHouse| English | World cross center|
    | BBHouse| Spanish | Sea cross center|
 
 @deletePlace
 Scenario: verify if delete place functionality is working 
        Given DeletePlace PayLoad
        When user calls "deletePLaceAPI" With "post" http Request
        Then the API call is success with status code 200
         And "status" in response body is "OK"
        
        
@AddPlace
Feature: Verify Place Validation

  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name    | language | address     |
      | AAhouse | English  | Westwijk    |
#      | Bhouse  | Dutch    | Kronenburg  |

#  @DeletePlace
#  Scenario: Verify if Delete Place functionality is working
#    Given DeletePlace Payload
#    When User calls "deletePlaceAPI" with "Post" http request
#    Then the API call got success with status code 200
#    And "status" in response body is "OK"

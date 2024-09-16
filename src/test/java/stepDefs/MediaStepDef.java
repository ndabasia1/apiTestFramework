package stepDefs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import data.Constants.URI;

import static org.hamcrest.Matchers.lessThan;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * Steps relating to the Project Page are found here
 */
public class MediaStepDef
{
    private Response response;

    /* **************** 
     *  PUBLIC METHODS 
     ****************** */

    /**
     * Make a GET request to the public endpoint
     */
    @Given("^I make a GET request to the public endpoint$")
    public void sendGetRequest()
    {
        RestAssured.baseURI = URI.TESTAPI_BASE;
        response = RestAssured.given().get(URI.PATH);
    }

    /**
     * Check the response code is expectedStatusCode
     * <p>
     * @param expectedStatusCode The status code to check for
     */
    @Then("the HTTP status code of the response is {int}")
    public void checkStatusCode(int expectedStatusCode)
    {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    /**
     * Check the response time of the request is less than maxTime
     * <p>
     * @param maxTime The maximum time the request should take
     */
    @Then("the response time of the request is less than {int} milliseconds")
    public void checkResponseTime(int maxTime)
    {
        response.then().time(lessThan((long) maxTime));
    }

    /**
     * Check the value for fieldName is never empty or null for all items in the array
     * <p>
     * @param fieldName The name of the field to check
     */
    @Then("the {string} field is never null or empty for all items present in the array")
    public void checkIdOccupied(String fieldName)
    {
        JsonPath jsonResponse = response.jsonPath();
        for (Object actualValue : jsonResponse.getList("data." + fieldName))
        {
            assertNotNull(actualValue);
            assertNotEquals("", actualValue);
        }
    }

    /**
     * Check the value for field is always a fixed expected value
     * <p>
     * @param fieldName The name of the field to check
     * @param expectedValue The expected value of fieldName
     */
    @Then("the {string} field for every track is always {string}")
    public void checkFieldEqualsValue(String fieldName, String expectedValue)
    {
        JsonPath jsonResponse = response.jsonPath();
        for (Object actualValue : jsonResponse.getList("data." + fieldName))
        {
            assertEquals(expectedValue, actualValue);
        }
    }

    /**
     * Check the value for field only ever has one true
     * <p>
     * @param fieldName The name of the field to check
     */
    @Then("only one track has {string} set to true")
    public void checkOnlyOneFieldSetToTrue(String fieldName)
    {
        JsonPath jsonResponse = response.jsonPath();
        int trueCounter = 0;
        for (Object actualValue : jsonResponse.getList("data." + fieldName))
        {
            if ((Boolean) actualValue)
            {
                trueCounter++;
            }
        }
        assertEquals(1, trueCounter);
    }
    
    /**
     * Check a response header is occupied and not null
     * <p>
     * @param headerName The name of the header to check
     */
    @Then("a {string} value is returned within the response headers")
    public void checkResponseHeader(String headerName)
    {
        String dateHeader = response.getHeader(headerName);
        assertNotNull(dateHeader);
        assertNotEquals("", dateHeader);
    }
}
package helpers;

import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiHelper {

    // Assertion for validating status codes
    public static void assertStatusCode(Response response, int expectedStatusCode, String logMessage) {
        assertEquals(expectedStatusCode, response.getStatusCode(), logMessage);
    }

    // Assertion for checking response body contains specific keys or fields
    public static void assertResponseContains(Response response, String key, String logMessage) {
        assertTrue(response.getBody().asString().contains(key), logMessage);
    }
}
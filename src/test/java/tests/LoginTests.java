package tests;

import config.ConfigurationManager;
import helpers.ApiHelper;
import helpers.PayloadHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class LoginTests extends BaseTest {

    @Test
    public void testValidLogin() {
        test = extent.createTest("Valid Login Test");
        try {
            RestAssured.baseURI = ConfigurationManager.getProperty("base.uri");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("x-api-key", "reqres-free-v1")
                    .body(PayloadHelper.getLoginPayload("eve.holt@reqres.in", "cityslicka"))
                    .post(ConfigurationManager.getProperty("login.endpoint"));

            ApiHelper.assertStatusCode(response, 200, "Expected status code 200.");
            logPass("Status code is 200.");

            ApiHelper.assertResponseContains(response, "token", "Response body does not contain 'token'.");
            logPass("Response contains 'token'.");

            logInfo("Response: " + response.getBody().asString());
            System.out.println("[PASS] testValidLogin passed");
        } catch (Exception e) {
            logFail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidLogin() {
        test = extent.createTest("Invalid Login Test");
        try {
            RestAssured.baseURI = ConfigurationManager.getProperty("base.uri");

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("x-api-key", "reqres-free-v1")
                    .body(PayloadHelper.getLoginPayload("invaliduser", "wrongpassword"))
                    .post(ConfigurationManager.getProperty("login.endpoint"));

            ApiHelper.assertStatusCode(response, 400, "Expected status code 400.");
            logPass("Status code is 400.");

            ApiHelper.assertResponseContains(response, "error", "Response body does not contain 'error'.");
            logPass("Response contains 'error'.");

            logInfo("Response: " + response.getBody().asString());
            System.out.println("[PASS] testInvalidLogin passed");
        } catch (Exception e) {
            logFail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testEmptyEmailLogin() {
        test = extent.createTest("Empty Email Login Test");
        try {
            RestAssured.baseURI = ConfigurationManager.getProperty("base.uri");

            // Send POST request with an empty email field
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("x-api-key", "reqres-free-v1")
                    .body(PayloadHelper.getLoginPayload("", "abc")) // Empty email, valid password
                    .post(ConfigurationManager.getProperty("login.endpoint"));

            // Validate Status Code
            ApiHelper.assertStatusCode(response, 400, "Expected status code 400.");
            logPass("Status code is 400.");

            // Validate Response Body Contains Specific Error
            ApiHelper.assertResponseContains(response, "Missing email or username", "Response body does not contain the expected error message.");
            logPass("Response contains the error message: 'Missing email or username'.");

            logInfo("Response: " + response.getBody().asString());
            System.out.println("[PASS] testEmptyEmailLogin passed");
        } catch (Exception e) {
            logFail("Exception occurred: " + e.getMessage());
        }
    }
    @Test
    public void testEmptyEmailAndPassword() {
        test = extent.createTest("Empty Email and Password Test");
        try {
            RestAssured.baseURI = ConfigurationManager.getProperty("base.uri");

            // Send POST request with both email and password as empty
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("x-api-key", "reqres-free-v1")
                    .body(PayloadHelper.getLoginPayload("", "")) // Both fields are empty
                    .post(ConfigurationManager.getProperty("login.endpoint"));

            // Validate Status Code
            ApiHelper.assertStatusCode(response, 400, "Expected status code 400.");
            logPass("Status code is 400.");

            // Validate Response Body Contains Specific Error
            ApiHelper.assertResponseContains(response, "Missing email or username", "Response body does not contain the expected error message.");
            logPass("Response contains the error message: 'Missing email or username'.");

            logInfo("Response: " + response.getBody().asString());
            System.out.println("[PASS] testEmptyEmailAndPassword passed");
        } catch (Exception e) {
            logFail("Exception occurred: " + e.getMessage());
        }
    }
}
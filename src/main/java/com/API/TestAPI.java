package com.API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestAPI {

/*
    There are following public apis
#	Route	        Method  Type	                    Full route	                                Description	Details
1	/employee	    GET	    JSON	http://dummy.restapiexample.com/api/v1/employees	    Get all employee data Details
2	/employee/{id}	GET	    JSON	http://dummy.restapiexample.com/api/v1/employee/{id}	Get a single employee data Details
3	/create	        POST	JSON	http://dummy.restapiexample.com/api/v1/create	        Create new record in database Details
4	/update/{id}	PUT	    JSON	http://dummy.restapiexample.com/api/v1/update/{id}	    Update an employee record Details
5	/delete/{id}	DELETE	JSON	http://dummy.restapiexample.com/api/v1/update/{id}	    Delete an employee record Details
*/

    private String BaseURI = RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/";
    private String employees = "employees";
    private String employeeWithID = "employee/";
    private String create = "create/";
    private String update = "update/";

    @Test
    public void testGetAllEmployee() {
        Response response = given().when().get(employees).then()
                .extract().response();
        JsonPath x = new JsonPath(response.asString());
        System.out.println("Response : " + response.asString());
        System.out.println("Status Code : " + response.getStatusCode());
    }

    @Test
    public void GetSpecificEmployee() {
        Response response = given().when().get(employeeWithID + "2").then()
                .extract().response();
        System.out.println("Response is : " + response.asString());
        JsonPath x = new JsonPath(response.asString());
        System.out.println("Response : " + response.asString());
        System.out.println("Status Code : " + response.getStatusCode());
    }

    @Test
    public void testPutMethod() {
        String requestBody = "{\r\n" +
                " \"name\":\"Lion\",\r\n" +
                " \"salary\":\"20000\",\r\n" +
                " \"age\":\"40\"\r\n" +
                "}";
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(update + "2");
        System.out.println("Response : " + response.asString());
        System.out.println("Status Code : " + response.getStatusCode());

    }
//
    @Test
    public void testPostMethod() throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Jupitar John");
        requestParams.put("salary", "100000");
        requestParams.put("age", "25");
        Response response;
        response = given().header("Content-Type", "application/json").
                body(requestParams.toString()).
                when().post(create).
                then().statusCode(200).extract().response();
        System.out.println(requestParams.toString());
        System.out.println("Response : " + response.asString());
        System.out.println("Status Code : " + response.getStatusCode());
    }
//
    @Test
    public void testDeleteMethod() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .delete("/delete/2");
        System.out.println("Response : " + response.asString());
        System.out.println("Status Code : " + response.getStatusCode());
    }
}

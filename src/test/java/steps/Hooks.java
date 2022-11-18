package steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class Hooks {
    public static RequestSpecification requestSpec;

    @Before
    public void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        RestAssured.requestSpecification = requestSpec;
    }
}

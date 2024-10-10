package basicRestAssured;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateProjectTest2 {


    @Test
    @DisplayName("Verify Create Project by API - Todo.ly JSONObject")
    public void verifyCreateProjectJSONObject(){
        JSONObject body = new JSONObject();
        body.put("Content","ThompsonVerification200andBody");
        body.put("Icon",3);

        Response response = given()
                .auth()
                .preemptive()
                .basic("api2024@2024.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json");
                response.then()
                .statusCode(200)
                .body("Content",equalTo(body.get("Content")))
                .body("Icon",equalTo(3))
                .log().all();

                int id = response.then().extract().path("Id");
        String name = response.then().extract().path("Content");

        System.out.println("id: " + id);
        System.out.println("name: " + name);
    }

}

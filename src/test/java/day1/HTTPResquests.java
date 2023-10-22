package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPResquests {


    @Test
    public void getUser(){

       given()
       .when()
        .get("https://reqres.in/api/users/2")
         .then().statusCode(200)
               .body("data.id",equalTo(2))
               .log().all();

    }
    @Test
    public void getListOfUsers(){

        given()
         .when()
          .get("https://reqres.in/api/users?page=2")
           .then()
                .statusCode(200)
                .body("page",equalTo(2))
           .log().all();
    }
}

package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

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
    @Test
    public void userNotFound(){
        given()
          .when()
           .get("https://reqres.in/api/users/23")
            .then()
           .statusCode(404);
    }
    @Test
    public void listResource(){

        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("page",equalTo(1))
                .log().all();
    }
    @Test
    public void singleResource(){
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.id",equalTo(2))
                .log().all();
    }

    @Test
    public void createUser(){
        HashMap data = new HashMap();
        data.put("name","pavan");
        data.put("job","tester");

        given()
           .contentType("application/json")
             .body(data)
               .when()
                  .post("https://reqres.in/api/users")
               .then()
                 .statusCode(201)
                 .log().all();
    }
}

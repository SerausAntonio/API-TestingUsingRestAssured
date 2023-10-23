package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPResquests {
    int ID;

    @Test(priority = 1)
    public void getUser() {

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then().statusCode(200)
                .body("data.id", equalTo(2))
                .log().all();
    }
    @Test(priority = 2)
    public void getListOfUsers() {

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 3)
    public void userNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404)
            .log().all();
    }
    @Test(priority = 4)
    public void listResource() {

        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("page", equalTo(1))
                .log().all();
    }
    @Test(priority = 5)
    public void singleResource() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .log().all();
    }
    @Test(priority = 6)
    public void createUser() {
        HashMap data = new HashMap();
        data.put("name", "Antonio");
        data.put("job", "Trainer");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().all();
    }

    public class CreateAndUpdateUser {
        @Test(priority = 7)
        public void createuser() {

            HashMap data = new HashMap();
            data.put("name", "Peter");
            data.put("job", "Superman");

            ID = given()
                    .contentType("application/json")
                    .body(data)

                    .when()
                    .post("https://reqres.in/api/users")
                    .jsonPath().getInt("id");
        }

        @Test(priority = 8)
        public void updateuser() {
            HashMap data = new HashMap();
            data.put("name", "Peter");
            data.put("job", "Spyderman");

            given()
                    .contentType("application/json")
                    .body(data)

                    .when()
                    .put("https://reqres.in/api/users/" + ID)

                    .then()
                    .statusCode(200)
                    .log().all();
       }

    }
}
package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Students {

    @Test(priority = 1)
    public void healthCheck(){
        given()
                .when()
                .get("http://localhost:3000/students/ping")
                .then()
                .assertThat()
                .statusCode(404);
    }
    @Test(priority = 2)
    public void getStudent() {
        //GET
        given()
           .when()
             .get("http://localhost:3000/students/303")
          .then()
              .assertThat()
              .statusCode(200)
              .body("name",equalTo("Stephany"))
          .log().all();
    }
    @Test(priority = 3)
    public void getStudents() {
        //GET
        given()
          .when()
             .get("http://localhost:3000/students/")
          .then()
             .statusCode(200)
          .log().all();
    }
    @Test(priority = 4)
    public void createStudent(){
        //POST
        HashMap data = new HashMap();
        data.put("id",1);
        data.put("title","hello");

        given()
                .contentType("application/json")
                .body(data)

                .when()
                  .post("http://localhost:3000/students")

                .then()
                .assertThat()
                    .statusCode(201)
                .log().all();
    }
    @Test(priority = 5)
    public void upDateStudent(){
        //PUT
        HashMap data = new HashMap();
        data.put("id",1);
        data.put("title","afternoon");

        given()
                .contentType("application/json")
                .body(data)

                .when()
                    .put("http://localhost:3000/students/1")
                .then()
                     .assertThat()
                      .statusCode(200)
                .log().all();
    }
    @Test(priority = 6)
    public void deleteStudent(){
        //DELETE
        given()
                .contentType("application/json")
                .when()
                     .delete("http://localhost:3000/students/1")
                .then()
                     .assertThat()
                     .statusCode(200)
                .log().all();

    }
}
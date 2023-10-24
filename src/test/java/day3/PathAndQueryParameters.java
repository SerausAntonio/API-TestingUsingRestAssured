package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathAndQueryParameters {
    //https://reqres.in/api/users?page=2&id=5
    @Test
    public void testQueryAndPathParameters(){


    given()
            .pathParams("mypath","users") //path parameter
            .queryParam("page",2)      //Query parameter
            .queryParam("id",5)     //Query parameter
      .when()
            .get("https://reqres.in/api/{mypath}")
      .then()
         .assertThat()
         .statusCode(200)
         .log().all();
    }
}

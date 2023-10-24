package day4;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookStore {

    @Test
    public void testJsonResponse(){

        given()
                .contentType("ContentType.JSON")
            .when()
               .get("http://localhost:3000/store/")
               .then()
                 .assertThat()
                 .statusCode(200)
                .body("book[3].title",equalTo("The Lord of the Rings"));
        //.log().all();
    }

}

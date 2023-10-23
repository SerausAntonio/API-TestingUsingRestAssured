package day2;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Students {

    HashMap data = new HashMap();

    @Test
    public void testPostUsingHashMap(){
        data.put("id","660");
        data.put("name","John");
        data.put("active",true);
        Integer gradesArr[]={3,5,6};
        data.put("grades",gradesArr);
        data.put("yearsOld",15);
        data.put("color","blue");



        given()
           .contentType("application/json")
           .body(data)
        .when()
          .post("http://localhost:3000/students")
          .then()
             .assertThat()
             .statusCode(201)
                .body("id",equalTo("660"))
                .body("name",equalTo("John"))
                .body("grades[0]",equalTo(3))
                .body("grades[1]",equalTo(5))
                .body("active",equalTo(true))
                .body("yearsOld",equalTo(15))
                .body("color",equalTo("blue"))
            .log().all();
    }
}

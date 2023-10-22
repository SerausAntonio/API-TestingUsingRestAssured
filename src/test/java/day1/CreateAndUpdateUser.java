package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class CreateAndUpdateUser {
    int ID;

    @Test(priority =1)
    public void createuser(){

        HashMap data = new HashMap();
        data.put("name","Peter");
        data.put("job","Superman");

        ID = given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
      }

    @Test(priority = 2)
    public void updateuser(){
        HashMap data = new HashMap();
        data.put("name","Peter");
        data.put("job","Spyderman");

       given()
                .contentType("application/json")
                .body(data)

                .when()
                .put("https://reqres.in/api/users/"+ ID)

                .then()
                .statusCode(200)
                .log().all();

    }
}

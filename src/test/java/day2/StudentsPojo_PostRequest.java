package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class StudentsPojo_PostRequest {

 @Test
 public void getPojoRequest(){

     Pojo_PostRequest data = new Pojo_PostRequest();

     data.setId("222");
     data.setName("Bill");
     data.setActive(true);
     data.setYearsOld(12);
     data.setColor("Yellow");
     Integer gradesArr[] = {4,5,7};
     data.setGradesArr(gradesArr);

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
    @Test
    public void getExternalJsonFile() throws FileNotFoundException {
        File f = new File(System.getProperty("user.dir") + "\\src\\test\\java\\day2\\body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")
                .then()
                .assertThat()
                .statusCode(201)
                .log().all();
    }
  }

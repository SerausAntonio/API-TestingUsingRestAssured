package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
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
                .header("Content-Type","application/json; charset=utf-8")
                .body("book[3].title",equalTo("The Lord of the Rings"))
                .body("book[2].title",equalTo("Moby Dick"))
                .body("book[2].category",equalTo("fiction"))
                .log().all();
    }
    @Test
    public void getJsonResponse(){

        given()
                .contentType("ContentType.JSON")
                .when()
                    .get("http://localhost:3000/store/")
                .then()
                     .assertThat()
                     .statusCode(200)
                     .body("bicycle.color",equalTo("red"))
                     .body("bicycle.price",equalTo(19.95F))
                .log().body();
    }
    @Test
    public void validateJsonResponse(){

        Response resp = given()
                .contentType("ContentType.JSON")
                .when()
                .get("http://localhost:3000/store/");
        Assert.assertEquals(resp.getStatusCode(),200);
        Assert.assertEquals(resp.header("Content-Type"),"application/json; charset=utf-8");
        Assert.assertEquals(resp.jsonPath().get("book[3].title"),"The Lord of the Rings");

        System.out.println(resp.jsonPath().get("book[3].title").toString());
    }
    @Test
    public void getAllBookTitlesJsonResponse(){

        Response resp = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/store/");
            //JSON Object class

        JSONObject jo = new JSONObject(resp.asString());
        System.out.println(jo.getJSONArray("book").length());
        for(int i=0; i < jo.getJSONArray("book").length(); i++){
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            System.out.println(bookTitle);
        }

    }
    @Test
    public void getAllStudentsNamesJsonResponse(){

        Response resp = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/students/");
        //JSON Object class

        JSONObject jo = new JSONObject(resp.asString());
        System.out.println(jo.getJSONArray("student").length());

        for(int i=0; i < jo.getJSONArray("student").length(); i++){
            String names = jo.getJSONArray("student").getJSONObject(i).get("name").toString();
            System.out.println(names);
        }

    }
}

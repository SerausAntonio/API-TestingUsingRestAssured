package day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesAndHeaders {

    @Test
    public void getCookies(){
        given()
          .when()
          .get("https://www.google.com/")
          .then()
               // .cookie("AEC","Ackid1SFf01xbXuuCAw9xkdwp1fehujYQWzCqH81xr2RCDOJRPyRBNUTeqs")
          .log().all();
    }
    @Test
    public void getCookiesInfo(){
        Response resp = given()
                .when()
                .get("https://www.google.com/");
        Assert.assertEquals(resp.getStatusCode(),200);
        System.out.println(resp.getStatusCode());

        // get single coockie info
       String cookie_value = resp.getCookie("AEC");
       System.out.println(cookie_value);

       // get all coockies
        Map<String, String> cookies_values = resp.getCookies();
        System.out.println(cookies_values.keySet());

        for(String k: cookies_values.keySet()){
          //  String cookie_val= resp.getCookie();
          //  System.out.println(k+ "" + cookie_val);
        }
    }
    @Test
    public void getHeaders(){
        Response res = given()
                .when()
                .get("http://www.google.com");
        Headers myheaders = res.getHeaders();
        for (Header hd:myheaders){
            System.out.println(hd.getName() +  " => " + hd.getValue());
        }
    }
    @Test
    public void getBodyExample(){
        given()
           .when()
              .get("https://reqres.in/api/users?page=2")
           .then()
            //.log().body();
            //.log().cookies();
             // .log().headers();
                .log().all();

    }
}

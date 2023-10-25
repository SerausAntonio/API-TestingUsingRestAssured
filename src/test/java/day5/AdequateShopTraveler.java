package day5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class AdequateShopTraveler {
    //http://restapi.adequateshop.com/api/Traveler?page=1

    @Test
    public void getTravelerInfo(){
        Response resp = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        Assert.assertEquals(resp.getStatusCode(),200);
        Assert.assertEquals(resp.header("Content-Type"),"application/xml; charset=utf-8");
        String pageNo = resp.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(pageNo,"1");
    }
    @Test
    public void getAllTheTravelersInfo(){
        Response resp = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        XmlPath xmlobj = new XmlPath(resp.asString());
        List<String> travellers= xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");

        System.out.println(travellers.size());
        Assert.assertEquals(travellers.size(),10);

        String totalRecordCount= xmlobj.getString("TravelerinformationResponse.totalrecord");
        System.out.println(totalRecordCount);
        String totalPagesCount= xmlobj.getString("TravelerinformationResponse.total_pages");
        System.out.println(totalPagesCount);
    }
    @Test
    public void getAllTravelersNames(){
        Response resp = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        XmlPath xmlobj = new XmlPath(resp.asString());
        List<String> travellerNames = xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

        System.out.println(travellerNames.size());
        Assert.assertEquals(travellerNames.size(),10);
        for(String name:travellerNames){
            System.out.println(name);
        }

    }

}

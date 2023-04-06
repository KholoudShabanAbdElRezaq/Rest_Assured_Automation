package TestCase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static  io.restassured.RestAssured.*;

public class RestTestITI {
    String restURL = "https://63dc0666c45e08a043538b7c.mockapi.io/";
    String restPath = "/api/v1/Users";

    // Get all data
     @Test
    public void GetAllUsers_TC1()
     {
         given().baseUri(restURL).
                 when().get(restPath).
                 then().log().all().assertThat().statusCode(200);
     }

     // add new users
    @Test
    public  void PostNewUser_TC2()
    {
        String newuser = "{\n" +
                "  \"createdAt\": \"2023-02-09T01:13:55.121Z\",\n" +
                "  \"name\": \"Kholoud\",\n" +
                "  \"avatar\": \"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/669.jpg\",\n" +
                "  \"Country\": \"Egypt\",\n" +
                "  \"id\": \"6\"\n" +
                " }";
        given().baseUri(restURL).contentType(ContentType.JSON).body(newuser).log().all()
                .when().post(restPath).then().assertThat().statusCode(201).log().all();

    }
    @Test
    public void GetAllUsersAfterAddNEwUser_TC3()
    {
        given().baseUri(restURL).
                when().get(restPath).
                then().log().all().assertThat().statusCode(200);
    }

    @Test
    public  void updateuser_TC4()
    {
        String newuser = "{\n" +
                "  \"createdAt\": \"2023-02-09T01:13:55.121Z\",\n" +
                "  \"name\": \"alyaa\",\n" +
                "  \"avatar\": \"https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/669.jpg\",\n" +
                "  \"Country\": \"Egypt\",\n" +
                "  \"id\": \"8\"\n" +
                " }";

        given().contentType(ContentType.JSON).body(newuser)
                .when().put("https://63dc0666c45e08a043538b7c.mockapi.io/api/v1/Users/6").then().log().all();

    }
    @Test
    public void GetAllUsersAfterUpdtate_TC5()
    {
        given().baseUri(restURL).
                when().get(restPath).
                then().log().all().assertThat().statusCode(200);
    }

    @Test
    public  void deleteuser_TC6()
    {
        given().contentType(ContentType.JSON)
                .when().delete("https://63dc0666c45e08a043538b7c.mockapi.io/api/v1/Users/6").then().log().all();
    }

    @Test
    public void GetAllUsersAfterdeleteuser_TC7()
    {
        given().baseUri(restURL).
                when().get(restPath).
                then().log().all().assertThat().statusCode(200);
    }
}

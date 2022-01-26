import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestAPIBrewery extends BaseTest{

    Brewery brewery;

    @Test
    public void getListadoCervecerias() {

        setup();
        String response = given()
                .contentType(ContentType.JSON)
                .get("/autocomplete?query=lagunitas")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();

        System.out.println(response);
    }

    @Test
    public void getCerveceriaLagunitas() {

        setup();
                 given()
                .contentType(ContentType.JSON)
                .get("/search?query=Lagunitas Brewing Co")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
    }

    @Test
    public void validarNombreCerveceria() {

        setup();
        String response = given()
                .when()
                .get("/lagunitas-brewing-co-chicago")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .asString();

        brewery = from(response).getObject("",Brewery.class);

        assertThat(brewery.getName(),equalTo("Lagunitas Brewing Co"));
        assertThat(brewery.getStreet(), equalTo("1280 N McDowell Blvd"));
        assertThat(brewery.getPhone(), equalTo("7077694495"));

        System.out.println("Nombre: " + brewery.getName());
        System.out.println("Calle: " + brewery.getStreet());
        System.out.println("Telefono: " + brewery.getPhone());

    }
}

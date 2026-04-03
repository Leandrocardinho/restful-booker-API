import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class TestBooking{
    //atributos 
    static String ct = "application/json";    //Content-Type
    static String uri = "https://restful-booker.herokuapp.com/";
    static int bookingId; // Usado para guardar o ID no post

    // Funções e métodos (comuns / uteís)

    //Função de leitura do Json
    public static String lerArquivoJson(String arquivoJson) throws IOException{
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));

    }
 
    //Metodos de testes
   @Test @Order(1)
    public void testToken() throws IOException{
        // carregar os dados do arquivo json
        String jsonBody = lerArquivoJson("src/test/resources/json/token.json");

        given()
            .contentType(ct)
            .log().all()
            .body(jsonBody)
        .when()
            .post(uri + "auth") // Endpoint de auth

        .then()
            .log().all()
            .statusCode(200)
            .body("$", hasKey("token")); // Procura a chave "token" no objeto principal
        ;
    }           

    @Test @Order(2)
    public void testPostBooking() throws IOException{
        // carregar os dados do arquivo json do usuario
        String jsonBody = lerArquivoJson("src/test/resources/json/bookingPost.json");

        bookingId = given()  // bookingId usado para pegar o Id no post(usar no GET)
            .contentType(ct)    //Content-Type
            .log().all()
            .body(jsonBody)

        .when()
            .post(uri + "booking")
        
        .then()
            .log().all()
            .statusCode(200)
            
            //Teste de Contrato 
            .body("booking", hasKey("firstname"))
            .body("booking", hasKey("lastname"))
            .body("booking", hasKey("totalprice"))
            .body("booking", hasKey("depositpaid"))
            .body("booking", hasKey("bookingdates"))
            .body("booking.bookingdates", hasKey("checkin"))
            .body("booking.bookingdates", hasKey("checkout"))
            
            //Teste Funcional
            .body("booking.firstname", is("Leandro"))
            .body("booking.lastname", is("Cardinho"))
            .body("booking.totalprice", is(150))
            .body("booking.depositpaid", is(true))
            .body("booking.additionalneeds", is("Breakfast"))   
                .extract()
                .path("bookingid"); // <--- "Pesca" o ID do JSON de resposta e salva (usar no GET) 
        ;       
            
    }

    @Test@Order(3)
    public void testGetBooking(){
       given()
            .contentType(ct)
            .log().all()
       
        .when()  
            .get(uri + "booking/" + bookingId)

        .then()
            .log().all()
            .statusCode(200)

            //Teste de Contrato 
            .body("$", hasKey("firstname"))
            .body("$", hasKey("lastname"))
            .body("$", hasKey("totalprice"))
            .body("$", hasKey("depositpaid"))
            .body("$", hasKey("bookingdates"))
            .body("bookingdates", hasKey("checkin"))
            .body("bookingdates", hasKey("checkout"))
            
            //Teste Funcional
            .body("firstname", is("Leandro"))
            .body("lastname", is("Cardinho"))
            .body("totalprice", is(150))
            .body("depositpaid", is(true))
            .body("additionalneeds", is("Breakfast"))
        ;

    }

}
 
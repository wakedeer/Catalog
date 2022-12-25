package inno.tech.api;

import inno.tech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    private final OAuth2RestTemplate oAuth2RestTemplate;

    @GetMapping("/product/{product_id}")
    public ResponseEntity<String> getProductInfo(@PathVariable("product_id") String productId) {
        var review = oAuth2RestTemplate.getForObject("http://localhost:8083/products/" + productId + "/reviews/report", String.class);
        //var valuserr = restTemplate.getForObject("https://www.adidas.co.uk/api/products/BB5476", String.class);


//        var client = HttpClient.newHttpClient();
//        var request = HttpRequest.newBuilder(
//                        URI.create("https://www.adidas.co.uk/api/products/BB5476"))
//                .header("accept", "application/json")
//                .build();
//
//        HttpResponse<String> response = null;
//        try {
//            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        var body = response.body();
//        System.out.println(body);

//        WebClient client = WebClient.builder()
//                .baseUrl("https://www.adidas.co.uk/api/products/BB5476")
////                .defaultCookie("cookieKey", "cookieValue")
////                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
////                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
//                .build();

        WebClient client = WebClient.create();

        var block = client.get().uri("https://www.adidas.co.uk/api/products/BB5476").retrieve().bodyToMono(String.class).block();


        return ResponseEntity.ok("OK with " + productId + "  " + review  + " " + block);

//        return productService.findProductData(productId)
//                .map(ResponseEntity::ok)
//                .orElseGet(ResponseEntity.notFound()::build);
    }
}

package d24.challenge.max.connect_layer;

import d24.challenge.max.exceptions.ClientException;
import d24.challenge.max.model.pojo.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static d24.challenge.max.utils.JsonInterpreter.getHttpMessageConverters;

@Service
public class RestTemplateLayer {

    @Value("${page.base_url_director_api}")
    String BASE_URL;

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateLayer.class);
    private final RestTemplate restTemplate;

    public RestTemplateLayer(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = getHttpMessageConverters();
        this.restTemplate.setMessageConverters(messageConverters);
    }

    public Page getMoviesByPage(int pageNumber) {
        try {
            return getMoviesByPerPage(pageNumber);
        } catch (Exception e) {
            throw new ClientException("Message: Movies list error, Error: ", e);
        }
    }

    @Async
    protected Page getMoviesByPerPage(int pageNumber) {
        try {
            logger.info("> Call to API - Page number -> " + pageNumber);
            String url = String.format(BASE_URL, pageNumber);
            logger.info("> Formed URI -> " + url);
            ResponseEntity<Page> results = getPageResponse(url);
            return results.getBody();
        } catch (Exception e) {
            throw new ClientException("Message: Pages list error, Error: ", e);
        }
    }

    private ResponseEntity<Page> getPageResponse(String url) throws URISyntaxException {
        return restTemplate.exchange(
                new URI(url),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
    }


}

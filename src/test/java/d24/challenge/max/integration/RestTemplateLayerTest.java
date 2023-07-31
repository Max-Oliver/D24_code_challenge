package d24.challenge.max.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import d24.challenge.max.connect_layer.RestTemplateLayer;
import d24.challenge.max.model.pojo.Movie;
import d24.challenge.max.model.pojo.Page;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.TestPropertySource;

import org.springframework.test.web.client.MockRestServiceServer;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@TestPropertySource(properties = {
        "page.test_base_url_director_api=https://directa24-movies.wiremockapi.cloud/api/movies/search?page=%s",
        "page.default_number=1"
})
@RestClientTest(RestTemplateLayer.class)
class RestTemplateLayerTest {

    @Value("${page.test_base_url_director_api}")
    String BASE_URL;

    @Value("${page.default_number}")
    String DEFAULT_VALUE;
    private static final String URI = "/users/{accountNumber}";
    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private Logger logger;

    @Autowired
    private RestTemplateLayer restTemplateLayer;

    @Mock
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @BeforeEach
    public void init() {
        logger = Mockito.mock(Logger.class);
        mappingJackson2HttpMessageConverter = Mockito.mock(MappingJackson2HttpMessageConverter.class);
       //mockServer = MockRestServiceServer.createServer(restTemplate);

    }

    @Test
    public void getMovies_HappyPath() throws Exception {
        Page pageOne = createPageMockObject();

        mockServer.expect(once(),
                        requestTo(String.format(BASE_URL, DEFAULT_VALUE)))
                .andRespond(
                        withSuccess(objectMapper.writeValueAsString(pageOne), MediaType.APPLICATION_JSON));

        List<Movie>  movies = restTemplateLayer.getMoviesByPage(1).getData();
        logger.info("Test > Movies: " + movies.get(0).toString());
        mockServer.verify();
    }

    @Test
    public void getPage_HappyPath() throws Exception {
        Page pageOne = createPageMockObject();
        mockServer.expect(once(),
                        requestTo(String.format(BASE_URL, DEFAULT_VALUE)))
                .andRespond(
                        withSuccess(objectMapper.writeValueAsString(pageOne), MediaType.APPLICATION_JSON));
        Page page = restTemplateLayer.getMoviesByPage(1);

        mockServer.verify();
        assertThat(page).extracting("totalPages");

    }

    private Page createPageMockObject() {
        List<Movie> movies = createMovieList();
        Page pageOne = new Page();
        pageOne.setPage(1);
        pageOne.setPerPage(10);
        pageOne.setTotal(26);
        pageOne.setTotal_pages(3);
        pageOne.setData(movies);

        return pageOne;
    }

    private List<Movie> createMovieList() {
        List<Movie> movies = new ArrayList<>();
        Movie movieOne = new Movie();
        movieOne.setActors("Andrew Garfield, Adam Driver, Liam Neeson");
        movieOne.setGenre("Drama, History");
        movieOne.setDirector("Martin Scorsese");
        movieOne.setWriter("Jay Cocks, Martin Scorsese, Shûsaku Endô");
        movieOne.setRuntime("161 min");
        movieOne.setReleased("13 Jan 2017");
        movieOne.setYear("2016");
        movieOne.setRated("R");
        movieOne.setTitle("Silence");
        movies.add(movieOne);
        return movies;
    }

}


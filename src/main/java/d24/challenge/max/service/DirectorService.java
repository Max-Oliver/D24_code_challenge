package d24.challenge.max.service;

import d24.challenge.max.connect_layer.RestTemplateLayer;
import d24.challenge.max.model.dto.DTODirector;
import d24.challenge.max.model.pojo.Movie;
import d24.challenge.max.model.pojo.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DirectorService {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateLayer.class);
    @Autowired
    private RestTemplateLayer restTemplateLayer;

    @Value("${page.default_number}")
    Integer DEFAULT_PAGE_NUMBER;

    /**
     * @note: This is not the better way to handle this type of calls. but to the purpose.
     * @advice: In these cases, we can create another api where we can get all information about the movies and don't get one by one pages.
     *          Or directly get filters in the current API to search by directors and how many movies they have.
     *
     * @steps:
     * First  - Get amount of pages
     * Second - Get whole information from the rest of the pages
     * Third: - Group Directors by amount of movie made (in order).
     * Four:  - Validate which Director pass the condition
     * */
    public List<DTODirector> getDirectorsCompleteSearch(int umbral) {
        Page pageOne = getAmountPagesByDefaultValue(DEFAULT_PAGE_NUMBER);
        List<Movie> movies = getAllMoviesByMaxPages(pageOne);
        TreeMap<String, Integer> directors = getCountMoviesByDirectorsMap(movies);
        return getAboveUmbralDirectors(umbral, directors);
    }

    public List<DTODirector> getDirectorsPartialSearch(int pageNumber, int umbral) {
        Page pageOne = getAmountPagesByDefaultValue(pageNumber);
        List<Movie> movies = new ArrayList<>(pageOne.getData());
        TreeMap<String, Integer> directors = getCountMoviesByDirectorsMap(movies);
        return getAboveUmbralDirectors(umbral, directors);
    }


    private static List<DTODirector> getAboveUmbralDirectors(int umbral,  TreeMap<String, Integer> directors) {
        List<DTODirector> expertDirectors = new ArrayList<>();
        directors.forEach((name, val) -> {
            logger.info(String.format("Este es el nombre del Director: %s, y este es el value: %s", name, val));
            if(val > umbral){
                expertDirectors.add(new DTODirector(name, val));
            }
        });

        return expertDirectors;
    }

    private static TreeMap<String, Integer> getCountMoviesByDirectorsMap( List<Movie> movies) {
        TreeMap<String, Integer> directors = new TreeMap<>();
        movies.forEach(movie -> {
            if (directors.containsKey(movie.getDirector())) {
                directors.computeIfPresent(movie.getDirector(), (key, cant) -> cant + 1);
            } else {
                directors.put(movie.getDirector(), 1);
            }
        });

        return directors;
    }

    private Page getAmountPagesByDefaultValue(int pageNumber) {
        return restTemplateLayer.getMoviesByPage(pageNumber);
    }

    private List<Movie> getAllMoviesByMaxPages(Page pageOne) {
        List<Movie> movies = new ArrayList<>(pageOne.getData());
        for (int page = 2; page <= pageOne.getTotalPages(); page++) {
            movies.addAll(restTemplateLayer.getMoviesByPage(page).getData());
        }
        return movies;
    }
}

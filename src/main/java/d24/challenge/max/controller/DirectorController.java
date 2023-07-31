package d24.challenge.max.controller;

import d24.challenge.max.model.dto.DTODirector;
import d24.challenge.max.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping( value = "/{umbral}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DTODirector> getDirectorsFull(@PathVariable(value = "umbral") int umbral) {
        return directorService.getDirectorsCompleteSearch(umbral);
    }

    @GetMapping( value = "/page/{page}/umbral/{umbral}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DTODirector> getDirectorsPartial(@PathVariable(value = "page") int pageNumber, @PathVariable(value = "umbral") int umbral) {
        return directorService.getDirectorsPartialSearch(pageNumber, umbral);
    }

}

package movierating;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDTO> listAllMovie() {
        return movieService.listAll();
    }

    @PostMapping
    public MovieDTO addMovie(@RequestBody CreateMovieCommand command) {
        return movieService.saveMovie(command);
    }

    @PostMapping("/{id}/rating")
    public MovieDTO addRating(@PathVariable("id") long id, @RequestBody CreateRatingCommand command) {
        return movieService.addRating(id, command);
    }
}

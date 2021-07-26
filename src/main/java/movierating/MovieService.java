package movierating;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@AllArgsConstructor
@Service
public class MovieService {

    private MovieRepository repository;

    private ModelMapper modelMapper;

    public List<MovieDTO> listAll() {
        Type targetListType = new TypeToken<List<MovieDTO>>(){}.getType();
        List<Movie> movies = repository.findAll();
        return modelMapper.map(movies, targetListType);
    }

    public MovieDTO saveMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());
        repository.save(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Transactional
    public MovieDTO addRating(long id, CreateRatingCommand command) {
        Movie movie = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find movie with id: " + id));
        movie.addRating(command.getRating());
        return modelMapper.map(movie, MovieDTO.class);
    }

}

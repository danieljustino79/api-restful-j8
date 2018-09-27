package com.ub.contract;

import com.ub.contract.response.EntityResponse;
import com.ub.models.Movie;
import com.ub.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("api/movie")
public class MovieEndpoint {

    @Autowired
    private MovieService movieService;

    @GetMapping("list")
    public ResponseEntity<EntityResponse<List<Movie>>> getMovies(){
        return ResponseEntity.ok(new EntityResponse<>(movieService.getMovies()));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<EntityResponse<Movie>> getMovieById(@PathVariable int id){
        try{
            Movie movie = movieService.getMovieById(id);
            return ResponseEntity.ok(new EntityResponse<Movie>(movie));
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public ResponseEntity<EntityResponse<Movie>> create(@Valid @RequestBody Movie movie,
                                                        BindingResult result){
        if(result.hasErrors()){
            List<String> errors = new ArrayList<String>();
            result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new EntityResponse<Movie>(errors));
        }
        return ResponseEntity.ok(new EntityResponse<Movie>(movieService.createMovie(movie)));
    }

    @PatchMapping("patch/{id}")
    public ResponseEntity<EntityResponse<Movie>> patch(@PathVariable int id,
                                                       @RequestBody HashMap<String, String> dateParam,
                                                       BindingResult result){
        Calendar date = Calendar.getInstance();

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date datex = f.parse(dateParam.get("date"));
            date.setTime(datex);
            return ResponseEntity.ok(new EntityResponse<Movie>(movieService.patchMovieDate(id, date)));
        } catch (ParseException e){
            List<String> list = new ArrayList<String>();
            list.add("Date bad format.");
            return ResponseEntity.badRequest().body(new EntityResponse<Movie>(list));
        } catch (IllegalArgumentException e){
            List<String> list = new ArrayList<String>();
            list.add(e.getMessage());
            return ResponseEntity.badRequest().body(new EntityResponse<Movie>(list));
        }
    }

    @PutMapping("put/{id}")
    public ResponseEntity<EntityResponse<Movie>> patch(@PathVariable int id,
                                                       @Valid @RequestBody Movie movie,
                                                       BindingResult result){
        if(result.hasErrors()){
            List<String> errors = new ArrayList<String>();
            result.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new EntityResponse<Movie>(errors));
        }
        try{
            return ResponseEntity.ok(new EntityResponse<Movie>(movieService.putMovie(id, movie)));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<EntityResponse<Integer>> delete(@PathVariable int id){
        try{
            movieService.removeMovie(id);
            return ResponseEntity.ok(new EntityResponse<Integer>(1));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}

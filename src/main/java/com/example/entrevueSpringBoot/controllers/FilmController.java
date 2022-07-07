package com.example.entrevueSpringBoot.controllers;
import com.example.entrevueSpringBoot.domains.Film;
import com.example.entrevueSpringBoot.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api")
public class FilmController {

    @Autowired
    private FilmService service;

    @GetMapping("/film/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable("id") Long filmId){
        Film film = new Film();
        HttpStatus statusCode;
        try {
            film = service.readFilm(filmId);
            statusCode = HttpStatus.OK;
        }
        catch(EntityNotFoundException enfe){
            statusCode = HttpStatus.NOT_FOUND;
        }
        catch (Exception ex){
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity
                .status(statusCode)
                .contentType(MediaType.APPLICATION_JSON)
                .body(film);
    }

    @PostMapping("/film")
    public ResponseEntity saveMovie(@RequestBody Film newFilm){
        Film film = service.saveFilm(newFilm);
        return ResponseEntity
                .status(film.equals(null)?HttpStatus.INTERNAL_SERVER_ERROR:HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(film);
    }
}

package com.example.entrevueSpringBoot.services;
import com.example.entrevueSpringBoot.domains.Film;
import com.example.entrevueSpringBoot.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    private FilmRepository repository;

    public Film readFilm (Long id){
        return repository.findById(id).get();
    }

    public Film saveFilm(Film filmToSave){
        return repository.save(filmToSave);
    }
}

package com.example.entrevueSpringBoot.repositories;

import com.example.entrevueSpringBoot.domains.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {

}

package com.online.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.entity.MovieShow;

public interface ShowRepository extends JpaRepository<MovieShow, Long> {}

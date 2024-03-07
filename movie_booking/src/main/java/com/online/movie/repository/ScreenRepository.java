package com.online.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Long> {}

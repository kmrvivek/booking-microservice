package com.online.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.online.movie.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

  Seat findBySeatNumber(String seatNumber);
}

package com.online.movie.service;

import java.util.List;
import com.online.movie.dto.BookingDetailDto;

public interface BookingService {

  public BookingDetailDto bookSeat(String userId, Long showId, List<String> selectedSeats);
}

package com.online.movie.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.online.movie.entity.Screen;
import com.online.movie.entity.Seat;
import com.online.movie.model.ScreenSeats;

@Component
public class ScreenSeatCreator {

  public List<Screen> createScreen(Map<String, List<ScreenSeats>> screenSeats) {
    List<Screen> screens = new ArrayList<>();
    for (Map.Entry<String, List<ScreenSeats>> screenSeat : screenSeats.entrySet()) {
      Screen screen = new Screen();
      screen.setScreenName(screenSeat.getKey());
      List<Seat> createdSeats = createSeats(screenSeat.getValue());
      screen.setSeats(createdSeats);
      screens.add(screen);
    }
    return screens;
  }

  private List<Seat> createSeats(List<ScreenSeats> seatsMapping) {
    List<Seat> seats = new ArrayList<>();
    for (ScreenSeats screenSeat : seatsMapping) {
      for (char i = screenSeat.getStartRow().charAt(0);
          i <= screenSeat.getEndRow().charAt(0);
          i++) {
        for (int j = 1; j <= screenSeat.getNumberOfSeats(); i++) {
          Seat seat = new Seat();
          seat.setSeatNumber(String.valueOf(i) + "" + j);
          seat.setSeatType(screenSeat.getType());
          seats.add(seat);
        }
      }
    }
    return seats;
  }
}

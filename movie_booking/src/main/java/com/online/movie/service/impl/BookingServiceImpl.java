package com.online.movie.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.online.movie.dto.BookingDetailDto;
import com.online.movie.dto.TicketDetailDto;
import com.online.movie.entity.MovieShow;
import com.online.movie.entity.Screen;
import com.online.movie.entity.Seat;
import com.online.movie.service.BookingService;
import com.online.movie.support.DiscountServiceConsumer;

@Service
public class BookingServiceImpl implements BookingService {

  private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

  @Autowired private EntityManager entityManager;

  @Autowired private DiscountServiceConsumer discountServiceConsumer;

  @Override
  public BookingDetailDto bookSeat(String userId, Long showId, List<String> selectedSeats) {
    log.info("Start booking of tickets for user " + userId);
    BookingDetailDto bookingDetailDto = new BookingDetailDto();
    try {
      double price = 0.0d;
      entityManager.getTransaction().begin();
      MovieShow show = entityManager.find(MovieShow.class, showId, LockModeType.OPTIMISTIC);
      if (show == null) {
        throw new IllegalArgumentException("Invalid show ID");
      }
      Screen screen = show.getScreen();
      for (String seatNumber : selectedSeats) {
        Query query =
            entityManager.createQuery(
                "SELECT s FROM Seat s WHERE s.screen = :screen AND s.seatNumber = :seatNumber",
                Seat.class);

        query.setParameter("screen", screen);
        query.setParameter("seatNumber", seatNumber);
        Seat seat = (Seat) query.getSingleResult();

        if (seat == null) {
          throw new IllegalArgumentException("Invalid seat number");
        }

        if (seat.isBooked()) {
          throw new IllegalStateException("Seat " + seatNumber + " is already booked");
        }
        seat.setBooked(true);
        price +=
            show.getPrices().stream()
                .filter(p -> p.getSeatType().equals(seat.getSeatType()))
                .findFirst()
                .get()
                .getCost();
        entityManager.merge(seat);
      }

      entityManager.getTransaction().commit();
      TicketDetailDto ticketDetailDto = new TicketDetailDto();
      ticketDetailDto.setBookingDate(
          createBookingDateTime(show.getMovieDate(), show.getMovieTime()));
      ticketDetailDto.setCategory("Wednesday");
      ticketDetailDto.setItemCount(selectedSeats.size());
      ticketDetailDto.setTotalPrice(price);
      double discountedPrice = discountServiceConsumer.calculateDiscount(ticketDetailDto);
      bookingDetailDto.setAddress(screen.getCinemaHall().getStreetAddress());
      bookingDetailDto.setBookingPrice(discountedPrice);
      bookingDetailDto.setSeatNumbers(selectedSeats);
      bookingDetailDto.setShowDate(
          show.getMovieDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
      bookingDetailDto.setShowTime(
          show.getMovieTime().toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
      bookingDetailDto.setTheatreName(screen.getCinemaHall().getName());
      return bookingDetailDto;

    } catch (Exception e) {
      entityManager.getTransaction().rollback();
      throw e;
    }
  }

  private LocalDateTime createBookingDateTime(Date movieDate, Date movieTime) {
    return LocalDateTime.of(
        movieDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
        movieTime.toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
  }
}

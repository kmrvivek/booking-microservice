package com.online.movie.support;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.online.movie.common.ErrorResponse;
import com.online.movie.exception.SeatReservedException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {SeatReservedException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponse handleException(SeatReservedException ex) {
    return ErrorResponse.builder()
        .httpStatus(HttpStatus.BAD_REQUEST)
        .status(HttpStatus.BAD_REQUEST.value())
        .errorMessages(Stream.of(ex.getMessage()).collect(Collectors.toList()))
        .build();
  }

  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody ErrorResponse handleOtherException(Exception ex) {
    return ErrorResponse.builder()
        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .errorMessages(Stream.of(ex.getMessage()).collect(Collectors.toList()))
        .build();
  }
}

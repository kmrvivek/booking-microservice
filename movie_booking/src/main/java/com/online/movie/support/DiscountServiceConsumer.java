package com.online.movie.support;

import static com.online.movie.util.BookingServiceConstant.DISCOUNT;
import static com.online.movie.util.BookingServiceConstant.DISCOUNT_SERVICE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.online.movie.dto.TicketDetailDto;

@FeignClient(DISCOUNT_SERVICE)
public interface DiscountServiceConsumer {

  @GetMapping(DISCOUNT)
  public Double calculateDiscount(@RequestBody TicketDetailDto ticketDetailDto);
}

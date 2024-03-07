package com.ms.core.common;

import java.util.List;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

  private HttpStatus httpStatus;
  private int status;
  private List<String> errorMessages;
  
}

package com.ms.user.command.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.core.user.model.UpdateUserDto;
import com.ms.core.user.model.UserDto;
import com.ms.user.command.service.IUserCommandService;

@WebMvcTest(UserCommandController.class)
class UserServiceControllerTests {

  @Autowired private MockMvc mvc;

  @MockBean private IUserCommandService iUserCommandService;

  @Test
  void createNewUserAPI() throws Exception {

    UserDto userDto = new UserDto();
    userDto.setFirstName("Rohit");
    userDto.setLastName("Sharma");
    userDto.setEmail("rkg130790@gmail.com");
    userDto.setAccountBalance(BigDecimal.valueOf(5000.24));
    userDto.setContactNumber("9560032156");
    userDto.setPassword("Hello123");
    userDto.setUserId(UUID.randomUUID().toString());

    when(this.iUserCommandService.createNewUser(userDto)).thenReturn(userDto.getUserId());

    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post("/api/user/addNewUser")
                    .content(asJsonString(userDto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();

    Assertions.assertThat(mvcResult.getResponse().getContentAsString())
        .isEqualTo(userDto.getUserId());
  }

  @Test
  void updateUserAPI() throws Exception {

    UpdateUserDto userDto = new UpdateUserDto();
    userDto.setFirstName("Rohit");
    userDto.setLastName("Sharma");
    userDto.setEmail("rkg130790@gmail.com");
    userDto.setContactNumber("9560032156");
    userDto.setPassword("Hello123");
    userDto.setUserId(UUID.randomUUID().toString());

    doNothing().when(this.iUserCommandService).updateExistingUser(userDto);

    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put("/api/user/updateUser")
                    .content(asJsonString(userDto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    Mockito.verify(this.iUserCommandService, times(1)).updateExistingUser(userDto);

    Assertions.assertThat(mvcResult.getResponse().getContentAsString())
        .isEqualTo("User is updated successfully");
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

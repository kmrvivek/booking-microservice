package com.online.discount.configuration;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.online.discount.model.DiscountRule;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "discount")
@Data
public class DiscountConfiguration {

  private List<DiscountRule> rules;
}

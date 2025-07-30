package com.lancao.practice.ignite.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class IgnitePropertiesConfig {
    @Value("${ignite.sequence.name:testing}")
    private String sequenceName;
    @Value("${ignite.sequence.iniVal:0}")
    private int iniVal;
}

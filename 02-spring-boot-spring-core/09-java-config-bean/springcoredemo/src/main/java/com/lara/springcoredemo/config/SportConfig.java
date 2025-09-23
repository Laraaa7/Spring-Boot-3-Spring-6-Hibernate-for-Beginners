package com.lara.springcoredemo.config;

import com.lara.springcoredemo.common.Coach;
import com.lara.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}

package com.lara.springboot.demo.demoapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // inyectar propiedades para: coach.name y team.name

   @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    // exponer nuevo endpoint para "teaminfo"

    @GetMapping("/teaminfo")
    public String getTeamInfo() {
        return "Coach: "+ coachName + ", Team name: "+ teamName;
    }

    //"/" que va a devolver "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    //endpoint para "workout"

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }

    // endpoint para "fortune"

    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day.";
    }
}

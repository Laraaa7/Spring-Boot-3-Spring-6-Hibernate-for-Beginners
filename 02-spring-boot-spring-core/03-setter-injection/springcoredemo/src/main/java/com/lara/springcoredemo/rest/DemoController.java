package com.lara.springcoredemo.rest;

import com.lara.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    // definir un campo private para la dependencia
    private Coach myCoach;
 /*
    //definir un constructor para la dependencia
    @Autowired
    public DemoController(Coach theCoach) {
        myCoach=theCoach;
    }
*/
   @Autowired
   public void setCoach(Coach theCoach) {
       myCoach = theCoach;
   }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}

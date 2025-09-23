package com.lara.springcoredemo.rest;

import com.lara.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    // definir un campo private para la dependencia
    private Coach myCoach;
    private Coach anotherCoach;

    //definir un constructor para la dependencia
    @Autowired
    public DemoController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("cricketCoach")Coach theAnotherCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach=theCoach;
        anotherCoach=theAnotherCoach;
    }


  /* @Autowired
   public void setCoach(Coach theCoach) {
       myCoach = theCoach;
   }
*/

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }


    @GetMapping("/check")
    public String check() {
        return "Comparing beans: myCoach == anotherCoach, " + (myCoach==anotherCoach);
    }
}
package com.lara.aopdemo;

import com.lara.aopdemo.dao.AccountDAO;
import com.lara.aopdemo.dao.MembershipDAO;
import com.lara.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
                                               MembershipDAO theMembershipDAO,
                                               TrafficFortuneService theTrafficFortuneService) {

        return runner ->
                // demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
                // demoTheAfterReturningAdvice(theAccountDAO);
                // demoTheAfterThrowingAdvice(theAccountDAO);
                // demoTheAfterAdvice(theAccountDAO);
                 //  demoTheAroundAdvice(theTrafficFortuneService);
                // demoTheAroundAdviceHandleException(theTrafficFortuneService);
                demoTheAroundAdviceRethrowException(theTrafficFortuneService);

    }

    private void demoTheAroundAdviceRethrowException(TrafficFortuneService theTrafficFortuneService) {

        System.out.println("\nMain Program: demoTheAroundAdviceRethrowException");

        System.out.println("Calling getFortune()");

        boolean tripWire = true;

        String data= theTrafficFortuneService.getFortune(tripWire);

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");
    }

    private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {

        System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

        System.out.println("Calling getFortune()");

        boolean tripWire = true;

        String data= theTrafficFortuneService.getFortune(tripWire);

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");

    }

    private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {

        System.out.println("\nMain Program: demoTheAroundAdvice");

        System.out.println("Calling getFortune()");

        String data= theTrafficFortuneService.getFortune();

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO theAccountDAO) {

        // llamar al metodo para encontrar cuentas
        List<Account> theAccounts = null;
        try{
            // añadir boolean para simular excepciones
            boolean tripWire = false;
            theAccounts = theAccountDAO.findAccounts(tripWire);

        }catch(Exception exc){
            System.out.println("\n\nMain Program: .... caught exception: "+ exc);
        }

        // mostrar las cuentas
        System.out.println("\n\nMain program: demoTheAfterReturningAdvice");
        System.out.println("-----");
        System.out.println(theAccounts);
        System.out.println("\n");
    }

    private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {

        // llamar al metodo para encontrar cuentas
        List<Account> theAccounts = null;
        try{
            // añadir boolean para simular excepciones
            boolean tripWire = true;
           theAccounts = theAccountDAO.findAccounts(tripWire);

        }catch(Exception exc){
            System.out.println("\n\nMain Program: .... caught exception: "+ exc);
        }

        // mostrar las cuentas
        System.out.println("\n\nMain program: demoTheAfterReturningAdvice");
        System.out.println("-----");
        System.out.println(theAccounts);
        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {

        // llamar al metodo para encontrar cuentas
        List<Account> theAccounts = theAccountDAO.findAccounts();

        // mostrar las cuentas
        System.out.println("\n\nMain program: demoTheAfterReturningAdvice");
        System.out.println("-----");
        System.out.println(theAccounts);
        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO,  MembershipDAO theMembershipDAO) {

        // llamar al metodo de negocio
        Account myAccount = new Account();
        myAccount.setName("Lara");
        myAccount.setLevel("Platinum");
        theAccountDAO.addAccount(myAccount, true);
        theAccountDAO.doWork();

        // llamar a los metodos getter/setter de accountdao
        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");

        String name = theAccountDAO.getName();
        String code = theAccountDAO.getServiceCode();

        // llamar al metodo membership
        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();


    }
}

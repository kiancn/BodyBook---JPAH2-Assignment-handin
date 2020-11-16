package kcn.kea.assignment.handin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HandinApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(HandinApplication.class, args);
        System.out.println("Message to log/command line. Body Book started.");
    }

}

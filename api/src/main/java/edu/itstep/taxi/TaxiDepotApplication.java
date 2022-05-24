package edu.itstep.taxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Drozdovskaya Anna
 * <p>
 * created 24.05.2022
 */
@SpringBootApplication(scanBasePackages = "edu.itstep.taxi")
public class TaxiDepotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxiDepotApplication.class, args);
    }
}

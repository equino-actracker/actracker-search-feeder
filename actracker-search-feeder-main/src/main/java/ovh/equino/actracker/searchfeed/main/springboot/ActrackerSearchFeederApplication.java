package ovh.equino.actracker.searchfeed.main.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ovh.equino.actracker.searchfeed.main.springboot")
public class ActrackerSearchFeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActrackerSearchFeederApplication.class, args);
    }

}

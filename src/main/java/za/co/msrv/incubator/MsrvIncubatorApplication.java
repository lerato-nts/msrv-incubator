package za.co.msrv.incubator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MsrvIncubatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsrvIncubatorApplication.class, args);
    }

}

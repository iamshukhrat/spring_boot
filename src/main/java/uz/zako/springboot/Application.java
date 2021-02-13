package uz.zako.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Scheduled(fixedRate = 5000L)
//	public void fixedRate(){
//		System.out.println("Now date ===> "+new Date());
//	}
//	@Scheduled(fixedDelay = 5000L)
//	public void fixedDelay(){
//		System.out.println("Now date ===> "+new Date());
//	}
//	@Scheduled(cron =


}

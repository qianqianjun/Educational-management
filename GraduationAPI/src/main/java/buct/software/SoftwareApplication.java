package buct.software;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("buct.software.dao")
public class SoftwareApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoftwareApplication.class, args);
    }
}

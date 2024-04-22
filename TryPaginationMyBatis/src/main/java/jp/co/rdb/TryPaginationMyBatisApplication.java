package jp.co.rdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("jp.co.rdb.repository")
@SpringBootApplication
public class TryPaginationMyBatisApplication {

	public static void main(String[] args) {

		SpringApplication.run(TryPaginationMyBatisApplication.class, args);

	}

}

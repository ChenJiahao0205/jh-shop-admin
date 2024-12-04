package pers.jhshop.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan(basePackages = "pers.jhshop.admin.mapper")
@SpringBootApplication
public class JhShopAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JhShopAdminServiceApplication.class, args);
    }

}

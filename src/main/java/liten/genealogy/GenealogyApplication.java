package liten.genealogy;

import jakarta.inject.Inject;
import java.util.Date;
import java.util.List;
import liten.genealogy.mainEntities.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {"liten.genealogy.webServices", "liten.genealogy.managers", "liten.genealogy.mainEntities"})
public class GenealogyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenealogyApplication.class, args);
    }

}

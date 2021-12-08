package edu.bbte.idde.zdim1981;

import edu.bbte.idde.zdim1981.dao.CpuDao;
import edu.bbte.idde.zdim1981.dao.MotherboardDao;
import edu.bbte.idde.zdim1981.model.Cpu;
import edu.bbte.idde.zdim1981.model.Motherboard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Main {
    @Autowired
    private CpuDao cpuDao;
    @Autowired
    private MotherboardDao motherboardDao;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            for (Cpu cpu : cpuDao.readAll()) {
                log.info("{}", cpu);
            }

            for (Motherboard motherboard : motherboardDao.readAll()) {
                log.info("{}", motherboard);
            }
        };
    }
}

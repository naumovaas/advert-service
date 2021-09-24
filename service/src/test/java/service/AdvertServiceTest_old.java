package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.tsc.anaumova.advertservice.repository.AdvertRepository;

import static org.junit.Assert.assertTrue;

//@Testcontainers
//@RunWith(SpringRunner.class)
//@ContextConfiguration(initializers = {AdvertServiceTest.Initializer.class})
//@SpringBootTest(classes = TestConfig.class)
public class AdvertServiceTest_old {

//    @Autowired
//    private AdvertRepository advertRepository;
//
//    @Container
//    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
//            .withDatabaseName("test")
//            .withUsername("admin")
//            .withPassword("admin")
//            .withInitScript("test_data.sql");
//
//    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            TestPropertyValues.of(
//                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
//                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
//                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
//            ).applyTo(configurableApplicationContext.getEnvironment());
//        }
//
//    }
//
//    @Test
//    public void shouldAnswerWithTrue() {
//        assertTrue(true);
//    }

}
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class AppSpringDataApplicationTests {
    // tag::verify[]
    final Driver driver;
    public AppSpringDataApplicationTests(@Autowired Driver driver) {
        this.driver = driver;
    }

    @Test
    final void testConnection() {
        driver.verifyConnectivity();
    }
    // end::verify[]
}
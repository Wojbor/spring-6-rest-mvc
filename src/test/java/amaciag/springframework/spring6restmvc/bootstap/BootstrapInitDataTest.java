package amaciag.springframework.spring6restmvc.bootstap;

import amaciag.springframework.spring6restmvc.repositories.BeerRepository;
import amaciag.springframework.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BootstrapInitDataTest {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    BootstrapInitData bootstrapInitData;

    @BeforeEach
    void setUp() {
        bootstrapInitData = new BootstrapInitData(beerRepository, customerRepository);
    }

    @Test
    void runTest() throws Exception {
        bootstrapInitData.run(null);

        assertThat(beerRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}
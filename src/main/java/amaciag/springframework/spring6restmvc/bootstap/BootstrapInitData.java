package amaciag.springframework.spring6restmvc.bootstap;

import amaciag.springframework.spring6restmvc.entities.Beer;
import amaciag.springframework.spring6restmvc.entities.Customer;
import amaciag.springframework.spring6restmvc.model.BeerStyle;
import amaciag.springframework.spring6restmvc.repositories.BeerRepository;
import amaciag.springframework.spring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class BootstrapInitData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        Beer beer1 = Beer.builder()
                .beerName("Zimny Lokiec")
                .beerStyle(BeerStyle.IPA)
                .upc("123456")
                .price(new BigDecimal("15.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .beerName("Mokra Maczeta")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("1234563333")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(333)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .beerName("Wojownik")
                .beerStyle(BeerStyle.PORTER)
                .upc("1236666")
                .price(new BigDecimal("15.99"))
                .quantityOnHand(444)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Customer customer1 = Customer
                .builder()
                .customerName("Arek")
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        Customer customer2 = Customer
                .builder()
                .customerName("Lila")
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        Customer customer3 = Customer
                .builder()
                .customerName("Marek")
                .createdDate(LocalDateTime.now())
                .lastModifyDate(LocalDateTime.now())
                .build();

        beerRepository.save(beer1);
        beerRepository.save(beer2);
        beerRepository.save(beer3);

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        log.debug("3 Beers and Customers were created and saved.");
    }
}

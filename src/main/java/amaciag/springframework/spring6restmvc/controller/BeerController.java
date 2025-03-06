package amaciag.springframework.spring6restmvc.controller;

import amaciag.springframework.spring6restmvc.model.Beer;
import amaciag.springframework.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_URI = "/api/v1/beer";
    public static final String BEER_URI_ID = BEER_URI + "/{beerId}";

    private final BeerService beerService;

    @PatchMapping(BEER_URI_ID)
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {

        beerService.patchBeerById(beerId,beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_URI_ID)
    public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID uuid) {

        beerService.deleteById(uuid);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_URI_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {

        beerService.updateBeerById(beerId, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_URI)
    public ResponseEntity saveNewBeer(@RequestBody Beer beer) {

        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping(BEER_URI)
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping(BEER_URI_ID)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(beerId);
    }
}

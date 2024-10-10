package amaciag.springframework.spring6restmvc.services;

import amaciag.springframework.spring6restmvc.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);
}

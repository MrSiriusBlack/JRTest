package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest")
public class ShipController {

    @Autowired
    private ShipRepository shipRepository;

    @GetMapping(value = "/ships")
    public @ResponseBody
    List<Ship> getShipList(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "planet", required = false) String planet,
            @RequestParam(name = "shipType", required = false) ShipType shipType,
            @RequestParam(name = "after", required = false) Long after,
            @RequestParam(name = "before", required = false) Long before,
            @RequestParam(name = "isUsed", required = false) Boolean isUsed,
            @RequestParam(name = "minSpeed", required = false) Double minSpeed,
            @RequestParam(name = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(name = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam(name = "maxCrewSize", required = false) Integer maxCrewSize,
            @RequestParam(name = "minRating", required = false) Double minRating,
            @RequestParam(name = "maxRating", required = false) Double maxRating,
            @RequestParam(name = "order", required = false, defaultValue = "ID") ShipOrder order,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") Integer pageSize
            ) {
        int a = 0;
        return shipRepository.getShipList(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating, order, pageNumber, pageSize);
    }

    @GetMapping(value = "/ships/count")
    public @ResponseBody long getShipsCount(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "planet", required = false) String planet,
            @RequestParam(name = "shipType", required = false) ShipType shipType,
            @RequestParam(name = "after", required = false) Long after,
            @RequestParam(name = "before", required = false) Long before,
            @RequestParam(name = "isUsed", required = false) Boolean isUsed,
            @RequestParam(name = "minSpeed", required = false) Double minSpeed,
            @RequestParam(name = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(name = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam(name = "maxCrewSize", required = false) Integer maxCrewSize,
            @RequestParam(name = "minRating", required = false) Double minRating,
            @RequestParam(name = "maxRating", required = false) Double maxRating
    ) {
        return shipRepository.getShipsCount(name, planet, shipType, after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating);
    }

    @PostMapping(value = "/ships")
    public @ResponseBody void createShip(
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "planet", required = true) String planet,
            @RequestParam(name = "shipType", required = true) ShipType shipType,
            @RequestParam(name = "prodDate", required = true) Long prodDate,
            @RequestParam(name = "isUsed", required = false, defaultValue = "false") Boolean isUsed,
            @RequestParam(name = "speed", required = true) Double speed,
            @RequestParam(name = "crewSize", required = true) Integer crewSize
    ) {

    }

    @GetMapping(value = "/ships/{id}")
    public @ResponseBody Ship getShip(
            @PathVariable(value = "id") int id
    ) {
        return shipRepository.getShip(id);
    }

    @PostMapping(value = "/ships/{id}")
    public @ResponseBody void updateShip(
            @PathVariable(value = "id") int id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "planet", required = false) String planet,
            @RequestParam(name = "shipType", required = false) ShipType shipType,
            @RequestParam(name = "prodDate", required = false) Long prodDate,
            @RequestParam(name = "isUsed", required = false) Boolean isUsed,
            @RequestParam(name = "speed", required = false) Double speed,
            @RequestParam(name = "crewSize", required = false) Integer crewSize
    ) {

    }

    @DeleteMapping(value = "/ships/{id}")
    public @ResponseBody void deleteShip() {

    }
}

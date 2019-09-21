package com.space.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rest")
public class ShipController {

    @Autowired
    private ShipRepository shipRepository;

//    @Autowired
//    private HttpServletRequest request;

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

    @PostMapping(value = "/ships/")
    public @ResponseBody Ship createShip(HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(request.getInputStream(), new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = map.containsKey("name") ? (String) map.get("name") : null;
        String planet = map.containsKey("planet") ? (String) map.get("planet") : null;
        ShipType shipType = map.containsKey("shipType") ? ShipType.valueOf((String) map.get("shipType")) : null;
        Long prodDate = map.containsKey("prodDate") ? (Long) map.get("prodDate") : null;
        boolean isUsed = map.containsKey("isUsed") ? (boolean) map.get("isUsed") : false;
        Double speed = map.containsKey("speed") ? Double.parseDouble((String) map.get("speed")) : null;
        Integer crewSize = map.containsKey("crewSize") ? Integer.parseInt((String) map.get("crewSize")) : null;

        if (name == null || planet == null || shipType == null || prodDate == null || speed == null || crewSize == null)
            throw  new ShipBadRequest();
        if (name.equals("") || planet.equals("") || name.length() > 50 || planet.length() > 50)
            throw  new ShipBadRequest();
        speed = Math.round(speed * 100) / 100.0;
        if (speed < 0.01 || speed > 0.99 || crewSize < 1 || crewSize > 9999)
            throw  new ShipBadRequest();
        if (prodDate < 0)
            throw  new ShipBadRequest();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(prodDate);
        int year = calendar.get(Calendar.YEAR);
        if (year < 2800 || year > 3019)
            throw  new ShipBadRequest();

        Ship ship = shipRepository.createShip(name, planet, shipType, prodDate, isUsed, speed, crewSize);
        return ship;
    }

    @GetMapping(value = "/ships/{id}")
    public @ResponseBody Ship getShip(
            @PathVariable(value = "id") Long id
    ) {
        Ship ship = shipRepository.getShip(id);
        if (ship == null)
            throw new ShipNotFoundException();
        if (id <= 0)
            throw new ShipBadRequest();
        return shipRepository.getShip(id);
    }

    @PostMapping(value = "/ships/{id}")
    public @ResponseBody Ship updateShip (
            @PathVariable(value = "id") Long id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (shipRepository.getShip(id) == null)
            throw new ShipNotFoundException();
        if (id <= 0)
            throw new ShipBadRequest();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(request.getInputStream(), new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = map.containsKey("name") ? (String) map.get("name") : null;
        String planet = map.containsKey("planet") ? (String) map.get("planet") : null;
        ShipType shipType = map.containsKey("shipType") ? ShipType.valueOf((String) map.get("shipType")) : null;
        Long prodDate = map.containsKey("prodDate") ? (Long) map.get("prodDate") : null;
        Boolean isUsed = map.containsKey("isUsed") ? (boolean) map.get("isUsed") : null;
        Double speed = map.containsKey("speed") ? Double.parseDouble((String) map.get("speed")) : null;
        Integer crewSize = map.containsKey("crewSize") ? Integer.parseInt((String) map.get("crewSize")) : null;

        Ship ship = shipRepository.updateShip(id, name, planet, shipType, prodDate, isUsed, speed, crewSize);
        return ship;
    }

    @DeleteMapping(value = "/ships/{id}")
    public @ResponseBody void deleteShip(@PathVariable(value = "id") Long id) {
        if (shipRepository.getShip(id) == null)
            throw new ShipNotFoundException();
        if (id <= 0)
            throw new ShipBadRequest();
        shipRepository.deleteShip(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class ShipNotFoundException extends RuntimeException { }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class ShipBadRequest extends RuntimeException { }
}

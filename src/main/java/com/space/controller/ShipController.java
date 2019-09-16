package com.space.controller;

import com.space.model.Ship;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rest")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @GetMapping(value = "/ships")
    public @ResponseBody
    ModelAndView getShipList() {
        return shipService.getShipList();
    }

    @GetMapping(value = "/ships/count")
    public @ResponseBody int getShipsCount() {
        return shipService.getShipsCount();
    }

    @PostMapping(value = "/ships")
    public @ResponseBody void createShip() {

    }

    @GetMapping(value = "/ships/{id}")
    public @ResponseBody Ship getShip() {
        return shipService.getShip();
    }

    @PostMapping(value = "/ships/{id}")
    public @ResponseBody void updateShip() {

    }

    @DeleteMapping(value = "/ships/{id}")
    public @ResponseBody void deleteShip() {

    }
}

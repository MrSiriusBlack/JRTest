package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipDaoImpl;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipService {

    private ShipRepository shipRepository = new ShipDaoImpl();

    @Transactional
    public ModelAndView getShipList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        List<Ship> ships = shipRepository.getShipList();

        modelAndView.addObject(ships);
        return modelAndView;
    }

    public Ship getShip() {
        return new Ship();
    }

    public int getShipsCount() {return 1;}
}

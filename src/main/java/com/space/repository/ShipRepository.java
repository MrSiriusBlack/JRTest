package com.space.repository;

import com.space.model.Ship;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository {

    List<Ship> getShipList();
}

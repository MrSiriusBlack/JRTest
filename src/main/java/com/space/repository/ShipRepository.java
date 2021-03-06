package com.space.repository;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;

import java.util.List;

public interface ShipRepository {

    List<Ship> getShipList(
            String name,
            String planet,
            ShipType shipType,
            Long after,
            Long before,
            Boolean isUsed,
            Double minSpeed,
            Double maxSpeed,
            Integer minCrewSize,
            Integer maxCrewSize,
            Double minRating,
            Double maxRating,
            ShipOrder shipOrder,
            Integer pageNumber,
            Integer pageSize
    );
    long getShipsCount(
            String name,
            String planet,
            ShipType shipType,
            Long after,
            Long before,
            Boolean isUsed,
            Double minSpeed,
            Double maxSpeed,
            Integer minCrewSize,
            Integer maxCrewSize,
            Double minRating,
            Double maxRating
    );
    Ship getShip(Long id);
    Ship createShip(
            String name,
            String planet,
            ShipType shipType,
            Long prodDate,
            boolean isUsed,
            Double speed,
            Integer crewSize
    );
    Ship updateShip(
            Long id,
            String name,
            String planet,
            ShipType shipType,
            Long prodDate,
            Boolean isUsed,
            Double speed,
            Integer crewSize
    );
    void deleteShip(Long id);
}

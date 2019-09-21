package com.space.service;

import com.space.repository.ShipRepository;
import org.springframework.stereotype.Service;
import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Repository
@Transactional

@Service
public class ShipService implements ShipRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Ship> getShipList(
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
    ) {
        Query query = em.createQuery("from Ship s where (:name is null or s.name like :name) " +
                "and (:planet is null or s.planet like :planet) " +
                "and (:shipType is null or s.shipType = :shipType) " +
                "and (:afterDate is null or s.prodDate > :afterDate) " +
                "and (:beforeDate is null or s.prodDate < :beforeDate) " +
                "and (:minCrewSize is null or s.crewSize >= :minCrewSize) " +
                "and (:maxCrewSize is null or s.crewSize <= :maxCrewSize) " +
                "and (:minSpeed is null or s.speed >= :minSpeed) "  +
                "and (:maxSpeed is null or s.speed <= :maxSpeed) " +
                "and (:minRating is null or s.rating >= :minRating) " +
                "and (:maxRating is null or s.rating <= :maxRating) " +
                "and (:isUsed is null or s.isUsed = :isUsed) ", Ship.class);
        String nameParam = (name == null ? name : "%" + name + "%");
        query.setParameter("name", nameParam);
        String planetParam = (planet == null ? planet : "%" + planet + "%");
        query.setParameter("planet", planetParam);
        query.setParameter("shipType", shipType);
        query.setParameter("minCrewSize", minCrewSize);
        query.setParameter("maxCrewSize", maxCrewSize);
        query.setParameter("minSpeed", minSpeed);
        query.setParameter("maxSpeed", maxSpeed);
        query.setParameter("minRating", minRating);
        query.setParameter("maxRating", maxRating);
        query.setParameter("isUsed", isUsed);

        Calendar calendar = Calendar.getInstance();
        Date afterParam;
        Date beforeParam;
        if (after != null) {
            calendar.setTimeInMillis(after);
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            afterParam = calendar.getTime();
        }
        else
            afterParam = null;
        if (before != null) {
            calendar.setTimeInMillis(before);
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            beforeParam = calendar.getTime();
        }
        else
            beforeParam = null;

        query.setParameter("afterDate", afterParam);
        query.setParameter("beforeDate", beforeParam);

        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        List<Ship> ships = query.getResultList();

        switch (shipOrder) {
            case SPEED:
                Collections.sort(ships, (s1 , s2) -> Double.compare(s1.getSpeed(), s2.getSpeed()));
                break;
            case DATE:
                Collections.sort(ships, (s1 , s2) -> s1.getProdDate().compareTo(s2.getProdDate()));
                break;
            case RATING:
                Collections.sort(ships, (s1 , s2) -> Double.compare(s1.getRating(), s2.getRating()));
                break;
            case ID:
                Collections.sort(ships, (s1, s2) -> Long.compare(s1.getId(), s2.getId()));
        }

        return ships;
    }

    @Override
    public long getShipsCount(
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
    ) {
        Query query = em.createQuery("Select count (s.id) from Ship s where " +
                "(:name is null or s.name like :name) " +
                "and (:planet is null or s.planet like :planet) " +
                "and (:shipType is null or s.shipType = :shipType) " +
                "and (:afterDate is null or s.prodDate > :afterDate) " +
                "and (:beforeDate is null or s.prodDate < :beforeDate) " +
                "and (:minCrewSize is null or s.crewSize >= :minCrewSize) " +
                "and (:maxCrewSize is null or s.crewSize <= :maxCrewSize) " +
                "and (:minSpeed is null or s.speed >= :minSpeed) "  +
                "and (:maxSpeed is null or s.speed <= :maxSpeed) " +
                "and (:minRating is null or s.rating >= :minRating) " +
                "and (:maxRating is null or s.rating <= :maxRating) " +
                "and (:isUsed is null or s.isUsed = :isUsed) ", Long.class);

        String nameParam = (name == null ? name : "%" + name + "%");
        query.setParameter("name", nameParam);
        String planetParam = (planet == null ? planet : "%" + planet + "%");
        query.setParameter("planet", planetParam);
        query.setParameter("shipType", shipType);
        query.setParameter("minCrewSize", minCrewSize);
        query.setParameter("maxCrewSize", maxCrewSize);
        query.setParameter("minSpeed", minSpeed);
        query.setParameter("maxSpeed", maxSpeed);
        query.setParameter("minRating", minRating);
        query.setParameter("maxRating", maxRating);
        query.setParameter("isUsed", isUsed);

        Calendar calendar = Calendar.getInstance();
        Date afterParam;
        Date beforeParam;
        if (after != null) {
            calendar.setTimeInMillis(after);
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            afterParam = calendar.getTime();
        }
        else
            afterParam = null;
        if (before != null) {
            calendar.setTimeInMillis(before);
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            beforeParam = calendar.getTime();
        }
        else
            beforeParam = null;

        query.setParameter("afterDate", afterParam);
        query.setParameter("beforeDate", beforeParam);

        return (Long)query.getSingleResult();
    }

    @Override
    public Ship getShip(Long id) {
        return em.find(Ship.class, id);
    }

    @Override
    public Ship createShip(
            String name,
            String planet,
            ShipType shipType,
            Long prodDate,
            boolean isUsed,
            Double speed,
            Integer crewSize
    ) {
        Calendar calendar = Calendar.getInstance();
        Date prodDateParam;
        calendar.setTimeInMillis(prodDate);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        prodDateParam = calendar.getTime();
        Ship ship = new Ship(name, planet, shipType, prodDateParam, isUsed, speed, crewSize);
        em.merge(ship);

        Query query = em.createQuery("select max (id) from Ship ", Long.class);
        ship.setId((Long) query.getSingleResult());

        return ship;
    }

    @Override
    public Ship updateShip(
            Long id,
            String name,
            String planet,
            ShipType shipType,
            Long prodDate,
            Boolean isUsed,
            Double speed,
            Integer crewSize
    ) {
        Ship ship = getShip(id);
        if (name != null)
            ship.setName(name);
        if (planet != null)
            ship.setPlanet(planet);
        if (shipType != null)
            ship.setShipType(shipType);
        if (prodDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(prodDate);
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            ship.setProdDate(calendar.getTime());
        }
        if (isUsed != null)
            ship.setUsed(isUsed);
        if (speed != null)
            ship.setSpeed(speed);
        if (crewSize != null)
            ship.setCrewSize(crewSize);
        ship.setRating();

        return ship;
    }

    @Override
    public void deleteShip(Long id) {
        em.remove(getShip(id));
    }

}

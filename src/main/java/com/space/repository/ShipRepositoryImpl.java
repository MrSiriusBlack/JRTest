package com.space.repository;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ShipRepositoryImpl implements ShipRepository{

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
                "and (:planet is null or s.planet = :planet) " +
                "and (:shipType is null or s.shipType = :shipType) " +
                "and (:afterDate is null or s.prodDate >= :afterDate) " +
                "and (:beforeDate is null or s.prodDate <= :beforeDate) " +
                "and (:minCrewSize is null or s.crewSize >= :minCrewSize) " +
                "and (:maxCrewSize is null or s.crewSize <= :maxCrewSize) " +
                "and (:minSpeed is null or s.speed >= :minSpeed) "  +
                "and (:maxSpeed is null or s.speed <= :maxSpeed) " +
                "and (:minRating is null or s.rating >= :minRating) " +
                "and (:maxRating is null or s.rating <= :maxRating) " +
                "and (:isUsed is null or s.isUsed = :isUsed) ", Ship.class);
        String nameParam = (name == null ? name : "%" + name + "%");
        query.setParameter("name", nameParam);
        query.setParameter("planet", planet);
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
            afterParam = calendar.getTime();
        }
        else
            afterParam = null;
        if (before != null) {
            calendar.setTimeInMillis(before);
            calendar.set(Calendar.MONDAY, 11);
            calendar.set(Calendar.DAY_OF_MONTH, 31);
            beforeParam = calendar.getTime();
        }
        else
            beforeParam = null;

        query.setParameter("afterDate", afterParam);
        query.setParameter("beforeDate", beforeParam);
//        query.setParameter("shipOrder", shipOrder.getFieldName());

        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
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
                "and (:planet is null or s.planet = :planet) " +
                "and (:shipType is null or s.shipType = :shipType) " +
                "and (:afterDate is null or s.prodDate >= :afterDate) " +
                "and (:beforeDate is null or s.prodDate <= :beforeDate) " +
                "and (:minCrewSize is null or s.crewSize >= :minCrewSize) " +
                "and (:maxCrewSize is null or s.crewSize <= :maxCrewSize) " +
                "and (:minSpeed is null or s.speed >= :minSpeed) "  +
                "and (:maxSpeed is null or s.speed <= :maxSpeed) " +
                "and (:minRating is null or s.rating >= :minRating) " +
                "and (:maxRating is null or s.rating <= :maxRating) " +
                "and (:isUsed is null or s.isUsed = :isUsed) ", Long.class);

        String nameParam = (name == null ? name : "%" + name + "%");
        query.setParameter("name", nameParam);
        query.setParameter("planet", planet);
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
            afterParam = calendar.getTime();
        }
        else
            afterParam = null;
        if (before != null) {
            calendar.setTimeInMillis(before);
            calendar.set(Calendar.MONDAY, 11);
            calendar.set(Calendar.DAY_OF_MONTH, 31);
            beforeParam = calendar.getTime();
        }
        else
            beforeParam = null;

        query.setParameter("afterDate", afterParam);
        query.setParameter("beforeDate", beforeParam);

        return (Long)query.getSingleResult();
    }

    @Override
    public Ship getShip(int id) {
        return null;
    }
}

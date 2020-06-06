package com.boca.grabswebservice.service.dashboard;

import com.boca.grabswebservice.dom.Dashboard;
import com.boca.grabswebservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
public class QueryService implements  IQueryService{


    @Autowired
    EntityManagerFactory emf;


    @Autowired
    private TruckService truckService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private MateService mateService;

    @Autowired
    private CompanyOwnerService companyOwnerService;

    @Autowired
    private PrivateOwnerService privateOwnerService;

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @Autowired
    private MerchantService merchantService;


    @Override
    public Dashboard getAdminDetails() {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin( );

        Dashboard dashboard = em.createQuery("select new com.boca.grabswebservice.dom.Dashboard (count(*) as truckCount, " +
                "(select count(*) from Driver d where d.deleted_by = 0 ORDER BY d.id DESC) AS driverCount," +
                "(select count(*) from Trip trp where trp.deleted_by = 0 ORDER BY trp.id DESC) AS tripCount," +
                "(select count(*) from Mate m where m.deleted_by = 0 ORDER BY m.id DESC) AS mateCount," +
                "(select count(*) from Trip ct where status ='End' and ct.deleted_by = 0 ORDER BY ct.id DESC) AS completedTripCount," +
                "(SELECT SUM(CASE WHEN status ='End' THEN e.charge ELSE 0 END) FROM Trip e where status ='End') AS earning)" +
                " from Truck t where t.deleted_by = 0 ORDER BY t.id DESC"
               , Dashboard.class).getSingleResult();




        em.close();

        return dashboard;
    }

    @Override
    public Dashboard getCompanyOwnerDetails() {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin( );

        Dashboard dashboard = em.createQuery("select new com.boca.logistica.dom.Dashboard (count(*) as truckCount, " +
                        "(select count(*) from Driver d where d.deleted_by = 0 ORDER BY d.id DESC) AS driverCount," +
                        "(select count(*) from Trip trp where trp.deleted_by = 0 ORDER BY trp.id DESC) AS tripCount," +
                        "(select count(*) from Mate m where m.deleted_by = 0 ORDER BY m.id DESC) AS mateCount," +
                        "(select count(*) from Trip ct where status ='End' and ct.deleted_by = 0 ORDER BY ct.id DESC) AS completedTripCount," +
                        "(SELECT SUM(CASE WHEN status ='End' THEN e.charge ELSE 0 END) FROM Trip e where status ='End') AS earning)" +
                        " from Truck t where t.deleted_by = 0 ORDER BY t.id DESC"
                , Dashboard.class).getSingleResult();




        em.close();

        return dashboard;
    }

    @Override
    public Dashboard getPrivateOwnerDetails() {
        return null;
    }

    @Override
    public Dashboard getDriverDetails() {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin( );

        Dashboard dashboard = em.createQuery("select new com.boca.logistica.dom.Dashboard (count(*) as truckCount, " +
                        "(select count(*) from Driver d where d.deleted_by = 0 ORDER BY d.id DESC) AS driverCount," +
                        "(select count(*) from Trip trp where trp.deleted_by = 0 ORDER BY trp.id DESC) AS tripCount," +
                        "(select count(*) from Mate m where m.deleted_by = 0 ORDER BY m.id DESC) AS mateCount," +
                        "(select count(*) from Trip ct where status ='End' and ct.deleted_by = 0 ORDER BY ct.id DESC) AS completedTripCount," +
                        "(SELECT SUM(CASE WHEN status ='End' THEN e.charge ELSE 0 END) FROM Trip e where status ='End') AS earning)" +
                        " from Truck t where t.deleted_by = 0 ORDER BY t.id DESC"
                , Dashboard.class).getSingleResult();




        em.close();

        return dashboard;
    }
}

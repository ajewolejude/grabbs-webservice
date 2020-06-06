package com.boca.grabswebservice.service.dashboard;


import com.boca.grabswebservice.dom.Dashboard;

public interface IQueryService {

    Dashboard getAdminDetails();
    Dashboard getCompanyOwnerDetails();
    Dashboard getPrivateOwnerDetails();
    Dashboard getDriverDetails();
}

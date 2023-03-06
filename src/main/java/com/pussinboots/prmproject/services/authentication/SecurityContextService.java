package com.pussinboots.prmproject.services.authentication;

import com.pussinboots.prmproject.data.entities.Customer;
import com.pussinboots.prmproject.data.entities.Staff;

public interface SecurityContextService {
    public void setSecurityContext(String username);

    public Staff getCurrentStaff();

    public Customer getCurrentCustomer();
}

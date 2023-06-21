package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetails;

public interface AuthenticationService {

    String generateJwtToken(AccessDetails accessDetails);

}

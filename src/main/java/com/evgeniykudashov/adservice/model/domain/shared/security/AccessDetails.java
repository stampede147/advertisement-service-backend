package com.evgeniykudashov.adservice.model.domain.shared.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface AccessDetails extends UserDetails {

    UsernameAndPassword getUsernameAndPassword();
}

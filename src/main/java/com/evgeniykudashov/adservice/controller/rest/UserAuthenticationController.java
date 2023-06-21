package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetails;
import com.evgeniykudashov.adservice.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentications")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserAuthenticationController {

    protected AuthenticationService authenticationService;

    @SneakyThrows
    @PostMapping("/jwts")
    public ResponseEntity<?> createAuthentication(@RequestBody AccessDetails accessDetails) {
        return ResponseEntity
                .ok(authenticationService.generateJwtToken(accessDetails));
    }


}

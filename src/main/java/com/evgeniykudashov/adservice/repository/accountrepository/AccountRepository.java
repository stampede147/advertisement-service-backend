package com.evgeniykudashov.adservice.repository.accountrepository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends ListCrudRepository<AccountRepository, Long> {
}

package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.advertisementEdit.EditLayoutStep;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface EditLayoutStepRepository extends ListCrudRepository<EditLayoutStep, Long> {


    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "fields")
    List<EditLayoutStep> findAllByCategoryIdOrderByPosition(long categoryId);
}

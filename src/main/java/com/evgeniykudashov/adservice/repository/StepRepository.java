package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.step.Step;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface StepRepository extends ListCrudRepository<Step, Long> {


    public List<Step> getStepsByCategoryId(long categoryId);
}

package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.field.Field;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface FieldRepository extends ListCrudRepository<Field, Long> {


    @Query("select f from Field f where  f.step.id in :stepIds")
    public List<Field> getFieldsByStepIds(long[] stepIds);
}

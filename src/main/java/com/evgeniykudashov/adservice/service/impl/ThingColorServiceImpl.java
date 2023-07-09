package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.model.advertisement.ThingColor;
import com.evgeniykudashov.adservice.repository.ThingColorRepository;
import com.evgeniykudashov.adservice.service.ThingColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ThingColorServiceImpl implements ThingColorService {

    private final ThingColorRepository thingColorRepository;

    @Override
    public String createThingColor(ThingColor color) {
        return thingColorRepository.save(color).getColor();
    }

    @Override
    public void deleteThingColorByColor(String color) {
        this.thingColorRepository.deleteById(color);
    }

    @Override
    public List<ThingColor> getThingColors() {
        return this.thingColorRepository.findAll();
    }
}

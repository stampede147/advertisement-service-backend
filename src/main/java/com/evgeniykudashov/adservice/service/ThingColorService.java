package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.advertisement.ThingColor;

import java.util.List;

public interface ThingColorService {

    String createThingColor(ThingColor color);

    void deleteThingColorByColor(String color);

    List<ThingColor> getThingColors();
}

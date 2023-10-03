package com.evgeniykudashov.adservice.dto;

import com.evgeniykudashov.adservice.model.advertisementEdit.ViewSize;
import lombok.Data;

@Data
public class ViewConfigDto {

    public ViewSize size;

    public String type;
}

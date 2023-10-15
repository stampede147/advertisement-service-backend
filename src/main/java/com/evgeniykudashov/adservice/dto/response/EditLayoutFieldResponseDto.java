package com.evgeniykudashov.adservice.dto.response;

import com.evgeniykudashov.adservice.dto.ViewConfigDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditLayoutFieldResponseDto {

    private long id;

    private String name;

    private String label;

    private long position;

    private ViewConfigDto viewConfig;

}

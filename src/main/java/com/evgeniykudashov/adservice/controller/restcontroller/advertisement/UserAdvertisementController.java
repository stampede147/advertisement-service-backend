package com.evgeniykudashov.adservice.controller.restcontroller.advertisement;


import com.evgeniykudashov.adservice.controller.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.controller.mapper.dto.advertisement.AdvertisementCreateRequestDto;
import com.evgeniykudashov.adservice.controller.service.AdvertisementService;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users/{userId}/advertisements")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserAdvertisementController {

    private AdvertisementService advertisementService;
    private ObjectMapper jsonMapper;
    private AdvertisementMapper dtoEntityMapper;

    @PostMapping()
    public ResponseEntity<Void> onCreate(@RequestBody AdvertisementCreateRequestDto requestDto) {
        long resourceId = advertisementService.create(dtoEntityMapper.toAdvertisement(requestDto));
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{id}")
                        .build(resourceId))
                .build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> onPatch(@RequestBody Map<String, Object> map, @PathVariable Long id) {

        //if request body does not contain certain id that corresponds path variable id
        if (!map.containsKey("id") || !id.equals(Long.valueOf(map.get("id").toString()))) {
            return ResponseEntity.badRequest().build();
        }
        map.remove("id");

        //converting all objects (that are linkedHashMap) from requestBody to concrete objects due to Advertisement.class
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Field field = ReflectionUtils.findField(Advertisement.class, entry.getKey());
            if (Objects.isNull(field)) {
                return ResponseEntity.badRequest().build();
            } else {
                map.put(entry.getKey(), jsonMapper.convertValue(entry.getValue(), field.getType()));
            }
        }
        advertisementService.patchUpdate(map, id);

        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<?> handlePatchMethodExceptions(MismatchedInputException e) {
        return ResponseEntity.badRequest().body(Map.of("message", "unknown properties found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> onDelete(@PathVariable Long id) {
        advertisementService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> onGetById(@PathVariable Long id) {
        return ResponseEntity.ok(advertisementService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<?> onGetAll(@PathVariable Long userId,
                                      @PageableDefault Pageable pageable) {
        Page<Advertisement> allByUserId = advertisementService.findAllByUserId(userId, pageable);
        return ResponseEntity.ok(
                dtoEntityMapper.toPageDto(allByUserId));
    }
}

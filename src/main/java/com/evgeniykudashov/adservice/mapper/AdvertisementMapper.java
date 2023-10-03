package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementCategoryResponseDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import com.evgeniykudashov.adservice.model.user.User;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ImageEntityMapperDecorator.class})
@Setter(onMethod_ = @Autowired)
public abstract class AdvertisementMapper {

    private CategoryMapper categoryMapper;

    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "images", source = "images")
    public abstract Advertisement toAdvertisement(AdvertisementRequestDto dto,
                                                  LocalDate startTime,
                                                  AdvertisementStatus status,
                                                  User seller,
                                                  Category category,
                                                  List<ImageEntity> images);

    @Mapping(target = "category", source = "category", qualifiedByName = "categoryToAdvertisementCategoryResponseDto")
    public abstract AdvertisementResponseDto toResponseDto(Advertisement advertisement);

    public abstract PageDto<AdvertisementResponseDto> toPageDto(Page<Advertisement> advertisementsPage);

    @Named(value = "categoryToAdvertisementCategoryResponseDto")
    public AdvertisementCategoryResponseDto categoryToAdvertisementCategoryResponseDto(Category category) {
        LinkedList<String> title = new LinkedList<>();

        Category current = category;
        while (current.getParent() != null) {
            title.addFirst(current.getTitle());
            current = current.getParent();
        }

        AdvertisementCategoryResponseDto advertisementCategoryResponseDto = new AdvertisementCategoryResponseDto();
        advertisementCategoryResponseDto.setFormat(String.join(" — ", title));
        return advertisementCategoryResponseDto;
    }

}

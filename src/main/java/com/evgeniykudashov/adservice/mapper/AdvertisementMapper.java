package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementCategoryResponseDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.dto.response.ReviewAdvertisementResponse;
import com.evgeniykudashov.adservice.model.ViewedAdvertisement;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.category.Category;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.LinkedList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ImageEntityMapper.class, UserMapper.class})
@Setter(onMethod_ = @Autowired)
public abstract class AdvertisementMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "images", ignore = true)
    public abstract Advertisement toAdvertisement(AdvertisementRequestDto dto);

    @Mapping(target = "category", source = "category", qualifiedByName = "categoryToAdvertisementCategoryResponseDto")
    public abstract AdvertisementResponseDto toResponseDto(Advertisement advertisement);

    public abstract List<AdvertisementResponseDto> toResponseDto(List<Advertisement> advertisements);

    public AdvertisementResponseDto toResponseDto(ViewedAdvertisement viewedAdvertisement) {
        return this.toResponseDto(viewedAdvertisement.getAdvertisement());
    }

    public abstract PageDto<AdvertisementResponseDto> toPageDto(Page<Advertisement> advertisementsPage);

    @Mapping(target = "seller", source = "advertisement.seller.firstName")
    public abstract ReviewAdvertisementResponse toReviewAdvertisementResponse(Advertisement advertisement);

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

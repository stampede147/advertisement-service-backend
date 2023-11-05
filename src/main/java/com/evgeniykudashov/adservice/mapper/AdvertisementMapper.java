package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementCategoryResponseDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.ViewedAdvertisement;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.repository.CategoryRepository;
import com.evgeniykudashov.adservice.repository.ImageEntityRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.factory.AdvertisementFactory;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.LinkedList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {CategoryMapper.class, ImageEntityMapper.class, UserMapper.class, AdvertisementFactory.class}
)
@Setter(onMethod_ = @Autowired)
public abstract class AdvertisementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Deprecated
    public abstract Advertisement toAdvertisement(AdvertisementRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", expression = "java(categoryRepository.getReferenceById(dto.getCategoryId()))")
    @Mapping(target = "seller", expression = "java(userRepository.getReferenceById(userSellerId))")
    @Mapping(target = "images", expression = "java(dto.getImages().stream().map(imageEntityRepository::getReferenceById).toList())")
    public abstract Advertisement toAdvertisement(AdvertisementRequestDto dto,
                                                  long userSellerId,
                                                  UserRepository userRepository,
                                                  ImageEntityRepository imageEntityRepository,
                                                  CategoryRepository categoryRepository);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", expression = "java(categoryRepository.getReferenceById(dto.getCategoryId()))")
    @Mapping(target = "seller", expression = "java(userRepository.getReferenceById(userSellerId))")
    @Mapping(target = "images", expression = "java(dto.getImages().stream().map(imageEntityRepository::getReferenceById).toList())")
    protected abstract Advertisement mapAdvertisement(@MappingTarget Advertisement advertisement,
                                                      AdvertisementRequestDto dto,
                                                      long userSellerId,
                                                      UserRepository userRepository,
                                                      ImageEntityRepository imageEntityRepository,
                                                      CategoryRepository categoryRepository);

    @Mapping(target = "category", source = "category", qualifiedByName = "categoryToAdvertisementCategoryResponseDto")
    public abstract AdvertisementResponseDto toResponseDto(Advertisement advertisement);

    public abstract List<AdvertisementResponseDto> toResponseDto(List<Advertisement> advertisements);

    public AdvertisementResponseDto toResponseDto(ViewedAdvertisement viewedAdvertisement) {
        return this.toResponseDto(viewedAdvertisement.getAdvertisement());
    }

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

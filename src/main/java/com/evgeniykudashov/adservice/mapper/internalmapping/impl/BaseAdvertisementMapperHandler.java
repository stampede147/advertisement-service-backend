package com.evgeniykudashov.adservice.mapper.internalmapping.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementCategoryResponseDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.mapper.ImageEntityMapper;
import com.evgeniykudashov.adservice.mapper.UserMapper;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandler;
import com.evgeniykudashov.adservice.mapper.internalmapping.AdvertisementMapperHandlerBeanNameConstants;
import com.evgeniykudashov.adservice.mapper.internalmapping.GenericAdvertisementMapperHandler;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.CategoryRepository;
import com.evgeniykudashov.adservice.repository.ImageEntityRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component(AdvertisementMapperHandlerBeanNameConstants.BASE_ADVERTISEMENT_MAPPER_HANDLER)
public final class BaseAdvertisementMapperHandler
        extends GenericAdvertisementMapperHandler<Advertisement, AdvertisementRequestDto, AdvertisementResponseDto> {

    private static final String BASE_ADVERTISEMENT_MAPPER_HANDLER_HELPER_CLASS_NAME = "DefaultAdvertisementMapperHandlerHelper";

    private static final String BASE_ADVERTISEMENT_MAPPER_HANDLER_HELPER_BEAN_NAME = "defaultAdvertisementMapperHandlerHelper";

    private final AdvertisementMapperHandler delegate;

    public BaseAdvertisementMapperHandler(@Qualifier(BASE_ADVERTISEMENT_MAPPER_HANDLER_HELPER_BEAN_NAME) AdvertisementMapperHandler delegate) {
        super(null);
        this.delegate = delegate;
    }

    @Override
    protected void map(Advertisement target, AdvertisementRequestDto source) {
        delegate.toEntity(target, source);
    }

    @Override
    protected void map(AdvertisementResponseDto target, Advertisement source) {
        delegate.toDto(target, source);
    }

    @Mapper(componentModel = "spring",
            uses = {ImageEntityMapper.class, UserMapper.class},
            implementationName = BASE_ADVERTISEMENT_MAPPER_HANDLER_HELPER_CLASS_NAME)
    @Setter(onMethod_ = @Autowired)
    static abstract class BaseAdvertisementMapperHandlerHelper implements AdvertisementMapperHandler {

        protected CategoryRepository categoryRepository;

        protected UserRepository userRepository;

        protected ImageEntityRepository imageEntityRepository;

        protected Converter<Principal, Long> converter;

        @Mapping(target = "category", expression = "java(categoryRepository.getReferenceById(rtd.getCategoryId()))")
        @Mapping(target = "seller", source = "rtd", qualifiedByName = "toSeller")
        @Mapping(target = "images", expression = "java(rtd.getImages().stream().map(imageEntityRepository::getReferenceById).toList())")
        @Override
        public abstract void toEntity(@MappingTarget Advertisement a, AdvertisementRequestDto rtd);

        @Mapping(target = "category", source = "category", qualifiedByName = "categoryToAdvertisementCategoryResponseDto")
        @Override
        public abstract void toDto(@MappingTarget AdvertisementResponseDto red, Advertisement a);

        @Named(value = "toSeller")
        protected User toSeller(AdvertisementRequestDto dto) {

            return userRepository.getReferenceById(this.converter.convert(SecurityContextHolder.getContext().getAuthentication()));
        }

        @Named(value = "categoryToAdvertisementCategoryResponseDto")
        protected AdvertisementCategoryResponseDto categoryToAdvertisementCategoryResponseDto(Category category) {

            AdvertisementCategoryResponseDto advertisementCategoryResponseDto = new AdvertisementCategoryResponseDto();

            advertisementCategoryResponseDto.setFormat(Stream.iterate(category, Objects::nonNull, Category::getParent)
                    .map(Category::getTitle)
                    .collect(Collectors.joining(" — ")));

            return advertisementCategoryResponseDto;
        }

    }
}

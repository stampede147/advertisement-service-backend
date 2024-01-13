package com.evgeniykudashov.adservice.mapper.internalmapping;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;

/**
 * default abstract implementation for inheritors
 * <p>
 * provides methods to mapping
 * from <RTD> type to <A> type
 * and
 * from <A> type to <RED> type
 *
 * @param <A>
 * @param <RTD>
 * @param <RED>
 */
public abstract class GenericAdvertisementMapperHandler
        <A extends Advertisement, RTD extends AdvertisementRequestDto, RED extends AdvertisementResponseDto>
        implements AdvertisementMapperHandler {

    protected AdvertisementMapperHandler parent;

    public GenericAdvertisementMapperHandler(AdvertisementMapperHandler parent) {
        this.parent = parent;
    }

    @SuppressWarnings("unchecked cast")
    @Override
    public final void toEntity(Advertisement a, AdvertisementRequestDto rtd) {
        if (parent != null) {
            parent.toEntity(a, rtd);
        }

        map((A) a, (RTD) rtd);
    }

    @SuppressWarnings("unchecked cast")
    @Override
    public final void toDto(AdvertisementResponseDto red, Advertisement a) {
        if (parent != null) {
            parent.toDto(red, a);
        }

        map((RED) red, (A) a);
    }

    protected abstract void map(A target, RTD source);

    protected abstract void map(RED target, A source);

}

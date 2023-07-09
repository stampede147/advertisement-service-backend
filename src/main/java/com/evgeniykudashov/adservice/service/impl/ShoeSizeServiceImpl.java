package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.ShoeSizeDto;
import com.evgeniykudashov.adservice.mapper.ShoeSizeMapper;
import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import com.evgeniykudashov.adservice.repository.ShoeSizeRepository;
import com.evgeniykudashov.adservice.service.ShoeSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ShoeSizeServiceImpl implements ShoeSizeService {

    private final ShoeSizeRepository shoeSizeRepository;
    private final ShoeSizeMapper shoeSizeMapper;

    @Override
    @Transactional
    public int create(ShoeSizeDto dto) {
        return shoeSizeRepository.save(shoeSizeMapper.toShoeSize(dto))
                .getSize();
    }

    @Override
    @Transactional
    public void delete(int size) {
        shoeSizeRepository.deleteById(size);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShoeSize> getShoeSizes() {
        return shoeSizeRepository.findAll();
    }
}

package com.unir.libraryoperator.service.implementation;

import com.unir.libraryoperator.domain.dto.LendDto;
import com.unir.libraryoperator.domain.entity.LendEntity;
import com.unir.libraryoperator.exception.GenericException;
import com.unir.libraryoperator.repository.LendRepository;
import com.unir.libraryoperator.service.Lend;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LendService implements Lend {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LendRepository lendRepository;

    @Override
    public LendDto create(LendDto lendDto) throws GenericException {
        LendEntity lend = modelMapper.map(lendDto, LendEntity.class);
        lendRepository.saveAndFlush(lend);
        return getById(lend.getLendId());
    }

    @Override
    public LendDto update(LendDto lendDto) throws GenericException {
        LendEntity lend = modelMapper.map(lendDto, LendEntity.class);
        lendRepository.saveAndFlush(lend);
        //lendRepository.flush();
        return getById(lend.getLendId());
    }

    @Override
    public LendDto getById(long id) {
        Optional<LendEntity> lendOpt = lendRepository.findById(id);
        if (lendOpt.isPresent()) {
            LendDto lend = modelMapper.map(lendOpt.get(), LendDto.class);
            return lend;
        }
        return null;
    }
}

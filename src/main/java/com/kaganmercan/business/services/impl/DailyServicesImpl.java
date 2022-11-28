package com.kaganmercan.business.services.impl;

import com.kaganmercan.bean.ModelMapperBean;
import com.kaganmercan.business.dto.DailyDto;
import com.kaganmercan.business.services.IDailyServices;
import com.kaganmercan.data.entity.DailyEntity;
import com.kaganmercan.data.repository.IDailyRepository;
import com.kaganmercan.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lombok
@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
//asıl iş katmanı olan yer
public class DailyServicesImpl implements IDailyServices {

    //injection
    private final IDailyRepository repository;
    private final ModelMapperBean modelMapperBean;

    // Model Mapper(Provides conversion between entity and dto)
    @Override
    public DailyDto entityToDto(DailyEntity dailyEntity) {
        return modelMapperBean.modelMapperMethod().map(dailyEntity, DailyDto.class);
    }

    @Override
    public DailyEntity dtoToEntity(DailyDto dailyDto) {
        return modelMapperBean.modelMapperMethod().map(dailyDto, DailyEntity.class);
    }

    //CREATE
    @Override
    public DailyDto createDaily(DailyDto dailyDto) {
        DailyEntity registerEntity = dtoToEntity(dailyDto);
        repository.save(registerEntity);
        return dailyDto;
    }

    //LIST
    @Override
    public List<DailyDto> listDaily() {
        List<DailyEntity> registerEntityList = repository.findAll();
        List<DailyDto> dtoList = new ArrayList<>();
        for (DailyEntity temp : registerEntityList) {
            DailyDto entityToDto = entityToDto(temp);
            dtoList.add(entityToDto);
        }
        return dtoList;
    }

    //FIND
    @Override
    public DailyDto findDaily(Long id) {
        DailyEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        DailyDto entityToDto = entityToDto(registerEntity);
        return entityToDto;
    }

    //DELETE
    @Override
    public Map<String, Boolean> deleteDaily(Long id) {
        DailyEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        repository.delete(registerEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    //UPDATE
    @Override
    public DailyDto updateDaily(Long id, DailyDto dailyDto) {
        DailyEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        if (registerEntity != null) {
            registerEntity.setDailyName(dailyDto.getDailyName());
            registerEntity.setDailyDescription(dailyDto.getDailyDescription());
            repository.save(registerEntity);
        }
        return dailyDto;
    }
}

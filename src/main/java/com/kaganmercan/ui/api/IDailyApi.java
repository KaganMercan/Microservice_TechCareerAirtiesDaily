package com.kaganmercan.ui.api;
import com.kaganmercan.business.dto.DailyDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface IDailyApi {
    //CREATE
    ResponseEntity<?> createDaily(DailyDto dailyDto);

    //LIST
    ResponseEntity<List<DailyDto>>  listDaily();

    //FIND
    ResponseEntity<DailyDto> findDaily(Long id);


    //UPDATE
    ResponseEntity<DailyDto>  updateDaily(Long id, DailyDto dailyDto);

    //DELETE
    ResponseEntity <Map<String, Boolean>> deleteDaily( Long id);
}

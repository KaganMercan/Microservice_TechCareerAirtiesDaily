package com.kaganmercan.ui.mvc.impl;

import com.kaganmercan.bean.ModelMapperBean;
import com.kaganmercan.business.dto.DailyDto;
import com.kaganmercan.data.entity.DailyEntity;
import com.kaganmercan.data.repository.IDailyRepository;
import com.kaganmercan.exception.ResourceNotFoundException;
import com.kaganmercan.ui.mvc.IDailyController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//lombok
@RequiredArgsConstructor
@Log4j2

//Controller
@Controller
public class DailyController implements IDailyController {


    // Inject
    private final IDailyRepository repository;
    private final ModelMapperBean modelMapperBean;

    // SPEED DATA
    // http://localhost:2222/speedData
    @Override
    @GetMapping("/speedData")
    public String createSpeedData(Model model) {
        int counter = 0;
        for (int i = 1; i <= 5; i++) {
            DailyEntity dailyEntity = DailyEntity.builder()
                    .dailyName("Name " + i).dailyDescription("Description " + i).build();
            repository.save(dailyEntity);
            counter++;
        }
        model.addAttribute("key_dataset", counter + " Daily Entity is created...");
        return "redirect:/daily/list";
    }

    // SPEED DELETE
    // http://localhost:2222/speedData
    @Override
    @GetMapping("/speedDelete")
    public String deleteSpeedData(Model model) {
        repository.deleteAll();
        return "redirect:/daily/list";
    }


    // CREATE 2497-2588
    // http://localhost:1111/daily/create
    @Override
    @GetMapping("/daily/create")
    public String validationGetDaily(Model model) {
        model.addAttribute("key_daily", new DailyDto());
        return "daily_create";
    }

    //CREATE
    // http://localhost:2222/daily/create
    @Override
    @PostMapping("/daily/create")
    public String validationPostDaily(@Valid @ModelAttribute("key_daily") DailyDto dailyDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("ERROR: " + bindingResult);
            return "daily_create";
        }
        // If validation has no error occurence
        model.addAttribute("daily_success", "Daily post successfully added." + dailyDto);
        log.info("Success " + dailyDto);


        // Model Mapper for use to convert entity with mapping
        DailyEntity dailyEntity = modelMapperBean.modelMapperMethod().map(dailyDto, DailyEntity.class);
        try {
            if (dailyEntity != null) {
                repository.save(dailyEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //File
        return "success";
    }


    // LIST
    // http://localhost:2222/daily/list
    @Override
    @GetMapping("/daily/list")
    public String dailyList(Model model) {
        List<DailyEntity> list = repository.findAll();
        model.addAttribute("key_daily", list);
        list.forEach((temp) -> {
            System.out.println(temp);
        });
        return "daily_list";
    }

    // FIND
    // http://localhost:2222/daily/find
    // http://localhost:2222/daily/find/1
    @Override
    @GetMapping("/daily/find/{id}")
    public String dailyFindById(@PathVariable(name = "id") Long id, Model model) {
        DailyEntity dailyEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + "not found"));
        model.addAttribute("daily_find", dailyEntity);
        return "daily_detail_page";
    }

    // DELETE
    // http://localhost:2222/daily/delete
    // http://localhost:2222/daily/delete/1
    @Override
    @GetMapping({"/daily/delete", "/daily/delete/{id}"})
    public String dailyDeleteById(@PathVariable(name = "id", required = false) Long id, Model model) {
        DailyEntity dailyEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " not found"));
        if (dailyEntity != null) {
            repository.deleteById(id);
            model.addAttribute("key_delete", dailyEntity + " silindi");
        } else
            model.addAttribute("key_delete", id + " not found");
        return "redirect:/daily/list";
    }

    //UPDATE
    // http://localhost:2222/update/daily
    @Override
    @GetMapping("/daily/update/{id}")
    public String updateGetDaily(@PathVariable(name = "id") Long id, Model model) {
        DailyEntity dailyEntityFind = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " not found"));
        if (dailyEntityFind != null) {
            model.addAttribute("key_update", dailyEntityFind);
        } else
            model.addAttribute("key_update", id + " not found");
        return "daily_update";
    }

    //UPDATE
    // http://localhost:2222/update/daily
    @Override
    @PostMapping("/daily/update/{id}")
    public String updatePostDaily(@PathVariable(name = "id") Long id, @Valid @ModelAttribute("key_update") DailyDto dailyDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("HATA: " + bindingResult);
            return "daily_update";
        }
        DailyEntity dailyEntity = modelMapperBean.modelMapperMethod().map(dailyDto, DailyEntity.class);
        try {
            if (dailyEntity != null) {
                repository.save(dailyEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/daily/list";
    }
}
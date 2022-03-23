package com.example.sectors.controller;

import com.example.sectors.DTO.SectorDTO;
import com.example.sectors.service.SectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.List;

@RestController
public class SectorsController {

    @Autowired
    private SectorsService sectorsService;

    @GetMapping("getSectorsList")
    public List<SectorDTO> getSectorsList() {
        return sectorsService.getSectorsList();
    }

    @PostMapping("insert")
    public void insertEmployee(@RequestBody List<String> employee) {
        sectorsService.insertEmployeeAndSectors(employee);
    }



}

package com.example.sectors.service;

import com.example.sectors.DTO.SectorDTO;
import com.example.sectors.repository.SectorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorsService {

    @Autowired
    private SectorsRepository sectorsRepository;

    public List<SectorDTO> getSectorsList(){
        List<SectorDTO> sectorList = sectorsRepository.getSectorsList();
        for(int i = 0; i<sectorList.size(); i++){
            System.out.println(sectorList.get(i).getSectorID());
            System.out.println(sectorList.get(i).getSectorName());
        }
        return sectorsRepository.getSectorsList();
    }

    public void insertEmployeeAndSectors(List<String> employee) {
        String name = employee.get(0);

        if(checkEmployee(name)) {
            int employeeID = sectorsRepository.getEmployeeID(name);
            sectorsRepository.deleteSectorsForEmployee(employeeID);
            for(int i = 1; i < employee.size(); i++) {
                int sectorID = sectorsRepository.getSectorID(employee.get(i));
                sectorsRepository.insertSectorForEmployee(employeeID, sectorID);
            }
        } else {
            boolean agreeToTerms = true;
            sectorsRepository.insertEmployee(name, agreeToTerms);
            int employeeID = sectorsRepository.getEmployeeID(name);
            for(int i = 1; i < employee.size(); i++) {
                int sectorID = sectorsRepository.getSectorID(employee.get(i));
                sectorsRepository.insertSectorForEmployee(employeeID, sectorID);
            }
        }
    }

    public boolean checkEmployee(String name) {
        int nameCount = sectorsRepository.checkEmployee(name);
        if(nameCount == 0) {
            return false;
        } else {
            return true;
        }
    }

}

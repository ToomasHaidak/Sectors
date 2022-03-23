package com.example.sectors.repository;

import com.example.sectors.DTO.SectorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SectorsRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<SectorDTO> getSectorsList() {
        String sql = "SELECT * FROM sectors ORDER BY sector_id";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new SectorsRepository.SectorDTORowMapper());
    }

    public static class SectorDTORowMapper implements RowMapper<SectorDTO>{

        @Override
        public SectorDTO mapRow(ResultSet resultSet, int i) throws SQLException{
            SectorDTO result = new SectorDTO();
            result.setSectorID(resultSet.getInt("sector_id"));
            result.setSectorName(resultSet.getString("sector_name"));
            result.setIndent(resultSet.getString("indent_value"));
            return result;
        }
    }

    public int getSectorID(String name) {
        String sql = "SELECT sector_id FROM sectors WHERE sector_name = :sectorName";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("sectorName", name);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public void insertEmployee(String name, boolean agreeToTerms) {
        String sql = "INSERT INTO employees (employee_name, agree_to_terms) VALUES (:employeeName, :agree)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("employeeName", name);
        paramMap.put("agree", agreeToTerms);
        jdbcTemplate.update(sql, paramMap);
    }

    public int getEmployeeID(String name) {
        String sql = "SELECT employee_id FROM employees WHERE employee_name = :employeeName";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("employeeName", name);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public int checkEmployee(String name) {
        String sql = "SELECT COUNT(*) FROM employees WHERE employee_name = :employeeName";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("employeeName", name);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public void insertSectorForEmployee(int employeeID, int sectorID) {
        String sql = "INSERT INTO employees_sectors_join (employee_id, sector_id) VALUES (:eID, :sID)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eID", employeeID);
        paramMap.put("sID", sectorID);
        jdbcTemplate.update(sql, paramMap);
    }

    public void deleteSectorsForEmployee(int employeeID) {
        String sql = "DELETE FROM employees_sectors_join WHERE employee_id = :eID";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("eID", employeeID);
        jdbcTemplate.update(sql, paramMap);
    }

}

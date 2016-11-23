package com.tj.tema5.services

import com.tj.tema5.dao.school.DefaultSchoolRepository
import com.tj.tema5.dao.school.SchoolRepository
import com.tj.tema5.dto.SchoolDTO

import java.sql.Connection

class DefaultSchoolService implements SchoolService {
    SchoolRepository schoolRepository

    DefaultSchoolService() {
        schoolRepository = new DefaultSchoolRepository()
    }

    List<SchoolDTO> getAllSchools(Connection connection) {
        schoolRepository.getAll(connection).collect {
            new SchoolDTO(name: it.name)
        }
    }
}

package com.tj.tema5.services

import com.tj.tema5.dao.school.DefaultSchoolRepository
import com.tj.tema5.dao.school.SchoolRepository
import com.tj.tema5.dto.SchoolDTO
import com.tj.tema5.model.School
import com.tj.tema5.model.SchoolPreference

import java.sql.Connection

class DefaultSchoolService implements SchoolService {
    SchoolRepository schoolRepository

    DefaultSchoolService() {
        schoolRepository = new DefaultSchoolRepository()
    }

    List<SchoolDTO> getAllSchools(Connection connection) {
        schoolRepository.getAll(connection).collect {
            new SchoolDTO(
                    name: it.name,
                    maxAllowedStudents: it.schoolPreferences.maxStudentAllowed,
                    minAllowedGrade: it.schoolPreferences.minGradeAllowed
            )
        }
    }

    @Override
    void add(SchoolDTO schoolDTO, Connection connection) {
        schoolRepository.add(
                new School(
                        name: schoolDTO.name,
                        schoolPreferences: new SchoolPreference(
                                maxStudentAllowed: schoolDTO.maxAllowedStudents,
                                minGradeAllowed: schoolDTO.minAllowedGrade
                        )),
                connection)
    }
}

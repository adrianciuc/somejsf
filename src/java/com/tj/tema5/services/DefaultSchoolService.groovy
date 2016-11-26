package com.tj.tema5.services

import com.tj.tema5.dao.school.DefaultSchoolRepository
import com.tj.tema5.dao.school.SchoolRepository
import com.tj.tema5.dao.user.DefaultStudentRepository
import com.tj.tema5.dao.user.PersonRepository
import com.tj.tema5.dto.SchoolDTO
import com.tj.tema5.dto.StudentDTO
import com.tj.tema5.model.School
import com.tj.tema5.model.SchoolPreference

import java.sql.Connection

class DefaultSchoolService implements SchoolService {
    SchoolRepository schoolRepository
    PersonRepository personRepository

    DefaultSchoolService() {
        schoolRepository = new DefaultSchoolRepository()
        personRepository = new DefaultStudentRepository()
    }

    List<SchoolDTO> getAllSchools(Connection connection) {
        schoolRepository.getAll(connection).collect {
            new SchoolDTO(
                    id: it.id,
                    name: it.name,
                    maxAllowedStudents: it.schoolPreferences.maxStudentAllowed,
                    minAllowedGrade: it.schoolPreferences.minGradeAllowed,
                    students:
                            personRepository
                                    .getStudentsWhoChoosedSchool(it.id, connection)
                                    .collect { student ->
                                        new StudentDTO(
                                                name: student.name,
                                                identificationNumber: student.identificationNumber)
                            }
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
        connection.close()
    }
}

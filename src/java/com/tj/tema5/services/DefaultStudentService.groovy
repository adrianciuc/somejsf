package com.tj.tema5.services

import com.tj.tema5.beans.authentication.AuthenticationBean
import com.tj.tema5.dao.school.DefaultSchoolRepository
import com.tj.tema5.dao.school.SchoolRepository
import com.tj.tema5.dao.user.DefaultStudentRepository
import com.tj.tema5.dao.user.PersonRepository
import com.tj.tema5.dto.SchoolDTO

import java.sql.Connection


class DefaultStudentService implements StudentService {
    SchoolRepository schoolRepository
    PersonRepository personRepository

    DefaultStudentService() {
        schoolRepository = new DefaultSchoolRepository()
        personRepository = new DefaultStudentRepository()
    }
    @Override
    void choose(AuthenticationBean authenticationBean, SchoolDTO schoolDTO, Connection connection) {
        Integer schoolId = schoolRepository.getId(schoolDTO.name, connection)
        personRepository.chooseSchool(authenticationBean.userName, connection, schoolId)
        connection.close()
    }
}

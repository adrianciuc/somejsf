package com.tj.tema5.controller

import com.tj.tema5.beans.authentication.AuthenticationBean
import com.tj.tema5.beans.school.SchoolBean
import com.tj.tema5.dto.SchoolDTO
import com.tj.tema5.services.DefaultSchoolService
import com.tj.tema5.services.DefaultStudentService
import com.tj.tema5.services.SchoolService
import com.tj.tema5.services.StudentService

import javax.faces.bean.ManagedBean
import javax.faces.bean.RequestScoped

@ManagedBean(eager = true)
@RequestScoped
class SchoolController extends AbstractController {
    SchoolService schoolService
    StudentService studentService

    SchoolController() {
        schoolService = new DefaultSchoolService()
        studentService = new DefaultStudentService()
    }

    List<SchoolDTO> getAllSchools() {
        schoolService.getAllSchools(connection.connection)
    }

    String add(SchoolBean schoolBean) {
        schoolService.add(new SchoolDTO(
                name: schoolBean.name,
                maxAllowedStudents: schoolBean.maxNumberAllowedStudents,
                minAllowedGrade: schoolBean.minAllowedGrade),
                connection.connection)
        "home?faces-redirect=true"
    }

    String choose(AuthenticationBean authenticationBean, SchoolDTO schoolDTO) {
        studentService.choose(authenticationBean, schoolDTO, connection.connection)
        "home?faces-redirect=true"
    }
}

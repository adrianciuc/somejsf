package com.tj.tema5.controller

import com.tj.tema5.dto.SchoolDTO
import com.tj.tema5.services.DefaultSchoolService
import com.tj.tema5.services.SchoolService

import javax.faces.bean.ManagedBean
import javax.faces.bean.RequestScoped

@ManagedBean(eager = true)
@RequestScoped
class SchoolController extends AbstractController {
    SchoolService schoolService

    SchoolController() {
        schoolService = new DefaultSchoolService()
    }

    List<SchoolDTO> getAllSchools() {
        schoolService.getAllSchools(connection.connection)
    }
}

package com.tj.tema5.services

import com.tj.tema5.dto.SchoolDTO

import java.sql.Connection


interface SchoolService {

    List<SchoolDTO> getAllSchools(Connection connection)
}
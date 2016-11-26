package com.tj.tema5.services

import com.tj.tema5.beans.authentication.AuthenticationBean
import com.tj.tema5.dto.SchoolDTO

import java.sql.Connection


interface StudentService {

    void choose(AuthenticationBean authenticationBean, SchoolDTO schoolDTO, Connection connection)
}
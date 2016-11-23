package com.tj.tema5.dao.school

import com.tj.tema5.model.School

import java.sql.Connection


interface SchoolRepository {

    List<School> getAll(Connection connection)
}
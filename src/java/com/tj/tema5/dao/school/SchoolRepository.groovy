package com.tj.tema5.dao.school

import com.tj.tema5.model.School

import java.sql.Connection


interface SchoolRepository {

    List<School> getAll(Connection connection)

    void add(School school, Connection connection)

    Integer getId(String name, Connection connection)
}
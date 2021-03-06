package com.tj.tema5.dao.user

import com.tj.tema5.model.Person

import java.sql.Connection

interface PersonRepository<T extends Person> {

    T get(String userName, String password, Connection connection)

    void chooseSchool(String username, Connection connection, Integer schoolId)

    void add(List<T> students, Connection connection)

    void add(T student, Connection connection)

    Long count(Connection connection)

    List<T> getStudentsWhoChoosedSchool(Integer schoolId, Connection connection)

    List<T> getUnrepartitioinedStudents(Connection connection)

    void updateElectedSchools(List<T> persons, Connection connection)
}
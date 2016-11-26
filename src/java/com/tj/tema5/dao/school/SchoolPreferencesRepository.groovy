package com.tj.tema5.dao.school

import com.tj.tema5.model.SchoolPreference

import java.sql.Connection


interface SchoolPreferencesRepository {

    SchoolPreference getSchoolPreference(Integer schoolId, Connection connection)

    List<SchoolPreference> getAll(Connection connection)
}
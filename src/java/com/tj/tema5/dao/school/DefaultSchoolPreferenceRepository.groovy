package com.tj.tema5.dao.school

import com.tj.tema5.model.SchoolPreference

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet


class DefaultSchoolPreferenceRepository implements SchoolPreferencesRepository {
    @Override
    SchoolPreference getSchoolPreference(Integer id, Connection connection) {
        PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM school_preferences WHERE school_id = ?")
        statement.setInt(1, id)
        ResultSet resultSet = statement.executeQuery()
        if (resultSet.next()) {
            new SchoolPreference(
                    maxStudentAllowed: resultSet.getInt("max_students_allowed"),
                    minGradeAllowed: resultSet.getInt("min_grade_allowed"))
        }
    }
}

package com.tj.tema5.services

import com.tj.tema5.dao.school.DefaultSchoolRepository
import com.tj.tema5.dao.school.SchoolRepository
import com.tj.tema5.dao.user.DefaultStudentRepository
import com.tj.tema5.dao.user.PersonRepository
import com.tj.tema5.model.School
import com.tj.tema5.model.Student

import java.sql.Connection

import static java.util.UUID.randomUUID
import static java.lang.Math.round

import static com.tj.tema5.dao.DataSource.DATA_SOURCE

class DefaultStudentServiceGenerator implements StudentServiceGenerator {

    SchoolRepository schoolRepository
    PersonRepository studentRepository

    DefaultStudentServiceGenerator() {
        schoolRepository = new DefaultSchoolRepository()
        studentRepository = new DefaultStudentRepository()
    }

    @Override
    void generateStudents() {
        Connection connection = DATA_SOURCE.connection
        if (studentRepository.count(connection) <= 10000) {
            List<School> schools = schoolRepository.getAll(connection)
            List<Student> students = getRandomStudents(schools)
            studentRepository.add(students, connection)
        }
    }

    private List<Student> getRandomStudents(List<School> schoolList) {
        int studentsCount = 0
        Random random = new Random()
        List<Student> studentList = []
        while(studentsCount++ < 100) {
            studentList.add(
                    new Student(
                            chosenSchool: schoolList.get(random.nextInt(schoolList.size() - 1)),
                            identificationNumber: randomUUID().toString().substring(0, 12),
                            grade: round(1 + 10 * random.nextDouble() * 100)/100.0,
                            userName: randomUUID().toString().substring(0, 10),
                            password: randomUUID().toString().substring(0, 10),
                            name: randomUUID().toString().substring(0, 10)))
        }
        studentList
    }
}

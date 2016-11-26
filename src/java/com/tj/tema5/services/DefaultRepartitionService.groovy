package com.tj.tema5.services

import com.tj.tema5.dao.school.DefaultSchoolRepository
import com.tj.tema5.dao.school.SchoolRepository
import com.tj.tema5.dao.user.DefaultStudentRepository
import com.tj.tema5.dao.user.PersonRepository
import com.tj.tema5.dto.Node
import com.tj.tema5.dto.SchoolDTO
import com.tj.tema5.model.School
import com.tj.tema5.model.Student

import java.sql.Connection

class DefaultRepartitionService implements RepartitionService {
    SchoolService schoolService
    PersonRepository personRepository
    SchoolRepository schoolRepository

    DefaultRepartitionService() {
        schoolService = new DefaultSchoolService()
        personRepository = new DefaultStudentRepository()
        schoolRepository = new DefaultSchoolRepository()
    }

    @Override
    List<Node> getNodes(Connection connection) {
        schoolService.getAllSchools(connection).collect {
            new Node(
                    name: it.name,
                    type: "school",
                    childes: it.students.collect{ student ->
                        new Node(
                                name: student.name,
                                type: "student"
                        )
                    })
        }
    }

    @Override
    void repartition(Connection connection) {
        List<SchoolDTO> schools = schoolService.getAllSchools(connection)
        List<Student> students = personRepository.getUnrepartitioinedStudents(connection).each { student ->
            SchoolDTO chosenSchool = schools.find { schoolDTO ->
                schoolDTO.minAllowedGrade < student.grade &&
                        student?.chosenSchool?.name == schoolDTO.name &&
                        schoolDTO.maxAllowedStudents > 0
            }
            chosenSchool ?
                    makePerfectMatch(chosenSchool, (Student)student)
                    : findPartialMatch(schools, (Student)student, connection)

        }
        personRepository.updateElectedSchools(students, connection)
    }

    private void makePerfectMatch(SchoolDTO schoolDTO, Student student) {
        schoolDTO.maxAllowedStudents--
        student.electedSchool = student.chosenSchool
    }

    private void makePartialMatch(SchoolDTO schoolDTO, Student student, Connection connection) {
        schoolDTO.maxAllowedStudents--
        student.electedSchool = new School(
                                id: schoolRepository.getId(schoolDTO.name, connection))
    }

    private void findPartialMatch(List<SchoolDTO> schools, Student student, Connection connection) {
        SchoolDTO schoolDto = schools.findAll { someSchool ->
            someSchool.minAllowedGrade < student.grade &&
                    someSchool.maxAllowedStudents > 0
        }.toSorted { a,b ->
            a.minAllowedGrade <=> b.minAllowedGrade
        }.find()
        schoolDto ? makePartialMatch(schoolDto, student, connection) : student.electedSchool
    }
}

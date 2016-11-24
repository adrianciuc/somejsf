package com.tj.tema5.beans.school

import javax.faces.bean.ManagedBean
import javax.faces.bean.RequestScoped


@ManagedBean
@RequestScoped
class SchoolBean {
    String name
    Integer maxNumberAllowedStudents
    Integer minAllowedGrade
}

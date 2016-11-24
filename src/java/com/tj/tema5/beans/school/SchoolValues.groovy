package com.tj.tema5.beans.school

import javax.faces.bean.ManagedBean
import javax.faces.bean.RequestScoped


@ManagedBean
@RequestScoped
class SchoolValues {
    List<Integer> maxStudentsNumberAllowed
    List<Integer> minGradeAllowed

    SchoolValues() {
        maxStudentsNumberAllowed = 1..100
        minGradeAllowed = 1..10
    }
}

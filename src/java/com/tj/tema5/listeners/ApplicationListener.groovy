package com.tj.tema5.listeners

import com.tj.tema5.services.DefaultStudentServiceGenerator

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

@WebListener
class ApplicationListener implements ServletContextListener {

    @Override
    void contextInitialized(ServletContextEvent sce) {
        new DefaultStudentServiceGenerator().generateStudents()
    }
}

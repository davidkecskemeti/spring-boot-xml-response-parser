package com.dk.spring.xmlresponseparser.runner;

import com.dk.spring.xmlresponseparser.model.Course;
import com.dk.spring.xmlresponseparser.service.XMLService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class XmlServiceCommandLineRunner implements CommandLineRunner {

    @Autowired
    private XMLService xmlService;

    @Override
    public void run(String... args) throws Exception {
        Course course = xmlService.parseCourse();
        log.info(course.toString());
    }
}

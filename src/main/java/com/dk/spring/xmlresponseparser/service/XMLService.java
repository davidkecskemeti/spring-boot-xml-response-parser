package com.dk.spring.xmlresponseparser.service;

import com.dk.spring.xmlresponseparser.model.Course;
import com.dk.spring.xmlresponseparser.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class XMLService {

    @Value("${endpoint.xml}")
    private String url;// fake end point that returns XML response


    public Course parseCourse() {

        Course course = null;

        try {
            log.info("Calling URL: " + url);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url);

            // normalize XML response
            doc.getDocumentElement().normalize();

            //read course details first
            course = new Course(
                    Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent()),
                    doc.getElementsByTagName("title").item(0).getTextContent(),
                    Double.parseDouble(doc.getElementsByTagName("price").item(0).getTextContent()),
                    new SimpleDateFormat("yyyy-MM-dd").parse(doc.getElementsByTagName("created").item(0).getTextContent())
            );

            //read students list
            NodeList nodeList = doc.getElementsByTagName("student");

            //create an empty list for students
            List<Student> students = new ArrayList<>();

            //loop all available student nodes
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Student student = new Student(
                            Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent()),
                            elem.getElementsByTagName("first_name").item(0).getTextContent(),
                            elem.getElementsByTagName("last_name").item(0).getTextContent(),
                            elem.getElementsByTagName("avatar").item(0).getTextContent()
                    );
                    students.add(student);
                }
            }

            //set students in course
            course.setStudents(students);

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

        return course;
    }
}
package dk.sdu.tekvideo

import grails.validation.Validateable

@Validateable
class ImportCourseCommand {
    String newCourseName
    String newCourseFullName
    Integer newSemester
    Boolean newSemesterSpring
    String newDescription
    Course course
    Boolean visible

    static constraints = {
        newSemester min: 1900, max: 9999
    }
}

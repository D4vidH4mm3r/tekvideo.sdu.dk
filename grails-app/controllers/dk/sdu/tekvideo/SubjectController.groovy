package dk.sdu.tekvideo

import org.springframework.security.access.annotation.Secured

class SubjectController {

    TeachingService teachingService

    @Secured("permitAll")
    def viewByTeacherAndCourse(String teacherName, String courseName, String subjectName) {
        Subject subject = teachingService.getSubject(teacherName, courseName, subjectName)
        if (subject) {
            render view: "view", model: [subject: subject]
        } else {
            render status: 404, text: "Unable to find subject!"
        }
    }
}

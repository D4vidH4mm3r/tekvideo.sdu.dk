package dk.sdu.tekvideo

import org.springframework.security.access.annotation.Secured

class CourseController {
    static defaultAction = "list"

    TeachingService teachingService
    StudentService studentService
    CourseService courseService

    @Secured("permitAll")
    def list() {
        List<Course> courses = courseService.listVisibleCourses()
        [courses: courses]
    }

    @Secured("permitAll")
    def viewByTeacher(String teacherName, String courseName, Integer year, Boolean spring) {
        Course course = teachingService.getCourse(teacherName, courseName, year, spring)
        if (courseService.canAccess(course)) {
            render(view: "view", model: [course: course])
        } else {
            render status: "404", text: "Course not found!"
        }
    }

    @Secured("ROLE_STUDENT")
    def signup(Course course) {
        Student student = studentService.authenticatedStudent
        [course: course, studentCount: courseService.getStudentCount(course), inCourse: studentService.isInCourse(student, course),
         student: student] // TODO Check if inCourse loads in all students
    }

    @Secured("ROLE_STUDENT")
    def completeSignup(Course course) {
        if (course) {
            def result = studentService.signupForCourse(studentService.authenticatedStudent, course)
            result.updateFlashMessage(flash)
            redirect(action: "signup", id: course.id)
        } else {
            render status: "404", text: "Course not found!"
        }
    }

    @Secured("ROLE_STUDENT")
    def completeSignoff(Course course) {
        if (course) {
            def result = studentService.signoffForCourse(studentService.authenticatedStudent, course)
            result.updateFlashMessage(flash)
            redirect(action: "signup", id: course.id)
        } else {
            render status: "404", text: "Course not found!"
        }
    }
}

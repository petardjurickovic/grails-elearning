package duduk.test

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserCourseController {

    UserCourseService userCourseService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userCourseService.list(params), model:[userCourseCount: userCourseService.count()]
    }

    def show(Long id) {
        respond userCourseService.get(id)
    }

    def create() {
        respond new UserCourse(params)
    }

    def save(UserCourse userCourse) {
        if (userCourse == null) {
            notFound()
            return
        }

        try {
            userCourseService.save(userCourse)
        } catch (ValidationException e) {
            respond userCourse.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userCourse.label', default: 'UserCourse'), userCourse.id])
                redirect userCourse
            }
            '*' { respond userCourse, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userCourseService.get(id)
    }

    def update(UserCourse userCourse) {
        if (userCourse == null) {
            notFound()
            return
        }

        try {
            userCourseService.save(userCourse)
        } catch (ValidationException e) {
            respond userCourse.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userCourse.label', default: 'UserCourse'), userCourse.id])
                redirect userCourse
            }
            '*'{ respond userCourse, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userCourseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userCourse.label', default: 'UserCourse'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userCourse.label', default: 'UserCourse'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

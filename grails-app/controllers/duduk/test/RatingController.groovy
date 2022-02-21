package duduk.test

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RatingController {

    RatingService ratingService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ratingService.list(params), model:[ratingCount: ratingService.count()]
    }

    def show(Long id) {
        respond ratingService.get(id)
    }

    def create() {
        respond new Rating(params)
    }

    def save(Rating rating) {
        if (rating == null) {
            notFound()
            return
        }

        try {
            ratingService.save(rating)
        } catch (ValidationException e) {
            respond rating.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rating.label', default: 'Rating'), rating.id])
                redirect rating
            }
            '*' { respond rating, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond ratingService.get(id)
    }

    def update(Rating rating) {
        if (rating == null) {
            notFound()
            return
        }

        try {
            ratingService.save(rating)
        } catch (ValidationException e) {
            respond rating.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rating.label', default: 'Rating'), rating.id])
                redirect rating
            }
            '*'{ respond rating, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        ratingService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rating.label', default: 'Rating'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rating.label', default: 'Rating'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

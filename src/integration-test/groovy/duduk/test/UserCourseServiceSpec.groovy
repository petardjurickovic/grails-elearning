package duduk.test

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserCourseServiceSpec extends Specification {

    UserCourseService userCourseService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserCourse(...).save(flush: true, failOnError: true)
        //new UserCourse(...).save(flush: true, failOnError: true)
        //UserCourse userCourse = new UserCourse(...).save(flush: true, failOnError: true)
        //new UserCourse(...).save(flush: true, failOnError: true)
        //new UserCourse(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userCourse.id
    }

    void "test get"() {
        setupData()

        expect:
        userCourseService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserCourse> userCourseList = userCourseService.list(max: 2, offset: 2)

        then:
        userCourseList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userCourseService.count() == 5
    }

    void "test delete"() {
        Long userCourseId = setupData()

        expect:
        userCourseService.count() == 5

        when:
        userCourseService.delete(userCourseId)
        sessionFactory.currentSession.flush()

        then:
        userCourseService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserCourse userCourse = new UserCourse()
        userCourseService.save(userCourse)

        then:
        userCourse.id != null
    }
}

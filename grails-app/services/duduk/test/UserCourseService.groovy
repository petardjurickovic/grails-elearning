package duduk.test

import grails.gorm.services.Service

@Service(UserCourse)
interface UserCourseService {

    UserCourse get(Serializable id)

    List<UserCourse> list(Map args)

    Long count()

    void delete(Serializable id)

    UserCourse save(UserCourse userCourse)

}
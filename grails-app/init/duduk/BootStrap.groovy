package duduk

import duduk.auth.Role
import duduk.auth.User
import duduk.auth.UserRole
import duduk.test.Category
import duduk.test.Course
import duduk.test.Enrollment
import duduk.test.Lesson

class BootStrap {

    def init = { servletContext ->
        Role adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save()
        Role userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save()
        def user1 =  new User(password: "test1", username: "test").save(failOnError : true)
        def student = new User(username: "student", password: "test").save(failOnError : true)
        def student1 = new User(username: "student2", password: "test").save(failOnError : true)

        UserRole.findByUserAndRole(student,userRole) ?: new UserRole(user: student,role: userRole).save()
        UserRole.findByUserAndRole(user1,adminRole) ?: new UserRole(user: user1,role: adminRole).save()

        def category1 = new Category(title: 'Programing').save(failOnError : true)
        def category2 = new Category(parent: category1, title: 'ML').save(failOnError : true)



        def course = new Course(title: "Intro to ML", description: "For beginners", instructor: user1).save(failOnError : true)


        def les = new Lesson(title: "First lesson", course: course).save(failOnError : true)
        def les2 = new Lesson(title: "2 lesson", course: course).save(failOnError : true)

        new Enrollment(student: student, course: course).save(failOnError : true)
        new Enrollment(student: student1, course: course).save(failOnError : true)




    }
    def destroy = {
    }
}

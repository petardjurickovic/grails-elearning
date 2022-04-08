package duduk

import duduk.auth.User
import duduk.test.Category
import duduk.test.Course
import duduk.test.Lesson
import duduk.test.UserCourse

class BootStrap {

    def init = { servletContext ->
        def user1 =  new User(password: "test1", username: "test").save(failOnError : true)
        def student = new User(username: "student", password: "test").save(failOnError : true)
        def student1 = new User(username: "student2", password: "test").save(failOnError : true)

        def category1 = new Category(title: 'Programing').save(failOnError : true)
        def category2 = new Category(parent: category1, title: 'ML').save(failOnError : true)



        def course = new Course(title: "Intro to ML", description: "For beginners", instructor: user1).save(failOnError : true)


        def les = new Lesson(title: "First lesson", course: course).save(failOnError : true)
        def les2 = new Lesson(title: "2 lesson", course: course).save(failOnError : true)

        new UserCourse(student: student, course: course).save(failOnError : true)
        new UserCourse(student: student1, course: course).save(failOnError : true)




    }
    def destroy = {
    }
}

package duduk.test

import duduk.auth.User

class Course {

    String title
    String description
    User instructor
    static  hasMany = [lessons:Lesson,students:User]


    static constraints = {
    }
}

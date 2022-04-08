package duduk.auth

import duduk.test.Course

class User {

    String username
    String password

    static belongsTo = Course
    static hasMany = [courses: Course]

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true

    }

    static mapping = {
        password column: '`password`'
    }
}

package duduk.test

import duduk.auth.User

class Rating {

    User user
    Course course
    int score

    static constraints = {
        score range: 1..5
       //user unique: 'course'
    }
}

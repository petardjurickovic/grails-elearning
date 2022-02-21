package duduk.test

class Lesson {

    String title
    static belongsTo = [course: Course]

    static constraints = {
    }
}

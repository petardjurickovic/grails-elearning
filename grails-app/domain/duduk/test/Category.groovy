package duduk.test

class Category {

    Category parent
    String title
    static constraints = {
        parent nullable: true
    }
}

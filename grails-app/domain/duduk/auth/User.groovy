package duduk.auth

class User {

    String username
    String password
    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true

    }

    static mapping = {
        password column: '`password`'
    }
}

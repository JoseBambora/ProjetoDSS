package PackageUtilizador;

public class Admin extends Utilizador {

    public Admin() {
    }

    public Admin(String _username, String _password) {
        super(_username, _password);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "_username= " + this.get_username() + "\n" +
                "_password= " + this.get_password() + "\n" +
                "}";
    }
}
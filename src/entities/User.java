package entities;

public class User {

    private int id;
    private String imie;
    private String nazwisko;
    private String login;
    private String haslo;
    private String email;
    private String url;



    public User(String imie, String nazwisko, String login, String email, String haslo, int id,String url) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.login = login;
        this.email = email;
        this.haslo = haslo;
        this.id = id;
        this.url=url;
    }


    public User(String imie2, String nazwisko2, String login2, String email2, String haslo2) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.login = login;
        this.email = email;
        this.haslo = haslo;

    }









    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}

package Entity;
public class User{
    private String name;
    private String loginEmail;
    private String password;
    private boolean isAdmin;

    /**
     * @param name 
     * @param loginEmail
     * @param password
     * @param isAdmin
     */
    public User(String name, String loginEmail, String password, boolean isAdmin){
        this.name = name;
        this.loginEmail = loginEmail;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return loginEmail;
    }

    public String getPassword(){
        return password;
    }

    public boolean checkAdmin(){
        return isAdmin;
    }
}
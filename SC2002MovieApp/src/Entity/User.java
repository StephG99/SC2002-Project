package Entity;
public class User{
    private String name;
    private String loginEmail;
    private int mobileNo;
    private String password;
    private boolean isAdmin;

    /**
     * @param name 
     * @param loginEmail
     * @param password
     * @param isAdmin
     */
    public User(String name, String loginEmail,int mobileNo, String password, boolean isAdmin){
        this.name = name;
        this.loginEmail = loginEmail;
        this.mobileNo = mobileNo;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    //Guest User creation
    public User(String loginEmail,int mobileNo){
        this.loginEmail = loginEmail;
        this.mobileNo = mobileNo;
        //for further logic processing in the backend
        this.name = null;

    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return loginEmail;
    }
    public void setEmailAndPhoneNo(String Email,int phoneNo){
            this.loginEmail = Email;
            this.mobileNo = phoneNo;
    }

    public String getPassword(){
        return password;
    }

    public boolean checkAdmin(){
        return isAdmin;
    }
    public boolean isRegisteredUser(String email,String password){
        if(this.loginEmail.equalsIgnoreCase(email) && this.password.equals(password)){
            return true;
        }
        return false;
    }
    public int getMobileNo() {
        return mobileNo;
    }
}
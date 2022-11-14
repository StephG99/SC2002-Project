package Entity;
public class User{
    private String name;
    private String loginEmail;
    private int mobileNo;
    private String password;
    private boolean isAdmin;

    /**
     * @param name Name of User
     * @param loginEmail Login Email for user
     * @param password password for user
     * @param isAdmin if is admin
     * Constructor for registered User
     */
    public User(String name, String loginEmail,int mobileNo, String password, boolean isAdmin){
        this.name = name;
        this.loginEmail = loginEmail;
        this.mobileNo = mobileNo;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    //Guest User creation
    /**
     * @param loginEmail Email details for booking of ticket
     * @param mobileNo mobile no for booking of ticket
     * Constructor for Guest user not to be inserted into database
     */
    public User(String loginEmail,int mobileNo){
        this.loginEmail = loginEmail;
        this.mobileNo = mobileNo;
        //for further logic processing in the backend
        this.name = null;

    }

    /**
     * @return name
     */
    public String getName(){
        return name;
    }
    /**
     * 
     * @return email
     */
    public String getEmail(){
        return loginEmail;
    }
    /**
     * 
     * @param Email new Email
     * @param phoneNo new phone
     * Set new Email and Phone for user;
     */
    public void setEmailAndPhoneNo(String Email,int phoneNo){
            this.loginEmail = Email;
            this.mobileNo = phoneNo;
    }
    /**
     * 
     * @return password
     */
    public String getPassword(){
        return password;
    }
    /**
     * 
     * @return boolean true if is admin false if is not
     */
    public boolean checkAdmin(){
        return isAdmin;
    }
    /**
     *  function is a login validator
     * @param email Login Email
     * @param password login Password
     * @return a boolean if is valid login
     */
    public boolean isRegisteredUser(String email,String password){
        if(this.loginEmail.equalsIgnoreCase(email) && this.password.equals(password)){
            return true;
        }
        return false;
    }
    /**
     * 
     * @return mobile no
     */
    public int getMobileNo() {
        return mobileNo;
    }
}
public class MuseumOfficer {
    private String username;
    private String password1;
    private int password2;

    /** a museum officer with 2 passwords and a username
     * @param username
     * @param pass1
     * @param pass2
     */
    public MuseumOfficer(String username,String pass1,int pass2)
    {
        this.username = username;
        password1 = pass1;
        password2 = pass2;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public int getPassword2() {
        return password2;
    }

    public void setPassword2(int password2) {
        this.password2 = password2;
    }

    

}

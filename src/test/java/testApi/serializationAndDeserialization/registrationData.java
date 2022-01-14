package testApi.serializationAndDeserialization;

import java.util.List;

public class registrationData {

    public String email;
    public String password;
    //List<String> courses;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    } */

    public String getRegistrationData() {
        return (this.email+" "+this.password);
    }
}

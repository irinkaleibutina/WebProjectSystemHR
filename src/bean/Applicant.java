package bean;

import java.io.Serializable;

/**
 * Created by irinaleibutina on 25.02.17.
 */
public class Applicant implements Serializable {

    private String country;
    private String city;
    private String education;
    private String professionalSkills;
    private Interview interview;
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String phoneNumber;
    private String email;

    public Applicant() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfessionalSkills() {
        return professionalSkills;
    }

    public void setProfessionalSkills(String professionalSkills) {
        this.professionalSkills = professionalSkills;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        if (id != applicant.id) return false;
        if (country != null ? !country.equals(applicant.country) : applicant.country != null) return false;
        if (city != null ? !city.equals(applicant.city) : applicant.city != null) return false;
        if (education != null ? !education.equals(applicant.education) : applicant.education != null) return false;
        if (professionalSkills != null ? !professionalSkills.equals(applicant.professionalSkills) : applicant.professionalSkills != null)
            return false;
        if (interview != null ? !interview.equals(applicant.interview) : applicant.interview != null) return false;
        if (name != null ? !name.equals(applicant.name) : applicant.name != null) return false;
        if (surname != null ? !surname.equals(applicant.surname) : applicant.surname != null) return false;
        if (login != null ? !login.equals(applicant.login) : applicant.login != null) return false;
        if (password != null ? !password.equals(applicant.password) : applicant.password != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(applicant.phoneNumber) : applicant.phoneNumber != null)
            return false;
        return email != null ? email.equals(applicant.email) : applicant.email == null;
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (professionalSkills != null ? professionalSkills.hashCode() : 0);
        result = 31 * result + (interview != null ? interview.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", education='" + education + '\'' +
                ", professionalSkills='" + professionalSkills + '\'' +
                ", interview=" + interview +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

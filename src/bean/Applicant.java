package bean;

/**
 * Created by irinaleibutina on 25.02.17.
 */
public class Applicant extends User{

    private String country;
    private String city;
    private String education;
    private String professionalSkills;
    private Interview interview;

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
}

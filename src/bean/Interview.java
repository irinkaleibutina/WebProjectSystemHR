package bean;

import java.io.Serializable;

public class Interview implements Serializable {

    private int id;     // maybe it's not important field
    private InterviewResult preliminaryInterview;
    private InterviewResult technicalInterview;
    private InterviewResult commonResult;
    private String datePreInt;
    private String timePreInt;

    private String dateTecInt;
    private String timeTecInt;



    private String notes;

    public Interview() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InterviewResult getPreliminaryInterview() {
        return preliminaryInterview;
    }

    public void setPreliminaryInterview(InterviewResult preliminaryInterview) {
        this.preliminaryInterview = preliminaryInterview;
    }

    public InterviewResult getTechnicalInterview() {
        return technicalInterview;
    }

    public void setTechnicalInterview(InterviewResult technicalInterview) {
        this.technicalInterview = technicalInterview;
    }

    public InterviewResult getCommonResult() {
        return commonResult;
    }

    public void setCommonResult(InterviewResult commonResult) {
        this.commonResult = commonResult;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDatePreInt() {
        return datePreInt;
    }

    public void setDatePreInt(String datePreInt) {
        this.datePreInt = datePreInt;
    }

    public String getTimePreInt() {
        return timePreInt;
    }

    public void setTimePreInt(String timePreInt) {
        this.timePreInt = timePreInt;
    }

    public String getDateTecInt() {
        return dateTecInt;
    }

    public void setDateTecInt(String dateTecInt) {
        this.dateTecInt = dateTecInt;
    }

    public String getTimeTecInt() {
        return timeTecInt;
    }

    public void setTimeTecInt(String timeTecInt) {
        this.timeTecInt = timeTecInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interview interview = (Interview) o;

        if (id != interview.id) return false;
        if (preliminaryInterview != interview.preliminaryInterview) return false;
        if (technicalInterview != interview.technicalInterview) return false;
        if (commonResult != interview.commonResult) return false;
        return notes != null ? notes.equals(interview.notes) : interview.notes == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (preliminaryInterview != null ? preliminaryInterview.hashCode() : 0);
        result = 31 * result + (technicalInterview != null ? technicalInterview.hashCode() : 0);
        result = 31 * result + (commonResult != null ? commonResult.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "id=" + id +
                ", preliminaryInterview=" + preliminaryInterview +
                ", technicalInterview=" + technicalInterview +
                ", commonResult=" + commonResult +
                ", datePreInt='" + datePreInt + '\'' +
                ", timePreInt='" + timePreInt + '\'' +
                ", dateTecInt='" + dateTecInt + '\'' +
                ", timeTecInt='" + timeTecInt + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}

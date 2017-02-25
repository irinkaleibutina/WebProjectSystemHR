package bean;

/**
 * Created by irinaleibutina on 25.02.17.
 */
public class Interview {

    private int id;     // maybe it's not important field
    private InterviewResult preliminaryInterview;
    private InterviewResult technicalInterview;
    private InterviewResult commonResult;
    private String notes;

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
}

package es.wacoco.nowle.Spring.Model;

public class PatentData {
    private String title;
    private String description;
    private String applicant;
    private String inventors;

    public PatentData(String title, String description, String applicant, String inventors) {
        this.title = title;
        this.description = description;
        this.applicant = applicant;
        this.inventors = inventors;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for applicant
    public String getApplicant() {
        return applicant;
    }

    // Setter for applicant
    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    // Getter for inventors
    public String getInventors() {
        return inventors;
    }

    // Setter for inventors
    public void setInventors(String inventors) {
        this.inventors = inventors;
    }
}

package es.wacoco.nowle.Spring.Model;

public class LinkedinData {
    private String name;
    private String email;
    private String company;

    // Constructor to initialize the fields
    public LinkedinData(String name, String email, String company) {
        this.name = name;
        this.email = email;
        this.company = company;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for company
    public String getCompany() {
        return company;
    }

    // Setter for company
    public void setCompany(String company) {
        this.company = company;
    }
}
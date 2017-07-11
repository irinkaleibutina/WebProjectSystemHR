package by.epam.systemHR.bean;

import java.io.Serializable;

public class Skill implements Serializable {
    private int id;
    private String title;
    private String current_status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (id != skill.id) return false;
        if (!title.equals(skill.title)) return false;
        return current_status.equals(skill.current_status);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + current_status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", current_status='" + current_status + '\'' +
                '}';
    }
}

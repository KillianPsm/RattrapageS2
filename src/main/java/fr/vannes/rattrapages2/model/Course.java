package fr.vannes.rattrapages2.model;

public class Course {
    private int id;
    private String code;
    private String label;

    public Course() {
    }

    /**
     * @param code
     * @param label
     */
    public Course(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}

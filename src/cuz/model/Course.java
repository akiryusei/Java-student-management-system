package cuz.model;

public class Course {
    private String id;
    private String name;
    private double credit;

    public Course(String id, String name, double credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public Double getCredit() {
        return this.credit;
    }

    public String toString() {
        return this.id + ": " + this.name + ", " + this.credit;
    }
}

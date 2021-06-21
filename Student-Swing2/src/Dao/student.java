package Dao;

public class student {
    public String name;
    public String id;
    public int age;
    public String sex;
    public String dept;
    public String building;

    public student(String name, String id, int age, String sex, String dept, String building) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.dept = dept;
        this.building = building;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getDept() {
        return dept;
    }

    public String getBuilding() {
        return building;
    }
}

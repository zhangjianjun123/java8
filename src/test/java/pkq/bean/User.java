package pkq.bean;

import lombok.AllArgsConstructor;

import lombok.Data;

import java.util.Objects;

@Data
public class User {

    private String naem;
    private Integer age;
    private double gz;
    private Status Status;



    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }

    public  Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getGz() {
        return gz;
    }

    public void setGz(double gz) {
        this.gz = gz;
    }

   public User(String naem, Integer age, double gz) {
        this.naem = naem;
        this.age = age;
        this.gz = gz;
    }
    public User() {

    }
    public User(String naem,Integer age) {
        this.naem = naem;
        this.age = age;
    }
    public User(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "naem='" + naem + '\'' +
                ", age=" + age +
                ", gz=" + gz +
                ", Status=" + Status +
                '}';
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status Status) {
        this.Status = Status;
    }

    public User(String naem, Integer age, double gz,  pkq.bean.User.Status Status) {
        this.naem = naem;
        this.age = age;
        this.gz = gz;
        this.Status = Status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getAge() == user.getAge() &&
                Double.compare(user.getGz(), getGz()) == 0 &&
                Objects.equals(getNaem(), user.getNaem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaem(), getAge(), getGz());
    }

    public void setNaem() {
    }

   public enum Status{
       Red, Orange,Yellow, Green;

   }
}

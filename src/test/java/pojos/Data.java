package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    //If we are given limited data (for ex: just salary and name) what do you do?
    //with @JsonIgnoreProperties(ignoreUnknown = true) => it means:  if you have unknown properties, ignore them(skkip that data)
    //                                                     otherwise it will give us  error
    //                                                     for example we didnt write "profile_image here"
    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;

    public Data(int id, String employee_name, int employee_salary, int employee_age) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
    }

    public Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(int employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }

    @Override
    public String toString() { // convert whole data to String
        return "Data{" +
                "id=" + id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", employee_age=" + employee_age +
                '}';
    }
}

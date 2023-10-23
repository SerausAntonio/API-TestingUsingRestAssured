package day2;

public class Pojo_PostRequest {

    String id;
    String name;
    boolean active;
    int yearsOld;
    String color;
    Integer gradesArr[];

    public void setId(String id){
         this.id = id;

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setGradesArr(Integer[] gradesArr) {
        this.gradesArr = gradesArr;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public Integer[] getGradesArr() {
        return gradesArr;
    }

    public int getYearsOld() {
        return yearsOld;
    }

    public String getColor() {
        return color;
    }
}
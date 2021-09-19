package kalyagina.hw1;

public class Animal {
    private String name;
    private int age;
    private int weight;
    private String color;
    private String declension;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void say() {
        System.out.println("Я говорю");
    }

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    public String getDeclension(int age) {
        int lastChar = age % 10;
        if (age == 11)
            declension = "лет";
        else if (("" + age).endsWith("1"))
            declension = "год";
        else if (age > 11 && age < 15)
            declension = "лет";
        else if (lastChar > 1 && lastChar < 5)
            declension = "года";
        else declension = "лет";
        return declension;
    }

    @Override
    public String toString() {
        return "Привет! Меня зовут " + name +
                ", мне " + age + " " + getDeclension(age) +
                ", я вешу - " + weight +
                " кг, мой цвет - " + color;
    }
}
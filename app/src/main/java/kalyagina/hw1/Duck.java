package kalyagina.hw1;

public class Duck extends Animal {
    @Override
    public void say() {
        System.out.println("Кря");
    }

    public void fly() {
        System.out.println("Я лечу");
    }
}
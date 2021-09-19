package kalyagina.hw1;

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

    private static void registerNewAnimal(ArrayList<Animal> animal, Scanner scan) {
        String nameInput;
        int ageInput;
        int weightInput;
        String colorInput;
        System.out.println("Введите имя животного");
        nameInput = scan.next();
        System.out.println("Введите возраст животного");
        ageInput = Integer.parseInt(scan.next());
        System.out.println("Введите вес животного");
        weightInput = Integer.parseInt(scan.next());
        System.out.println("Введите цвет животного");
        colorInput = scan.next();
        Animal inputAnimal = animal.get(animal.size() - 1);
        inputAnimal.setName(nameInput);
        inputAnimal.setAge(ageInput);
        inputAnimal.setWeight(weightInput);
        inputAnimal.setColor(colorInput);
    }

    public static void main(String[] args) {

        ArrayList<Animal> animal = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String menuInput = new String();
        String menuAnimal;
        while (!menuInput.equals("exit")) {
            CommandsEnum command;
            System.out.println("Меню");
            System.out.println("Введите add для добавления животного");
            System.out.println("Введите list для печати списка животных");
            System.out.println("Введите exit для выхода из программы");
            menuInput = scan.next().trim().toUpperCase();
            try {
                command = CommandsEnum.valueOf(menuInput);
                switch (command) {
                    case ADD:
                        System.out.println("Введите животное: сat, dog или duck");
                        menuAnimal = scan.next().trim().toLowerCase();
                        switch (menuAnimal) {
                            case "cat":
                                Cat firstAnimal = new Cat();
                                animal.add(firstAnimal);
                                firstAnimal.say();
                                registerNewAnimal(animal, scan);
                                break;
                            case "dog":
                                Dog secondAnimal = new Dog();
                                animal.add(secondAnimal);
                                secondAnimal.say();
                                registerNewAnimal(animal, scan);
                                break;
                            case "duck":
                                Duck thirdAnimal = new Duck();
                                animal.add(thirdAnimal);
                                thirdAnimal.say();
                                registerNewAnimal(animal, scan);
                                break;
                            default:
                                System.out.println("Выберите животное из списка");
                                break;
                        }
                        break;
                    case LIST:
                        if (animal.isEmpty()) {
                            System.out.println("Животное не добавлено в список");

                        } else {
                            for (int i = 0; i < animal.size(); i++) {
                                System.out.println(animal.get(i).toString());
                            }
                        }
                        break;
                    case EXIT:
                        System.out.println("Завершение программы");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Вы ввели некорректную команду");
            }
        }
        System.out.println("До скорых встреч!");
    }
}
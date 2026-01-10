package develop.desafio.cadastro.models;

import java.util.Scanner;

public class Pet {
    String name;
    String breed;
    Gender gender;
    Type type;
    Adress adress;
    double age;
    double weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("preencha o nome do pet!");

        String regex = "^[\\p{L}-]+\\s+[\\p{L}\\s-]+$";
        if(name.trim().matches(regex)) {
            this.name = name.trim();
        } else {
            throw new IllegalArgumentException("preencha o nome e o sobrenome do pet!");
        }
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if(gender == null) throw new IllegalArgumentException("Escolha pelo menos um sexo entre macho ou femea");
        this.gender = gender;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if(type == null) throw new IllegalArgumentException("Escolha pelo menos um tipo entre cachorro ou gato");
        this.type = type;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        if(age < 0 || age > 20) throw  new IllegalArgumentException("idade não pode ser negativa ou maior do que 20");
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if(weight < 0.5 || weight > 60) throw new  IllegalArgumentException("Peso não pode ser negativo ou maior do que 60kg!");
        this.weight = weight;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Pet pet = new Pet();
        pet.setAge(input.nextDouble());
        System.out.println(pet.getAge());
    }
}

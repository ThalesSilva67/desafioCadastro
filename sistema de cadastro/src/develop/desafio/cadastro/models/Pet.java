package develop.desafio.cadastro.models;

import java.util.Scanner;

public class Pet {
    String name;
    String breed;
    Gender gender;
    Type type;
    Adress adress;
    String age;
    String weight;

    public static final String NAO_INFORMADO = "NAO INFORMADO";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("preencha o nome e o sobrenome do pet!");

        String regex = "^[\\p{L}-]+\\s+[\\p{L}\\s-]+$";
        if(name.trim().matches(regex)) {
            this.name = name.trim();
        } else {
            this.name = NAO_INFORMADO;
        }
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        if(breed == null) throw new IllegalArgumentException("A raça do pet não pode ser nula");
        String regex = "^([A-Za-z])\\w+$";
        if(breed.trim().matches(regex)) {
            this.breed = breed.trim();
        } else {
            this.breed = NAO_INFORMADO;
        }
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        if(age == null || age.isBlank()) {
            this.age = NAO_INFORMADO;
            return;
        }
        try {
            float ageFloat = Float.parseFloat(age);
            if(ageFloat < 0 || ageFloat > 20) throw new IllegalArgumentException("Idade não pode ser negativa ou maior do que 20!");
            this.age = String.valueOf(ageFloat);
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        if(weight == null || weight.isBlank()) {
            this.weight = NAO_INFORMADO;
            return;
        }
        try {
            float weightFloat = Float.parseFloat(weight);
            if(weightFloat < 0.5 || weightFloat > 60) throw new IllegalArgumentException("peso não pode ser menor do que 0.5 ou maior do que 60!");
            this.weight = String.valueOf(weightFloat);
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Pet pet = new Pet();
        pet.setWeight(input.nextLine());
        System.out.println(pet.getWeight());

    }
}

package develop.desafio.cadastro.services;

import develop.desafio.cadastro.models.Adress;
import develop.desafio.cadastro.models.Gender;
import develop.desafio.cadastro.models.Pet;
import develop.desafio.cadastro.models.Type;
import develop.desafio.cadastro.repository.FileRepository;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class PetService {
    Pet pet = new Pet();
    Adress adress = new Adress();
    FileRepository fileRepository  = new FileRepository();
    Path petCadastrados = Paths.get( "petsCadastrados");

    public void savePetFile() {
        String namePet = pet.getName();
        LocalDateTime now =  LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String formattedDate = dtf.format(now);
        String timeStamp = formattedDate + "-" + namePet + ".txt";

        File fileDirectory = new File("petsCadastrados");

        if(!fileDirectory.exists()) {
            if(fileDirectory.mkdirs()) System.out.println("Diretorio criado com sucesso!");
        }

        File file = new File(fileDirectory, timeStamp);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("1 - " + pet.getName());
            bw.newLine();
            bw.write("2 - " + pet.getType());
            bw.newLine();
            bw.write("3 - " + pet.getGender());
            bw.newLine();
            bw.write("4 - " + pet.getAdress().getStreet() + ", " + pet.getAdress().getCity() + ", " + pet.getAdress().getHouseNumber());
            bw.newLine();
            bw.write("5 - " + pet.getAge() + " ano(s)");
            bw.newLine();
            bw.write("6 - " + pet.getWeight() + " kg");
            bw.newLine();
            bw.write("7 - " + pet.getBreed());

            System.out.println("Pet criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void create(Scanner input) {
        fileRepository.readSpecificLine(1);
        pet.setName(input.nextLine());

        fileRepository.readSpecificLine(2);
        pet.setType(Type.valueOf(input.nextLine().toUpperCase()));

        fileRepository.readSpecificLine(3);
        pet.setGender(Gender.valueOf(input.nextLine().toUpperCase()));

        fileRepository.readSpecificLine(4);
        System.out.println("Rua: ");
        adress.setStreet(input.nextLine());
        System.out.println("Cidade: ");
        adress.setCity(input.nextLine());
        System.out.println("Número da casa: ");
        adress.setHouseNumber(input.nextLine());
        pet.setAdress(adress);

        fileRepository.readSpecificLine(5);
        pet.setAge(input.nextLine());

        fileRepository.readSpecificLine(6);
        pet.setWeight(input.nextLine());

        fileRepository.readSpecificLine(7);
        pet.setBreed(input.nextLine());


        savePetFile();
    }

    public ArrayList<Pet> getPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        File[] files =  petCadastrados.toFile().listFiles();

        for(int i = 0; i < Objects.requireNonNull(files).length; i++) {
            if(files[i].getName().endsWith(".txt")) {
                try(BufferedReader br = new BufferedReader(new FileReader(files[i]))) {
                    String name = br.readLine().split("-")[1].trim();
                    String type = br.readLine().split("-")[1].trim();
                    String gender = br.readLine().split("-")[1].trim();
                    String adress1 = br.readLine().split("-")[1].trim();
                    String age = br.readLine().split("-")[1].trim();
                    String weight = br.readLine().split("-")[1].trim();
                    String breed = br.readLine().split("-")[1].trim();

                    adress.adressCasting(adress1);

                    pet.setName(name);
                    pet.setType(Type.valueOf(type));
                    pet.setGender(Gender.valueOf(gender));
                    pet.setAdress(adress);
                    pet.setAge(age);
                    pet.setWeight(weight);
                    pet.setBreed(breed);
                    pet.setFile(files[i]);

                    pets.add(pet);
                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pets;
    }

    public void searchCategory(Scanner input) {
        boolean continuar = true;
        ArrayList<Pet> pets = getPets();

        if(pets.isEmpty()) { System.out.println("Nenhum pet encontrado!"); return; }

        while (continuar) {
            printOptionsSearch();

            System.out.println("Escolha uma opção: ");
            int option = Integer.parseInt(input.nextLine());

            System.out.println("Digite o valor da busca: ");
            String value = input.nextLine().toLowerCase();

            pets = filter(option, pets, value);

            System.out.println("Deseja adicionar outro filtro? (S/N)");
            continuar = input.nextLine().equalsIgnoreCase("S");
        }

        if(pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado!");
        } else {
            printPets(pets);
        }
    }

    public ArrayList<Pet> filter(int option, ArrayList<Pet> pets, String value) {
        ArrayList<Pet> petsFilter = new ArrayList<>();
        for (Pet pet : pets) {
            switch (option) {
                case 1:
                    if (pet.getName().toLowerCase().contains(value)) {
                        petsFilter.add(pet);
                    }
                    break;
                case 2:
                    if (pet.getGender().name().toLowerCase().contains(value)) {
                        petsFilter.add(pet);
                    }
                    break;
                case 3:
                    if (pet.getAge().contains(value)) {
                        petsFilter.add(pet);
                    }
                    break;
                case 4:
                    if (pet.getWeight().contains(value)) {
                        petsFilter.add(pet);
                    }
                    break;
                case 5:
                    if (pet.getBreed().toLowerCase().contains(value)) {
                        petsFilter.add(pet);
                    }
                    break;
                case 6:
                    if (pet.getAdress().toString().toLowerCase().contains(value)) {
                        petsFilter.add(pet);
                    }
                    break;

            }
        }
        return petsFilter;
    }

    public void delete(Scanner input) {
        ArrayList<Pet> pets = getPets();

        if(pets.isEmpty()) {System.out.println("Nenhum pet encontrado!"); return; }

        printPets(pets);
        int option;
        while(true){
            System.out.print("Digite o número do pet que deseja deletar: ");
            try{
                option = Integer.parseInt(input.nextLine());
                if(option < 1 || option > pets.size()) {
                    System.out.println("Número invalido, escolha novamente!");
                } else{
                    break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Pet selectedPet =  pets.get(option - 1);
        System.out.println("Tem certeza que deseja excluir o pet '" + selectedPet.getName().toUpperCase() + "'? (SIM / NÃO)");
        String decision =  input.nextLine();

        if(!decision.equalsIgnoreCase("SIM")){
            System.out.println("Cancelando exclusão");
            return;
        }

        File file = selectedPet.getFile();

        if(file.exists() && file.delete()) {
            System.out.println("Pet deletado com sucesso!");
        } else {
            System.out.println("Erro ao tentar deletar o pet!");
        }
    }

    public void updatePet(Scanner input) {
        ArrayList<Pet> pets = getPets();

        if(pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado!");
            return;
        }

        printPets(pets);
        int option;
        while(true){
            System.out.print("Escolha o número do pet que deseja alterar: ");
            try{
                option = Integer.parseInt(input.nextLine());
                if(option < 1 || option > pets.size()) {
                    System.out.println("Número invalido, escolha novamente!");
                } else{
                    break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

        Pet selectedPet =  pets.get(option - 1);
        System.out.println("Pet selecionado: " + selectedPet.getName());

        System.out.println("Novo nome (ENTER para manter o atual");
        String newName =  input.nextLine();
        System.out.println("Nova idade (ENTER para manter o atual");
        String newAge =  input.nextLine();
        System.out.println("Novo peso (ENTER para manter o atual");
        String newWeight =  input.nextLine();
        System.out.println("Nova raça (ENTER para manter o atual");
        String newBreed =  input.nextLine();
        System.out.println("Novo endereço (ENTER para manter o atual");
        String newAdress =  input.nextLine();


        if (!newName.isBlank()) {
            selectedPet.setName(newName);
        }
        if (!newAge.isBlank()) {
            selectedPet.setAge(newAge);
        }
        if (!newWeight.isBlank()) {
            selectedPet.setWeight(newWeight);
        }
        if (!newBreed.isBlank()) {
            selectedPet.setBreed(newBreed);
        }
        if (!newAdress.isBlank()) {
            selectedPet.getAdress().adressCasting(newAdress);
        }

        updatePetFile(selectedPet, selectedPet.getFile());
    }

    private void updatePetFile(Pet pet, File petFile) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(petFile))) {
            bw.write("1 - " + pet.getName());
            bw.newLine();
            bw.write("2 - " + pet.getType());
            bw.newLine();
            bw.write("3 - " + pet.getGender());
            bw.newLine();
            bw.write("4 - " + pet.getAdress().getStreet() + ", " + pet.getAdress().getCity() + ", " + pet.getAdress().getHouseNumber());
            bw.newLine();
            bw.write("5 - " + pet.getAge() + " ano(s)");
            bw.newLine();
            bw.write("6 - " + pet.getWeight() + " kg");
            bw.newLine();
            bw.write("7 - " + pet.getBreed());

            System.out.println("Pet atualizado!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPets(ArrayList<Pet> pets) {
        if(pets.isEmpty()) return;
        int i = 1;
        for(Pet p : pets){
            System.out.println(i + ". " + p.getName() + " - "
                    + p.getGender() + " - "
                    + p.getAdress().getStreet() + " - "
                    + p.getAdress().getCity() + " - "
                    + p.getAdress().getHouseNumber() + " - "
                    + p.getAge() + " - " + p.getWeight() + " - "
                    + p.getBreed());
            i++;
        }
    }

    public void printOptionsSearch() {
        String[] optionsSearch = new String[6];
        optionsSearch[0] = "1 - Nome ou sobrenome";
        optionsSearch[1] = "2 - Sexo";
        optionsSearch[2] = "3 - Idade";
        optionsSearch[3] = "4 - Peso";
        optionsSearch[4] = "5 - Raça";
        optionsSearch[5] = "6 - Endereço";

        for(String option : optionsSearch) {
            System.out.println(option);
        }
    }

    public static void main(String[] args) {
        PetService petService = new PetService();
        Scanner input = new Scanner(System.in);
        petService.delete(input);
    }


}

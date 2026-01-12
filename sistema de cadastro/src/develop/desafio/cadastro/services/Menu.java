package develop.desafio.cadastro.services;

import java.util.Scanner;

public class Menu {
    PetService  petService = new PetService();
    public static void options() {
        System.out.println("1 - Cadastrar um novo pet");
        System.out.println("2 - Alterar os dados do pet cadastrado");
        System.out.println("3 - Deletar um pet cadastrado");
        System.out.println("4 - Listar todos os pets cadastrados");
        System.out.println("5 - Listar pets por algum critério (idade, nome, raça)");
        System.out.println("6 - Sair");
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        int option;
        while(true) {
            options();
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    petService.create(input);
                    break;
                case 2:
                    petService.updatePet(input);
                    break;
                case 3:
                    petService.delete(input);
                    break;
                case 4:
                    petService.printPets(petService.getPets());
                    break;
                case 5:
                    petService.searchCategory(input);
                    break;
                case 6:
                    System.out.println("Finalizando programa...");
                    break;
                default:
                    System.out.println("Por favor, escolha uma opção de 1 a 6!");
            }
            if(option == 6) break;
        }
    }

}

package develop.desafio.cadastro.repository;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileRepository {
    Path path = Paths.get("src\\develop\\desafio\\cadastro\\forms\\formulario.txt");

    public void createFile() {
        File file = path.toFile();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("1 - Qual o nome e sobrenome do pet?\n2 - Qual o tipo do pet (Cachorro/Gato)\n3 - Qual o sexo do animal?\n4 - Qual endereço e bairro que ele foi encontrado?\n5 - Qual a idade aproximada do pet?\n6 - Qual o peso aproximado do pet?\n7 - Qual a raça do pet?");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readLine() {
        File file = path.toFile();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readSpecificLine(int l) {
        File file = path.toFile();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            while((line = br.readLine()) != null) {
                count++;
                if(l == count) {
                    System.out.println(line);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }




}

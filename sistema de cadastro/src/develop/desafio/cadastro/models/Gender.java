package develop.desafio.cadastro.models;

public enum Gender {
    GATO("Gato"),
    CACHORRO("Cachorro");

     final String description;
    Gender(String description) {
        this.description = description;
    }

}

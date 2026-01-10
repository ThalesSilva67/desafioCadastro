package develop.desafio.cadastro.models;

public enum Type {
    CACHORRO("Cachorro"),
    GATO("Gato");


    final String description;
    Type(String description) {
        this.description = description;
    }
}

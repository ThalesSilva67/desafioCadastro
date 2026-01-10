package develop.desafio.cadastro.models;

public enum Type {
    MACHO("Macho"),
    FEMEA("Femêa");


    final String description;
    Type(String description) {
        this.description = description;
    }
}

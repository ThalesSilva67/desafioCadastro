package develop.desafio.cadastro.models;

public enum Gender {
    MACHO("Macho"),
    FEMEA("Femea");

     final String description;
    Gender(String description) {
        this.description = description;
    }

}

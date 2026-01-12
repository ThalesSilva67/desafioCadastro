package develop.desafio.cadastro.models;

public class Adress {
    String street;
    String city;
    String houseNumber;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }

    public void adressCasting(String adress) {
        String[] formatter = adress.split(",");
        this.street = formatter[0].trim();
        this.city = formatter[1].trim();
        this.houseNumber = formatter[2].trim();
    }
}

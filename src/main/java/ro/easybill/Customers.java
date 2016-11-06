package ro.easybill;

public class Customers {
    private String name;
    private String amount;
    private String type;
    private String address;

    public Customers(String name, String amount, String type, String address) {
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

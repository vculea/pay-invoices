package ro.easybill;

public class Customers {
    private String name;
    private Long amount;
    private String address;

    public Customers(String name, Long amount, String address) {
        this.name = name;
        this.amount = amount;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", address='" + address + '\'' +
                '}';
    }
}

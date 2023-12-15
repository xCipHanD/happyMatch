package asia.sustech.happymatch;

public class Item {
    private final String name;
    private final String description;
    private final int price;
    private final int id;

    public Item(String name, String description, int price, int id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}

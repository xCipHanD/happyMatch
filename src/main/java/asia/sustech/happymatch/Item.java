package asia.sustech.happymatch;

public class Item {
    private final String name;
    private final String description;
    private final int price;
    private final int id;

    private int count;

    public Item(String name, String description, int price, int id, int count) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

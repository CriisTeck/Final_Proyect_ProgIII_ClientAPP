package models;

public class Article {
    private final int quantity;
    private final String name;
    private final int cost;

    public Article(int quantity, String name, int cost) {
        this.cost = cost;
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public static int compare(Article o, Article o1) {
        return Integer.compare(o.getQuantity(),o1.getQuantity());
    }

    public Object[] toArray(){
        return new Object[]{name,cost,quantity};
    }
}

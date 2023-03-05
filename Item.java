/**
 * Creates an item to be used throughout the dungeon.
 */
public class Item {
    private ItemType type;
    private String name;
    private double weight;
    private double value;
    private int strength = 0;

    /**
     * Generates item based on specified values.
     * @param type Enum Type of item.
     * @param name Name of item.
     * @param weight weight of item.
     * @param value value of item.
     */
    public Item(ItemType type, String name, double weight, double value) {
        this.type = type;
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    /**
     * Gets the enum type from item.
     * @return Enum Type
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Gets the name from item.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the weight from item.
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets the value from item.
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Gets the strength from item.
     * @return strength
     */
    public int getStrength() {
        return strength;
    }
}

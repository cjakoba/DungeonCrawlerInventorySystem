/**
 * Creates weapons or armor to be used throughout the dungeon.
 */
public class WeaponArmor extends Item {
    private int strength = 0;

    /**
     * Creates an Item with a strength value.
     * @param strength strength of weapon or armor.
     */
    public WeaponArmor(ItemType type, String name, double weight, double value, int strength) {
        super(type, name, weight, value);
        this.strength = strength;
    }
}

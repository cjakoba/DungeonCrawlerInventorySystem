import java.util.ArrayList;
import java.util.Scanner;

/**
 * Inventory management for adding items within max weight capacity, dropping items, printing items, and equipping items
 */
public class Inventory {
    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<Item> equippableWeapons = new ArrayList<>();
    private ArrayList<Item> equippableArmor = new ArrayList<>();
    private double maxWeight;
    // By default, hands and body are always equipped, unless a new item is generated to replace it.
    private Item equippedWeapon = new Item(null, "Fleshy Fists", 0, 0);
    private Item equippedArmor = new Item(null, "Fleshy Body", 0, 0);

    public Inventory(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * Adds an item to inventory if its weight doesn't exceed max weight of player's inventory.
     * @param item item to add to inventory.
     */
    public void add(Item item) {
        if (getCurrentWeight() + item.getWeight() <= maxWeight) {
            inventory.add(item);
            if (item.getType() == ItemType.WEAPON) {
                equippableWeapons.add(item);
            } else if (item.getType() == ItemType.ARMOR) {
                equippableArmor.add(item);
            }
            System.out.printf("The %s was added to your inventory.\n", item.getName());
        } else {
            System.out.printf("The %s cannot be picked up. Inventory full.\n", item.getName());
        }
    }

    /**
     * Prints a selective inventory based on item type.
     * @param type the type of item to search.
     * @param itemList the items to search through.
     * @return 0 if no items of matching type found, >= 1 if 1 or more item of matching type found.
     */
    public int printItem(ItemType type, ArrayList<Item> itemList) {
        if (itemList.size() == 0) {
            return 0;
        }

        int i = 0;
        for (Item item : itemList) {
            if (item.getType() == type) {
                System.out.printf("%d. %s\n", ++i, item.getName());
            }
        }
        if (i == 0) {
            System.out.println("No equipment to equip.");
        }
        return i;
    }

    /**
     * Prints the full inventory non-selectively with or without line numbers.
     * @param numbered boolean for printing line numbers.
     */
    public void printInventory(boolean numbered) {
        System.out.println("================== INVENTORY ==================");
        // When the inventory ArrayList (bag) is empty...
        if (inventory.size() == 0) {
            System.out.println("Inventory is empty...");
        } else {
            // Formatting header for table of items
            System.out.printf("   %-40s%10s%15s%15s\n", "Item", "Weight", "Value", "Strength");
            System.out.printf("%s\n", "-".repeat(85));
            int i = 1;
            for (Item item : inventory) {
                if (numbered) {
                    System.out.printf("%2d. ", i++);
                }
                System.out.printf("%-40s%10s%15s%15s\n", item.getName(), item.getWeight(), item.getValue(), item.getStrength());
            }
        }
        System.out.printf("Current weight %.2f.\n", getCurrentWeight());
    }

    /**
     * Drops an inventory item based on player's choice. Reverts to fists and body if currently equipped item is dropped
     */
    public void drop() {
        // Print full inventory with line numbers.
        printInventory(true);
        if (inventory.size() >= 1) {
            System.out.printf("Which item would you like to drop? [1 - %d] : ", inventory.size());
            Scanner in = new Scanner(System.in);
            Item choice = inventory.get(in.nextInt() - 1);
            // Remove weapon from equippable weapons, if currently equipped, equip fists.
            if (choice.getType() == ItemType.WEAPON) {
                if (equippedWeapon == choice) {
                    equippedWeapon = new Item(null, "Fleshy Fists", 0, 0);
                }
                equippableWeapons.remove(choice);
                // Remove armor from equippable armor, if currently equipped, equip body.
            } else if (choice.getType() == ItemType.ARMOR) {
                if (equippedArmor == choice) {
                    equippedArmor = new Item(null, "Fleshy Body", 0, 0);
                }
                equippableArmor.remove(choice);
            }
            // Remove from inventory as well.
            inventory.remove(choice);
        } else {
            System.out.println("No items to drop.");
        }
    }

    /**
     * Equips the player with their choice of picked up weapons or armors.
     * @param item item weapon or armor to equip.
     */
    public void equip(ItemType item) {
        Scanner in = new Scanner(System.in);
        if (item == ItemType.WEAPON) {
            int i = printItem(ItemType.WEAPON, equippableWeapons);
            if (i != 0) {
                System.out.printf("Which weapon would you like to equip? [1 - %d] : ", i);
                equippedWeapon = equippableWeapons.get(in.nextInt() - 1);
            }
        } else if (item == ItemType.ARMOR){
            int i = printItem(ItemType.ARMOR, equippableArmor);
            if (i != 0) {
                System.out.printf("Which armor would you like to equip? [1 - %d] : ", i);
                equippedArmor = equippableArmor.get(in.nextInt() - 1);
            }
        } else {
            System.out.println("No weapon or armor to equip in inventory.");
        }
        System.out.println("===============================================");
    }

    /**
     * Displays currently equipped weapon and armor.
     */
    public void printEquipped() {
        System.out.println("=============== EQUIPPED ITEMS ================");
        System.out.printf("Weapon slot: %s\n", equippedWeapon.getName());
        System.out.printf("Armor slot: %s\n", equippedArmor.getName());
    }

    /**
     * Gets the current inventory weight.
     * @return weight of inventory.
     */
    public double getCurrentWeight() {
        if (inventory.size() == 0) {
            return 0;
        }
        double sum = 0;
        // Cycle through inventory items summing up weight
        for (Item i : inventory) {
            sum += i.getWeight();
        }
        return sum;
    }
}
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads in an item text file and provides a randomly chosen item.
 */
public class ItemGenerator {
    private static final ArrayList<String[]> items = new ArrayList<>();

    /**
     * Reads in item data from specified text file.
     * @param fileName the text file storing world item data.
     */
    public ItemGenerator(String fileName) {
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Missing item file.");
        }

        Scanner file = new Scanner(reader);
        while (file.hasNext()) {
            String line = file.nextLine();
            // Read each line's item information seperated by $. Remove all tabbed spaces.
            // Example. items[0][0], is the first item's type.
            // items[0][1] = name, items[0][2] = weight, items[0][3] = value, items[0][4] = strength.
            items.add(line.replaceAll("[\t]","").split("\\$"));
        }
    }

    /**
     * Generates a random object from the world of type Weapon, Armor, or Misc.
     * @return Random Item object of Weapon, Armor, or Misc type.
     */
    public static Item generate() {
        // Generate a random number from 0 to number of items that exist in the dungeon.
        int rand = (int) Math.floor(Math.random() * (items.size() + 1));
        String[] item = items.get(rand);

        // Check the item's type, create item based off its stats.
        if (item[0].equals("WEAPON") || item[0].equals("ARMOR")) {
            return new WeaponArmor(ItemType.valueOf(item[0]), item[1], Double.parseDouble(item[2]), Double.parseDouble(item[3]), Integer.parseInt(item[4]));
        }
        return new Item(ItemType.MISC, item[1], Double.parseDouble(item[2]), Double.parseDouble(item[3]));
    }
}

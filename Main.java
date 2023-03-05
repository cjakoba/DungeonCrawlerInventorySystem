import java.util.Scanner;

/**
 * Game Controller for picking up items, dropping items, checking inventory, and equipping items.
 * @author Christian Jakob
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int input;
        // At least in Skyrim, all players start off with a carry weight of 300.
        Inventory inventory = new Inventory(300);
        new ItemGenerator("items.txt");

        // Game Menu Loop
        do {
            System.out.println("===================== MENU ====================");
            System.out.println("1. Print inventory");
            System.out.println("2. Add random item");
            System.out.println("3. Drop item");
            System.out.println("4. Equip weapon");
            System.out.println("5. Equip armor");
            System.out.println("6. Print equipped weapon and armor");
            System.out.println("7. Exit");
            System.out.print(" : ");
            // Take only integer input, avoiding exceptions
            while(!in.hasNextInt()) in.next();
            input = in.nextInt();
            // Menu items
            switch (input) {
                case 1:
                    inventory.printInventory(false);
                    break;
                case 2:
                    inventory.add(ItemGenerator.generate());
                    break;
                case 3:
                    inventory.drop();
                    break;
                case 4:
                    inventory.equip(ItemType.WEAPON);
                    break;
                case 5:
                    inventory.equip(ItemType.ARMOR);
                    break;
                case 6:
                    inventory.printEquipped();
                    break;
                default:
                    if (input != 7) {
                        System.out.println("Not a valid input");
                    }
            }
        } while (input != 7);
    }
}

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MultiConstKnapsack {
    static Item[] items;
    static int[] capacities;

    public static void main(String[] args) throws FileNotFoundException {

        readFile("sample1.txt");
        for (int i = 0; i < capacities.length; i++) {
            for (Item item : items) System.out.println(item.toString());
        }

//        readFile("sample2.txt");
//        for (int i = 0; i < capacities.length; i++) {
//            for (Item item : items) System.out.println(item.toString());
//        }
//
//        readFile("sample3.txt");
//        for (int i = 0; i < capacities.length; i++) {
//            for (Item item : items) System.out.println(item.toString());
//        }
    }

    private static void readFile(String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);
        Scanner sc = new Scanner(inputFile);

        int knapCount = sc.nextInt();
        int itemCount = sc.nextInt();
        items = new Item[itemCount];
        capacities = new int[knapCount];
        for (int i = 0; i < itemCount; i++) {
            int value = sc.nextInt();
            Item currentItem = new Item(value, knapCount);
            items[i] = currentItem;
        }

        for (int i = 0; i < knapCount; i++)
            capacities[i] = sc.nextInt();

        for (int j = 0; j < knapCount; j++) {
            for (int i = 0; i < itemCount; i++) {
                int weight = sc.nextInt();
                items[i].SetWeights(weight);
            }
        }
        sc.close();
    }
}
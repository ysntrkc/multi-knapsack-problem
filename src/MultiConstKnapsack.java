import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MultiConstKnapsack {

    public static void main(String[] args) throws FileNotFoundException {

        File inputFile = new File("sampleinput.txt");
        Scanner sc = new Scanner(inputFile);

        int knapCount = sc.nextInt();
        int itemCount = sc.nextInt();
        Item[] items = new Item[itemCount];
        int[] capacities = new int[knapCount];
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
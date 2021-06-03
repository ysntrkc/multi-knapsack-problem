import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MultiConstKnapsack {
    static Item[] items;
    static Item[] knapsack;
    static int[] capacities;
    static int knapCount, itemCount;

    public static void main(String[] args) throws IOException {

        readFile("inputs/sample3.txt");
        Arrays.sort(items);
        System.out.println(Arrays.toString(items));

        //todo
        // add one by one from items array to the knapsack and each time compare the capacities
        // if exceeds capacity remove that item and go on with other items
        // if not go on adding other items.

//        long startTime = System.nanoTime();
//        writeFile("outputs/output1.txt");
//         long endTime = System.nanoTime();
//         long timeApproval = endTime - startTime;
//         System.out.println("time elapsed  : " + timeApproval / 1000000);
    }

    private static void readFile(String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);
        Scanner sc = new Scanner(inputFile);

        knapCount = sc.nextInt();
        itemCount = sc.nextInt();
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

    private static void writeFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        // calculate value sum
        // printWriter.println(allSum);
        for (int i = 0; i < itemCount; i++) {
            if (knapsack[i] == null) {
                printWriter.println(0);
            } else {
                printWriter.println(1);
            }
        }
        printWriter.close();
    }
}

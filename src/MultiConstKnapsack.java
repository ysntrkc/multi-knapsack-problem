import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MultiConstKnapsack {
    static Item[] items;
    static int[] knapsack;
    static int[] capacities;
    static int[] weightSum;
    static int knapCount, itemCount;

    public static void main(String[] args) throws IOException {
        readFile("inputs/sample1.txt");
        Arrays.sort(items);

        int totalValue = fillKnapsack();
        writeFile("outputs/output1.txt", totalValue);


//        readFile("inputs/sample2.txt");
//        Arrays.sort(items);
//
//        int totalValue2 = fillKnapsack();
//        writeFile("outputs/output2.txt", totalValue2);
//
//
//        readFile("inputs/sample3.txt");
//        Arrays.sort(items);
//
//        int totalValue3 = fillKnapsack();
//        writeFile("outputs/output3.txt", totalValue3);
    }

    private static int fillKnapsack() {

        int totalValue = 0;
        boolean control = false;
        weightSum = new int[knapCount];
        knapsack = new int[itemCount];

        // Add one by one from items array to the knapsack and each time compare the capacities
        for (int i = 0; i < itemCount; i++) {
            for (int j = 0; j < knapCount; j++) {

                // If exceeds capacity don't add that item and go on with other items
                control = capacities[j] >= (weightSum[j] + items[i].GetWeightsElement(j));

                // If not go on adding other items
                if (!control)
                    break;
            }
            //Add to knapsack according to the control boolean
            if (control) {
                for (int j = 0; j < knapCount; j++) {
                    weightSum[j] += items[i].GetWeightsElement(j);
                }
                totalValue += items[i].GetValue();
                knapsack[items[i].GetID()] = 1;
            }
        }

        return totalValue;
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

    private static void writeFile(String fileName, int totalValue) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(totalValue);

        for (int i = 0; i < itemCount; i++) {
            printWriter.println(knapsack[i]);
        }
        printWriter.close();
    }
}

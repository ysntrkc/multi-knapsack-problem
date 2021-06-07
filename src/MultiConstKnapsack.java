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
    static int[] weightSum;
    static int[] idArr;
    static int knapCount, itemCount;

    public static void main(String[] args) throws IOException {

        readFile("inputs/sample3.txt");
        Arrays.sort(items);

        //todo
        // add one by one from items array to the knapsack and each time compare the capacities
        // if exceeds capacity remove that item and go on with other items
        // should add weight sum array.
        // if not go on adding other items.

        int totalValue = fillKnapsack();
        writeFile("outputs/output3.txt",totalValue);
    }

    private static int fillKnapsack() {
        int totalValue = 0;
        boolean control = false;
        weightSum = new int[knapCount];
        idArr = new int [itemCount];
        knapsack = new Item[itemCount];
        for (int i = 0; i < itemCount; i++) {
            for(int j = 0; j < knapCount; j++){
                control = capacities[j] >= (weightSum[j] + items[i].GetWeightsElement(j));
            }
            //add to knapsack according to the boolean value
            if(control){
                knapsack[i] = items[i];
                for(int j = 0; j < knapCount; j++){
                    weightSum[j] += items[i].GetWeightsElement(j);
                }
                totalValue += items[i].GetValue();
                idArr[items[i].GetID()] = 1;
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
        //todo check here later

        for (int i = 0; i < itemCount; i++) {
            printWriter.println(idArr[i]);
        }
        printWriter.close();
    }
}

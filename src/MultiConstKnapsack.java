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

        readFile("inputs/sample1.txt");
        writeFile("outputs/output1.txt");

//        long startTime = System.nanoTime();
//        int[] weights = new int[knapCount];
//        int allSum = SubsetSums(items);
//        System.out.println(allSum);
//        int sum = 0;
//        for (int i = 0; i < itemCount; i++){
//            if(knapsack[i] == null){
//                System.out.println(0);
//            }
//            else {
//                System.out.println(1);
//                for(int j = 0; j < knapCount; j++){
//                    weights[j] += knapsack[i].GetWeightsElement(j);
//                }
//                sum += knapsack[i].GetValue();
//            }
//        }
//        System.out.println("The real sum: " + sum);
//        System.out.println(Arrays.toString(weights));
//        long endTime = System.nanoTime();
//        long timeApproval = endTime - startTime;
//        System.out.println("time elapsed  : " + timeApproval / 1000000);
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
        int allSum = SubsetSums(items);
        printWriter.println(allSum);
        for (int i = 0; i < itemCount; i++) {
            if (knapsack[i] == null) {
                printWriter.println(0);
            } else {
                printWriter.println(1);
            }
        }
        printWriter.close();
    }

    private static int SubsetSums(Item[] arr) {
        int total = 1 << itemCount;
        int max = Integer.MIN_VALUE;

        knapsack = new Item[itemCount];
        Item[] tempKnapsack = new Item[itemCount];

        int[] itemWeights = new int[knapCount];

        for (int i = 0; i < total; i++) {
            int sum = 0;
            for (int j = 0; j < itemCount; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[j].GetValue();
                    for (int k = 0; k < knapCount; k++) {
                        itemWeights[k] += arr[j].GetWeightsElement(k);
                    }
                    tempKnapsack[j] = arr[j];
                }
            }

            for (int m = 0; m < knapCount; m++) {
                if (capacities[m] < itemWeights[m]) {
                    sum = 0;
                    Arrays.fill(tempKnapsack, null);
                    break;
                }
            }

            Arrays.fill(itemWeights, 0);

            if (sum > max) {
                max = sum;
                Arrays.fill(knapsack, null);
                knapsack = tempKnapsack.clone();
            }
            Arrays.fill(tempKnapsack, null);
        }
        return max;
    }
}
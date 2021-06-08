import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MultiConstKnapsack {
    static Item[] items; //To hold all items
    static int[] knapsack; //To hold the existence state of each item.
    static int[] capacities; //To hold the capacities of each constraint.
    static int[] weightSum; //To hold each constraint's total weight.
    static int knapCount, itemCount;

    static String inputText, outputText;

    public static void main(String[] args) throws IOException {
        int totalValue = 0;

        TakeUserInput();                            //Take input
        readFile(inputText);                        //Read file
        Arrays.sort(items);                         //Sort by each average ratio.
        totalValue = fillKnapsack();                //Fill the hypothetical knapsack.
        writeFile(outputText , totalValue);         //Write to an output file.

    }

    private static void TakeUserInput() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your input file name: ");
        inputText = input.next();

        System.out.print("\nEnter your output file name: ");
        outputText = input.next();
    }

    //Fill knapsack hypothetically, in reality function fills a zero-one array for each item in the hypothetical knapsack.
    private static int fillKnapsack() {

        int totalValue = 0;
        boolean control = false; //controls the availability state
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
                totalValue += items[i].GetValue(); //sum up each value
                knapsack[items[i].GetID()] = 1; //add it to the knapsack
            }
        }

        return totalValue;
    }

    //Reads that according to the input format.
    private static void readFile(String fileName) throws FileNotFoundException {
        File inputFile = new File(fileName);
        Scanner sc = new Scanner(inputFile);

        knapCount = sc.nextInt(); //constraint count
        itemCount = sc.nextInt(); //number of items
        items = new Item[itemCount];
        capacities = new int[knapCount];

        //Get the value and create an object with those values.
        for (int i = 0; i < itemCount; i++) {
            int value = sc.nextInt();
            Item currentItem = new Item(value, knapCount);
            items[i] = currentItem;
        }

        //Get the capacities of each constraint.
        for (int i = 0; i < knapCount; i++)
            capacities[i] = sc.nextInt();

        //Get and set each weight onto that item.
        for (int j = 0; j < knapCount; j++) {
            for (int i = 0; i < itemCount; i++) {
                int weight = sc.nextInt();
                items[i].SetWeights(weight);
            }
        }
        sc.close();
    }

    //Write to file
    private static void writeFile(String fileName, int totalValue) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(totalValue); //First print total value.

        for (int i = 0; i < itemCount; i++) { //Print the zero-one array.
            printWriter.println(knapsack[i]);
        }
        printWriter.close();
    }
}

import java.util.Arrays;

public class Item implements Comparable<Item> {

    private final static double EPSILON = 0.000001;
    private final int VALUE; //value
    private final int KNAPCOUNT; //number of constraints
    private final int ID; //order in input file
    private final int[] WEIGHTS; //weight fore each constraint

    private static int idCounter;

    private float avgRatio;
    private int index;

    //Constructor
    public Item(int value, int knapCount) {
        VALUE = value;
        KNAPCOUNT = knapCount;
        WEIGHTS = new int[knapCount];
        ID = idCounter;
        idCounter++; //increments order in input file.
    }

    //Set weight of that item for each constraint.
    public void SetWeights(int weight) {
        if (index == KNAPCOUNT)
            return;

        WEIGHTS[index] = weight;
        index++;
        if(index == KNAPCOUNT) {
            SetAvgRatio();
        }
    }
    //Sort items according to this value.
    public void SetAvgRatio() {
        int weightSum = 0;
        for (int i = 0; i < KNAPCOUNT; i++){ //Weight sum is calculated.
            weightSum += WEIGHTS[i];
        }
        avgRatio = (float) VALUE / weightSum;
    }

    public float GetAvgRatio(){
        return avgRatio;
    }

    public int GetValue() {
        return VALUE;
    }

    public int GetKnapCount() {
        return KNAPCOUNT;
    }

    public int GetID() {
        return ID;
    }

    public int[] GetWeights() {
        return WEIGHTS;
    }

    public int GetWeightsElement(int index) {
        return WEIGHTS[index];
    }


    @Override
    public int compareTo(Item item) { //Compares according to avgRatio of that item.
        if(avgRatio < item.avgRatio)
            return 1;
        else if (Math.abs(item.avgRatio - avgRatio) < EPSILON) //avoiding precision errors.
            return 0;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "Item{" +
                "VALUE=" + VALUE +
                ", WEIGHTS=" + Arrays.toString(WEIGHTS) +
                ", id=" + ID +
                ", avgRatio=" + avgRatio +
                "}\n";
    }
}

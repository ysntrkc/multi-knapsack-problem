import java.util.Arrays;

public class Item implements Comparable<Item> {

    private final static double EPSILON = 0.000001;
    private final int VALUE;
    private final int KNAPCOUNT;
    private final int ID;
    private final int[] WEIGHTS;

    private static int idCounter;

    private float avgRatio;
    private int index;

    public Item(int value, int knapCount) {
        VALUE = value;
        KNAPCOUNT = knapCount;
        WEIGHTS = new int[knapCount];
        ID = idCounter;
        idCounter++;
    }

    public void SetWeights(int weight) {
        if (index == KNAPCOUNT)
            return;

        WEIGHTS[index] = weight;
        index++;
        if(index == KNAPCOUNT) {
            SetAvgRatio();
        }
    }

    public void SetAvgRatio() {
        int weightSum = 0;
        for (int i = 0; i < KNAPCOUNT; i++){
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
    public int compareTo(Item item) {
        if(avgRatio < item.avgRatio)
            return 1;
        else if (Math.abs(item.avgRatio - avgRatio) < EPSILON)
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

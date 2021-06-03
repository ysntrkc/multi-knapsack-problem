import java.util.Arrays;

public class Item implements Comparable<Item> {
    private final static double EPSILON = 0.000001;

    private final int value;
    private final int knapCount;
    private float avgRatio;
    private final int[] weights;
    private int index;

    public Item(int value, int knapCount) {
        this.value = value;
        this.knapCount = knapCount;
        weights = new int[knapCount];
        index = 0;
    }

    public void SetWeights(int weight) {
        if (index == knapCount)
            return;

        weights[index] = weight;
        index++;
        if(index == knapCount) {
            SetAvgRatio();
        }
    }

    public void SetAvgRatio() {
        int weightSum = 0;
        for (int i = 0; i < knapCount; i++){
            weightSum += weights[i];
        }
        avgRatio = (float) value / weightSum;
    }

    public float GetAvgRatio(){
        return avgRatio;
    }

    public int GetValue() {
        return value;
    }

    public int GetKnapCount() {
        return knapCount;
    }

    public int[] GetWeights() {
        return weights;
    }

    public int GetWeightsElement(int index) {
        return weights[index];
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
                "value=" + value +
                ", avgRatio=" + avgRatio +
                ", weights=" + Arrays.toString(weights) +
                "}\n";
    }
}

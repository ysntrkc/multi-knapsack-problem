import java.util.Arrays;

public class Item {

    private final int value;
    private final int knapCount;
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

    @Override
    public String toString() {
        return "Item{" +
                "value=" + value +
                ", weights=" + Arrays.toString(weights) +
                '}';
    }
}

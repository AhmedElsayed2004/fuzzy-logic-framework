package variables;

public class Domain {
    private double min;
    private double max;

    public Domain(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("min cannot be greater than max");
        }
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public boolean contains(double value) {
        return value >= min && value <= max;
    }
}

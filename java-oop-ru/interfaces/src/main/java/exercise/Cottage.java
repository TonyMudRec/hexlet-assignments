package exercise;

// BEGIN
public class Cottage implements Home {
    double area;
    int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public int getFloorCount() {
        return floorCount;
    }

    @Override
    public String toString() {
        return getFloorCount() + " этажный коттедж площадью " + getArea() + " метров";
    }

    @Override
    public double getArea() {
        return 0;
    }
}
// END

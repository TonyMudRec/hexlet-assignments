package exercise;

// BEGIN
class Circle extends NegativeRadiusException {
    Point point;
    int radius;

    public Circle(Point point, int radius) {
        super("");
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
            return validateSquare(Math.PI * Math.pow(radius, 2));
    }

    public double validateSquare(double square) throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("radius must be greater than zero");
        } else {
            return square;
        }
    }
}
// END

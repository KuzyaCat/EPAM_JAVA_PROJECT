class Triangle {

    private double firstSide;
    private double secondSide;
    private double thirdSide;

    public void Triangle(double firstSide, double secondSide, double thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    public boolean isValidSides(double a, double b, double c) throws TriangleException{

        if (a == 0 || b == 0 || c == 0){
            throw new TriangleException("Sorry, you can't create a triangle with zero sides");
        }
        else if(a < 0 || b < 0 || c < 0){
            throw new TriangleException("You can't create a triangle with negative sides");
        }
        else if ( Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c)){
            throw new TriangleException("Sorry, you can't create a triangle with NaN sides");
        }
        return ((a + b) > c && (a + c) > b && (b + c) > a);
    }

    public String typeOfTriangle(double a, double b, double c) throws TriangleException{
        Triangle triangle = new Triangle();
        if(triangle.isValidSides(a,b,c)) {
            if (a == b && b == c) return "EQUILATERAL";
            if (a == b || b == c || a == c) return "ISOSCELES";
            if( (a*a + b*b == c*c) || (a*a + c*c == b*b) || (c*c + b*b == a*a)) return "RIGHT";
            return "SCALENE";
        }
        else
            throw new TriangleException("Это даже не треугольник.....");
    }

}

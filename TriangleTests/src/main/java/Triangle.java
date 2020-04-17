class Triangle {
    private float firstSide;
    private float secondSide;
    private float thirdSide;

    public void Triangle(float firstSide, float secondSide, float thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    public boolean isTriangle(float a,float b, float c){
        return (a + b) > c && (a + c) > b && (b + c) > a;

    }
}

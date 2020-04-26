import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TriangleTest {
    private static Triangle triangle;

    @BeforeClass
    public static void setupTriangle() {
        triangle = new Triangle();
    }

    @DataProvider
    public static Object[][] fractionNumbers() {
        return new Object[][] { {1/3d, 1/2d, 1/4d}, {15/6d, 15/7d, 15/8d}, {14/6, 13/8, 12/5d} };
    }

    @DataProvider
    private Object[][] sidesAreZero() {
        return new Object[][] { { 0, 1, 1 },
                {0,1,0}, {1,1,0}, {0,0,1}, {1,0,0}, {1,0,1}, {0,0,0} };
    }

    @DataProvider
    private Object[][] negativeSides(){
        return new Object[][] { {-1,2,2}, {-1, -2, 5}, {1,-4,7}, {-6,-3,-2} };
    }

    @DataProvider
    private Object[][] doubleNumbers(){
        return new Object[][] { {1.24,2.48,3.14}, {2.48,4.96,6.28}, {3, 4.78, 5.24}, {1.24, 2, 3.14}, {1.24, 2.48, 3}, };
    }

    @DataProvider
    private Object[][] bigSides(){
        return new Object[][] { {1111111111111111111111111d,1111111111111111111111111d, 1111111111111111111111111d},
                {152135231521351345314d,152135231521351345314d,234523454354412d}, {5653543623424524532342453245245d,5653543623424524532342453245245d,5653543623424524532342453245245d} };
    }

    @DataProvider
    private Object[][] smallSides(){
        return new Object[][] { {0.0001d, 0.0001d, 0.0001d}, {0.0000003d, 0.0000001d, 0.0000003d}, {0.0000000003d, 0.0000000004d, 0.0000000005d} };
    }

    @DataProvider
    private Object[][] existingOfEquilateralTriangle(){
        return new Object[][] { {124,124,124}, {2,2,2}, {12321,12321,12321 }};
    }

    @DataProvider
    private Object[][] existingOfIsoscelesTriangle(){
        return new Object[][] { {5,5,2}, {1235,1235, 212}, {16,16,10} };
    }

    @DataProvider
    private Object[][] existingOfScaleneTriangle(){
        return new Object[][] { {7,9,10}, {16,17,18}, {21,26,35} };
    }
    @DataProvider
    private Object[][] existingOfRightTriangle(){
        return new Object[][] { {3,4,5}, {6,8,10}, {16,12,20} };
    }
    @DataProvider
    private Object[][] impossibleSides() {
        return new Object[][]{{1, Double.NaN, 15}, {Double.NaN, 12, 41}};
    }


    @Test(dataProvider = "sidesAreZero",
            expectedExceptions = { TriangleException.class },
            expectedExceptionsMessageRegExp = "Sorry, you can't create a triangle with zero sides")
    public void nullSide(double a, double b, double c) throws TriangleException {
        triangle.isValidSides(a,b,c);
    }

    @Test(dataProvider = "negativeSides",
            expectedExceptions = { TriangleException.class },
            expectedExceptionsMessageRegExp = "You can't create a triangle with negative sides")
    public void negativeSides(double a, double b, double c) throws TriangleException {
        triangle.isValidSides(a,b,c);
    }

    @Test(dataProvider = "doubleNumbers")
    public void doubleSides(double a, double b, double c) throws TriangleException {
        triangle.isValidSides(a,b,c);
    }

    @Test(dataProvider = "fractionNumbers")
    public void fractionSides(double a, double b, double c) throws TriangleException{
        triangle.isValidSides(a,b,c);
    }

    @Test(dataProvider = "bigSides")
    public void bigSides(double a, double b, double c) throws TriangleException{
        triangle.isValidSides(a,b,c);
    }

    @Test(dataProvider = "smallSides")
    public void smallSides(double a, double b, double c) throws TriangleException{
        triangle.isValidSides(a,b,c);
    }

    @Test(dataProvider = "existingOfEquilateralTriangle")
    public void equilateralTriangle(double a, double b, double c) throws TriangleException{
        Assert.assertEquals(triangle.typeOfTriangle(a, b, c), "EQUILATERAL");
    }
    @Test(dataProvider = "existingOfIsoscelesTriangle")
    public void isoscelesTriangle(double a, double b, double c) throws TriangleException{
        Assert.assertEquals(triangle.typeOfTriangle(a,b,c), "ISOSCELES");
    }

    @Test(dataProvider = "existingOfScaleneTriangle")
    public void scaleneTriangle(double a, double b, double c) throws TriangleException{
        Assert.assertEquals(triangle.typeOfTriangle(a,b,c), "SCALENE");
    }
    @Test(dataProvider = "existingOfRightTriangle")
    public void rightTriangle(double a, double b, double c) throws TriangleException{
        Assert.assertEquals(triangle.typeOfTriangle(a,b,c), "RIGHT");
    }
    @Test(dataProvider = "impossibleSides", expectedExceptions = Exception.class, expectedExceptionsMessageRegExp = "Sorry, you can't create a triangle with NaN sides")
    public void checkIncorrectSides(double a, double b, double c) throws TriangleException {
        triangle.isValidSides(a,b,c);
    }


}

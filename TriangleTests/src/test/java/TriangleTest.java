import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TriangleTest {
    private static Triangle triangle;

    @BeforeClass
    public static void setupTriangle() {
        triangle = new Triangle();
    }

    @Test
    public void existingOfEquilateralTriangle(){
        Assert.assertTrue(triangle.isTriangle(1,1,1));
        Assert.assertTrue(triangle.isTriangle(2,2,2));
        Assert.assertTrue(triangle.isTriangle(52315231,52315231,52315231));
        Assert.assertTrue(triangle.isTriangle(1111231252,1111231252,1111231252));
    }
    @Test
    public void existingOfScaleneTriangle(){
        Assert.assertTrue(triangle.isTriangle(3,4,5));
        Assert.assertTrue(triangle.isTriangle(7,9,10));
        Assert.assertTrue(triangle.isTriangle(16,17,18));
        Assert.assertTrue(triangle.isTriangle(21,26,35));
    }
    @Test
    public void existingOfIsoscelesTriangle(){
        Assert.assertTrue(triangle.isTriangle(5,5,2));
        Assert.assertTrue(triangle.isTriangle(1235,1235,212));
        Assert.assertTrue(triangle.isTriangle(55143545,55143545,12));
    }
    @Test
    public void checkNegativeSides(){
        Assert.assertTrue(triangle.isTriangle(3, 4, 5));
        Assert.assertTrue(triangle.isTriangle(6, 8, 10));
        Assert.assertTrue(triangle.isTriangle(7, 8, 9));
        Assert.assertTrue(triangle.isTriangle(-7, -18, 19));
        Assert.assertTrue(triangle.isTriangle(-7, -11, -12));
        Assert.assertTrue(triangle.isTriangle(7, -13, -15));
        Assert.assertTrue(triangle.isTriangle(7, 15, -21));
        Assert.assertTrue(triangle.isTriangle(7, -13, 8));
    }

    @Test
    public void nullSide(){
        Assert.assertFalse(triangle.isTriangle(1,1,0));
        Assert.assertFalse(triangle.isTriangle(0,1,1));
        Assert.assertFalse(triangle.isTriangle(1,0,1));
        Assert.assertFalse(triangle.isTriangle(0,0,1));
        Assert.assertFalse(triangle.isTriangle(1,0,0));
    }

    @Test
    public void noIllegalSides() {
        Assert.assertFalse(triangle.isTriangle(0,0,0));
    }
    @Test
    public void floatNumbers(){
        Assert.assertTrue(triangle.isTriangle(1.24f, 2.48f, 3.14f));
        Assert.assertTrue(triangle.isTriangle(2.48f, 4.96f, 6.28f));
        Assert.assertTrue(triangle.isTriangle(3f, 4.78f, 5.24f));
        Assert.assertTrue(triangle.isTriangle(1.24f, 2, 3.14f));
        Assert.assertTrue(triangle.isTriangle(1.24f, 2.48f, 3));
        Assert.assertTrue(triangle.isTriangle(1.24f, 2.48f, 3));
    }

    @Test
    public void fractionNumbers(){
        Assert.assertTrue(triangle.isTriangle(1/3f, 2/3f,3/4f ));
        Assert.assertTrue(triangle.isTriangle(3/3f, 3/3f,4/4f ));
        Assert.assertTrue(triangle.isTriangle(5/3f, 7/3f,6/4f ));
        Assert.assertTrue(triangle.isTriangle(4, 12/3f,16/4f ));
        Assert.assertTrue(triangle.isTriangle(8/3f, 3,4 ));
        Assert.assertTrue(triangle.isTriangle(4, 12/3f,16/4f ));
    }

    @Test
    public void bigSides(){
        Assert.assertTrue(triangle.isTriangle(1111111111111111111111111f,1111111111111111111111111f,1111111111111111111111111f));
        Assert.assertTrue(triangle.isTriangle(152135231521351345314f,152135231521351345314f,234523454354412F));
        Assert.assertTrue(triangle.isTriangle(5653543623424524532342453245245F,5653543623424524532342453245245F,5653543623424524532342453245245F));
    }

    @Test
    public void smallSides(){
        Assert.assertTrue(triangle.isTriangle(0.0001f, 0.0001f, 0.0001f));
        Assert.assertTrue(triangle.isTriangle(0.0000003f, 0.0000001f, 0.0000003f));
        Assert.assertTrue(triangle.isTriangle(0.0000000003f, 0.0000000004f, 0.0000000005f));
    }

    @Test
    public void nullException() throws TriangleException {
        triangle = null;
        if (triangle == null) {
            throw new TriangleException("Triangle is empty!");
        }
    }
}

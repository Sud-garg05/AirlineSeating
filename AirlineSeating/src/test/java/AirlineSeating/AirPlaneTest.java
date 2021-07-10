package AirlineSeating;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AirPlaneTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void allocateAll()
    {
        int[][] testArray= {{3,2},{4,3},{2,3},{3,4}};
        AirPlane airPlane=new AirPlane(testArray);
        int totalpassengers=30;
        boolean allreserved=true;
        for(int i=0;i<totalpassengers;i++)
        {
            if(!airPlane.cheakAndAllocate(i+1)) {
                allreserved = false;
                break;
            }
        }
        Assert.assertTrue(allreserved);
    }


    @Test
    public void reservingMoreThanThreshold ()
    {
        int[][] testArray= {{3,2},{4,3},{2,3},{3,4}};
        AirPlane airPlane=new AirPlane(testArray);
        int totalpassengers=50;
        boolean allreserved=true;
        int expectednotreserved=37;
        int actualnotreserved=0;
        for(int i=0;i<totalpassengers;i++)
        {
            if(!airPlane.cheakAndAllocate(i+1)) {
                allreserved = false;
                actualnotreserved=(i+1);
                break;
            }
        }
        Assert.assertEquals(expectednotreserved,actualnotreserved);
    }

}

package UnitTests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * Testing Planes
 *  @author Michael Bergshtein and Yishay Lutvak
 */
public class PlaneTest {

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane pl = new Plane(new Point3D(1,1,0),new Point3D(1,-1,0),new Point3D(-1,-1,0));
        assertTrue(pl.getNormal(null).equals(new Vector(0,0,1)));
    }
}
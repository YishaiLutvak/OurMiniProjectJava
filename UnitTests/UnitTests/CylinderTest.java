package UnitTests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * Testing Cylindes
 *  @author Michael Bergshtein and Yishai Lutvak
 */
public class CylinderTest {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        Cylinder cy = new Cylinder(5, new Ray(new Point3D(0,0,0),new Vector(1,0,0)),10);

        // ============ Equivalence Partitions Tests ==============

        //Tests for Equivalence Partition 1 - cylinder side
        assertTrue("getNormal function is not proper for the cylinder side",
                cy.getNormal(new Point3D(1,5,0)).equals(new Vector(new Point3D(0,1,0))));
        assertTrue("getNormal function is not proper for the cylinder side",
                cy.getNormal(new Point3D(1,-5,0)).equals(new Vector(new Point3D(0,-1,0))));
        assertTrue("getNormal function is not proper for the cylinder side",
                cy.getNormal(new Point3D(1,0,5)).equals(new Vector(new Point3D(0,0,1))));
        assertTrue("getNormal function is not proper for the cylinder side",
                cy.getNormal(new Point3D(1,0,-5)).equals(new Vector(new Point3D(0,0,-1))));

        //Tests for Equivalence Partition 2 - bottom base
        assertTrue("getNormal function is not proper for the bottom of the cylinder",
                cy.getNormal(new Point3D(0,0,0)).equals(new Vector(new Point3D(-1,0,0))));
        assertTrue("getNormal function is not proper for the bottom of the cylinder",
                cy.getNormal(new Point3D(0,2,3)).equals(new Vector(new Point3D(-1,0,0))));


        //Tests for Equivalence Partition 3 - Top base
        assertTrue("getNormal function is not proper for the top of the cylinder",
                cy.getNormal(new Point3D(10,0,0)).equals(new Vector(new Point3D(1,0,0))));
        assertTrue("getNormal function is not proper for the top of the cylinder",
                cy.getNormal(new Point3D(10,2,3)).equals(new Vector(new Point3D(1,0,0))));


        // =============== Boundary Values Tests ==================

        //The border between the bottom and the side
        assertTrue("getNormal function is not proper for The upper boundary of the cylinder",
                cy.getNormal(new Point3D(0,5,0)).equals(new Vector(new Point3D(-1,0,0))));
        assertTrue("getNormal function is not proper for The upper boundary of the cylinder",
                cy.getNormal(new Point3D(0,-5,0)).equals(new Vector(new Point3D(-1,0,0))));
        assertTrue("getNormal function is not proper for The upper boundary of the cylinder",
                cy.getNormal(new Point3D(0,0,5)).equals(new Vector(new Point3D(-1,0,0))));
        assertTrue("getNormal function is not proper for The upper boundary of the cylinder",
                cy.getNormal(new Point3D(0,0,-5)).equals(new Vector(new Point3D(-1,0,0))));

        //The border between the top base and the side
        assertTrue("getNormal function is not proper for The lower boundary of the cylinder",
                cy.getNormal(new Point3D(10,5,0)).equals(new Vector(new Point3D(1,0,0))));
        assertTrue("getNormal function is not proper for The lower boundary of the cylinder",
                cy.getNormal(new Point3D(10,-5,0)).equals(new Vector(new Point3D(1,0,0))));
        assertTrue("getNormal function is not proper for The lower boundary of the cylinder",
                cy.getNormal(new Point3D(10,0,5)).equals(new Vector(new Point3D(1,0,0))));
        assertTrue("getNormal function is not proper for The lower boundary of the cylinder",
                cy.getNormal(new Point3D(10,0,-5)).equals(new Vector(new Point3D(1,0,0))));
    }

    @Test
    public void findIntersections() {
    }
}
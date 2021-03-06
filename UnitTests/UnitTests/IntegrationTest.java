package UnitTests;

import elements.Camera;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;


/**
 * Testing integration of camera and intersection
 * in sphere, plane and triangle
 *
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class IntegrationTest {

    //camera for testing
    private Camera camera1 = new Camera(
            Point3D.ZERO,
            new Vector(0, 0, 1),
            new Vector(0, -1, 0));

    private Camera camera2 = new Camera(
            new Point3D(0, 0, -0.5),
            new Vector(0, 0, 1),
            new Vector(0, -1, 0));

    //list of results for camera1 and camera2
    List<Ray> rays1 = new LinkedList<Ray>();
    List<Ray> rays2 = new LinkedList<Ray>();
    int sumIntersections = 0;

    /**
     * construct the rays for camera1 (rays1) and the rays for camera2 (rays2)
     */
    public IntegrationTest() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                rays1.add((Ray) camera1.constructBeamThroughPixel(
                        3, 3, j, i, 1, 3, 3).get(0));


        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                rays2.add((Ray) camera2.constructBeamThroughPixel(
                        3, 3, j, i, 1, 3, 3).get(0));
    }

    /**
     * A test for the integration between the camera and the intersecting of the sphere by rays
     * {@link elements.Camera#constructBeamThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Sphere#getFindIntersections(primitives.Ray)}.
     */
    @Test
    public void CameraSphereIntersections() {

        // TC01: 3X3 center: (0,0,3) radius:1
        //small sphere in front of the view plane
        //the plane outside the sphere

        Sphere sphere1 = new Sphere(
                1,
                new Point3D(0, 0, 3));

        for (Ray ray : rays1) {
            List<GeoPoint> tempIntersections = sphere1.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 2, sumIntersections);


        // TC02: 3X3 center: (0,0,2.5) radius:2.5
        // the plane inside the sphere but the camera outside the sphere

        Sphere sphere2 = new Sphere(
                2.5,
                new Point3D(0, 0, 2.5));

        sumIntersections = 0;
        for (Ray ray : rays2) {
            List<GeoPoint> tempIntersections = sphere2.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 18, sumIntersections);


        // TC03: 3X3 center: (0,0,2) radius:2
        // the plane inside the sphere but the camera outside the sphere
        Sphere sphere3 = new Sphere(
                2,
                new Point3D(0, 0, 2));

        sumIntersections = 0;
        for (Ray ray : rays2) {
            List<GeoPoint> tempIntersections = sphere3.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 10, sumIntersections);

        // TC04: 3X3 center: (0,0,1) radius:4
        // the plane and the camera inside the sphere
        Sphere sphere4 = new Sphere(
                4,
                new Point3D(0, 0, 1));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<GeoPoint> tempIntersections = sphere4.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 9, sumIntersections);


        // TC05: 3X3 center: (0,0,-1) radius:0/5
        // the sphere behind the camera
        Sphere sphere5 = new Sphere(
                0.5,
                new Point3D(0, 0, -1));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<GeoPoint> tempIntersections = sphere5.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 0, sumIntersections);
    }

    /**
     * A test for the integration between the camera and the intersecting of the plane by rays
     * {@link elements.Camera#constructBeamThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Plane#getFindIntersections(primitives.Ray)}.
     */
    @Test
    public void CameraPlaneIntersections() {
        // TC11: 3X3
        // the plane is parallel to the view plane
        Plane plane1 = new Plane(new Point3D(0, 0, 2), new Vector(camera1.getVto()));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<GeoPoint> tempIntersections = plane1.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 9, sumIntersections);

        // TC12: 3X3
        // the plane is not parallel to the view plane - three intersection points
        Plane plane2 = new Plane(new Point3D(0, 0, 5), new Vector(0, 0.5, 1));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<GeoPoint> tempIntersections = plane2.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 9, sumIntersections);

        // TC13: 3X3
        // the plane is not parallel to the view plane - two intersection points
        Plane plane3 = new Plane(new Point3D(0, 0, 5), new Vector(0, 1, 1));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<GeoPoint> tempIntersections = plane3.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 6, sumIntersections);

        // TC14: 3X3
        // the plane is orthogonal to the view plane in pCenter point
        Plane plane4 = new Plane(new Point3D(0, 0, 2), new Vector(camera1.getVup()));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<GeoPoint> tempIntersections = plane4.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 0, sumIntersections);
    }

    /**
     * A test for the integration between the camera and the intersecting of the triangle by rays
     * {@link elements.Camera#constructBeamThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Triangle#getFindIntersections(primitives.Ray)}.
     */
    @Test
    public void CameraTriangleIntersections() {
        // TC21: 3X3
        // the triangle is parallel to the view plane - one intersection point
        Triangle triangle1 = new Triangle(
                new Point3D(0, -1, 2),
                new Point3D(1, 1, 2),
                new Point3D(-1, 1, 2));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<GeoPoint> tempIntersections = triangle1.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 1, sumIntersections);

        // TC22: 3X3
        // the triangle is parallel to the view plane - two intersection point
        Triangle triangle2 = new Triangle(
                new Point3D(0, -20, 2),
                new Point3D(1, 1, 2),
                new Point3D(-1, 1, 2));

        sumIntersections = 0;
        for (Ray ray : rays1) {
            List<GeoPoint> tempIntersections = triangle2.getFindIntersections(ray);
            if (tempIntersections != null)
                sumIntersections += tempIntersections.size();
        }
        assertEquals("Wrong number of points", 2, sumIntersections);
    }
}
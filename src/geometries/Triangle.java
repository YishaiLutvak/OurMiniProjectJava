package geometries;

import primitives.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Triangle class represents a triangle in 3D Cartesian coordinate system
 * by three vertexes
 * @author Michael Bergshtein and Yishay Lutvak
 */
public class Triangle extends Polygon {

    /************constractor**************/

    /**
     * constructor by 3 Point3D
     * @param emissionLight for color of Triangle
     * @param material the material of the Triangle
     * @param p1 for A vertex
     * @param p2 for B vertex
     * @param p3 for C vertex
     */
    public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,material,p1,p2,p3);
    }

    /**
     * constructor by 3 Point3D. material gets default value
     * @param emissionLight for color of Triangle
     * @param p1 for A vertex
     * @param p2 for B vertex
     * @param p3 for C vertex
     */
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,p1, p2, p3);
    }

    /**
     * constractor by 3 Point3D. material and emission light gets default value
     * @param p1 for A vertex
     * @param p2 for B vertex
     * @param p3 for C vertex
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }



    /**************getters*****************/

    /**
     * @return vertex A
     */
    public Point3D getVertexA() {
        return super._vertices.get(0);
    }

    /**
     * @return vertex B
     */
    public Point3D getVertexB() {
        return super._vertices.get(1);
    }

    /**
     * @return vertex C
     */
    public Point3D getVertexC() {
        return super._vertices.get(2);
    }

    /****************admin**************/
    @Override
    public String toString() {
        return "vertex A= " + super._vertices.get(0) +
                " vertex B= " + super._vertices.get(1) +
                " vertex C= " + super._vertices.get(2) +
                ", plane= " + _plane;
    }

    /**
     *
     * @param ray that intersect the triangle
     * @param max the maximum range from the source of the ray to the point
     * @return alist of intersect points
     */
    @Override
    protected List<GeoPoint> findIntersections(Ray ray, double max)
    {
        List<GeoPoint> intersections = _plane.findIntersections(ray, max);
        if (intersections == null) return null;

        Point3D p0 = ray.get_start();
        Vector v =  ray.get_direction();

        Vector v1 = getVertexA().subtract(p0).normalize();
        Vector v2 = getVertexB().subtract(p0).normalize();
        Vector v3 = getVertexC().subtract(p0).normalize();

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if(isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if(isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if(isZero(s3)) return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            intersections.get(0)._geometry = this;
            return intersections;
        }
        else
            return null;
    }
}

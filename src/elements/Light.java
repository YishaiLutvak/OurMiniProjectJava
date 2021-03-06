package elements;

import primitives.Color;

/**
 * Abstract class represents all light sources.
 * Contain the field intensity for the color of the
 * light source represented by Color class in RGB values
 * @author Michael Bergshtein and Yishai Lutvak
 */
public abstract class Light {
    protected Color _intensity;

    /**
     * Constructor
     * @param intensity the intensity of color of light source
     */
    public Light(Color intensity) {
        this._intensity = intensity;
    }

    /**
     * getter for _intensity
     * @return intensity
     */
    public Color getIntensity() {
        return _intensity;
    }
}

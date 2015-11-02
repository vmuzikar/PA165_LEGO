package org.legomanager.model.entities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.awt.Color;

/**
 * Converts Color object for storing in database and vice versa
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Converter
public class ColorConverter implements AttributeConverter<Color, Integer> {
    public Integer convertToDatabaseColumn(Color color) {
        return color.getRGB();
    }

    public Color convertToEntityAttribute(Integer rgbColor) {
        return new Color(rgbColor);
    }
}

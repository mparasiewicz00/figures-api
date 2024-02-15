package pl.kurs.figures.dto.DTOmapping;

import org.modelmapper.ModelMapper;
import pl.kurs.figures.dto.RectangleDTO;
import pl.kurs.figures.model.Rectangle;

public class RectangleMapping {
    ModelMapper modelMapper;
    private RectangleDTO mapToRectangleDTO(Rectangle rectangle) {
        RectangleDTO dto = modelMapper.map(rectangle, RectangleDTO.class);
        dto.setArea(rectangle.calculateArea());
        dto.setPerimeter(rectangle.calculatePerimeter());
        return dto;
    }
}

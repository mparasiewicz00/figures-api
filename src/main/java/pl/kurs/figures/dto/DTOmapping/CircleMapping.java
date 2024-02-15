package pl.kurs.figures.dto.DTOmapping;

import org.modelmapper.ModelMapper;
import pl.kurs.figures.dto.CircleDTO;
import pl.kurs.figures.dto.RectangleDTO;
import pl.kurs.figures.model.Circle;
import pl.kurs.figures.model.Rectangle;

public class CircleMapping {
    ModelMapper modelMapper;
    private CircleDTO mapToRectangleDTO(Circle circle) {
        CircleDTO dto = modelMapper.map(circle, CircleDTO.class);
        dto.setArea(circle.calculateArea());
        dto.setPerimeter(circle.calculatePerimeter());
        return dto;
    }
}

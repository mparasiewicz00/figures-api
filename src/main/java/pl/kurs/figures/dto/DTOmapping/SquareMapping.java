package pl.kurs.figures.dto.DTOmapping;

import org.modelmapper.ModelMapper;
import pl.kurs.figures.dto.SquareDTO;
import pl.kurs.figures.model.Square;

public class SquareMapping {
    ModelMapper modelMapper;
    private SquareDTO mapToSquareDTO(Square square) {
        SquareDTO dto = modelMapper.map(square, SquareDTO.class);
        dto.setArea(square.calculateArea());
        dto.setPerimeter(square.calculatePerimeter());
        return dto;
    }

}

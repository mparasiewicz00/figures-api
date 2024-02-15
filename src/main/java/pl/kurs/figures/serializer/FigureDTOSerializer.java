//package pl.kurs.figures.serializer;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//import pl.kurs.figures.dto.FigureDTO;
//
//import java.io.IOException;
//
//public class FigureDTOSerializer extends StdSerializer<FigureDTO> {
//
//    public FigureDTOSerializer() {
//        super(FigureDTO.class);
//    }
//
//    @Override
//    public void serialize(FigureDTO figureDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        jsonGenerator.writeStartObject();
//        jsonGenerator.writeObjectField("id", figureDTO.getId());
//        jsonGenerator.writeObjectField("type", figureDTO.getType());
//
//        if ("CIRCLE".equals(figureDTO.getType())) {
//            jsonGenerator.writeObjectField("radius", figureDTO.getRadius());
//        } else if ("SQUARE".equals(figureDTO.getType())) {
//            jsonGenerator.writeObjectField("side_length", figureDTO.getSide_length());
//        } else if ("RECTANGLE".equals(figureDTO.getType())) {
//            jsonGenerator.writeObjectField("first_side_length", figureDTO.getFirst_side_length());
//            jsonGenerator.writeObjectField("second_side_length", figureDTO.getSecond_side_length());
//        }
//
//        jsonGenerator.writeObjectField("version", figureDTO.getVersion());
//        jsonGenerator.writeObjectField("createdBy", figureDTO.getCreatedBy());
//        jsonGenerator.writeObjectField("createdAt", figureDTO.getCreatedAt());
//        jsonGenerator.writeObjectField("lastModifiedAt", figureDTO.getLastModifiedAt());
//        jsonGenerator.writeObjectField("lastModifiedBy", figureDTO.getLastModifiedBy());
//        jsonGenerator.writeObjectField("area", figureDTO.getArea());
//        jsonGenerator.writeObjectField("perimeter", figureDTO.getPerimeter());
//
//        jsonGenerator.writeEndObject();
//    }
//}

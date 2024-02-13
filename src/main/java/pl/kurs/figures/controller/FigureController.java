package pl.kurs.figures.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.service.FigureService;

@RestController
@RequestMapping("/api/v1/shapes")
@AllArgsConstructor
public class FigureController {
    private FigureService figureService;
    @PostMapping()
    @Operation(summary = "Add new figure")
    @ApiResponse(responseCode = "201", description = "Figure created successfully")
    @ApiResponse(responseCode = "500", description = "There is a problem during figure creation")
    public ResponseEntity<FigureDTO> addFigure(@RequestBody CreateFigureCommand command) {
        figureService.createFigure(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

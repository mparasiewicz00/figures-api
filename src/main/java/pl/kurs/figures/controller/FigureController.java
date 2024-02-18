package pl.kurs.figures.controller;


import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.command.FigureSearchCriteria;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.repository.FigureViewRepository;
import pl.kurs.figures.service.FigureService;
import pl.kurs.figures.service.FigureViewQueryCreator;
import pl.kurs.figures.view.FigureView;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(figureService.createFigure(command));
    }

    @GetMapping
    @Operation(summary = "Search by criteria")
    public ResponseEntity<List<FigureView>> searchFigures(FigureSearchCriteria criteria) {
        List<FigureView> figureViews = figureService.searchFigures(criteria);
        return ResponseEntity.ok(figureViews);
    }
}

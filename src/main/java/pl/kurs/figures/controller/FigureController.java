package pl.kurs.figures.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.kurs.figures.command.CreateFigureCommand;
import pl.kurs.figures.command.FigureSearchCriteria;
import pl.kurs.figures.command.ModifyFigureCommand;
import pl.kurs.figures.dto.FigureDTO;
import pl.kurs.figures.model.FigureView;
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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(figureService.createFigure(command));
    }

    @PostMapping("/modify")
    @Operation(summary = "Modify figure by id")
    @ApiResponse(responseCode = "304", description = "Non modified, may do not have access")
    @ApiResponse(responseCode = "200", description = "Figure modified successfully and returned in response body")
    @ApiResponse(responseCode = "304", description = "Permission denied")
    @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    @ApiResponse(responseCode = "404", description = "Figure not found")
    @ApiResponse(responseCode = "500", description = "There is a problem in modifying figure")
    public ResponseEntity<FigureDTO> modifyFigure(@RequestBody ModifyFigureCommand command) {
        return ResponseEntity.ok()
                .body(figureService.modifyFigure(command));
    }


    // Only admin can delete figures
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete figure by id")
    public ResponseEntity<Void> deleteFigure(@PathVariable("id") Long id) {
        figureService.deleteFigure(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Search by criteria")
    public ResponseEntity<Page<FigureView>> searchFigures(FigureSearchCriteria criteria,
                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(
                figureService.getFiguresCreatedByUserPage(criteria, PageRequest.of(page, size)));
    }


}

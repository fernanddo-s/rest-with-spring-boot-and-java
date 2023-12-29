package com.example.project.controllers;

import com.example.project.data.dto.v1.BookDTO;
import com.example.project.service.BookService;
import com.example.project.utils.MediaTypeCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book/v1")
@Tag(name = "Books", description = "Endpoints for Managing Books")
public class BookController {

    @Autowired
    private BookService service;
    @GetMapping(produces = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML})
    @Operation(summary = "Finds all Books", description = "Finds all Books",
        tags = {"Books"},
        responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))}),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        })
    public List<BookDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}",produces = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML})
    @Operation(summary = "Finds a Book", description = "Finds a Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public BookDTO findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }

    @PostMapping(consumes = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML},
            produces = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML})
    @Operation(summary = "Creating a Book", description = "Creating a Book, são suportados os MediaTypes: JSON, XML e YAML",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public BookDTO create(@RequestBody BookDTO book){
        return service.create(book);
    }

    @PutMapping(consumes = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML},
            produces = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML})
    @Operation(summary = "Updates a Book", description = "Updates a Book, são suportados os MediaTypes: JSON, XML e YAML",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BookDTO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public BookDTO update(@RequestBody BookDTO book){
        return service.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Book", description = "Deletes a Book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204",content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

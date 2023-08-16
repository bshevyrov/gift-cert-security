package com.epam.esm.veiw.controller;

import com.epam.esm.veiw.dto.TagDTO;
import com.epam.esm.veiw.facade.TagFacade;
import com.epam.esm.veiw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;

/**
 * TagController class is the REST controller which consumes JSON as the request, forwards to relevant
 * method in facade and produces JSON as the result of model's operations
 */
@RestController
@RequestMapping(value = "/v1/tags",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class TagController {

    private final TagFacade tagFacade;

    private final TagModelAssembler tagModelAssembler;

    private final PagedResourcesAssembler<TagDTO> pagedResourcesAssembler;
    @Autowired
    public TagController(TagFacade tagFacade, TagModelAssembler tagModelAssembler, PagedResourcesAssembler<TagDTO> pagedResourcesAssembler) {
        this.tagFacade = tagFacade;
        this.tagModelAssembler = tagModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    /**
     * Method consumes request object.
     * Produces response object as the result of create operation.
     *
     * @param tagDTO object for creation
     * @param ucb    UriComponentsBuilder
     * @return response header with uri of created object.
     */
    @PostMapping
    public ResponseEntity<TagDTO> create(@RequestBody @Valid TagDTO tagDTO, UriComponentsBuilder ucb) {

        TagDTO currentTag = tagFacade.create(tagDTO);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/tags/")
                .path(String.valueOf(currentTag.getId()))
                .build().toUri();

        headers.setLocation(locationUri);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Method consumes URL param.
     * Produces response object as the result of find by id operation.
     *
     * @param id URL parameter, which holds gift certificate id value
     * @return found {@link TagDTO}
     **/
    @GetMapping(value = "/{id}")
    public TagDTO findById(@PathVariable @Min(value = 1)
                           @Max(Long.MAX_VALUE) long id) {
        return tagFacade.findById(id);
    }

    /**
     * Method produces set of response objects
     *
     * @return list of all {@link TagDTO}
     */
//    @GetMapping(value = "")
//    public ResponseEntity<PagedModel<TagModel>> findAll(@PageableDefault Pageable pageable) {
//        Page<TagDTO> all = tagFacade.findAll(pageable);
//
////        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findAll(pageable)).withSelfRel();
////        PagedModel<TagModel> pagedModel = pagedResourcesAssembler.toModel(all, tagModelAssembler, link);
//        PagedModel<TagModel> pagedModel = pagedResourcesAssembler.toModel(all, tagModelAssembler);
//        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
//    }
    @GetMapping(value = "")
    public PagedModel<TagModel>  findAll(@PageableDefault Pageable pageable) {
        Page<TagDTO> all = tagFacade.findAll(pageable);

//
        return pagedResourcesAssembler.toModel(all,tagModelAssembler);
    }


    /**
     * Method consumes URL param.
     * Produces response object as the result of delete operation.
     *
     * @param id URL parameter, which holds tag id value
     * @return Http status
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TagDTO> deleteById(@PathVariable @Min(1) @Max(Long.MAX_VALUE) long id) {
        tagFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


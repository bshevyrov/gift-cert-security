package com.epam.esm.veiw.controller;

import com.epam.esm.veiw.dto.TagDTO;
import com.epam.esm.veiw.facade.TagFacade;
import com.epam.esm.veiw.model.TagModel;
import com.epam.esm.veiw.model.TagModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;

/**
 * TagController class is the REST controller which consumes JSON as the request, forwards to relevant
 * method in facade and produces JSON as the result of model's operations
 */
@RestController
@RequestMapping(value = "/api/v1/tags",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class TagController {
    private final TagFacade tagFacade;
    private final TagModelAssembler tagModelAssembler;
    private final PagedResourcesAssembler<TagDTO> pagedTagResourcesAssembler;

    /**
     * Method consumes request object.
     * Produces response object as the result of create operation.
     *
     * @param tagDTO object for creation
     * @param ucb    UriComponentsBuilder
     * @return {@link ResponseEntity} with header and uri of created object.
     */
    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
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
     * @param id URL parameter, which holds tag id value
     * @return {@link TagModel}
     **/
    @GetMapping(value = "/{id}")
    public ResponseEntity<TagModel> findById(@PathVariable @Min(value = 1)
                                             @Max(Long.MAX_VALUE) long id) {
        TagDTO tagDTO = tagFacade.findById(id);
        TagModel tagModel = tagModelAssembler.toModel(tagDTO);
        return new ResponseEntity<>(tagModel, HttpStatus.OK);
    }

    /**
     * Method produces set of response objects
     *
     * @param pageable pagination object
     * @return PagedModel of response
     */

    @GetMapping(value = "")
    public ResponseEntity<PagedModel<TagModel>> findAll(@PageableDefault Pageable pageable) {
        Page<TagDTO> all = tagFacade.findAll(pageable);
        PagedModel<TagModel> tagModels = pagedTagResourcesAssembler.toModel(all, tagModelAssembler);
        return new ResponseEntity<>(tagModels, HttpStatus.OK);
    }


    /**
     * Method consumes URL param.
     * Produces response object as the result of delete operation.
     *
     * @param id URL parameter, which holds tag id value
     * @return Http status
     */
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TagModel> deleteById(@PathVariable @Min(1) @Max(Long.MAX_VALUE) long id) {
        tagFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


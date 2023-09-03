package com.epam.esm.veiw.controller;

import com.epam.esm.veiw.dto.GiftCertificateDTO;
import com.epam.esm.veiw.dto.TagDTO;
import com.epam.esm.veiw.facade.GiftCertificateFacade;
import com.epam.esm.veiw.model.GiftCertificateModel;
import com.epam.esm.veiw.model.GiftCertificateModelAssembler;
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
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;

/**
 * GiftCertificateController class is the REST controller which consumes JSON as the request, forwards to relevant
 * method in facade and produces JSON as the result of model's operations.
 */
@RestController
@RequestMapping(value = "/api/v1/gifts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class GiftCertificateController {
    private final GiftCertificateModelAssembler giftCertificateModelAssembler;
    private final PagedResourcesAssembler<GiftCertificateDTO> pagedResourcesAssembler;
    private final GiftCertificateFacade giftCertificateFacade;


    /**
     * Method consumes URL params from web request
     * Produces set of response objects based on web request params
     *
     * @param pageable object, which holds URL request params for search
     * @return PagedModel as the result of search based on URL params
     */
    @GetMapping(value = "")
    public ResponseEntity<PagedModel<GiftCertificateModel>> findAll(@PageableDefault Pageable pageable) {
        Page<GiftCertificateDTO> all = giftCertificateFacade.findAll(pageable);
        PagedModel<GiftCertificateModel> pagedModel =
                pagedResourcesAssembler.toModel(all, giftCertificateModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    /**
     * Method consumes request object.
     * Produces response object as the result of create operation.
     *
     * @param giftCertificateDTO object for creation
     * @param ucb                UriComponentsBuilder
     * @return ResponseEntity with header and uri of created object.
     */
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<GiftCertificateModel> create(@Valid @RequestBody GiftCertificateDTO giftCertificateDTO,
                                                       UriComponentsBuilder ucb) {
        GiftCertificateDTO currentGiftCertificate = giftCertificateFacade.create(giftCertificateDTO);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/gifts/").path(String.valueOf(currentGiftCertificate.getId()))
                .build().toUri();

        headers.setLocation(locationUri);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Method consumes URL param.
     * Produces response object as the result of find by id operation.
     *
     * @param id URL parameter, which holds gift certificate id value
     * @return GiftCertificateModel
     **/
    @GetMapping(value = "/{id}")
    public ResponseEntity<GiftCertificateModel> findById(@PathVariable
                                                         @Min(1)
                                                         @Max(Long.MAX_VALUE) long id) {
        return new ResponseEntity<>(giftCertificateModelAssembler.toModel(
                giftCertificateFacade.findById(id)), HttpStatus.OK);
    }

    /**
     * Method consumes URL param.
     * Produces response object as the result of delete operation.
     *
     * @param id URL parameter, which holds gift certificate id value
     * @return Http status
     */
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GiftCertificateModel> deleteById(@PathVariable @Min(1) @Max(Long.MAX_VALUE) long id) {
        giftCertificateFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Method consumes request object and URL param.
     * Produces response object as the result of update operation.
     *
     * @param giftCertificateDTO GiftCertificateDtoRequest request object for update
     * @param id                 URL parameter, which holds gift certificate id value
     * @return GiftCertificateModel of updated object
     */
    @RolesAllowed("ROLE_ADMIN")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<GiftCertificateModel> update(@RequestBody @Valid GiftCertificateDTO giftCertificateDTO, @PathVariable @Min(1) @Max(Long.MAX_VALUE) long id) {
        giftCertificateDTO.setId(id);
        return new ResponseEntity<>(giftCertificateModelAssembler.toModel(
                giftCertificateFacade.update(giftCertificateDTO)), HttpStatus.OK);
    }

    /**
     * Method consumes request object and URL param.
     *
     * @param pageable pagination object
     * @param tags     body parameter, string  tags name
     * @return PagedModel
     */
    @GetMapping("/search")
    public ResponseEntity<PagedModel<GiftCertificateModel>> findAllByTagsName(@PageableDefault Pageable pageable,
                                                                              @RequestBody @NotEmpty List<TagDTO> tags) {

        Page<GiftCertificateDTO> allByTags = giftCertificateFacade.findAllByTagsName(tags, pageable);
        PagedModel<GiftCertificateModel> pagedModel =
                pagedResourcesAssembler.toModel(allByTags, giftCertificateModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }
}

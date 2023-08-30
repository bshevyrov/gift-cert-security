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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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

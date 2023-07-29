package com.epam.esm.veiw.controller;

import com.epam.esm.veiw.facade.GiftCertificateFacade;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import com.epam.esm.veiw.model.GiftCertificateModel;
import com.epam.esm.veiw.model.GiftCertificateModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
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
 * GiftCertificateController class is the REST controller which consumes JSON as the request, forwards to relevant
 * method in facade and produces JSON as the result of model's operations.
 */
@RestController
@RequestMapping(value = "/gifts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class GiftCertificateController {


    @Autowired
    private GiftCertificateModelAssembler giftCertificateModelAssembler;

    @Autowired
    private PagedResourcesAssembler<GiftCertificateDTO> pagedResourcesAssembler;
//    @Autowired

//    @Autowired
//    private BaseModelAssembler baseModelAssembler;
//
//    @Autowired
//    private PagedResourcesAssembler<BaseEntity> pagedResourcesAssembler;

    private final GiftCertificateFacade giftCertificateFacade;

    @Autowired
    public GiftCertificateController(GiftCertificateFacade giftCertificateFacade) {
        this.giftCertificateFacade = giftCertificateFacade;
    }


    /**
     * Method consumes URL params from web request
     * Produces set of response objects based on web request params
     *
     * @param pageable object, which holds URL request params for search
     * @return {@link  GiftCertificateDTO} as the result of search based on URL params
     */
    @RequestMapping(method = RequestMethod.GET,
            value = "")
    public ResponseEntity<CollectionModel<GiftCertificateModel>> findAll(@PageableDefault Pageable pageable) {
        Page<GiftCertificateDTO> all = giftCertificateFacade.findAll(pageable);
        CollectionModel<GiftCertificateModel> pagedModel =
//                pagedResourcesAssembler.toCollectionModel(all);

                pagedResourcesAssembler.toModel(all, giftCertificateModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }




    @PostMapping
    public ResponseEntity<GiftCertificateDTO> create(@Valid @RequestBody  GiftCertificateDTO giftCertificateDTO, UriComponentsBuilder ucb) {
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
     * @return found {@link GiftCertificateDTO}
     **/
    @GetMapping(value = "/{id}")
    public GiftCertificateDTO findById(@PathVariable
                                       @Min(1)
                                       @Max(Long.MAX_VALUE) long id) {
        return giftCertificateFacade.findById(id);
    }



    /**
     * Method consumes URL param.
     * Produces response object as the result of delete operation.
     *
     * @param id URL parameter, which holds gift certificate id value
     * @return Http status
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GiftCertificateDTO> deleteById(@PathVariable @Min(1) @Max(Long.MAX_VALUE) long id) {
        giftCertificateFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Method consumes request object and URL param.
     * Produces response object as the result of update operation.
     *
     * @param giftCertificateDTO GiftCertificateDtoRequest request object for update
     * @param id                 URL parameter, which holds gift certificate id value
     */
    @PatchMapping(value = "/{id}")
    public void update(@RequestBody @Valid GiftCertificateDTO giftCertificateDTO, @PathVariable @Min(1) @Max(Long.MAX_VALUE) long id) {
        giftCertificateDTO.setId(id);
        giftCertificateFacade.update(giftCertificateDTO);
    }

}

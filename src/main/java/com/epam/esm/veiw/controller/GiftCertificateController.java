package com.epam.esm.veiw.controller;

import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.exception.giftcertificate.GiftCertificateUpdateException;
import com.epam.esm.exception.tag.TagNameException;
import com.epam.esm.facade.GiftCertificateFacade;
import com.epam.esm.veiw.Error;
import com.epam.esm.veiw.model.GiftCertificateModel;
import com.epam.esm.veiw.model.GiftCertificateModelAssembler;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * GiftCertificateController class is the REST controller which consumes JSON as the request, forwards to relevant
 * method in facade and produces JSON as the result of model's operations.
 */
@RestController
@RequestMapping(value = "/gifts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
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
     * Method consumes request object.
     * Produces response object as the result of create operation.
     *
     * @param giftCertificateDTO object for creation
     * @param ucb                UriComponentsBuilder
     * @return response header with uri of created object.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GiftCertificateDTO> create(@RequestBody GiftCertificateDTO giftCertificateDTO, UriComponentsBuilder ucb) {
        long id = giftCertificateFacade.create(giftCertificateDTO).getId();

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/gifts/")
                .path(String.valueOf(id))
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
    @RequestMapping(method = RequestMethod.GET,
            value = "/{id}")
    public GiftCertificateDTO findById(@PathVariable long id) {
        return giftCertificateFacade.findById(id);
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
    public ResponseEntity<  CollectionModel<GiftCertificateModel> > findAll(Pageable pageable) {
        Page<GiftCertificateDTO> all = giftCertificateFacade.findAll(pageable);
        CollectionModel<GiftCertificateModel> pagedModel =
//                pagedResourcesAssembler.toCollectionModel(all);

               pagedResourcesAssembler.toModel(all, giftCertificateModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }
//        Response response = new Response(page.getContent().toString(),page.getPageable());
////        response.add(linkTo(GiftController.class).toUri(uri).withSelfRel());
//
//        CollectionModel<GiftCertificateDTO> result = CollectionModel.of(page.getContent(),
//                linkTo(GiftController.class).withSelfRel());
//        return result;
//        }


    /**
     * Method consumes URL param.
     * Produces response object as the result of delete operation.
     *
     * @param id URL parameter, which holds gift certificate id value
     * @return Http status
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<GiftCertificateDTO> deleteById(@PathVariable long id) {
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
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH)
    public void update(@RequestBody GiftCertificateDTO giftCertificateDTO,
                       @PathVariable long id) {
        giftCertificateDTO.setId(id);
        giftCertificateFacade.update(giftCertificateDTO);
    }

    @ExceptionHandler(GiftCertificateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error giftCertificateNotFound(GiftCertificateNotFoundException giftCertificateNotFoundException) {
        long certId = giftCertificateNotFoundException.getGiftCertificateId();
        if (LocaleContextHolder.getLocale().getLanguage().equals("uk")) {
            return new Error(Integer.parseInt(HttpStatus.NOT_FOUND.value() + "04"), "Gift Certificate [" + certId + "] не знайдено.");
        }
        return new Error(Integer.parseInt(HttpStatus.NOT_FOUND.value() + "04"), "Gift Certificate [" + certId + "] not found");
    }

    @ExceptionHandler(TagNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error tagNameError(TagNameException e) {
        String tagName = e.getTagName();
        if (LocaleContextHolder.getLocale().getLanguage().equals("uk")) {
            return new Error(Integer.parseInt(HttpStatus.BAD_REQUEST.value() + "05"), "Помилка в імені Tag [" + tagName + "].");
        }
        return new Error(Integer.parseInt(HttpStatus.BAD_REQUEST.value() + "05"), "Error in name Tag [" + tagName + "].");
    }

    @ExceptionHandler(GiftCertificateUpdateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error giftCertificateUpdateException(GiftCertificateUpdateException giftCertificateUpdateException) {
        long certId = giftCertificateUpdateException.getGiftCertificateId();
        if (LocaleContextHolder.getLocale().getLanguage().equals("uk")) {
            return new Error(Integer.parseInt(HttpStatus.BAD_REQUEST.value() + "06"), "Помилка в параметрах Gift Certificate [" + certId + "] .");
        }
        return new Error(Integer.parseInt(HttpStatus.BAD_REQUEST.value() + "06"), "Error in parameters Gift Certificate [" + certId + "] .");
    }
}

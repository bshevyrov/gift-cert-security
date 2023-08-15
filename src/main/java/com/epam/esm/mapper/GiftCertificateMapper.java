package com.epam.esm.mapper;

import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public interface GiftCertificateMapper {

    @Mapping(source = "tagEntities", target = "tagDTOS")
    GiftCertificateDTO toDTO(GiftCertificateEntity giftCertificateEntity);

    @Mapping(target = "createdDate",ignore = true )
    @Mapping(target = "updatedDate",ignore = true )
    @Mapping(target = "deletedDate",ignore = true )
    @Mapping(source ="tagDTOS" , target ="tagEntities" )

    GiftCertificateEntity toEntity(GiftCertificateDTO giftCertificateDTO);

    List<GiftCertificateDTO> toDTOList(List<GiftCertificateEntity> list);

}

package com.epam.esm.mapper;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public interface GiftCertificateMapper {
    @Mapping(source = "tags", target = "tagDTOList")
    GiftCertificateDTO toDTO(GiftCertificate giftCertificate);

    GiftCertificate toModel(GiftCertificateDTO giftCertificateDTO);

    List<GiftCertificateDTO> toDTOList(List<GiftCertificate> list);

}

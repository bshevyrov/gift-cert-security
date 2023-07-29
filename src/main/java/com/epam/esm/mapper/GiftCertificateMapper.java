package com.epam.esm.mapper;

import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public interface GiftCertificateMapper {
    GiftCertificateDTO toDTO(GiftCertificateEntity giftCertificateEntity);

    GiftCertificateEntity toEntity(GiftCertificateDTO giftCertificateDTO);

    List<GiftCertificateDTO> toDTOList(List<GiftCertificateEntity> list);

}

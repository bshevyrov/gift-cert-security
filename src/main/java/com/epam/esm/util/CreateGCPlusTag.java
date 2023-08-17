package com.epam.esm.util;

import com.epam.esm.veiw.dto.*;
import com.epam.esm.veiw.facade.CustomerFacade;
import com.epam.esm.veiw.facade.GiftCertificateFacade;
import com.epam.esm.veiw.facade.OrderFacade;
import com.epam.esm.veiw.facade.TagFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class CreateGCPlusTag {


    private final    OrderFacade orderFacade;
    Path gcName = Paths.get(getClass().getClassLoader()
            .getResource("gifts-names").toURI());


    Path users = Paths.get(getClass().getClassLoader()
            .getResource("users-verbs").toURI());
    Path tags = Paths.get(getClass().getClassLoader()
            .getResource("tags-adjectives").toURI());


    List<String> tagNames = new ArrayList<>();
    List<String> userNames = new ArrayList<>();
    List<String> gcNames = new ArrayList<>();
    private final GiftCertificateFacade giftCertificateFacade;
    private final CustomerFacade customerFacade;
    private final TagFacade tagFacade;

    @Autowired
    public CreateGCPlusTag(OrderFacade orderFacade, GiftCertificateFacade giftCertificateFacade, CustomerFacade customerFacade, TagFacade tagFacade) throws URISyntaxException, IOException {

        this.orderFacade = orderFacade;
        this.giftCertificateFacade = giftCertificateFacade;
        this.customerFacade = customerFacade;
        this.tagFacade = tagFacade;
        initLists();
    }

    private void initLists() throws IOException {
        Stream<String> lines = Files.lines(gcName);
        gcNames = lines.filter(StringUtils::isAlpha).collect(Collectors.toList());
        lines.close();
        System.out.println("gc lines "+gcNames.size());

        lines = Files.lines(tags);
        tagNames = lines.filter(StringUtils::isAlpha).collect(Collectors.toList());
        lines.close();
        System.out.println("tag lines "+tagNames.size());

        lines = Files.lines(users);
        userNames = lines.filter(StringUtils::isAlpha).collect(Collectors.toList());
        lines.close();
        System.out.println("users lines "+userNames.size());

    }

    private int getRandInt(int min, int max) {

        return  ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public void createGC() throws IOException {

        final int[] count = {0};
        gcNames

                .forEach(s -> {
                    System.out.println(s);
                 GiftCertificateDTO giftCertificateEntity = new GiftCertificateDTO();
                    giftCertificateEntity.setName(s);
                    giftCertificateEntity.setDescription("Description " + s);
                    giftCertificateEntity.setPrice(0.1 + (100 - 0.1) * new Random().nextDouble());
                    giftCertificateEntity.setDuration(getRandInt(1, 60));
                    try {
                        giftCertificateEntity.setTagDTOS(generateListTags());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    giftCertificateFacade.create(giftCertificateEntity);
                    System.out.println("created gc " + (++count[0]));
                });
//        lines.close();
    }

    private List<TagDTO> generateListTags() throws IOException {
        List<TagDTO> tagEntities = new ArrayList<>();

        for (int i = 0; i < getRandInt(1, 25); i++) {
            TagDTO build = TagDTO.builder().name(
                            tagNames.get(getRandInt(0, tagNames.size() - 1)))
                    .build();
            if(tagEntities.contains(build)){
                continue;
            }
            tagEntities.add(build);
        }
        return tagEntities;
    }

    public void createTag() throws IOException {

        tagNames.forEach(t->{
            TagDTO tagDTO = new TagDTO();
            tagDTO.setName(t);
            tagFacade.create(tagDTO);
        });
    }

    public void createUser() throws IOException {
        final int[] count = {0};

        userNames
                .forEach(s -> {
                    CustomerDTO customerEntity = new CustomerDTO();
                    customerEntity.setName(s);
                    CustomerDTO customerCurrent = customerFacade.create(customerEntity);
                    for (int i = 0; i < getRandInt(1,33); i++) {
                        OrderDTO orderDTO = createOrder(customerCurrent);
                        orderFacade.create(orderDTO);
                    }

                    System.out.println("created order " + (++count[0]));
                });
//        lines.close();
    }

    private OrderDTO createOrder(CustomerDTO customer) {
        OrderDTO orderDTO = new OrderDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        List<OrderItemDTO> orderItemDTOS = createOrderItemList();
        customerDTO.setId(customer.getId());
        orderDTO.setCustomerDTO(customerDTO);
        orderDTO.setOrderItemDTOS(orderItemDTOS);
        return orderDTO;
    }

    private List<OrderItemDTO> createOrderItemList() {
        ArrayList<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        for (int i = 0; i < getRandInt(1, 33); i++) {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setQuantity(getRandInt(1, 22));
            GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();
            giftCertificateDTO.setId(getRandInt(51, 10048));
            orderItemDTO.setGiftCertificateDTO(giftCertificateDTO);
            orderItemDTOS.add(orderItemDTO);
        }
        return orderItemDTOS;
    }
}

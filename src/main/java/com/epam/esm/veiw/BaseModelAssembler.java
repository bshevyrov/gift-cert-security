//package com.epam.esm.veiw;
//
//import com.epam.esm.veiw.dto.BaseDTO;
//import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BaseModelAssembler extends RepresentationModelAssemblerSupport<BaseDTO, BaseModel> {
//
//
////    /**
////     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
////     *
////     * @param controllerClass must not be {@literal null}.
////     * @param resourceType    must not be {@literal null}.
////     */
//    public BaseModelAssembler(Class<?> controllerClass) {
//        super(controllerClass.getClass(), BaseModel.class);
//    }
//
//    @Override
//    public BaseModel toModel(BaseDTO entity) {
//        BaseModel baseModel = new BaseModel();
//        baseModel.setContent(entity.toString());
//        return baseModel;
//    }
//
//}

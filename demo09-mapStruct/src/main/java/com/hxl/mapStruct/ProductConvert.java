package com.hxl.mapStruct;

import com.hxl.domain.dto.ProductDTO;
import com.hxl.domain.vo.ProductVO;
import com.hxl.util.SpringContextHolder;
import lombok.Builder;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Supplier;

/**
 * MapStruct对象转换器抽象类，用于ProductDTO与ProductVO之间的转换
 * 未指定componentModel，默认使用MapStruct原生方式（非Spring管理）
 */
@Mapper(componentModel = "spring") // 给生成的实现类上加了Component注解 注入Bean
public abstract class ProductConvert {

    /**
     * 静态实例，通过MapStruct的Mappers工具创建转换器实例
     * 用于在非Spring环境下直接获取转换器（如普通Java类中调用）
     * 如果加了 componentModel = "spring" 那么也可以不用下面的这个
     */
    public static final ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);

    /**
     * 静态实例，通过SpringContextHolder工具获取转换器实例
     * 用于在Spring环境下直接获取转换器（如Spring Bean中调用）
     */
    public static Supplier<ProductConvert> SUPPLIER = SpringContextHolder.getSupplier(ProductConvert.class);

    /**
     * 将ProductDTO对象转换为ProductVO对象
     * 具体映射规则由MapStruct根据字段名自动匹配（字段名不同时需通过@Mapping注解指定）
     *
     * @param productDTO 源对象（产品数据传输对象）
     * @return 目标对象（产品视图对象）
     */
    @Mapping(source = "id", target = "productId")
    @Mapping(target = "name", ignore = true)
    @Mapping(source = "price", target = "price", numberFormat = "#.00")
    @Mapping(source = "createdTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "lastModifiedDate", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract ProductVO ProductDto2VO(ProductDTO productDTO);

    /**
     * 映射完成后的自定义其它处理逻辑
     * TODO: 由于VO里使用了Builder模式，因此无法使用@AfterMapping注解进行自定义处理逻辑
     *       需要手动在VO的Builder模式中设置库存状态、标签集合、面积等信息
     *       因为Set/Get和Builder模式不兼容，因此只能使用手动设置方式
     */
    @AfterMapping
    public void dto2Vo(ProductDTO dto, @MappingTarget ProductVO.ProductVOBuilder vo) {
        // 设置库存状态
        vo.stockStatus(dto.getStockQuantity() > 0);
        // 将标签集合拼接
        vo.tagString(String.join(",", dto.getTags()));
        // 计算面积并且保留两位小数
        double area = dto.getHigh() * dto.getWidth();
        BigDecimal productArea = BigDecimal.valueOf(area).setScale(2, RoundingMode.HALF_UP);
        vo.area(productArea);
    }
}
package com.hxl;

import com.hxl.domain.dto.ProductDTO;
import com.hxl.domain.vo.ProductVO;
import com.hxl.mapStruct.ProductConvert;
import com.hxl.reflect.ObjectReflectUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@SpringBootTest
public class MapStructTest {

    @Autowired
    private ProductConvert productConvert;

    /**
     * 传统convert：DTO转VO
     * TODO: SimpleDateFormat 和 DateTimeFormatter 的使用区别
     */
    @Test
    public void test01() {
        ProductDTO productDTO = GetProductDTO();
        ProductVO vo = ProductVO.builder()
                .productId(productDTO.getId())
                .name(productDTO.getName())
                .stockStatus(productDTO.getStockQuantity() > 0).build();

        // 状态
        vo.setStockStatus(productDTO.getStockQuantity() > 0);

        // 将价格保留两位小数 TODO：#.00 里 #表示该位没有数字则不显示 ； 0表示该位置没数字则补0
        DecimalFormat df = new DecimalFormat("#.00");
        String price = df.format(productDTO.getPrice());
        vo.setPrice(price);

        // 将时间进行格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        vo.setCreateTime(productDTO.getCreatedTime().format(dtf));
        vo.setUpdateTime(productDTO.getLastModifiedDate().format(dtf));

        // 将标签集合拼接
        vo.setTagString(String.join(",", productDTO.getTags()));

        // 计算面积并且保留两位小数
        double area = productDTO.getHigh() * productDTO.getWidth();
        BigDecimal productArea = BigDecimal.valueOf(area).setScale(2, RoundingMode.HALF_UP);
        vo.setArea(productArea);

        // 自定义反射打印
        ObjectReflectUtil.printObjectFiled(vo);
    }

    /**
     * MapStruct convert：DTO转VO
     */
    @Test
    public void test02() {
        // 获取DTO
        ProductDTO productDTO = GetProductDTO();

        // 根据MapStruct转换
        ProductVO productVO = ProductConvert.INSTANCE.ProductDto2VO(productDTO);

        // 自定义反射打印
        ObjectReflectUtil.printObjectFiled(productVO);
    }

    /**
     * MapStruct convert：DTO转VO
     */
    @Test
    public void test03() {
        // 获取DTO
        ProductDTO productDTO = GetProductDTO();

        // 根据MapStruct转换
        ProductVO productVO = ProductConvert.SUPPLIER.get().ProductDto2VO(productDTO);

        // 自定义反射打印
        ObjectReflectUtil.printObjectFiled(productVO);
    }

    /**
     * MapStruct convert：DTO转VO
     * 依赖注入
     */
    @Test
    public void test04() {
        // 获取DTO
        ProductDTO productDTO = GetProductDTO();

        // 根据MapStruct转换
        ProductVO productVO = productConvert.ProductDto2VO(productDTO);

        // 自定义反射打印
        ObjectReflectUtil.printObjectFiled(productVO);
    }




    /**
     * 模拟获取DTO的接口调用
     *
     * @return ProductDTO
     */
    private ProductDTO GetProductDTO() {
        return ProductDTO.builder()
                .id(1L)
                .name("产品名称")
                .price(new BigDecimal("66.6666"))
                .stockQuantity(66)
                .createdTime(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .tags(Arrays.asList("标签1", "标签2"))
                .high(1.1)
                .width(2.2).build();
    }
}

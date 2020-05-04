package com.mapper.product;

import com.model.product.ScenicSpotImg;
import com.model.product.ScenicSpotImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScenicSpotImgMapper {
    int countByExample(ScenicSpotImgExample example);

    int deleteByExample(ScenicSpotImgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScenicSpotImg record);

    int insertSelective(ScenicSpotImg record);

    List<ScenicSpotImg> selectByExample(ScenicSpotImgExample example);

    ScenicSpotImg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScenicSpotImg record, @Param("example") ScenicSpotImgExample example);

    int updateByExample(@Param("record") ScenicSpotImg record, @Param("example") ScenicSpotImgExample example);

    int updateByPrimaryKeySelective(ScenicSpotImg record);

    int updateByPrimaryKey(ScenicSpotImg record);
}
package com.service.product;

import com.form.product.ScenicSpotForm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.product.ScenicSpotImgMapper;
import com.mapper.product.ScenicSpotMapper;
import com.model.product.ScenicSpot;
import com.model.product.ScenicSpotExample;
import com.model.product.ScenicSpotImg;
import com.model.product.ScenicSpotImgExample;
import com.result.Page;
import com.result.Result;
import com.result.ResultStatus;
import com.utils.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 景点 操作 业务逻辑层
 */
@Service
public class ScenicSpotServiceImpl implements ScenicSpotService{
    @Resource
    ScenicSpotMapper scenicSpotMapper;
    @Resource
    ScenicSpotImgMapper scenicSpotImgMapper;

    /**
     * 根据id查询 景点 详情信息
     * @param form
     * @return
     */
    @Override
    public Result findById(ScenicSpotForm.findByIdForm form) {
        //查询景点
        ScenicSpot scenicSpot = scenicSpotMapper.selectByPrimaryKey(form.getId());

        if(scenicSpot!=null){
            //查询图片
            ScenicSpotImgExample imgExample = new ScenicSpotImgExample();
            imgExample.createCriteria().andScenicSpotIdEqualTo(scenicSpot.getScenicSpotId());
            List<ScenicSpotImg> scenicSpotImgs = scenicSpotImgMapper.selectByExample(imgExample);
            scenicSpot.setImgUrls( convertImg(scenicSpotImgs) );
        }


        return Result.success(scenicSpot);
    }

    /**
     * 查询满足条件的 景点 列表
     * @param form
     * @return
     */
    @Override
    public Result list(ScenicSpotForm.listForm form) {
        ScenicSpotExample example = new ScenicSpotExample();
        ScenicSpotExample.Criteria criteria = example.createCriteria();

        // 筛选条件
		if(!StringUtils.isEmpty( form.getName() )){
			criteria.andNameLike("%"+form.getName()+"%");
		}


        // 排序
        if(!StringUtils.isEmpty(form.getOrderByClause())){
            example.setOrderByClause(form.getOrderByClause());
        }

        // 使用PageHelper插件分页
        PageHelper.startPage(form.getPage(),form.getLimit());
        List<ScenicSpot> scenicSpotList = scenicSpotMapper.selectByExample(example);

        //分页信息
        PageInfo<ScenicSpot> pageInfo = new PageInfo<>(scenicSpotList);
        Page page = form.pageHelperResult(pageInfo);

        //查询景点图片
        for (ScenicSpot scenicSpot : scenicSpotList) {
            ScenicSpotImgExample imgExample = new ScenicSpotImgExample();
            imgExample.createCriteria().andScenicSpotIdEqualTo(scenicSpot.getScenicSpotId());
            List<ScenicSpotImg> scenicSpotImgs = scenicSpotImgMapper.selectByExample(imgExample);

            scenicSpot.setImgUrls( convertImg(scenicSpotImgs) );
        }

        return Result.success(scenicSpotList,page);
    }

    /**
     * 提取景点url
     * @param scenicSpotImgs
     * @return
     */
    public List<String> convertImg(List<ScenicSpotImg> scenicSpotImgs){
        List<String> result = new ArrayList<>();

        for (ScenicSpotImg scenicSpotImg : scenicSpotImgs) {
            result.add(scenicSpotImg.getImgUrl());
        }

        return result;
    }

    /**
     * 新增 景点
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Result add(ScenicSpotForm.addForm form) {
        ScenicSpot scenicSpot = new ScenicSpot();

        //入参转实体对象
        BeanUtils.copyProperties(form,scenicSpot);

        //设置创建信息
        String id = CommonUtil.getLongId();
        scenicSpot.setScenicSpotId(id);
        scenicSpot.setCreateTime(new Date());

        //保存图片
        for (String imgUrl : form.getImgUrls()) {
            //图片实体对象
            ScenicSpotImg img = new ScenicSpotImg();
            img.setImgUrl(imgUrl);
            img.setScenicSpotId(id);

            //存入数据库
            scenicSpotImgMapper.insertSelective(img);
        }


        //插入数据库
        int num = scenicSpotMapper.insertSelective(scenicSpot);
        if(num>0){
            return Result.success(num);
        }

        return Result.fail(ResultStatus.ERROR_Add);
    }

    /**
     * 根据id，修改 景点
     * @param form
     * @return
     */
    @Override
    public Result update(ScenicSpotForm.updateForm form) {
        ScenicSpot scenicSpot = new ScenicSpot();

        //入参转实体对象
        BeanUtils.copyProperties(form,scenicSpot);

        //设置更新信息
        scenicSpot.setUpdateTime(new Date());

        //如果修改图片
        if (!form.getImgUrls().isEmpty()){
            //删除原图片
            ScenicSpotImgExample example = new ScenicSpotImgExample();
            example.createCriteria().andScenicSpotIdEqualTo(form.getScenicSpotId());
            scenicSpotImgMapper.deleteByExample(example);


            //保存新图片
            for (String imgUrl : form.getImgUrls()) {
                //图片实体对象
                ScenicSpotImg img = new ScenicSpotImg();
                img.setImgUrl(imgUrl);
                img.setScenicSpotId(form.getScenicSpotId());

                //存入数据库
                scenicSpotImgMapper.insertSelective(img);
            }

        }

        //更新数据库
        int num = scenicSpotMapper.updateByPrimaryKeySelective(scenicSpot);
        if(num>0){
            return Result.success(num);
        }

        return Result.fail(ResultStatus.ERROR_Update);
    }

    /**
     * 根据id，删除 景点，可批量删除，多个id逗号分隔
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Result delete(ScenicSpotForm.deleteForm form) {
   
        for (String id : form.getIds()) {
            scenicSpotMapper.deleteByPrimaryKey(id);
          
        }

        return Result.success(1);
    }
}

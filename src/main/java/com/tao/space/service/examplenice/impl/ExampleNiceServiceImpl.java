package com.tao.space.service.examplenice.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tao.commons.result.ResponseResultPage;
import com.tao.commons.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import com.tao.space.mapper.ExampleNiceMapper;
import com.tao.space.mapper.custom.CustomExampleNiceMapper;
import com.tao.space.entity.ExampleNice;
import com.tao.space.service.examplenice.ExampleNiceService;
import com.tao.space.service.examplenice.dto.*;

/**
 *
 * 新建新表请拷贝这个样例模板
 *
 * @author cboss
 * @Date 2023-04-08
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleNiceServiceImpl implements ExampleNiceService {
    private final ExampleNiceMapper exampleNiceMapper;
    private final CustomExampleNiceMapper customExampleNiceMapper;

    @Override
    public void insert(ExampleNiceInsertReq req){
        ExampleNice exampleNice = new ExampleNice();
        BeanUtils.copyProperties(req, exampleNice);
        exampleNiceMapper.insertSelective(exampleNice);
    }

    @Override
    public void update(ExampleNiceUpdateReq req){
        ExampleNice exampleNice = new ExampleNice();
        BeanUtils.copyProperties(req, exampleNice);
        exampleNiceMapper.updateByPrimaryKeySelective(exampleNice);
    }

    @Override
    public void delete(Long exampleId){
        exampleNiceMapper.deleteByPrimaryKey(exampleId);
    }

    @Override
    public ResponseResultPage<ExampleNiceListRsp> list(ExampleNiceListReq req){
        Page<ExampleNiceListRsp> page = PageHelper.startPage(req.getPage(), req.getSize());

        ExampleNiceListDaoReq daoReq = BeanUtils.copyBean(req, ExampleNiceListDaoReq.class);
        List<ExampleNiceListModel> modelList = customExampleNiceMapper.list(daoReq);

        List<ExampleNiceListRsp> rspList = BeanUtils.copyBeanList(modelList, ExampleNiceListRsp.class);
        return ResponseResultPage.page(page.getTotal(), rspList);
    }

    @Override
    public ExampleNiceRsp getExampleNice(Long exampleId){
        ExampleNice exampleNice = exampleNiceMapper.selectByPrimaryKey(exampleId);
        return BeanUtils.copyBean(exampleNice, ExampleNiceRsp.class);
    }

    /**
     * 订单列表
     * @param req
     * @return
     */
    public ResponseResultPage<ExampleNiceOrderListRsp> listOrder(ExampleNiceOrderListReq req) {		
        Page<ExampleNiceOrderListRsp> page = PageHelper.startPage(req.getPage(), req.getSize());

        ExampleNiceOrderListDaoReq daoReq = BeanUtils.copyBean(req, ExampleNiceOrderListDaoReq.class);
        List<ExampleNiceOrderListModel> modelList = customExampleNiceMapper.listOrder(daoReq);

        List<ExampleNiceOrderListRsp> rspList = BeanUtils.copyBeanList(modelList, ExampleNiceOrderListRsp.class);
        return ResponseResultPage.page(page.getTotal(), rspList);
    }
}
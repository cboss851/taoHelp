
    /**
     * ${description}
     * @param req
     * @return
     */
    public ResponseResultPage<${dtoName}Rsp> ${methodName}(${dtoName}Req req) {		
        Page<${dtoName}Rsp> page = PageHelper.startPage(req.getPage(), req.getSize());

        ${dtoName}DaoReq daoReq = BeanUtils.copyBean(req, ${dtoName}DaoReq.class);
        List<${dtoName}Model> modelList = ${mapperClassNameLower}.${methodName}(daoReq);

        List<${dtoName}Rsp> rspList = BeanUtils.copyBeanList(modelList, ${dtoName}Rsp.class);
        return ResponseResultPage.page(page.getTotal(), rspList);
    }

    @ApiOperation(value = "${description}", notes = "作者：${author} <br/> 模块功能：v0.0.1->管理->详情")
    @PostMapping("/${methodName}")
    public ResponseResult<${dtoName}Rsp> ${methodName}(@Validated @RequestBody ${dtoName}Req req) {
        return ResponseResult.successData(${serviceClassNameLower}.${methodName}(req));
    }
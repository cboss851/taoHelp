
    @ApiOperation(value = "${description}", notes = "作者：${author} <br/> 模块功能：v0.0.1->管理->详情")
    @GetMapping("/${methodName}/{${parameterName}}")
    public ResponseResult<${dtoName}Rsp> ${methodName}(@PathVariable ${parameterNameClass} ${parameterName}) {
        return ResponseResult.successData(${serviceClassNameLower}.${methodName}(${parameterName}));
    }
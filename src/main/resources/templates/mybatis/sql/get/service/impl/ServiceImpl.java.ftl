
    /**
     * ${description}
     * @param ${parameterName}
     * @return
     */
    public ${dtoName}Rsp ${methodName}(${parameterNameClass} ${parameterName}) {
        ${dtoName}Model model = ${mapperClassNameLower}.${methodName}(${parameterName});
        return BeanUtils.copyBean(model, ${dtoName}Rsp.class);
    }
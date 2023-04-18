<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.custom.${customMapperClassName}">
    <select id="list" resultType="${servicePackageName}.dto.${table.nameUpperCamelCase}ListModel">
        select * from ${table.name}
    </select>
</mapper>
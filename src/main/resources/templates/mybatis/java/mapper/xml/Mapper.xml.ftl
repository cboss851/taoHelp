<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${mapperClassName}">
  <select id="listPage" resultType="${packageName}.domain.vo.${table.nameUpperCamelCase}ListRsp">
    SELECT * from ${table.name}
	WHERE archived = 0 AND `enable` = true 
	ORDER BY created_time desc
  </select>
</mapper>
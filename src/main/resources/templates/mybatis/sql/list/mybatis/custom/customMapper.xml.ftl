    <select id="${methodName}" resultType="${servicePackageName}.dto.${dtoName}Model">
        ${sql}
		<where>
<#list conditionList as condition>
<#switch condition.condition>
  <#case "equal">
      <if test="${condition.name} != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
          and ${condition.fieldName} = ${r"#{"}${condition.name}${r"}"}
      </if>
      <#break>
  <#case "gt">
      <if test="${condition.name} != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
          and ${condition.fieldName} > ${r"#{"}${condition.name}${r"}"}
      </if>
    <#break>
  <#case "lt">
      <if test="${condition.name} != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
		<![CDATA[
          and ${condition.fieldName} < ${r"#{"}${condition.name}${r"}"}
		]]>
      </if>
    <#break>
  <#case "gte">
      <if test="${condition.name} != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
          and ${condition.fieldName} >= ${r"#{"}${condition.name}${r"}"}
      </if>
    <#break>
  <#case "lte">
      <if test="${condition.name} != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
		<![CDATA[
          and ${condition.fieldName} <= ${r"#{"}${condition.name}${r"}"}
		]]>
      </if>
    <#break>
  <#case "like">
      <if test="${condition.name} != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
		  and ${condition.fieldName} like concat('%',${r"#{"}${condition.name}${r"}"},'%')
      </if>
    <#break>
  <#case "include">
      <if test="${condition.name}List != null">
          and ${condition.fieldName} in
            <foreach item="item" index="index" collection="${condition.name}List" open="(" separator="," close=" )">
                ${r"#{"}item${r"}"}
        	</foreach>
      </if>
    <#break>
  <#case "between">
      <if test="${condition.name}Start != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
		<![CDATA[
          and ${condition.fieldName} > ${r"#{"}${condition.name}Start${r"}"}
        ]]>
      </if>
      <if test="${condition.name}End != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
          <![CDATA[
          and ${condition.fieldName} < ${r"#{"}${condition.name}End${r"}"}
          ]]>
      </if>
    <#break>
  <#case "between_equal">
      <if test="${condition.name}Start != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
          <![CDATA[
          and ${condition.fieldName} >= ${r"#{"}${condition.name}Start${r"}"}
          ]]>
      </if>
      <if test="${condition.name}End != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
          <![CDATA[
          and ${condition.fieldName} <= ${r"#{"}${condition.name}End${r"}"}
          ]]>
      </if>
    <#break>
  <#default>
      <if test="${condition.name} != null<#if condition.dataTypeNameJava == "String"> and ${condition.name} != ''</#if>">
          and ${condition.fieldName} = ${r"#{"}${condition.name}${r"}"}
      </if>
</#switch>
</#list>
		</where>
    </select>
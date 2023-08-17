/*
 * Copyright (c) 2011-2022, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tao.generator.standard.engine;

import com.tao.commons.utils.FileUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Map;


/**
 * 模板引擎实现文件输出
 */
@Slf4j
public class FreemarkerTemplateEngine {
    //模板路径
    private final static String basePackagePath = "/templates";
    //字符编码
    private final static String charsetName = "UTF-8";
    //配置
    private static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, basePackagePath);
    }

    public static void writer(Map<String, Object> objectMap, String templateName, String outputFullPathName) throws Exception {
        File outputFile = FileUtils.createNewFile(outputFullPathName);
        Template template = configuration.getTemplate(templateName);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        template.process(objectMap, new OutputStreamWriter(fileOutputStream, charsetName));
        fileOutputStream.close();
        log.info("文件:{},模板:{}", outputFile, templateName);
    }

    public static String generateStringFromTemplate(Map<String, Object> dataModel,String templateFilePath) throws Exception {
        Template template = configuration.getTemplate(templateFilePath);
        StringWriter writer = new StringWriter();
        template.process(dataModel, writer);
        return writer.toString();
    }
}
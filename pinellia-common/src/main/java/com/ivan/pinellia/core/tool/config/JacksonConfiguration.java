package com.ivan.pinellia.core.tool.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ivan.pinellia.core.tool.json.PinelliaJavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Jackson配置类
 *
 * @author Chill
 */
@Configuration
@AllArgsConstructor
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfiguration {

	@Primary
	@Bean
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
		builder.simpleDateFormat(DatePattern.NORM_DATETIME_MS_PATTERN);
		//创建ObjectMapper
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		//设置地点为中国
		objectMapper.setLocale(Locale.CHINA);
		//去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		//设置为中国上海时区
		objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
		//序列化时，日期的统一格式
		objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_MS_PATTERN, Locale.CHINA));
		//序列化处理
		objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
		objectMapper.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
		objectMapper.findAndRegisterModules();
		//失败处理
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//单引号处理
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		//反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		//日期格式化
		objectMapper.registerModule(new PinelliaJavaTimeModule());
		objectMapper.findAndRegisterModules();
		return objectMapper;
	}

}

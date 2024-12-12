package com.springprojects.AptiForge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.springprojects.AptiForge.dto.ParticipantDto;
import com.springprojects.AptiForge.dto.QuizDTO;
import com.springprojects.AptiForge.dto.TeamDTO;

@Configuration
public class RedisConfig {

    // ParticipantDTO RedisTemplate
    @Bean
    RedisTemplate<String, ParticipantDto> participantRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, ParticipantDto> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<ParticipantDto> serializer = new Jackson2JsonRedisSerializer<>(ParticipantDto.class);
        template.setDefaultSerializer(serializer);
        template.setValueSerializer(serializer);
        return template;
    }

    // QuizDTO RedisTemplate
    @Bean
    RedisTemplate<String, QuizDTO> quizRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, QuizDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<QuizDTO> serializer = new Jackson2JsonRedisSerializer<>(QuizDTO.class);
        template.setDefaultSerializer(serializer);
        template.setValueSerializer(serializer);
        return template;
    }

    // TeamDTO RedisTemplate
    @Bean
    RedisTemplate<String, TeamDTO> teamRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, TeamDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<TeamDTO> serializer = new Jackson2JsonRedisSerializer<>(TeamDTO.class);
        template.setDefaultSerializer(serializer);
        template.setValueSerializer(serializer);
        return template;
    }
}

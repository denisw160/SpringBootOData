package me.wirries.demo.springodata.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the {@link com.github.dozermapper.core.DozerBeanMapper}.
 *
 * @author denisw
 * @version 1.0
 * @since 23.05.2021
 */
@Configuration
public class DozerConfig {

    /**
     * Create a new instance of the {@link com.github.dozermapper.core.DozerBeanMapper}.
     *
     * @return Mapper
     */
    @Bean
    public Mapper dozerBeanMapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }

}

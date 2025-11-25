package com.hxl.room.config;//package com.hxl.room.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.core.type.filter.AssignableTypeFilter;
//import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
//import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
//import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
//import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
//import org.springframework.data.repository.CrudRepository;
//
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.EnumSet;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Configuration
//@EnableJdbcAuditing
//@EnableJdbcRepositories(basePackages = {"com.hst.boss.**.domain.model"},
//        repositoryFactoryBeanClass = CrudRepositoryFactoryBean.class)
//public class DataJdbcConfiguration extends AbstractJdbcConfiguration {
//
//    @Override
//    public JdbcCustomConversions jdbcCustomConversions() {
//        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
//        provider.addIncludeFilter(new AssignableTypeFilter(Converter.class));
//        Object collect = provider.findCandidateComponents("com.hst.boss.common.configuration.converter").stream()
//                .map(domain -> {
//                    try {
//                        return Class.forName(domain.getBeanClassName());
//                    } catch (ClassNotFoundException e) {
//                        throw new RuntimeException(e);
//                    }
//                }).filter(Class::isEnum).flatMap(clazz -> EnumSet.allOf((Class<? extends Enum>) clazz).stream())
//                .collect(Collectors.toList());
//        return new JdbcCustomConversions((List<?>) collect);
//    }
//
//    @Bean
//    Map<Type, CrudRepository> repositoryMap(List<CrudRepository> repositories) {
//        Map<Type, CrudRepository> map = repositories.stream().collect(Collectors.toMap(r -> {
//            Class<?> clazz = r.getClass().getInterfaces()[0];
//            ParameterizedType type = (ParameterizedType) clazz.getGenericInterfaces()[0];
//            return type.getActualTypeArguments()[0];
//        }, r -> r));
//        return map;
//    }
//
//}

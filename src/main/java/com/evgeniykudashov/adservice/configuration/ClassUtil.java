package com.evgeniykudashov.adservice.configuration;

import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class ClassUtil {

    public List<Class<?>> findClassesWithAnnotation(String basePackage, Class<? extends Annotation> anno) {

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);

        provider.addIncludeFilter(new AnnotationTypeFilter(anno));

        return find(basePackage, provider);
    }

    public <T> List<Class<?>> findClassesInheritors(String basePackage, Class<T> superclass) {

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);

        provider.addIncludeFilter(new AssignableTypeFilter(superclass));

        return find(basePackage, provider);
    }

    private static List<Class<?>> find(String basePackage, ClassPathScanningCandidateComponentProvider provider) {
        return provider.findCandidateComponents(basePackage)
                .stream()
                .map(beanDefinition -> {
                    try {
                        return Class.forName(beanDefinition.getBeanClassName());
                    } catch (ClassNotFoundException e) {
                        throw new IllegalStateException("Class not found " + beanDefinition.getBeanClassName(), e);
                    }
                }).collect(Collectors.toList());
    }

}

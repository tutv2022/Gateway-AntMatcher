package com.springcloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
//            "/ticket/**",
//            "/reservation/**"
            "/**/list",
            "/**/headers"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> {
                        AntPathMatcher antPathMatcher = new AntPathMatcher();
                        boolean retValue = antPathMatcher.match(uri, request.getURI().getPath());
                        log.info("RetValue: {} - Path: {} - pattern: {}", retValue, request.getURI().getPath(), uri);
                        return retValue;
                    } );

}

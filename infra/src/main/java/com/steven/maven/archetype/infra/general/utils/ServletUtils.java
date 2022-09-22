package com.steven.maven.archetype.infra.general.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Slf4j
public final class ServletUtils {
    private ServletUtils() {
    }

    public static String renderString(HttpServletResponse response, String str) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().print(str);
        } catch (Exception ex) {
            log.error("servlet response render string error.", ex);
        }
        return null;
    }
}

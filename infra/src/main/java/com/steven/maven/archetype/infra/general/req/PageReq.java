package com.steven.maven.archetype.infra.general.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PageReq implements Serializable {

    private static final Integer DEFAULT_PAGE_SIZE = 20;

    private static final Integer DEFAULT_PAGE_CURRENT = 1;

    private int size = DEFAULT_PAGE_SIZE;

    private int current = DEFAULT_PAGE_CURRENT;
}

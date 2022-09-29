package com.steven.maven.archetype.infra.general.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Req<T> implements Serializable {

    @Valid
    private T body;
}

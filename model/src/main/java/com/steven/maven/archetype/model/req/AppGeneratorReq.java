package com.steven.maven.archetype.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppGeneratorReq implements Serializable {

    @NotNull(message = "appName不能为空")
    @Length(max = 32, message = "app最长为32个字符")
    private String appName;

    @Length(max = 128, message = "描述最多为128个字符")
    private String desc;
}

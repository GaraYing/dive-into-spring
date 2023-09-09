package com.gara.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author GARA
 */
@Builder
@Data
public class Foo {
    private Long id;
    private String foo;
}

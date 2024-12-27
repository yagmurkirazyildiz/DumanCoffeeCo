package com.yagmur.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseEntity implements Serializable {

    @Builder.Default
    private Long createDate=System.currentTimeMillis();
    @Builder.Default
    private Long updateDate=System.currentTimeMillis();
}

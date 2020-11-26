package com.chpark.msa.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/26
 * Time : 12:39 AM
 */

@Builder
@Getter
@Embeddable
public class Orderer {
    private Long id;

    private String name;
}

package com.lanrenspace.dc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SplitBillItemDTO
 * @Author: LanRenSpace
 * @Description: TODO
 **/
@Data
public class SplitBillItemDTO implements Serializable {

    /**
     * 编码
     */
    private String code;

    /**
     * z_name
     */
    private String z_name;

    /**
     * k_name
     */
    private String k_name;

    /**
     * z_s
     */
    private Boolean z_s;

    /**
     * p
     */
    private Boolean p;

    /**
     * z_s
     */
    private Boolean z_f;

    /**
     * r
     */
    private Integer r;

    /**
     * r_rule 0- 1+
     */
    private Integer r_rule;
}

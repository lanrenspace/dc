package com.lanrenspace.dc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: PassTenLevelDTO
 * @Author: LanRenSpace
 * @Description: TODO
 **/
@Data
public class PassTenLevelDTO implements Serializable {

    /**
     * 开始
     */
    private Integer begin;

    /**
     * 结束
     */
    private Integer end;
}

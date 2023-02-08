package com.lanrenspace.dc.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: OddChangePlatform
 * @Author: LanRenSpace
 * @Description: TODO
 **/
@Data
public class OddChangePlatform implements Serializable {

    /**
     * 编码
     */
    private String code;

    /**
     * 路径
     */
    private String href;

    /**
     * 名称
     */
    private String name;
}

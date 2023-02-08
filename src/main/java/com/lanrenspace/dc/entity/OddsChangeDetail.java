package com.lanrenspace.dc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName: OddsChangeDetail
 * @Author: LanRenSpace
 * @Description: TODO
 **/
@TableName("odds_change_detail")
@Data
public class OddsChangeDetail {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    private String oddName;

    /**
     * 即赔胜
     */
    private BigDecimal j_s;

    /**
     * 即赔平
     */
    private BigDecimal j_p;

    /**
     * 即赔负
     */
    private BigDecimal j_f;

    private Integer b_s;

    private Integer b_p;

    private Integer b_f;

    /**
     * 变化时间
     */
    private String changeTime;
}

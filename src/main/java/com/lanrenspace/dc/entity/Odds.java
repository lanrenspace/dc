package com.lanrenspace.dc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: Odds
 * @Author: LanRenSpace
 * @Description: TODO
 **/
@TableName("odds")
@Data
public class Odds implements Serializable {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 场次
     */
    private String mid;

    /**
     * 主队名称
     */
    private String masterName;

    private String masterCompetition;

    /**
     * 客队名称
     */
    private String guestName;

    private String guestCompetition;

    /**
     * 赛事时间
     */
    private String competitionTime;

    /**
     * 赛事
     */
    private String competition;

    /**
     * 主队身价
     */
    private String masterWorth;

    /**
     * 客队身价
     */
    private String guestWorth;

    private String oddName;

    /**
     * 初赔胜
     */
    private BigDecimal c_s;

    /**
     * 初赔平
     */
    private BigDecimal c_p;

    /**
     * 初赔负
     */
    private BigDecimal c_f;

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

    private BigDecimal s_b_l;

    private BigDecimal p_b_l;

    private BigDecimal f_b_l;

}

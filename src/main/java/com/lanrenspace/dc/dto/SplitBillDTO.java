package com.lanrenspace.dc.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: SplitBillDTO
 * @Author: LanRenSpace
 * @Description: TODO
 **/
@Data
public class SplitBillDTO implements Serializable {

    /**
     * 场次
     */
    private LinkedList<SplitBillItemDTO> itemDTOS;

    /**
     * 过关方式
     */
    private List<PassTenLevelDTO> levelDTOS;

    /**
     * 胆
     */
    private List<String> courageList;
}

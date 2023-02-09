package com.lanrenspace.dc.utils;

import com.lanrenspace.dc.dto.PassTenLevelDTO;
import com.lanrenspace.dc.dto.SplitBillDTO;
import com.lanrenspace.dc.dto.SplitBillItemDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: SplitBill
 * @Author: LanRenSpace
 * @Description: TODO
 **/
public class SplitBill implements Serializable {

    public static void main(String[] args) {
        SplitBillDTO billDTO = mockData();
        List<PassTenLevelDTO> levelDTOS = billDTO.getLevelDTOS();
        LinkedList<SplitBillItemDTO> itemDTOS = billDTO.getItemDTOS();

        List<String> itemCodes = itemDTOS.stream().map(SplitBillItemDTO::getCode).collect(Collectors.toList());

        List<String> courageList = billDTO.getCourageList();
        for (PassTenLevelDTO levelDTO : levelDTOS) {
            Integer begin = levelDTO.getBegin();

        }
    }

    /**
     * mock 数据
     *
     * @return mock成功的数据
     */
    private static SplitBillDTO mockData() {
        /**
         * 001 3 0
         * 002 -1 3 1
         * 003 0
         * 004 3 1
         * 005 +2 1 0 D
         * 006 3 D
         * 007 1 D
         */
        SplitBillDTO billDTO = new SplitBillDTO();

        SplitBillItemDTO itemDTO1 = new SplitBillItemDTO();
        itemDTO1.setCode("001");
        itemDTO1.setZ_s(true);
        itemDTO1.setZ_f(true);

        SplitBillItemDTO itemDTO2 = new SplitBillItemDTO();
        itemDTO2.setCode("002");
        itemDTO2.setZ_s(true);
        itemDTO2.setP(true);
        itemDTO2.setR(1);
        itemDTO2.setR_rule(0);

        SplitBillItemDTO itemDTO3 = new SplitBillItemDTO();
        itemDTO3.setCode("003");
        itemDTO3.setZ_f(true);

        SplitBillItemDTO itemDTO4 = new SplitBillItemDTO();
        itemDTO4.setCode("004");
        itemDTO4.setZ_s(true);
        itemDTO4.setP(true);

        SplitBillItemDTO itemDTO5 = new SplitBillItemDTO();
        itemDTO5.setCode("005");
        itemDTO5.setP(true);
        itemDTO5.setZ_f(true);
        itemDTO5.setR(2);
        itemDTO5.setR_rule(1);

        SplitBillItemDTO itemDTO6 = new SplitBillItemDTO();
        itemDTO6.setCode("006");
        itemDTO6.setZ_s(true);

        SplitBillItemDTO itemDTO7 = new SplitBillItemDTO();
        itemDTO7.setCode("007");
        itemDTO7.setP(true);

        LinkedList<SplitBillItemDTO> itemDTOS = new LinkedList<>();
        itemDTOS.add(itemDTO1);
        itemDTOS.add(itemDTO2);
        itemDTOS.add(itemDTO3);
        itemDTOS.add(itemDTO4);
        itemDTOS.add(itemDTO5);
        itemDTOS.add(itemDTO6);


        List<String> courageList = new ArrayList<>();
        courageList.add("005");
        courageList.add("006");
        courageList.add("007");

        billDTO.setItemDTOS(itemDTOS);
        billDTO.setCourageList(courageList);

        List<PassTenLevelDTO> levelDTOS = new ArrayList<>();
        PassTenLevelDTO levelDTO1 = new PassTenLevelDTO();
        levelDTO1.setBegin(7);
        levelDTO1.setEnd(1);
        levelDTOS.add(levelDTO1);

        billDTO.setLevelDTOS(levelDTOS);

        return billDTO;
    }
}

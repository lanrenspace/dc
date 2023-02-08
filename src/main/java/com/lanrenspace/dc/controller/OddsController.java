package com.lanrenspace.dc.controller;

import com.lanrenspace.dc.service.OddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OddsController
 * @Author: LanRenSpace
 * @Description: TODO
 **/
@RequestMapping("/odds")
@RestController
public class OddsController {

    private OddsService oddsService;

    @Autowired
    public void setOddsService(OddsService oddsService) {
        this.oddsService = oddsService;
    }

    /**
     * 刷新场次
     *
     * @param mid 场次ID
     * @return 是否刷新成功
     */
    @GetMapping("/refresh/{mid}")
    public String refresh(@PathVariable String mid) {
        oddsService.refreshOdds(mid);
        return "刷新成功!";
    }
}

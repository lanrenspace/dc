package com.lanrenspace.dc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lanrenspace.dc.entity.Odds;

/**
 * @ClassName: OddsService
 * @Author: LanRenSpace
 * @Description: TODO
 **/
public interface OddsService extends IService<Odds> {

    /**
     * 刷新指定场次odds
     *
     * @param mid 场次ID
     */
    void refreshOdds(String mid);
}

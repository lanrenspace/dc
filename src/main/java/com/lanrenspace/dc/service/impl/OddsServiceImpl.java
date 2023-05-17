package com.lanrenspace.dc.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanrenspace.dc.entity.Odds;
import com.lanrenspace.dc.entity.OddsChangeDetail;
import com.lanrenspace.dc.extract.Oko;
import com.lanrenspace.dc.mapper.OddsMapper;
import com.lanrenspace.dc.model.OddChangePlatform;
import com.lanrenspace.dc.service.IProxyService;
import com.lanrenspace.dc.service.OddsChangeDetailService;
import com.lanrenspace.dc.service.OddsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.net.Proxy;
import java.util.List;

/**
 * @ClassName: OddsServiceImpl
 * @Author: LanRenSpace
 * @Description: TODO
 **/
@Slf4j
@Service
public class OddsServiceImpl extends ServiceImpl<OddsMapper, Odds> implements OddsService {

    private OddsChangeDetailService oddsChangeDetailService;

    private IProxyService proxyService;

    @Autowired
    public void setProxyService(IProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @Autowired
    public void setOddsChangeDetailService(OddsChangeDetailService oddsChangeDetailService) {
        this.oddsChangeDetailService = oddsChangeDetailService;
    }

    @Override
    public void refreshOdds(String mid) {
        if (ObjectUtils.isEmpty(mid)) {
            return;
        }
        Proxy proxy = proxyService.getProxy();
        List<Odds> odds = Oko.oddsExtract(mid, proxy);
        boolean remove = this.remove(Wrappers.lambdaQuery(new Odds()).eq(Odds::getMid, mid));
        log.info("场次：{} 历史数据清除. 状态：{}", mid, remove);
        if (CollectionUtils.isEmpty(odds)) {
            log.error("场次：{} odds数据采集失败", mid);
            return;
        }
        boolean oddsSave = this.saveBatch(odds);
        log.info("场次：{} odds基础数据采集状态：{}", mid, oddsSave);
        List<OddChangePlatform> oddChangePlatforms = Oko.oddChangePlatformExtract(mid, proxy);
        for (OddChangePlatform oddChangePlatform : oddChangePlatforms) {
            List<OddsChangeDetail> oddsChangeDetails = Oko.oddsChangeDetailExtract(mid, oddChangePlatform, proxy);
            oddsChangeDetailService.remove(Wrappers.lambdaQuery(new OddsChangeDetail()).eq(OddsChangeDetail::getOddName, oddChangePlatform.getName()));
            if (!CollectionUtils.isEmpty(oddsChangeDetails)) {
                oddsChangeDetailService.saveBatch(oddsChangeDetails);
            }
        }
    }
}

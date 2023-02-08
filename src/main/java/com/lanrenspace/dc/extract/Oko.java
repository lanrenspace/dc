package com.lanrenspace.dc.extract;

import com.lanrenspace.dc.entity.Odds;
import com.lanrenspace.dc.entity.OddsChangeDetail;
import com.lanrenspace.dc.model.OddChangePlatform;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: Okooo
 * @Author: LanRenSpace
 * @Description: TODO
 **/
public class Oko {

    private static final List<String> xspfTypes = Arrays.asList("xinsheng", "xinping", "xinfu");

    public static void main(String[] args) {
        oddsExtract("1208532");
    }


    /**
     * odd change detail 提取
     *
     * @param changePlatform 平台编码
     * @param matchId        场次ID
     * @return 变化详情
     */
    public static List<OddsChangeDetail> oddsChangeDetailExtract(String matchId, OddChangePlatform changePlatform) {
        List<OddsChangeDetail> resultList = new ArrayList<>();
        try {
            String context = execExtract("http://wx.m.okooo.com/match/change.php?mid=" + matchId + "&pid=" + changePlatform.getCode() + "&Type=odds");
            Document doc = Jsoup.parse(context);
            Elements tbodyElements = doc.getElementsByClass("changeTable").get(0).getElementsByTag("tbody").get(0).children();
            for (Element tbodyElement : tbodyElements) {
                OddsChangeDetail changeDetail = new OddsChangeDetail();
                changeDetail.setOddName(changePlatform.getName());

                Elements tdElements = tbodyElement.getElementsByTag("td");
                Elements spanElements = tdElements.get(0).getElementsByTag("span");

                changeDetail.setJ_s(new BigDecimal(spanElements.get(0).text()));
                changeDetail.setJ_p(new BigDecimal(spanElements.get(1).text()));
                changeDetail.setJ_f(new BigDecimal(spanElements.get(2).text()));

                int b_s = 0;
                if (spanElements.get(0).hasClass("fontred2")) {
                    b_s = 1;
                } else if (spanElements.get(0).hasClass("fontblue")) {
                    b_s = -1;
                }
                changeDetail.setB_s(b_s);

                int b_p = 0;
                if (spanElements.get(1).hasClass("fontred2")) {
                    b_p = 1;
                } else if (spanElements.get(1).hasClass("fontblue")) {
                    b_p = -1;
                }
                changeDetail.setB_p(b_p);

                int b_f = 0;
                if (spanElements.get(2).hasClass("fontred2")) {
                    b_f = 1;
                } else if (spanElements.get(2).hasClass("fontblue")) {
                    b_f = -1;
                }
                changeDetail.setB_f(b_f);

                changeDetail.setChangeTime(tdElements.get(1).text());

                resultList.add(changeDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    /**
     * odd change platform 提取
     *
     * @param matchId 场次
     * @return change platform 数据
     */
    public static List<OddChangePlatform> oddChangePlatformExtract(String matchId) {
        List<OddChangePlatform> resultList = new ArrayList<>();
        try {
            String context = execExtract("http://wx.m.okooo.com/match/change.php?mid=1208532&pid=2&Type=odds");
            Document doc = Jsoup.parse(context);
            Elements changeMenu = doc.getElementsByClass("changeMenu").get(0).children();
            for (Element menu : changeMenu) {
                OddChangePlatform platform = new OddChangePlatform();
                platform.setName(menu.text().replace("!", "").replace("#", ""));
                platform.setCode(menu.attr("pid"));
                platform.setHref(menu.attr("href"));
                resultList.add(platform);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    /**
     * ood提取
     *
     * @param matchId 场次
     * @return ood数据
     */
    public static List<Odds> oddsExtract(String matchId) {
        List<Odds> resultList = new ArrayList<>();
        try {
            URL url = new URL("http://wx.m.okooo.com/match/odds.php?MatchID=" + matchId + "&from=%2Fweixin%2Fjing%2F");
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.setRequestProperty("Cookie", "acw_tc=2f624a0416757389167422068e6d0c08e4a96408960f81bff96a5c9d55ec22; OKSID=b2ee3bc2d43f83ababb48baab99ccc866a2e898d; _ga=GA1.2.119808330.1675739024; Hm_lvt_e4182e76f237dab6efb8895d26c81947=1675739024; Hm_lpvt_e4182e76f237dab6efb8895d26c81947=1675739050");
            conn.setRequestProperty("Host", "wx.m.okooo.com");
            conn.setRequestProperty("If-Modified-Since", "Tue, 07 Feb 2023 03:02:42 GMT");
            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            String charset = conn.getContentEncoding();
            if (charset == null) {
                charset = "GBK";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

            // 提取

            // left title
            String masterName;
            String masterCompetition;
            String masterWorth;
            Document doc = Jsoup.parse(sb.toString());
            Elements elementsByClass = doc.getElementsByClass("n-match-header-left");
            Elements children = elementsByClass.get(0).children();

            masterCompetition = children.get(0).text();
            masterName = children.get(1).text();

            // center title
            Elements centerTitleElements = doc.getElementsByClass("n-match-header-center");
            Element centerTitle = centerTitleElements.get(0).children().get(0);

            String competitionTime = centerTitle.text();

            // right title
            Elements rightTitleElements = doc.getElementsByClass("n-match-header-right");
            Elements rightTitleChildren = rightTitleElements.get(0).children();
            String guestName = rightTitleChildren.get(0).text();
            String guestCompetition = rightTitleChildren.get(1).text();

            // price
            Elements leftTitle02Elements = doc.getElementsByClass("n-match-header-left02");
            masterWorth = leftTitle02Elements.get(0).children().get(0).text();

            Elements rightTitle02Elements = doc.getElementsByClass("n-match-header-right02");
            String guestWorth = rightTitle02Elements.get(0).children().get(0).text();

            // titleType
            Elements centerTitle02Elements = doc.getElementsByClass("n-match-header-center02");
            String competition = centerTitle02Elements.get(0).text();

            // oz
            Element oddsElement = Objects.requireNonNull(doc.getElementById("Odds")).getElementsByClass("matchtable").get(0);
            Elements tbodyElements = oddsElement.getElementsByTag("tbody").get(0).children();


            for (Element tbodyElement : tbodyElements) {

                Odds odds = new Odds();
                odds.setMasterName(masterName);
                odds.setMasterCompetition(masterCompetition);
                odds.setMasterWorth(masterWorth);
                odds.setGuestName(guestName);
                odds.setGuestWorth(guestWorth);
                odds.setGuestCompetition(guestCompetition);
                odds.setCompetitionTime(competitionTime);
                odds.setCompetition(competition);
                odds.setMid(matchId);

                // name
                String text = tbodyElement.getElementsByClass("width16").get(0).getElementsByTag("a").get(0).text();
                text = text.replace("!", "").replace("#", "");
                odds.setOddName(text);

                // spf
                String sheng = tbodyElement.getElementsByClass("datetxt02").get(0).getElementsByAttributeValue("type", "sheng").get(0).text();
                odds.setC_s(new BigDecimal(sheng));
                String ping = tbodyElement.getElementsByClass("datetxt02").get(0).getElementsByAttributeValue("type", "ping").get(0).text();
                odds.setC_p(new BigDecimal(ping));
                String fu = tbodyElement.getElementsByClass("datetxt02").get(0).getElementsByAttributeValue("type", "fu").get(0).text();
                odds.setC_f(new BigDecimal(fu));

                // xspf
                for (String xspfType : xspfTypes) {
                    Element xspfElement = tbodyElement.getElementsByClass("datetxt02").get(1).getElementsByAttributeValue("type", xspfType).get(0);
                    String xspfValue = xspfElement.text();
                    int b_s = 0;
                    if (xspfElement.hasClass("fontred2")) {
                        b_s = 1;
                    } else if (xspfElement.hasClass("fontblue")) {
                        b_s = -1;
                    }
                    switch (xspfType) {
                        case "xinsheng":
                            odds.setJ_s(new BigDecimal(xspfValue));
                            odds.setB_s(b_s);
                            odds.setS_b_l(odds.getC_s().subtract(odds.getJ_s()));
                            break;
                        case "xinping":
                            odds.setJ_p(new BigDecimal(xspfValue));
                            odds.setB_p(b_s);
                            odds.setP_b_l(odds.getC_p().subtract(odds.getJ_p()));
                            break;
                        case "xinfu":
                            odds.setJ_f(new BigDecimal(xspfValue));
                            odds.setB_f(b_s);
                            odds.setF_b_l(odds.getC_f().subtract(odds.getJ_f()));
                            break;
                    }
                }
                resultList.add(odds);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    /**
     * 执行内容提取
     *
     * @param path 请求地址
     * @return html内容
     */
    private static String execExtract(String path) throws IOException {
        URL url = new URL(path);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("Cache-Control", "max-age=0");
        conn.setRequestProperty("Cookie", "acw_tc=2f624a0416757389167422068e6d0c08e4a96408960f81bff96a5c9d55ec22; OKSID=b2ee3bc2d43f83ababb48baab99ccc866a2e898d; _ga=GA1.2.119808330.1675739024; Hm_lvt_e4182e76f237dab6efb8895d26c81947=1675739024; Hm_lpvt_e4182e76f237dab6efb8895d26c81947=1675739050");
        conn.setRequestProperty("Host", "wx.m.okooo.com");
        conn.setRequestProperty("If-Modified-Since", "Tue, 07 Feb 2023 03:02:42 GMT");
        conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        String charset = conn.getContentEncoding();
        if (charset == null) {
            charset = "GBK";
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }
}

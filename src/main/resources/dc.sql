/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : dc

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 08/02/2023 19:14:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for odds
-- ----------------------------
DROP TABLE IF EXISTS `odds`;
CREATE TABLE `odds`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `mid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '场次',
  `master_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '主队名称',
  `master_competition` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `guest_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '客队名称',
  `guest_competition` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `competition_time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '赛事时间',
  `competition` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '赛事',
  `master_worth` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '主队身价',
  `guest_worth` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '客队身价',
  `odd_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `c_s` decimal(10, 4) NULL DEFAULT NULL COMMENT '初赔胜',
  `c_p` decimal(10, 4) NULL DEFAULT NULL COMMENT '初赔平',
  `c_f` decimal(10, 4) NULL DEFAULT NULL COMMENT '初赔负',
  `j_s` decimal(10, 4) NULL DEFAULT NULL COMMENT '即赔胜',
  `j_p` decimal(10, 4) NULL DEFAULT NULL COMMENT '即赔平',
  `j_f` decimal(10, 4) NULL DEFAULT NULL COMMENT '即赔负',
  `b_s` tinyint(1) NULL DEFAULT NULL,
  `b_p` tinyint(1) NULL DEFAULT NULL,
  `b_f` tinyint(1) NULL DEFAULT NULL,
  `s_b_l` decimal(10, 4) NULL DEFAULT NULL,
  `p_b_l` decimal(10, 4) NULL DEFAULT NULL,
  `f_b_l` decimal(10, 4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for odds_change_detail
-- ----------------------------
DROP TABLE IF EXISTS `odds_change_detail`;
CREATE TABLE `odds_change_detail`  (
  `id` bigint(0) NOT NULL COMMENT '主键ID',
  `odd_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `change_time` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '变化时间',
  `j_s` decimal(10, 4) NULL DEFAULT NULL COMMENT '即赔胜',
  `j_p` decimal(10, 4) NULL DEFAULT NULL COMMENT '即赔平',
  `j_f` decimal(10, 4) NULL DEFAULT NULL COMMENT '即赔负',
  `b_s` tinyint(1) NULL DEFAULT NULL,
  `b_p` tinyint(1) NULL DEFAULT NULL,
  `b_f` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

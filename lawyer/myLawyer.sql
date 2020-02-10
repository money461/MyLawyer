/*
 Navicat Premium Data Transfer

 Source Server         : tz520
 Source Server Type    : MySQL
 Source Server Version : 100032
 Source Host           : 116.62.174.247:3306
 Source Schema         : myLawyer

 Target Server Type    : MySQL
 Target Server Version : 100032
 File Encoding         : 65001

 Date: 26/04/2018 23:26:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for za_cart
-- ----------------------------
DROP TABLE IF EXISTS `za_cart`;
CREATE TABLE `za_cart`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户或者律师id 指定该购物车信息是哪个用户的',
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品编号id',
  `cart_num` int(2) NOT NULL DEFAULT 0 COMMENT '商品已经选中购买的数量',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '该记录创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '该记录修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`item_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户添加商品至购物车展示信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_case_category
-- ----------------------------
DROP TABLE IF EXISTS `za_case_category`;
CREATE TABLE `za_case_category`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '委托类型id',
  `p_id` int(4) NULL DEFAULT 0 COMMENT '委托类型父节点',
  `sort_code` int(4) NULL DEFAULT NULL COMMENT '委托案例分类排序code',
  `case_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布委托类型分类名称',
  `annotate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该委托类型文字注释描述',
  `logo_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布委托分类图标链接地址',
  `link_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布委托分类链接跳转地址',
  `case_status` int(2) NULL DEFAULT 1 COMMENT '发布委托类型的状态  1、启用案例类型标签 2、删除停用案件类型标签',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sort_code`(`sort_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户委托案件类型分类信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_case_category
-- ----------------------------
INSERT INTO `za_case_category` VALUES (1, 0, 1, '民事类', '', NULL, NULL, 1, '2018-01-17 14:45:12', '2018-01-17 14:45:12', NULL);
INSERT INTO `za_case_category` VALUES (2, 0, 2, '刑事类', NULL, NULL, NULL, 1, '2018-01-17 14:45:41', '2018-01-17 14:45:41', NULL);
INSERT INTO `za_case_category` VALUES (3, 0, 3, '行政类', NULL, NULL, NULL, 1, '2018-01-17 14:45:53', '2018-01-17 14:45:53', NULL);
INSERT INTO `za_case_category` VALUES (4, 0, 4, '经济类', NULL, NULL, NULL, 1, '2018-01-17 14:46:01', '2018-01-17 14:46:01', NULL);
INSERT INTO `za_case_category` VALUES (5, 0, 5, '公司类', NULL, NULL, NULL, 1, '2018-01-17 14:46:08', '2018-01-17 14:46:08', NULL);
INSERT INTO `za_case_category` VALUES (6, 0, 6, '涉外类', NULL, NULL, NULL, 1, '2018-01-17 14:46:16', '2018-01-17 14:46:16', NULL);
INSERT INTO `za_case_category` VALUES (7, 0, 7, '知识产权', NULL, NULL, NULL, 1, '2018-01-17 14:46:50', '2018-01-17 14:46:50', NULL);
INSERT INTO `za_case_category` VALUES (8, 0, 8, '其他', NULL, NULL, NULL, 1, '2018-01-17 14:50:22', '2018-01-17 14:50:23', NULL);
INSERT INTO `za_case_category` VALUES (9, 1, 9, '一般民事', NULL, NULL, NULL, 1, '2018-01-17 14:51:33', '2018-01-17 14:51:33', NULL);
INSERT INTO `za_case_category` VALUES (10, 1, 10, '合同纠纷', NULL, NULL, NULL, 1, '2018-01-17 14:52:48', '2018-01-17 14:52:49', NULL);
INSERT INTO `za_case_category` VALUES (11, 1, 11, '债券债务', NULL, NULL, NULL, 1, '2018-01-17 14:53:06', '2018-01-17 14:53:06', NULL);
INSERT INTO `za_case_category` VALUES (12, 1, 12, '劳动仲裁', NULL, NULL, NULL, 1, '2018-01-17 14:53:40', '2018-01-17 14:53:41', NULL);
INSERT INTO `za_case_category` VALUES (13, 1, 13, '人身损害', NULL, NULL, NULL, 1, '2018-01-17 14:54:27', '2018-01-17 14:54:28', NULL);
INSERT INTO `za_case_category` VALUES (14, 1, 14, '交通事故', NULL, NULL, NULL, 1, '2018-01-17 14:54:52', '2018-01-17 14:54:53', NULL);
INSERT INTO `za_case_category` VALUES (15, 1, 15, '继承纠纷', NULL, NULL, NULL, 1, '2018-01-17 14:55:05', '2018-01-17 14:55:06', NULL);
INSERT INTO `za_case_category` VALUES (16, 1, 16, '婚姻家庭', NULL, NULL, NULL, 1, '2018-01-17 14:55:22', '2018-01-17 14:55:24', NULL);
INSERT INTO `za_case_category` VALUES (17, 1, 17, '确权纠纷', NULL, NULL, NULL, 1, '2018-01-17 14:55:44', '2018-01-17 14:55:45', NULL);
INSERT INTO `za_case_category` VALUES (18, 1, 18, '建设工程', NULL, NULL, NULL, 1, '2018-01-17 14:56:07', '2018-01-17 14:56:08', NULL);
INSERT INTO `za_case_category` VALUES (19, 1, 19, '财产侵权', NULL, NULL, NULL, 1, '2018-01-17 14:56:51', '2018-01-17 14:56:51', NULL);
INSERT INTO `za_case_category` VALUES (20, 1, 20, '抵押担保', NULL, NULL, NULL, 1, '2018-01-17 14:57:06', '2018-01-17 14:57:06', NULL);
INSERT INTO `za_case_category` VALUES (21, 1, 21, '消费权益', NULL, NULL, NULL, 1, '2018-01-17 14:57:20', '2018-01-17 14:57:21', NULL);
INSERT INTO `za_case_category` VALUES (22, 1, 22, '房屋拆迁', NULL, NULL, NULL, 1, '2018-01-17 14:58:13', '2018-01-17 14:58:14', NULL);
INSERT INTO `za_case_category` VALUES (23, 1, 23, '经济仲裁', NULL, NULL, NULL, 1, '2018-01-17 14:58:37', '2018-01-17 14:58:38', NULL);
INSERT INTO `za_case_category` VALUES (24, 1, 24, '医疗纠纷', NULL, NULL, NULL, 1, '2018-01-17 14:58:53', '2018-01-17 14:58:53', NULL);
INSERT INTO `za_case_category` VALUES (25, 2, 25, '一般刑事', NULL, NULL, NULL, 1, '2018-01-17 14:59:22', '2018-01-17 14:59:23', NULL);
INSERT INTO `za_case_category` VALUES (26, 2, 26, '取保候审', NULL, NULL, NULL, 1, '2018-01-17 14:59:36', '2018-01-17 14:59:36', NULL);
INSERT INTO `za_case_category` VALUES (27, 2, 27, '暴力犯罪', NULL, NULL, NULL, 1, '2018-01-17 14:59:53', '2018-01-17 14:59:51', NULL);
INSERT INTO `za_case_category` VALUES (28, 2, 28, '经济犯罪', NULL, NULL, NULL, 1, '2018-01-17 15:00:04', '2018-01-17 15:00:05', NULL);
INSERT INTO `za_case_category` VALUES (29, 2, 29, '毒品犯罪', NULL, NULL, NULL, 1, '2018-01-17 15:00:20', '2018-01-17 15:00:21', NULL);
INSERT INTO `za_case_category` VALUES (30, 2, 30, '渎职犯罪', NULL, NULL, NULL, 1, '2018-01-17 15:00:36', '2018-01-17 15:00:37', NULL);
INSERT INTO `za_case_category` VALUES (31, 2, 31, '公司犯罪', NULL, NULL, NULL, 1, '2018-01-17 15:00:50', '2018-01-17 15:00:48', NULL);
INSERT INTO `za_case_category` VALUES (32, 2, 32, '刑事辩护', NULL, NULL, NULL, 1, '2018-01-17 15:01:00', '2018-01-17 15:01:01', NULL);
INSERT INTO `za_case_category` VALUES (33, 3, 33, '行政复议', NULL, NULL, NULL, 1, '2018-01-17 15:01:21', '2018-01-17 15:01:22', NULL);
INSERT INTO `za_case_category` VALUES (34, 3, 34, '行政诉讼', NULL, NULL, NULL, 1, '2018-01-17 15:01:56', '2018-01-17 15:01:56', NULL);
INSERT INTO `za_case_category` VALUES (35, 3, 35, '国家赔偿', NULL, NULL, NULL, 1, '2018-01-17 15:02:17', '2018-01-17 15:02:19', NULL);
INSERT INTO `za_case_category` VALUES (36, 4, 36, '票据', NULL, NULL, NULL, 1, '2018-01-17 15:02:41', '2018-01-17 15:02:41', NULL);
INSERT INTO `za_case_category` VALUES (37, 4, 37, '反不正当竞争', NULL, NULL, NULL, 1, '2018-01-17 15:03:23', '2018-01-17 15:03:24', NULL);
INSERT INTO `za_case_category` VALUES (38, 4, 38, '银行信托', NULL, NULL, NULL, 1, '2018-01-17 15:03:44', '2018-01-17 15:03:45', NULL);
INSERT INTO `za_case_category` VALUES (39, 4, 39, '金融证券', NULL, NULL, NULL, 1, '2018-01-17 15:04:03', '2018-01-17 15:04:01', NULL);
INSERT INTO `za_case_category` VALUES (40, 4, 40, '保险', NULL, NULL, NULL, 1, '2018-01-17 15:04:20', '2018-01-17 15:04:21', NULL);
INSERT INTO `za_case_category` VALUES (41, 5, 41, '工商税务', NULL, NULL, NULL, 1, '2018-01-17 15:04:44', '2018-01-17 15:04:45', NULL);
INSERT INTO `za_case_category` VALUES (42, 5, 42, '资产拍卖', NULL, NULL, NULL, 1, '2018-01-17 15:05:02', '2018-01-17 15:05:03', NULL);
INSERT INTO `za_case_category` VALUES (43, 5, 43, '公司上市', NULL, NULL, NULL, 1, '2018-01-17 15:05:19', '2018-01-17 15:05:20', NULL);
INSERT INTO `za_case_category` VALUES (44, 5, 44, '融资借款', NULL, NULL, NULL, 1, '2018-01-17 15:05:27', '2018-01-17 15:05:27', NULL);
INSERT INTO `za_case_category` VALUES (45, 5, 45, '破产清算', NULL, NULL, NULL, 1, '2018-01-17 15:05:57', '2018-01-17 15:05:58', NULL);
INSERT INTO `za_case_category` VALUES (46, 5, 46, '公司解散', NULL, NULL, NULL, 1, '2018-01-17 15:06:24', '2018-01-17 15:06:25', NULL);
INSERT INTO `za_case_category` VALUES (47, 5, 47, '改制重组', NULL, NULL, NULL, 1, '2018-01-17 15:06:43', '2018-01-17 15:06:43', NULL);
INSERT INTO `za_case_category` VALUES (48, 5, 48, '兼并收购', NULL, NULL, NULL, 1, '2018-01-17 15:07:12', '2018-01-17 15:07:11', NULL);
INSERT INTO `za_case_category` VALUES (49, 5, 49, '股权纠纷', NULL, NULL, NULL, 1, '2018-01-17 15:07:25', '2018-01-17 15:07:26', NULL);
INSERT INTO `za_case_category` VALUES (50, 5, 50, '法律顾问', NULL, NULL, NULL, 1, '2018-01-17 15:07:45', '2018-01-17 15:07:46', NULL);
INSERT INTO `za_case_category` VALUES (51, 5, 51, '新三板', NULL, NULL, NULL, 1, '2018-01-17 15:07:58', '2018-01-17 15:08:03', NULL);
INSERT INTO `za_case_category` VALUES (52, 6, 52, '海事海商', NULL, NULL, NULL, 1, '2018-01-17 15:08:28', '2018-01-17 15:08:29', NULL);
INSERT INTO `za_case_category` VALUES (53, 6, 53, '国际贸易', NULL, NULL, NULL, 1, '2018-01-17 15:08:49', '2018-01-17 15:08:50', NULL);
INSERT INTO `za_case_category` VALUES (54, 6, 54, '外商投资', NULL, NULL, NULL, 1, '2018-01-17 15:09:12', '2018-01-17 15:09:14', NULL);
INSERT INTO `za_case_category` VALUES (55, 6, 55, '合资合作', NULL, NULL, NULL, 1, '2018-01-17 15:09:27', '2018-01-17 15:09:29', NULL);
INSERT INTO `za_case_category` VALUES (56, 6, 56, '反倾销', NULL, NULL, NULL, 1, '2018-01-17 15:09:44', '2018-01-17 15:09:45', NULL);
INSERT INTO `za_case_category` VALUES (57, 6, 57, '反补贴', NULL, NULL, NULL, 1, '2018-01-17 15:09:59', '2018-01-17 15:10:00', NULL);
INSERT INTO `za_case_category` VALUES (58, 6, 58, '涉外仲裁', NULL, NULL, NULL, 1, '2018-01-17 15:10:18', '2018-01-17 15:10:19', NULL);
INSERT INTO `za_case_category` VALUES (59, 7, 59, '专利', NULL, NULL, NULL, 1, '2018-01-17 15:10:52', '2018-01-17 15:10:53', NULL);
INSERT INTO `za_case_category` VALUES (60, 7, 60, '著作权', NULL, NULL, NULL, 1, '2018-01-17 15:11:06', '2018-01-17 15:11:08', NULL);
INSERT INTO `za_case_category` VALUES (61, 7, 61, '商标', NULL, NULL, NULL, 1, '2018-01-17 15:11:23', '2018-01-17 15:11:24', NULL);
INSERT INTO `za_case_category` VALUES (62, 8, 62, '法律援助', NULL, NULL, NULL, 1, '2018-01-17 15:11:49', '2018-01-17 14:11:49', NULL);
INSERT INTO `za_case_category` VALUES (63, 8, 63, '律师见证', NULL, NULL, NULL, 1, '2018-01-17 15:12:02', '2018-01-17 15:12:03', NULL);
INSERT INTO `za_case_category` VALUES (64, 8, 64, '工商查询', NULL, NULL, NULL, 1, '2018-01-17 15:12:23', '2018-01-17 15:12:24', NULL);
INSERT INTO `za_case_category` VALUES (65, 8, 65, '资信查询', NULL, NULL, NULL, 1, '2018-01-17 15:12:49', '2018-01-17 15:12:50', NULL);
INSERT INTO `za_case_category` VALUES (66, 8, 66, '合同审查', NULL, NULL, NULL, 1, '2018-01-17 15:13:03', '2018-01-17 15:13:03', NULL);
INSERT INTO `za_case_category` VALUES (67, 8, 67, '调解谈判', NULL, NULL, NULL, 1, '2018-01-17 15:13:26', '2018-01-17 15:13:26', NULL);
INSERT INTO `za_case_category` VALUES (68, 8, 68, '法律文书代写', NULL, NULL, NULL, 1, '2018-01-17 15:13:52', '2018-01-17 15:13:52', NULL);
INSERT INTO `za_case_category` VALUES (69, 8, 69, 'WTO事务', NULL, NULL, NULL, 1, '2018-01-17 15:14:10', '2018-01-17 15:14:11', NULL);
INSERT INTO `za_case_category` VALUES (70, 8, 70, '期货交易', NULL, NULL, NULL, 1, '2018-01-17 15:14:26', '2018-01-17 15:14:26', NULL);
INSERT INTO `za_case_category` VALUES (71, 8, 71, '风险代理', NULL, NULL, NULL, 1, '2018-01-17 15:15:03', '2018-01-17 15:15:03', NULL);
INSERT INTO `za_case_category` VALUES (72, 8, 72, '户籍查询', NULL, NULL, NULL, 1, '2018-01-17 15:15:18', '2018-01-17 15:15:16', NULL);
INSERT INTO `za_case_category` VALUES (73, 8, 73, '调查取证', NULL, NULL, NULL, 1, '2018-01-17 15:15:36', '2018-01-17 15:15:35', NULL);
INSERT INTO `za_case_category` VALUES (74, 8, 74, '刑事会见', NULL, NULL, NULL, 1, '2018-01-17 15:16:07', '2018-01-17 15:16:08', NULL);
INSERT INTO `za_case_category` VALUES (75, 8, 75, '代为开庭', NULL, NULL, NULL, 1, '2018-01-17 15:16:25', '2018-01-17 15:16:26', NULL);
INSERT INTO `za_case_category` VALUES (76, 8, 76, '代为立案', NULL, NULL, NULL, 1, '2018-01-17 15:16:46', '2018-01-17 15:16:46', NULL);
INSERT INTO `za_case_category` VALUES (77, 8, 77, '纯风险代理', NULL, NULL, NULL, 1, '2018-01-17 15:17:10', '2018-01-17 15:17:08', NULL);

-- ----------------------------
-- Table structure for za_chat_pricture
-- ----------------------------
DROP TABLE IF EXISTS `za_chat_pricture`;
CREATE TABLE `za_chat_pricture`  (
  `id` bigint(20) NOT NULL COMMENT '自增id',
  `hx_msg_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环信消息唯一id',
  `pricture_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片消息路径',
  `extension` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片扩展名jpg',
  `width` int(10) NULL DEFAULT NULL COMMENT '图片带宽',
  `height` int(10) NULL DEFAULT NULL COMMENT '图片高度',
  `size` int(10) NULL DEFAULT NULL COMMENT '图片大小',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '聊天图片内容记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_chat_text
-- ----------------------------
DROP TABLE IF EXISTS `za_chat_text`;
CREATE TABLE `za_chat_text`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `hx_msg_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环信消息唯id',
  `session_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会话唯一码',
  `from_user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息发送者id',
  `to_user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息接受者id',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '信息描述(文字 语言 图片链接 地理位置)',
  `type` int(2) NULL DEFAULT 0 COMMENT '消息类型：0文本消息  1 语言 2图片类型 3发送位置 ',
  `read_state` int(2) NOT NULL DEFAULT 0 COMMENT '阅读标志 0未阅读 1已阅读',
  `del_state` int(2) NOT NULL DEFAULT 0 COMMENT '消息删除标志 0未删除 1已删除',
  `created_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '消息创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '聊天文本内容记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_com_authentication
-- ----------------------------
DROP TABLE IF EXISTS `za_com_authentication`;
CREATE TABLE `za_com_authentication`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `law_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业担保律师id',
  `com_nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业简称、昵称',
  `com_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业详细名称',
  `com_manager` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业第一管理负责人',
  `com_phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业负责人电话',
  `com_legal_person` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业法人姓名',
  `legal_person_phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业法人电话',
  `com_introduce` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业的介绍信息',
  `com_status` int(2) NOT NULL DEFAULT 0 COMMENT '企业认证及运营状态  0.未支付 5.审核中 1.已通过审核认证可正常营业 2.企业关停 3.企业删除 4 企业账户冻结 ',
  `state` int(2) NULL DEFAULT 0 COMMENT '企业用户状态 默认 0在线  离线 1 隐身',
  `com_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业注册地址',
  `com_lon` double(9, 6) NULL DEFAULT NULL COMMENT '企业地址经度',
  `com_lat` double(9, 6) NULL DEFAULT NULL COMMENT '企业地址纬度',
  `com_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业logo',
  `licence_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业营业执照',
  `qualification_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人手持营业执照url',
  `ID_card` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人身份证复印件',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '申请时间',
  `examine_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '认证时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `examine_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核员id',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `com_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业资格认证信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_com_authentication
-- ----------------------------
INSERT INTO `za_com_authentication` VALUES ('ansdkafnfnksdnfjksdnfkn', 'asdjkladfjlasdjdfkladsgdfgdfgdf', '', '', '杭州哇哈哈集团有限公司', '宗庆后', '123456789225', '宗庆后', '13264564678', '杭州娃哈哈集团有限公司创建于1987年，为中国最大全球第五的食品饮料生产企业，在销售收入、利润、利税等指标上已连续11年位居中国饮料行业首位，成为目前中国最大、效益最好、最具发展潜力的食品饮料企业。2010年，全国民企500强排名第8位。', 1, 0, '浙江省杭州市', NULL, NULL, '', NULL, NULL, NULL, '2018-01-05 15:05:16', '2018-01-05 15:05:16', '2018-01-05 15:05:16', NULL, NULL);
INSERT INTO `za_com_authentication` VALUES ('b4d53315b04e4b70a1ffb53413113ecd', '71f84abb72ff497590aab2f4d66105b3', NULL, NULL, '啊啦啦啦啦', NULL, '15826584785', NULL, NULL, NULL, 0, 0, '阿里啦咯啦咯啦', NULL, NULL, NULL, 'group1/M00/00/00/rBAmwVpYhdKAK4lyAAFibB-bTDc021.jpg?2017111011202050767.jpg', 'group1/M00/00/00/rBAmwVpYhdiAecFSAADape7tCNo410.jpg?2017111011202036614.jpg', NULL, '2018-01-12 17:54:46', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for za_com_authority
-- ----------------------------
DROP TABLE IF EXISTS `za_com_authority`;
CREATE TABLE `za_com_authority`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业后台管理权限表id',
  `p_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限父节点目录',
  `authority_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称唯一',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限code关键词w唯一',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限信息描述',
  `priority` int(4) NULL DEFAULT NULL COMMENT '权限在同一级别目录下的优先级',
  `module` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该权限作用模块',
  `status` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '该权限是否启用或者停用,\"0\"启用,\"1\"停用',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `authority_name`(`authority_name`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业后台管理权限信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_com_category
-- ----------------------------
DROP TABLE IF EXISTS `za_com_category`;
CREATE TABLE `za_com_category`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '分类主键id',
  `p_id` int(4) NULL DEFAULT 0 COMMENT '企业分类父节id',
  `sort_code` int(4) NULL DEFAULT NULL COMMENT '企业分类排序code唯一',
  `category_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业分类名称',
  `logo_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业分类图标链接地址',
  `link_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业分类链接跳转地址',
  `category_status` int(2) NULL DEFAULT 1 COMMENT '企业分类状态 1、启用 2、删除停用',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录操作员id',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sort_code`(`sort_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业经营范围分类信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_com_category
-- ----------------------------
INSERT INTO `za_com_category` VALUES (1, 0, 1, '申请入驻', NULL, NULL, 1, NULL, '2018-01-05 15:45:01', '2018-01-05 15:45:01');
INSERT INTO `za_com_category` VALUES (2, 0, 2, '食品类', NULL, NULL, 1, NULL, '2018-01-05 15:45:27', '2018-01-05 15:45:28');
INSERT INTO `za_com_category` VALUES (3, 0, 3, '商贸类', NULL, NULL, 1, NULL, '2018-01-05 15:45:49', '2018-01-05 15:45:51');
INSERT INTO `za_com_category` VALUES (4, 0, 4, '服务类', NULL, NULL, 1, NULL, '2018-01-05 15:46:45', '2018-01-05 15:46:46');
INSERT INTO `za_com_category` VALUES (5, 0, 5, '设计类', NULL, NULL, 1, NULL, '2018-01-05 16:04:27', '2018-01-05 16:04:28');

-- ----------------------------
-- Table structure for za_com_manager
-- ----------------------------
DROP TABLE IF EXISTS `za_com_manager`;
CREATE TABLE `za_com_manager`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员信息列表id',
  `manager_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业管理员',
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(2) NULL DEFAULT NULL COMMENT '年龄',
  `manager_phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员电话唯一值',
  `manager_account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账户名称唯一',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密code',
  `manager_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Email邮箱唯一',
  `com_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该管理员所属商家',
  `status` int(2) NULL DEFAULT 1 COMMENT '用户账户是否冻结 1 启用,0停用',
  `last_login` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后一次登录时间',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `manager_phone`(`manager_phone`) USING BTREE,
  UNIQUE INDEX `manager_account`(`manager_account`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业产品后台管理用户列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_com_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `za_com_manager_role`;
CREATE TABLE `za_com_manager_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业后台管理用户角色中间表主键id',
  `manager_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业后台管理用户角色中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_com_role
-- ----------------------------
DROP TABLE IF EXISTS `za_com_role`;
CREATE TABLE `za_com_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业后台管理角色表主键id',
  `p_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色父节点目录',
  `role_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称唯一',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色关键字词唯一',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `priority` int(4) NULL DEFAULT NULL COMMENT '角色在统计目录下优先级',
  `status` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '该角色是否启用或者停用,\"0\"启用,\"1\"停用',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '角色创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '角色更新时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业后台管理角色信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_com_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `za_com_role_authority`;
CREATE TABLE `za_com_role_authority`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authority_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opertator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业后台管理角色权限中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_com_scope
-- ----------------------------
DROP TABLE IF EXISTS `za_com_scope`;
CREATE TABLE `za_com_scope`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `com_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业id',
  `com_category_id` int(4) NULL DEFAULT NULL COMMENT '企业营业分类id',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业经营范围类别中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_com_scope
-- ----------------------------
INSERT INTO `za_com_scope` VALUES ('9e9f6f11d72846c9b5cf73f3e47872ba', 'b4d53315b04e4b70a1ffb53413113ecd', 1, NULL, '2018-01-12 17:54:46');
INSERT INTO `za_com_scope` VALUES ('asdasdfsdf', 'ansdkafnfnksdnfjksdnfkn', 2, '2018-01-05 16:05:24', '2018-01-05 16:05:24');

-- ----------------------------
-- Table structure for za_content
-- ----------------------------
DROP TABLE IF EXISTS `za_content`;
CREATE TABLE `za_content`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文案广告内容id',
  `content_category_id` int(4) NOT NULL COMMENT '内容类目ID',
  `sort` int(4) NULL DEFAULT NULL COMMENT '顺序广告内容展示顺序',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文案标题',
  `content_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文案标题内容',
  `link_url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文案广告链接url',
  `homepage_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文案广告主页引导缩略图url',
  `pricture_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主页广告大图片url',
  `content_desc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转内容页面详细描述信息',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL,
  `updated_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告内容' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_content
-- ----------------------------
INSERT INTO `za_content` VALUES ('8qtpt29ylt7ayueq7lp10z99nowmda7k', 9, NULL, '如何通过法律捍卫自己的权利', '如何通过法律捍卫自己的权利呢?每个人有法律赋予个人的权利，当自身权利遭受他人侵害，个人的利益得不到保障，可以通过法律的正当途径捍卫自己的权利，那么捍卫的过程该如何操作呢？请见内容详解分析。。', NULL, NULL, NULL, NULL, NULL, '2017-12-27 22:33:11', '2017-12-27 22:33:15');
INSERT INTO `za_content` VALUES ('aasdjasbdjkabdabsdhabhdbahs', 16, 3, '我的收藏', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 16:52:18', '2018-01-04 16:52:18');
INSERT INTO `za_content` VALUES ('adbshajjsdbjakdsajkasdjhajksd', 16, 2, '我的委托', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 16:51:17', '2018-01-04 16:51:18');
INSERT INTO `za_content` VALUES ('ahdjnsdfjknfkldfjsdkmfklsfj', 16, 12, '我的设置', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:16:31', '2018-01-04 17:16:32');
INSERT INTO `za_content` VALUES ('asbdhjasbhdjkahdjkashdjkahdkja', 16, 6, '我的余额', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 16:53:46', '2018-01-04 16:53:48');
INSERT INTO `za_content` VALUES ('asdbjkahdjkfdnhjksfnjkdsjfsd', 18, 1, '企业律师', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('asdfjkdfdhgudfgiodgjiodfgd', 17, 11, '我的设置', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('asdfjksdfjksljfsdkl;fjdslifdjikgfd', 16, 11, '切换端口', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:16:27', '2018-01-04 17:16:29');
INSERT INTO `za_content` VALUES ('asdfnhsdfhfdushfuisdhfsdui', 17, 10, '切换端口', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('asdhbajksdhkjahdkasdfnksd', 16, 9, '收货地址', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:16:18', '2018-01-04 17:16:19');
INSERT INTO `za_content` VALUES ('asdhbsjkdfhjdhfjksdnfsjkdf', 17, 6, '购物车', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('asdhdjkfhkjdfskljkljdgkldjfjkj', 18, 6, '切换端口', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('asdhjasdhjkdfksfhjkfkhkdgj', 17, 5, '我的收藏', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:36:22', NULL);
INSERT INTO `za_content` VALUES ('asdhsdjkfhkjshfklsdfjksdhf', 17, 7, '我的订单', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('asdjdfshkldfjjgddsjkfgkldfjkj', 18, 5, '企业收藏', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('asdjhjdfhsjkfilsdsdfjksdfj', 17, 8, '收货地址', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('asdjhkdlfssgdfkdkfjskljfddsl', 17, 9, '个人资料', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('asdjlkasdfjsdfjidfjsdfjksdfh', 17, 4, '我的推荐', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:36:19', '2018-01-04 17:36:20');
INSERT INTO `za_content` VALUES ('asdljlksdfj;klsfjklsfjklsfjksfjsklfjk', 17, 1, '我的客户', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:36:11', '2018-01-04 17:36:12');
INSERT INTO `za_content` VALUES ('ashdjkashdjkahdjkkjkldjfsjklj', 18, 4, '企业订单', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('ashndjksahdjkahskjdhajkkdjsfj', 16, 8, '我的订单', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:16:14', '2018-01-04 17:16:16');
INSERT INTO `za_content` VALUES ('asudhuahdsajkhdjkassdhfjkshd', 18, 2, '我的收益', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('flc66z0weslrmtbmh20i6xvghkxsavdw', 9, NULL, '实现依法治国、实现法治中国', '十八大以来，以习近平总书记为中心党中央高举实现中华民族伟大复兴的中国梦的旗帜，其中明确指出，要实现中华民族的伟大复兴的中国梦，实现依法治国、建设一支强有力的法制队伍，是其中重要的内容。。。', NULL, NULL, NULL, NULL, NULL, '2017-12-27 22:33:43', '2017-12-27 22:33:45');
INSERT INTO `za_content` VALUES ('hdsahdjasdhjjhfdkdsjfksd', 17, 3, '我的收益', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:36:16', '2018-01-04 17:36:17');
INSERT INTO `za_content` VALUES ('jasbdasjhdadjakaskdjalksjdnjklkn', 16, 4, '律师认证', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 16:52:45', '2018-01-04 16:52:47');
INSERT INTO `za_content` VALUES ('jshfjshdfjkhsdjkfhjksdhfjksdf', 18, 7, '我的设置', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('ndfsjksfhklsdhfjksdfndsmnfn', 17, 2, '悬赏委托', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:36:13', '2018-01-04 17:36:14');
INSERT INTO `za_content` VALUES ('njy2gpgqkgfvos39787sh2xsg784tfrf', 9, NULL, '做一个知法守法的公民', '如何做一个知法守法的公民？首先需要公民了解学习使用法律法规,将法律法规融入到生活学习工作中，形成良好的社会主义和谐社会氛围。', NULL, NULL, NULL, NULL, NULL, '2017-12-26 13:09:12', '2017-12-26 13:09:13');
INSERT INTO `za_content` VALUES ('sadjkashdkjshkjshfkjsfhjksfhsjkfh', 16, 7, '购物车', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:16:11', '2018-01-04 17:16:12');
INSERT INTO `za_content` VALUES ('sadnjkashdjkashdkjahsdjkhasdajk', 16, 5, '企业认证', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 16:53:22', '2018-01-04 16:53:24');
INSERT INTO `za_content` VALUES ('sdfklsdfkl;dkfflsjfdifjkgjkdfgj', 18, 3, '企业商品', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `za_content` VALUES ('sdhjkasdjksfbjdsfndjkfnjdsfnkdjnnh', 16, 1, '我的律师', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 16:50:41', '2018-01-04 16:50:42');
INSERT INTO `za_content` VALUES ('sfklasdjfsdfjafdjdskfjkdfskdjf', 16, 10, '个人资料', NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-04 17:16:24', '2018-01-04 17:16:25');

-- ----------------------------
-- Table structure for za_content_category
-- ----------------------------
DROP TABLE IF EXISTS `za_content_category`;
CREATE TABLE `za_content_category`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `p_id` int(4) NULL DEFAULT 0 COMMENT '父类目ID=0时，代表的是一级的类目',
  `sort_code` int(4) NOT NULL COMMENT '排列序号code，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `annotate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容分类名称解释说明',
  `logo_url` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Logo分类表示图片地址',
  `link_address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容分类跳转地址',
  `category_status` int(2) NULL DEFAULT 1 COMMENT '商品分类状态 1、启用 2、删除停用',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sort_code`(`sort_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告内容分类' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_content_category
-- ----------------------------
INSERT INTO `za_content_category` VALUES (1, 0, 1, '广告内容目录结构', 'App中所有广告文案类类型目录', NULL, NULL, 1, NULL, '2017-12-26 11:48:01', '2017-12-26 11:48:06');
INSERT INTO `za_content_category` VALUES (2, 1, 2, '悬赏律师', '发布悬赏委托信息', NULL, NULL, 1, NULL, '2017-12-26 11:49:26', '2017-12-26 11:49:27');
INSERT INTO `za_content_category` VALUES (3, 1, 3, '咨询律师', '我要向律师咨询', NULL, NULL, 1, NULL, '2017-12-26 11:50:14', '2017-12-26 11:50:15');
INSERT INTO `za_content_category` VALUES (4, 1, 4, '自主寻找', '我要自己寻找律师', NULL, NULL, 1, NULL, '2017-12-26 11:51:01', '2017-12-26 11:51:03');
INSERT INTO `za_content_category` VALUES (5, 1, 5, '悬赏委托', '承接悬赏委托赚取赏金', NULL, NULL, 1, NULL, '2017-12-26 12:04:22', '2017-12-26 12:04:23');
INSERT INTO `za_content_category` VALUES (6, 1, 6, '代写文书', '为用户代写文书赚取佣金', NULL, NULL, 1, NULL, '2017-12-26 13:00:56', '2017-12-26 13:00:57');
INSERT INTO `za_content_category` VALUES (7, 1, 7, '推荐律师', NULL, NULL, NULL, 1, NULL, '2017-12-26 14:02:25', '2017-12-26 14:02:26');
INSERT INTO `za_content_category` VALUES (8, 1, 8, '推荐委托', NULL, NULL, NULL, 1, NULL, '2017-12-26 14:02:29', '2017-12-26 14:02:29');
INSERT INTO `za_content_category` VALUES (9, 1, 10, '法律资讯', NULL, NULL, NULL, 1, NULL, '2017-12-26 14:03:23', '2017-12-26 14:03:24');
INSERT INTO `za_content_category` VALUES (10, 0, 11, '主页广告类目', NULL, NULL, NULL, 1, NULL, '2017-12-26 14:06:47', '2017-12-26 14:06:48');
INSERT INTO `za_content_category` VALUES (11, 11, 12, '个人用户首页大图', '属于个人用户主页面广告', NULL, NULL, 1, NULL, '2017-12-26 14:07:58', '2017-12-26 14:07:58');
INSERT INTO `za_content_category` VALUES (12, 11, 13, '律师用户首页大图', '属于律师用户主页面广告', NULL, NULL, 1, NULL, '2017-12-26 14:09:30', '2017-12-26 14:09:33');
INSERT INTO `za_content_category` VALUES (13, 11, 14, '企业用户首页大图', '属于企业用户主页面广告', NULL, NULL, 1, NULL, '2017-12-26 14:10:37', '2017-12-26 14:10:39');
INSERT INTO `za_content_category` VALUES (14, 1, 9, '推荐文书', NULL, NULL, NULL, 1, NULL, '2017-12-29 12:00:29', '2017-12-29 12:00:30');
INSERT INTO `za_content_category` VALUES (15, 0, 15, '附近', NULL, NULL, NULL, 1, NULL, '2018-01-04 09:31:30', '2018-01-04 09:31:31');
INSERT INTO `za_content_category` VALUES (16, 0, 16, '普通个人', '该类别表示普通个人中心', NULL, NULL, 1, NULL, '2018-01-04 16:24:34', '2018-01-04 16:24:35');
INSERT INTO `za_content_category` VALUES (17, 0, 17, '律师个人', '该类别表示律师个人中心', NULL, NULL, 1, NULL, '2018-01-04 16:45:25', '2018-01-04 16:45:26');
INSERT INTO `za_content_category` VALUES (18, 0, 18, '企业个人', '该类别表示企业个人中心', NULL, NULL, 1, NULL, '2018-01-04 16:46:28', '2018-01-04 16:46:29');

-- ----------------------------
-- Table structure for za_content_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `za_content_evaluate`;
CREATE TABLE `za_content_evaluate`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文案评价文字内容描述',
  `comment_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评价时间',
  `show_status` int(2) NULL DEFAULT 1 COMMENT '是否显示 默认1 显示 2删除不显示',
  `reply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台回复信息',
  `reply_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '回复时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信息处理员id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文案广告评价留言信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_express_com_disable
-- ----------------------------
DROP TABLE IF EXISTS `za_express_com_disable`;
CREATE TABLE `za_express_com_disable`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '快递公司信息列表',
  `com` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递公司信息简码',
  `company_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递公司名称',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录操作员id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `com`(`com`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '快递公司简码信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_gift
-- ----------------------------
DROP TABLE IF EXISTS `za_gift`;
CREATE TABLE `za_gift`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '礼品id',
  `gift_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '礼品名称',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '礼品单价 单位元',
  `logo_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该礼品的图标链接地址',
  `status` int(2) NULL DEFAULT 1 COMMENT '该礼品是否启用 1 默认启用 2 删除停用',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '打赏礼品信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_gift
-- ----------------------------
INSERT INTO `za_gift` VALUES ('001', '法豆', 100.00, NULL, 1, '2018-01-02 10:34:18', '2018-01-02 10:34:19', NULL);
INSERT INTO `za_gift` VALUES ('002', '称赞', 100.00, NULL, 1, '2018-01-02 10:34:33', '2018-01-02 10:34:35', NULL);
INSERT INTO `za_gift` VALUES ('003', '锦旗', 100.00, NULL, 1, '2018-01-02 10:34:53', '2018-01-02 10:34:54', NULL);
INSERT INTO `za_gift` VALUES ('004', '礼盒', 100.00, NULL, 1, '2018-01-02 10:35:08', '2018-01-02 10:35:09', NULL);

-- ----------------------------
-- Table structure for za_item
-- ----------------------------
DROP TABLE IF EXISTS `za_item`;
CREATE TABLE `za_item`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `item_category_id` int(3) NULL DEFAULT NULL COMMENT '商品分类目录id',
  `com_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该商品所属企业',
  `item_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题信息',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品详情描述信息',
  `item_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品原价',
  `member_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品会员价',
  `item_post` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品邮费',
  `sale_num` int(8) NULL DEFAULT 0 COMMENT '商品上市数量',
  `sell_num` int(8) NULL DEFAULT 600 COMMENT '已售出数量',
  `barcode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品条形编码',
  `homepage_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品缩略图主图地址',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '轮播商品图片在图片服务器中的地址 包含1-5张图片地址以字符串形式保存',
  `detail_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品详情介绍图片地址',
  `status` int(2) NULL DEFAULT 1 COMMENT '商品状态 1 待审核中 2审核已通过 3 商品上架 4商品下架 5商品删除',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '商品创建时间',
  `examine_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '审核通过时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '商品修改时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据操作员id',
  `examine_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核员id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_item_category
-- ----------------------------
DROP TABLE IF EXISTS `za_item_category`;
CREATE TABLE `za_item_category`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '分类主键id',
  `p_id` int(4) NULL DEFAULT 0 COMMENT '商品分类父节id',
  `sort_code` int(4) NULL DEFAULT NULL COMMENT '商品分类排序code唯一',
  `category_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类名称',
  `logo_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类图标链接地址',
  `link_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类链接跳转地址',
  `category_status` int(2) NULL DEFAULT 1 COMMENT '商品分类状态 1、启用 2、删除停用',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录操作员id',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sort_code`(`sort_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_item_evaluate_disable
-- ----------------------------
DROP TABLE IF EXISTS `za_item_evaluate_disable`;
CREATE TABLE `za_item_evaluate_disable`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下单用户id',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号id',
  `com_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该订单在哪家企业平台产生企业编号id',
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户对商品评价文字内容描述',
  `imag_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户反馈上传的图片请求地址，以json字符串形式保存',
  `comment_time` datetime(0) NULL DEFAULT NULL COMMENT '该评价写入时间',
  `show_status` int(2) NULL DEFAULT 1 COMMENT '是否显示 默认1 显示 2删除不显示',
  `reply` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家回复文字信息',
  `reply_time` datetime(0) NULL DEFAULT NULL COMMENT '商家回复时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户订单商品评价信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_lawyer_achievements
-- ----------------------------
DROP TABLE IF EXISTS `za_lawyer_achievements`;
CREATE TABLE `za_lawyer_achievements`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `law_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师认证id',
  `case_id` int(4) NULL DEFAULT NULL COMMENT '案件类型',
  `case_desc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '案例详细描述信息',
  `case_begin` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '案例起始时间',
  `case_end` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '案例结束时间',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '律师经典案例信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_lawyer_achievements
-- ----------------------------
INSERT INTO `za_lawyer_achievements` VALUES ('7b9be98fce384a7a9165aa4ae60b7d9f', 'bt5sdsdsd9083423jhdf234', 2, '兔兔图图', '2018-01-17 17:08:26', '2018-01-17 17:08:26', '2018-01-17 17:08:26', '2018-01-17 17:08:26', NULL);
INSERT INTO `za_lawyer_achievements` VALUES ('9071b138cfe34e0091ddc4fe5b5f4ed1', 'bt5sdsdsd9083423jhdf234', 2, '密密麻麻密密麻麻么么么么么喵喵喵', '2018-01-17 17:08:30', '2018-01-17 17:08:30', '2018-01-17 17:08:30', '2018-01-17 17:08:30', NULL);
INSERT INTO `za_lawyer_achievements` VALUES ('9b1dc5640a5542e18fddf95ea96e3fd0', 'bt5sdsdsd9083423jhdf234', 2, '斤斤计较军军军军军', '2018-01-17 17:08:41', '2018-01-17 17:08:41', '2018-01-17 17:08:41', '2018-01-17 17:08:41', NULL);
INSERT INTO `za_lawyer_achievements` VALUES ('c095ab80831f47aaa61b0befd836ab60', NULL, 2, '斤斤计较军军军军军', '2014-12-03 00:00:00', '2018-12-12 00:00:00', '2018-01-12 17:10:12', '2018-01-12 17:10:12', NULL);

-- ----------------------------
-- Table structure for za_lawyer_authentication
-- ----------------------------
DROP TABLE IF EXISTS `za_lawyer_authentication`;
CREATE TABLE `za_lawyer_authentication`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'userid',
  `real_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师真实姓名',
  `grade` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '6.0' COMMENT '律师等级 默认 六颗星',
  `law_case` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师擅长案件类型 使用逗号，分隔',
  `age_limit` int(3) NULL DEFAULT NULL COMMENT '律师工作年限',
  `law_office` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师事务所',
  `law_introduce` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师个人简介信息描述',
  `licence_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师的从业证件(律师执照）',
  `qualification_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师资格证书内页url',
  `ID_card` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师身份证',
  `law_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师本人职业头像url',
  `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `law_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师工作地址范围',
  `com_lon` double(9, 6) NULL DEFAULT NULL COMMENT '律师地址经度',
  `com_lat` double(9, 6) NULL DEFAULT NULL COMMENT '律师地址纬度',
  `geo_code` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'geohash编码',
  `law_status` int(2) NULL DEFAULT 0 COMMENT '律师状态 0 审核中 1.已通过审核可以接受任务 2.律师账户冻结 3律师账户删除 4 未审核通过',
  `state` int(2) NULL DEFAULT 0 COMMENT '律师用户状态 默认 0在线  离线 1 隐身',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '申请时间',
  `examine_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '审核通过时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `examine_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核员id',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '律师资格认证信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_lawyer_authentication
-- ----------------------------
INSERT INTO `za_lawyer_authentication` VALUES ('18d15e58e5a54c7db17fa356c30a7358', '71f84abb72ff497590aab2f4d66105b3', '露露', '6.0', '', 10, '巴巴爸爸吧', NULL, 'group1/M00/00/00/rBAmwVpYW7iAfLmzAADape7tCNo193.jpg?2017111011202036614.jpg', 'group1/M00/00/00/rBAmwVpYW7KAfw4SAAFyVoCNsaw605.jpg?Favorite English song_16624615812538512.jpg', NULL, NULL, NULL, NULL, '好脾气是highhigh好', NULL, NULL, NULL, 0, 0, '2018-01-16 16:24:05', '2018-01-16 16:24:05', '2018-01-16 16:24:05', NULL, NULL);
INSERT INTO `za_lawyer_authentication` VALUES ('adssdsdash083423jhdf234', NULL, '露露', '6.0', '民事纠纷,债券债权,离婚诉讼', 20, '重庆市欢喜律师事务所', '先后毕业于辽宁师范大学、中国政法大学、获取理学学士、法学学士的双学士学位，并在当年荣获“中国政法大学优秀毕业生”荣誉称号。周华健律师视野开阔、思维活跃、逻辑严谨、作风文件、擅长民事商法（包括合同法、公司法、房地产法、婚姻家庭、劳动争议法等）', NULL, NULL, NULL, NULL, '重庆市', '重庆市', '重庆市九龙坡区高新区石桥铺石新路33号', 106.484390, 29.530904, 'uxuzzzzxzzyp', 1, 0, '2018-01-17 17:13:22', '2018-01-17 17:13:22', '2018-01-17 17:13:22', NULL, NULL);
INSERT INTO `za_lawyer_authentication` VALUES ('bt5sdsdsd9083423jhdf234', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', '露露', '6.0', '民事纠纷,债券债权,离婚诉讼', 20, '四川省成都市挚爱律师事务所', '先后毕业于辽宁师范大学、中国政法大学、获取理学学士、法学学士的双学士学位，并在当年荣获“中国政法大学优秀毕业生”荣誉称号。周华健律师视野开阔、思维活跃、逻辑严谨、作风文件、擅长民事商法（包括合同法、公司法、房地产法、婚姻家庭、劳动争议法等）', NULL, NULL, NULL, 'ASDJKASDJKA.JPG', '四川省', '成都市', '成都市天府五街丽景西路美城悦荣府(东小门)', 104.053140, 30.539852, 'uxvxyzyrypup', 1, 0, '2018-01-17 17:13:20', '2018-01-17 17:13:20', '2018-01-17 17:13:20', NULL, NULL);
INSERT INTO `za_lawyer_authentication` VALUES ('bt5ud6x8yy5vpkb7plqeal97oqu0otl3', NULL, '露露', '6.0', '民事纠纷,债券债权,离婚诉讼', 16, '四川省成都市天智律师事务所', '先后毕业于辽宁师范大学、中国政法大学、获取理学学士、法学学士的双学士学位，并在当年荣获“中国政法大学优秀毕业生”荣誉称号。王小川律师视野开阔、思维活跃、逻辑严谨、作风文件、擅长民事商法（包括合同法、公司法、房地产法、婚姻家庭、劳动争议法等）', NULL, NULL, NULL, NULL, '四川省', '成都市', '四川省成都市武侯区蜀锦路88楚峰国际中心', 104.070091, 30.584569, 'uxvxzzzzgpgz', 1, 0, '2018-01-17 17:13:19', '2018-01-17 17:13:19', '2018-01-17 17:13:19', NULL, NULL);
INSERT INTO `za_lawyer_authentication` VALUES ('d98d33effe0746a8969fb76517eb9d75', 'a47db8101c9943febf86a699d9596398', '露露', '6.0', '', 10, '啦啦啦啦', NULL, 'group1/M00/00/00/rBAmwVpcT3iAbKYXAADape7tCNo734.jpg?2017111011202036614.jpg', 'group1/M00/00/00/rBAmwVpcT2-AN-trAADape7tCNo532.jpg?2017111011202036614.jpg', NULL, NULL, NULL, NULL, '九里提立体角', NULL, NULL, NULL, 0, 0, '2018-01-17 17:13:18', '2018-01-17 17:13:18', '2018-01-17 17:13:18', NULL, NULL);
INSERT INTO `za_lawyer_authentication` VALUES ('df7dfadf79384439909a5a1cc750dc76', '71f84abb72ff497590aab2f4d66105b3', '露露', '6.0', '', 10, '摸摸摸摸摸摸', NULL, 'group1/M00/00/00/rBAmwVpYWxqAd72rAAEvKE3sS7s615.jpg?-6164e5277d3b3668.jpg', 'group1/M00/00/00/rBAmwVpYWv-AKaLPAAF35xH238o941.jpg?temp1515739891590.jpg', NULL, NULL, NULL, NULL, 'lol哦咯lol哦咯', NULL, NULL, NULL, 0, 0, '2018-01-16 16:24:09', '2018-01-16 16:24:09', '2018-01-16 16:24:09', NULL, NULL);
INSERT INTO `za_lawyer_authentication` VALUES ('e0324e8a5e034251a238a3e4dbee43d7', '71f84abb72ff497590aab2f4d66105b3', '露露', '6.0', '', 10, '嗯嗯嗯的呃呃呃额额的', NULL, 'group1/M00/00/00/rBAmwVpYYQmANWpPAADape7tCNo587.jpg?2017111011202036614.jpg', 'group1/M00/00/00/rBAmwVpYYQKABN01AAFibB-bTDc744.jpg?2017111011202050767.jpg', NULL, NULL, NULL, NULL, '路噜噜噜噜噜噜噜', NULL, NULL, NULL, 0, 0, '2018-01-16 16:24:11', '2018-01-16 16:24:11', '2018-01-16 16:24:11', NULL, NULL);
INSERT INTO `za_lawyer_authentication` VALUES ('f8b2ffe1adec44908ac1df68e29a6104', '71f84abb72ff497590aab2f4d66105b3', '露露', '6.0', NULL, 10, '估计提立体角', NULL, 'group1/M00/00/00/rBAmwVpYXByAeHu1AAFyVoCNsaw791.jpg?Favorite English song_16624615812538512.jpg', 'group1/M00/00/00/rBAmwVpYXBaAWjr3AAEvwbSwZoo434.jpg?I01026196.jpg', NULL, NULL, NULL, NULL, '集体立体角', NULL, NULL, NULL, 0, 0, '2018-01-12 14:56:49', NULL, '2018-01-12 14:56:49', NULL, NULL);
INSERT INTO `za_lawyer_authentication` VALUES ('m76sjs3qmiee4ho35tjs6194wqkwla9m', 'a47db8101c9943febf86a699d9596398', '露露', '6.0', '民事纠纷,债券债权,离婚诉讼', 18, '北京市祥云律师事务所', '先后毕业于辽宁师范大学、中国政法大学、获取理学学士、法学学士的双学士学位，并在当年荣获“中国政法大学优秀毕业生”荣誉称号。周华健律师视野开阔、思维活跃、逻辑严谨、作风文件、擅长民事商法（包括合同法、公司法、房地产法、婚姻家庭、劳动争议法等）', NULL, NULL, NULL, NULL, '四川省', '成都市', '成都市高新区天府大道北段1480号高新区孵化园', 104.070367, 30.579786, 'uxvpbpcrfpcz', 1, 0, '2018-01-17 17:13:16', '2018-01-17 17:13:16', '2018-01-17 17:13:16', NULL, NULL);

-- ----------------------------
-- Table structure for za_lawyer_case
-- ----------------------------
DROP TABLE IF EXISTS `za_lawyer_case`;
CREATE TABLE `za_lawyer_case`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '律师与技能标签中间信息表id',
  `law_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师id',
  `case_id` int(6) NULL DEFAULT NULL COMMENT '擅长案例id',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `law_id`(`law_id`, `case_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '律师与案件技能标签中间信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_lawyer_case
-- ----------------------------
INSERT INTO `za_lawyer_case` VALUES ('', 'bt5ud6x8yy5vpkb7plqeal97oqu0otl3', NULL, NULL, NULL, NULL);
INSERT INTO `za_lawyer_case` VALUES ('06946273615f4e358ced2e83f9c09dca', '2bb75d69823e453bbdfe213cfb335221', 2, '2018-01-12 14:23:54', '2018-01-12 14:23:54', NULL);
INSERT INTO `za_lawyer_case` VALUES ('08935ed0f1484b61b629a5353f56e2af', '582dcaac4c0449be8a4fbb5253eb1a7b', 2, '2018-01-12 14:09:01', '2018-01-12 14:09:01', NULL);
INSERT INTO `za_lawyer_case` VALUES ('12ccc45edd544700a010b42854914bc1', '3486d6ab685e4e9f8b740a61c10c29a2', 2, '2018-01-12 14:29:29', '2018-01-12 14:29:29', NULL);
INSERT INTO `za_lawyer_case` VALUES ('13c75f7740ad4e10943defbb8a806bed', '9ef5e9a038104574b9edde129a8e1e20', 1, '2018-01-12 14:23:24', '2018-01-12 14:23:24', NULL);
INSERT INTO `za_lawyer_case` VALUES ('1ea081706cf6425ca5127e5b8ef652e2', '5eedc20558bc47a5bbc958ece8de5eb6', 2, '2018-01-12 14:24:25', '2018-01-12 14:24:25', NULL);
INSERT INTO `za_lawyer_case` VALUES ('1xetb3gm0kinlcd1izljodz119duj3mc', 'bt5ud6x8yy5vpkb7plqeal97oqu0otl3', 4, '2017-12-26 11:29:22', '2017-12-26 11:29:23', NULL);
INSERT INTO `za_lawyer_case` VALUES ('22d0eb6a1cc24a55ac7e88cb89844aa0', 'b669cfff9e7442eeae0ef178673b32c2', 1, '2018-01-12 14:12:37', '2018-01-12 14:12:37', NULL);
INSERT INTO `za_lawyer_case` VALUES ('2523a3299f9e486db9727561a81a942f', '2bb75d69823e453bbdfe213cfb335221', 1, '2018-01-12 14:23:54', '2018-01-12 14:23:54', NULL);
INSERT INTO `za_lawyer_case` VALUES ('271d72ba8ee84e5480c46e0b26cfb1e6', 'b9f746a2570f4698a6c5c4ba7a9cdccf', 1, '2018-01-12 14:23:59', '2018-01-12 14:23:59', NULL);
INSERT INTO `za_lawyer_case` VALUES ('35525fdca03e4eccb4a30058669f70ac', 'e0324e8a5e034251a238a3e4dbee43d7', 2, '2018-01-12 15:17:52', '2018-01-12 15:17:52', NULL);
INSERT INTO `za_lawyer_case` VALUES ('388af7628be74f41b241955a33f0d33a', 'd98d33effe0746a8969fb76517eb9d75', 1, '2018-01-15 14:52:01', '2018-01-15 14:52:01', NULL);
INSERT INTO `za_lawyer_case` VALUES ('391a455c5e4743c281611c00e16ba0bc', 'bdd596625df6474b95530a1fa54a12a7', 2, '2018-01-12 14:26:13', '2018-01-12 14:26:13', NULL);
INSERT INTO `za_lawyer_case` VALUES ('3e3c58a00b444b77b20070cceedd9431', 'e0324e8a5e034251a238a3e4dbee43d7', 1, '2018-01-12 15:17:52', '2018-01-12 15:17:52', NULL);
INSERT INTO `za_lawyer_case` VALUES ('3ssai9gwbxihia9j8xmkp1il4d9xrr8c', 'bt5ud6x8yy5vpkb7plqeal97oqu0otl3', 2, '2017-12-26 11:28:39', '2017-12-26 11:28:40', NULL);
INSERT INTO `za_lawyer_case` VALUES ('569417c3669247a28ec4bc82d14b0c85', 'd98d33effe0746a8969fb76517eb9d75', 2, '2018-01-15 14:52:01', '2018-01-15 14:52:01', NULL);
INSERT INTO `za_lawyer_case` VALUES ('66f89e3f1dcd4beb89df8785d3b0aaa3', '0227482a58194d64bed6eeda262e2d8e', 3424, '2018-01-09 15:48:35', '2018-01-09 15:48:35', NULL);
INSERT INTO `za_lawyer_case` VALUES ('6bcb201733ae41a09f4ca680fcdd2d63', '3486d6ab685e4e9f8b740a61c10c29a2', 1, '2018-01-12 14:29:29', '2018-01-12 14:29:29', NULL);
INSERT INTO `za_lawyer_case` VALUES ('6cf91fed89134006aa9a9b10bc66be34', '9110c2fc172f46f4bdf0f589618cef23', 1, '2018-01-12 14:23:28', '2018-01-12 14:23:28', NULL);
INSERT INTO `za_lawyer_case` VALUES ('7c51036bb3004af6a0bcf9e79a8b85e7', 'bdd596625df6474b95530a1fa54a12a7', 1, '2018-01-12 14:26:13', '2018-01-12 14:26:13', NULL);
INSERT INTO `za_lawyer_case` VALUES ('93d2fa8a0216498aa1e9cd106337a97f', '0227482a58194d64bed6eeda262e2d8e', 123123, '2018-01-09 15:48:35', '2018-01-09 15:48:35', NULL);
INSERT INTO `za_lawyer_case` VALUES ('992cfe56184b46d180f52e523820fc3b', '89d65a11f8cf474794d6f34cd9e9cc28', 1, '2018-01-12 14:23:39', '2018-01-12 14:23:39', NULL);
INSERT INTO `za_lawyer_case` VALUES ('9d648651b3234195902284b94c9d855d', 'f8b2ffe1adec44908ac1df68e29a6104', 2, '2018-01-12 14:56:49', '2018-01-12 14:56:49', NULL);
INSERT INTO `za_lawyer_case` VALUES ('aba78b0e88a14588a96a5281e8f8de61', '5eedc20558bc47a5bbc958ece8de5eb6', 1, '2018-01-12 14:24:25', '2018-01-12 14:24:25', NULL);
INSERT INTO `za_lawyer_case` VALUES ('afe7f7ada3d2438e87d65a442bc2c821', 'b669cfff9e7442eeae0ef178673b32c2', 2, '2018-01-12 14:12:37', '2018-01-12 14:12:37', NULL);
INSERT INTO `za_lawyer_case` VALUES ('ahsdauhdssajkhuioweruwhuwehdnf', 'm76sjs3qmiee4ho35tjs6194wqkwla9m', 6, '2017-12-29 14:50:57', '2017-12-29 14:50:58', NULL);
INSERT INTO `za_lawyer_case` VALUES ('asdasjdklajsdkasdhklaushd90naklsdj', 'm76sjs3qmiee4ho35tjs6194wqkwla9m', 5, '2017-12-29 14:50:35', '2017-12-29 14:50:36', NULL);
INSERT INTO `za_lawyer_case` VALUES ('ashdjkashdjadnhaj,ndjakdnjjdnsjk', 'bt5sdsdsd9083423jhdf234', 3, '2017-12-29 15:19:46', '2017-12-29 15:19:47', NULL);
INSERT INTO `za_lawyer_case` VALUES ('b4ceb684a65f4788974c5b27f544b773', 'e8450b798f9844d8bcbbdf99002c2563', 1, '2018-01-12 14:09:44', '2018-01-12 14:09:44', NULL);
INSERT INTO `za_lawyer_case` VALUES ('b7a33aa710b14a0d9f81bfa49258c06a', 'e8450b798f9844d8bcbbdf99002c2563', 2, '2018-01-12 14:09:44', '2018-01-12 14:09:44', NULL);
INSERT INTO `za_lawyer_case` VALUES ('boaomsw17ea1cc2zf0jmrha3x6y75yai', 'bt5ud6x8yy5vpkb7plqeal97oqu0otl3', 5, '2017-12-29 14:46:23', '2017-12-29 14:46:23', NULL);
INSERT INTO `za_lawyer_case` VALUES ('cfc70246f3754fc985bd516ae1c5a54d', 'dbe6a55ebed94d1c80b42fafbc762d95', 1, '2018-01-12 14:22:45', '2018-01-12 14:22:45', NULL);
INSERT INTO `za_lawyer_case` VALUES ('d78bf01956854c56942ab052880ce249', '0227482a58194d64bed6eeda262e2d8e', 324214, '2018-01-09 15:48:35', '2018-01-09 15:48:35', NULL);
INSERT INTO `za_lawyer_case` VALUES ('dd75121464534d7cb334efa3f7468a75', '582dcaac4c0449be8a4fbb5253eb1a7b', 1, '2018-01-12 14:09:01', '2018-01-12 14:09:01', NULL);
INSERT INTO `za_lawyer_case` VALUES ('eb42fdf8e3414e50b732a23f537df8cc', 'ac1e770df03f43fabe33b1c6b815524b', 1, '2018-01-12 14:26:40', '2018-01-12 14:26:40', NULL);
INSERT INTO `za_lawyer_case` VALUES ('fb803d063299451ea140a01570848afa', 'f8b2ffe1adec44908ac1df68e29a6104', 1, '2018-01-12 14:56:49', '2018-01-12 14:56:49', NULL);
INSERT INTO `za_lawyer_case` VALUES ('fc6d707d46764eae8f032c0c21e34cb6', 'ac1e770df03f43fabe33b1c6b815524b', 2, '2018-01-12 14:26:40', '2018-01-12 14:26:40', NULL);
INSERT INTO `za_lawyer_case` VALUES ('gka110a0d4dtnufcfnizisv9aec37j93', 'm76sjs3qmiee4ho35tjs6194wqkwla9m', 2, '2017-12-26 11:26:59', '2017-12-26 11:27:00', NULL);
INSERT INTO `za_lawyer_case` VALUES ('iiep0g4ptv6bg46n75pyg5jv8kw2z3mh', 'm76sjs3qmiee4ho35tjs6194wqkwla9m', 4, '2017-12-26 11:28:15', '2017-12-26 11:28:15', NULL);
INSERT INTO `za_lawyer_case` VALUES ('tcpc0z0v8lpn9mzdasx1qayjtkw2s85o', 'bt5ud6x8yy5vpkb7plqeal97oqu0otl3', 3, '2017-12-26 11:28:58', '2017-12-26 11:28:58', NULL);
INSERT INTO `za_lawyer_case` VALUES ('zbp334o6fja6pglfos8c71kgg8bwk1mu', 'm76sjs3qmiee4ho35tjs6194wqkwla9m', 3, '2017-12-26 11:27:22', '2017-12-26 11:27:23', NULL);

-- ----------------------------
-- Table structure for za_lawyer_recommend_disabled
-- ----------------------------
DROP TABLE IF EXISTS `za_lawyer_recommend_disabled`;
CREATE TABLE `za_lawyer_recommend_disabled`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `law_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师id',
  `object_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师推荐对象id',
  `ob_type` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '律师推荐 1推荐商品 2 推荐企业',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '律师推荐商品或企业信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_order_benefit_disable
-- ----------------------------
DROP TABLE IF EXISTS `za_order_benefit_disable`;
CREATE TABLE `za_order_benefit_disable`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `pay_coin` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '该订单支付抵扣货币值 单位元',
  `pay_discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '该订单抵扣打折信息 单位元',
  `discount_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该订单折扣描述',
  `buyer_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家订单留言信息',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录修改时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录操作修改抵扣信息操作员id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单折扣优惠信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_order_disable
-- ----------------------------
DROP TABLE IF EXISTS `za_order_disable`;
CREATE TABLE `za_order_disable`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单用户id',
  `com_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该订单所属某个商家',
  `order_status` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '订单状态 1、未付款，2、已付款待发货、3、已发货待收货，4、已收货待评价，5、未评或已评价交易成功，6、申请退款中，7、已退款成功，8、交易关闭或者订单已取消，9、订单已删除',
  `order_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '订单类型 默认为 1购物订单类型',
  `payment` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '该订单实际付款总金额(含运费)',
  `pay_type` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '支付方式0.默认微信支付1.支付宝支付 2法币支付 3网银支付',
  `pay_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付账户信息',
  `order_num` int(2) NULL DEFAULT 0 COMMENT '订单中商品总件数',
  `order_post` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '订单支付总邮费',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '订单创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '订单更新修改时间',
  `pay_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '订单支付时间',
  `consign_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '订单发货时间',
  `succeed_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '订单交易成功时间',
  `closed_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '订单交易关闭或者取消时间',
  `drawback_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '退款成功时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_order_item_disable
-- ----------------------------
DROP TABLE IF EXISTS `za_order_item_disable`;
CREATE TABLE `za_order_item_disable`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单中商品id',
  `item_num` int(2) NULL DEFAULT NULL COMMENT '订单中该商品的数量',
  `item_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '该商品的原价',
  `member_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品会员价',
  `actual_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '实际支付价钱',
  `item_post` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '该商品的邮费信息',
  `item_payment` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '该商品实际支付总金额(含该商品运费)',
  `item_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该商品的标题信息',
  `item_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该商品的描述信息',
  `homepage_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品缩略图片链接地址(商品主图地址)',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品价钱数量总价修改操作人id',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单商品信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_order_shipping_disable
-- ----------------------------
DROP TABLE IF EXISTS `za_order_shipping_disable`;
CREATE TABLE `za_order_shipping_disable`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shipping_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递运单编号',
  `express_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单所属快递公司id',
  `consignee_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人姓名',
  `consignee_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人电话',
  `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'c城市',
  `district` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区县',
  `detail_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址信息',
  `postalcode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录发货修改地址信息操作员id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单收货人详细信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_plat_authority
-- ----------------------------
DROP TABLE IF EXISTS `za_plat_authority`;
CREATE TABLE `za_plat_authority`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '平台后台管理权限表id',
  `p_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限父节点目录',
  `authority_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称唯一',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限code关键词w唯一',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限信息描述',
  `priority` int(4) NULL DEFAULT NULL COMMENT '权限在同一级别目录下的优先级',
  `module` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该权限作用模块',
  `status` int(2) NULL DEFAULT 0 COMMENT '该权限是否启用或者停用,\"0\"启用,\"1\"停用',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `authority_name`(`authority_name`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台后台管理权限信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_plat_manager
-- ----------------------------
DROP TABLE IF EXISTS `za_plat_manager`;
CREATE TABLE `za_plat_manager`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '平台后台管理员列表主键id',
  `manager_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台后台管理员姓名',
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(2) NULL DEFAULT NULL COMMENT '年龄',
  `manager_phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员电话',
  `manager_account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账户信息',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加密code',
  `manager_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'email邮箱',
  `last_login` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近一次登录时间',
  `status` int(2) NULL DEFAULT NULL COMMENT '用户状态： 0 冻结 1 正常',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台后台管理员列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_plat_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `za_plat_manager_role`;
CREATE TABLE `za_plat_manager_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业后台管理用户角色中间表主键id',
  `manager_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台后台管理用户角色中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_plat_role
-- ----------------------------
DROP TABLE IF EXISTS `za_plat_role`;
CREATE TABLE `za_plat_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '平台后台管理角色表主键id',
  `p_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色父节点目录',
  `role_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称唯一',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色关键字词唯一',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `priority` int(4) NULL DEFAULT NULL COMMENT '角色在统计目录下优先级',
  `status` int(2) NULL DEFAULT 0 COMMENT '该角色是否启用或者停用,\"0\"启用,\"1\"停用',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '角色创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '角色更新时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台后台管理角色信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_plat_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `za_plat_role_authority`;
CREATE TABLE `za_plat_role_authority`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authority_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opertator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台后台管理角色权限中间表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_sys_feedback
-- ----------------------------
DROP TABLE IF EXISTS `za_sys_feedback`;
CREATE TABLE `za_sys_feedback`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈用户id',
  `feedback_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈信息内容',
  `feedback_status` int(2) NULL DEFAULT 0 COMMENT '反馈处理状态 0未处理 1已处理',
  `feedback_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `revovery_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '回复时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈处理操作员id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户反馈给系统的信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `za_sys_notice`;
CREATE TABLE `za_sys_notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '递增主键id',
  `system_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统id',
  `from_user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息发起者id',
  `to_user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息接受者id',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息内容',
  `event_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息相关事件id(案件id,消息打赏记录id)',
  `notice_type` int(2) NULL DEFAULT NULL COMMENT '消息类型 0其他系统消息 1案件接受消息 2打赏悬赏消息. 3案件完成回复消息.4.邀请律师消息 5系统通知所有人的消息',
  `agree` int(2) NULL DEFAULT NULL COMMENT '0忽略拒绝请求 1同意请求 2替换律师请求',
  `handle_state` int(2) NULL DEFAULT 0 COMMENT '该消息接受者是否已处理过 0未处理 1已处理',
  `del_state` int(1) NULL DEFAULT 0 COMMENT '该消息是否删除或展示 0展示 1删除',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_user
-- ----------------------------
DROP TABLE IF EXISTS `za_user`;
CREATE TABLE `za_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `user_account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录账户信息',
  `user_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码加密code',
  `user_nick` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_gender` int(1) NULL DEFAULT 1 COMMENT '用户性别 1默认男士 2女士',
  `user_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '注册短信发送手机号',
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱redis设置30分钟有效激活时间',
  `head_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址链接',
  `user_address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户所在物理地址区域',
  `user_type` int(2) NULL DEFAULT 1 COMMENT '用户类型 1普通用户 2律师用户 3企业用户',
  `status` int(2) NULL DEFAULT 1 COMMENT '用户账户是否冻结 0 冻结 1 不冻结 2 删除',
  `state` int(2) NULL DEFAULT 0 COMMENT '用户状态 默认 0在线  离线 1 隐身',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '用户注册时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '用户最近修改时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '用户最后一次登录时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该记录操作人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '普通用户信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_user
-- ----------------------------
INSERT INTO `za_user` VALUES ('2cd0d18d868a4ca784485b547c752365', '15756565835', 'ef4e6095ab5a98a4ca58783a3ec50f51', '4Sbav1jPQH02', 'lawyer_560408', 1, '15756565835', NULL, 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', NULL, 1, 1, 0, '2018-01-11 14:26:29', '2018-01-11 14:26:29', '2018-01-11 14:26:29', NULL);
INSERT INTO `za_user` VALUES ('71f84abb72ff497590aab2f4d66105b3', '18398369370', '182ad5d6d2ff3731a75fcede0c5733d1', 'A8IZcge4Asd', 'lawyer_865653', 1, '18398369370', NULL, 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', NULL, 1, 1, 0, '2018-01-09 15:19:53', '2018-01-13 11:08:21', '2018-01-18 04:51:40', NULL);
INSERT INTO `za_user` VALUES ('a47db8101c9943febf86a699d9596398', '15756265835', '4147a9452deb1e19db356e2a82ba3827', 'fhCqVeWnSk8', 'lawyer_947872', 1, '15756265835', NULL, 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', NULL, 1, 1, 0, '2018-01-10 17:44:30', '2018-01-10 17:44:30', '2018-01-18 11:24:13', NULL);
INSERT INTO `za_user` VALUES ('asdjkladfjlasdjdfkladsgdfgdfgdf', '15353453455', '12112', '212', '哇哈哈', 1, '135454545', '415453453453', '', '浙江省杭州市哇哈哈路', 1, 1, 0, '2018-01-05 11:49:54', '2018-01-05 11:49:56', '2018-01-05 14:52:32', NULL);
INSERT INTO `za_user` VALUES ('b87fcb7b42d042d8a2e7505e477381f7', '15756265535', 'e8a2e8784b62603d7b1efb41a938506c', 'nPV495Mz18F1K', 'lawyer_327716', 1, '15756265535', NULL, 'group1/M00/00/11/rBBH51obzR-ATAk7AAALPq6KRzM809.png?TIM图片20171125131702.png', NULL, 1, 1, 0, '2018-01-11 10:32:09', '2018-01-11 10:32:09', '2018-01-15 15:38:07', NULL);
INSERT INTO `za_user` VALUES ('v4umc48dpqiy5gz9u919zusqnvit1mhf', '13883254087', '123456', 'abc', '小小爱哦', 1, '13883254087', '2282500426@qq.com', 'asdjahsdjkha.jap', '四川省成都市孵化园楚峰国际中心', 1, 1, 0, '2018-01-05 11:49:58', '2018-01-02 10:31:12', '2018-01-05 11:50:10', NULL);

-- ----------------------------
-- Table structure for za_user_award_record
-- ----------------------------
DROP TABLE IF EXISTS `za_user_award_record`;
CREATE TABLE `za_user_award_record`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打赏人id',
  `awardee_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '受赏者id',
  `gift_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打赏礼品id',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '打赏礼品单价 元',
  `gift_num` int(6) NULL DEFAULT 0 COMMENT '礼品件数',
  `reward` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '赏金 单位元 ',
  `award_status` int(2) NULL DEFAULT 0 COMMENT '打赏支付状态  0 赏金未支付 1赏金支付成功 2打赏取消',
  `type` int(2) NULL DEFAULT 1 COMMENT '0直播间打赏,1普通打赏 2案件委托悬赏 3代写文书悬赏',
  `award_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '打赏实际支付时间',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '打赏时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户打赏(悬赏)礼物记录明细列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_user_award_record
-- ----------------------------
INSERT INTO `za_user_award_record` VALUES ('071d2d7ab1fd4c9395c5f12871250295', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '002', 100.00, 1, 100.00, 1, 1, '2018-01-09 15:10:59', '2018-01-09 15:11:02', '2018-01-09 15:11:02', NULL);
INSERT INTO `za_user_award_record` VALUES ('21258a6d5db240b8a4d203bca66df2c5', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '003', 100.00, 1, 100.00, 1, 2, '2018-01-16 15:45:54', '2018-01-16 15:45:54', '2018-01-16 15:45:54', NULL);
INSERT INTO `za_user_award_record` VALUES ('2cffef68d203409ab293f618408207c3', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '002', 100.00, 1, 100.00, 1, 1, '2018-01-16 15:45:57', '2018-01-16 15:45:57', '2018-01-16 15:45:57', NULL);
INSERT INTO `za_user_award_record` VALUES ('315a0e37106a44918c4f32ea4f7ac387', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '002', 100.00, 1, 100.00, 1, 1, '2018-01-09 15:09:32', '2018-01-09 15:09:34', '2018-01-09 15:09:34', NULL);
INSERT INTO `za_user_award_record` VALUES ('8f5156e19e76464589ab72050ebe7a51', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '002', 100.00, 1, 100.00, 1, 1, '2018-01-09 15:09:48', '2018-01-09 15:09:51', '2018-01-09 15:09:51', NULL);
INSERT INTO `za_user_award_record` VALUES ('93f452dc6dbc48db825f411ee61cb3ec', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '004', 100.00, 1, 100.00, 1, 1, '2018-01-09 15:08:18', '2018-01-09 15:08:21', '2018-01-09 15:08:21', NULL);
INSERT INTO `za_user_award_record` VALUES ('b2b711b30c424982bc87fb813cf77908', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '003', 100.00, 1, 100.00, 1, 2, '2018-01-16 16:13:02', '2018-01-16 16:13:02', '2018-01-16 16:13:02', NULL);
INSERT INTO `za_user_award_record` VALUES ('bfc2d6f75cb840d5b2c17443f4eb26b4', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '002', 100.00, 1, 100.00, 1, 1, '2018-01-09 15:09:36', '2018-01-09 15:09:39', '2018-01-09 15:09:39', NULL);

-- ----------------------------
-- Table structure for za_user_chat
-- ----------------------------
DROP TABLE IF EXISTS `za_user_chat`;
CREATE TABLE `za_user_chat`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '普通用户id',
  `law_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师id',
  `session_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会话唯一码主键id',
  `chat_category` int(2) NULL DEFAULT 0 COMMENT '对话分类：0普通聊天对话 1悬赏会话',
  `start_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '会话创建时间',
  `end_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '会话结束时间',
  `show_state` int(2) NULL DEFAULT 0 COMMENT '是否展示会话关系 0展示 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户聊天会话记录关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_user_collection
-- ----------------------------
DROP TABLE IF EXISTS `za_user_collection`;
CREATE TABLE `za_user_collection`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户与企业收藏关系信息表id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户或律师或者企业id',
  `collection_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '收藏对象id',
  `ob_type` int(2) NULL DEFAULT 1 COMMENT '收藏类型 1 用户收藏律师 2 用户收藏企业 3 用户收藏商品 4用户收藏案例',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户收藏信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_user_collection
-- ----------------------------
INSERT INTO `za_user_collection` VALUES ('aksdjkasnhdjkashdjk', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'ansdkafnfnksdnfjksdnfkn', 2, '2018-01-08 15:21:52', '2018-01-08 15:21:54');

-- ----------------------------
-- Table structure for za_user_deal_log
-- ----------------------------
DROP TABLE IF EXISTS `za_user_deal_log`;
CREATE TABLE `za_user_deal_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户或者律师id',
  `user_type` int(2) NULL DEFAULT 1 COMMENT '用户类型 （1 个人用户 2 律师用户 3 企业用户)',
  `user_id_to` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户打赏用户的id',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `trade_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易号 ',
  `pay_type` int(2) NULL DEFAULT 0 COMMENT '支付类型 0.微信 1.支付宝 2.充值消费',
  `deal_purpose` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '交易用途 1、充值 2、企业认证  3.打赏  4.委托悬赏 5.商品购买 6.提现  ',
  `pay_cash` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '支付金额',
  `pay_coin` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '支付充值的货币 单位元',
  `pay_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款账号',
  `headline` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易标题说明',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易记录详细描述信息',
  `status` int(2) NULL DEFAULT 0 COMMENT '支付状态  0未付款 1已付款 2付款失败 ',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deal_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户在app中所有支出收入记录日志信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_user_escrow
-- ----------------------------
DROP TABLE IF EXISTS `za_user_escrow`;
CREATE TABLE `za_user_escrow`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `escrow_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方登录账户',
  `ticket_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方登录成功票据认证id',
  `type` int(2) NULL DEFAULT 1 COMMENT '第三方登录账号类型 1 微信 2 QQ账户 3微博账户',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '绑定时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '第三方账户登录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for za_user_income_record
-- ----------------------------
DROP TABLE IF EXISTS `za_user_income_record`;
CREATE TABLE `za_user_income_record`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师用户或者企业(收益人)id',
  `deal_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易订单号id或者打赏悬赏id',
  `in_cash` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '收益货币值(充值货币金额) 单位元',
  `in_status` int(2) NULL DEFAULT 0 COMMENT '收益状态：0处理中 1已到账 2处理失败',
  `in_type` int(2) NULL DEFAULT 6 COMMENT '收益类型：4充值 5购物收益 6打赏收益 7悬赏收益 8取消悬赏',
  `in_out` int(1) NULL DEFAULT 0 COMMENT '该记录属于支出或收入 0 收入 +    1消费支出 -   2其他',
  `event_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收益信息描述',
  `user_account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '充值用户账户',
  `payee_real_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户收款人实际真实姓名',
  `income_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '收益或充值到账时间',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '律师用户收益记录明细表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_user_income_record
-- ----------------------------
INSERT INTO `za_user_income_record` VALUES ('1e5c845b176c42f9adb1275eae5a818d', 'bt5sdsdsd9083423jhdf234', '93f452dc6dbc48db825f411ee61cb3ec', 100.00, 1, 6, 0, '获得用户送的1个礼盒', NULL, NULL, '2018-01-09 15:08:18', '2018-01-09 15:08:21', '2018-01-09 15:08:21', NULL);
INSERT INTO `za_user_income_record` VALUES ('45fbbe44cd104a02b027d4f8daa1ca2e', 'bt5sdsdsd9083423jhdf234', 'bfc2d6f75cb840d5b2c17443f4eb26b4', 100.00, 1, 6, 0, '获得用户送的1个称赞', NULL, NULL, '2018-01-09 15:09:36', '2018-01-09 15:09:39', '2018-01-09 15:09:39', NULL);
INSERT INTO `za_user_income_record` VALUES ('8a60b9f2c7034e868708dadf75077a63', 'bt5sdsdsd9083423jhdf234', '315a0e37106a44918c4f32ea4f7ac387', 100.00, 1, 6, 0, '获得用户送的1个称赞', NULL, NULL, '2018-01-09 15:09:32', '2018-01-09 15:09:35', '2018-01-09 15:09:35', NULL);
INSERT INTO `za_user_income_record` VALUES ('ba249e37e361454a8be422313ffc077a', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', '21258a6d5db240b8a4d203bca66df2c5', 100.00, 1, 8, 2, '您取消了一个悬赏案件，系统返还案件打赏。', NULL, NULL, '2018-01-11 09:37:26', '2018-01-11 09:37:32', '2018-01-11 09:37:32', NULL);
INSERT INTO `za_user_income_record` VALUES ('cac2c864667e41f2a43979ba4fd865ed', 'bt5sdsdsd9083423jhdf234', '2cffef68d203409ab293f618408207c3', 100.00, 1, 6, 0, '获得用户送的1个称赞', NULL, NULL, '2018-01-09 15:09:34', '2018-01-09 15:09:37', '2018-01-09 15:09:37', NULL);
INSERT INTO `za_user_income_record` VALUES ('cdde34fd79604594ae851bc239a987e2', 'bt5sdsdsd9083423jhdf234', '8f5156e19e76464589ab72050ebe7a51', 100.00, 1, 6, 0, '获得用户送的1个称赞', NULL, NULL, '2018-01-09 15:09:48', '2018-01-09 15:09:51', '2018-01-09 15:09:51', NULL);
INSERT INTO `za_user_income_record` VALUES ('e476f1b2d0574aa091627170cc9b0817', 'bt5sdsdsd9083423jhdf234', '071d2d7ab1fd4c9395c5f12871250295', 100.00, 1, 6, 0, '获得用户送的1个称赞', NULL, NULL, '2018-01-09 15:10:59', '2018-01-09 15:11:02', '2018-01-09 15:11:02', NULL);
INSERT INTO `za_user_income_record` VALUES ('f135eafa8f4041e08d256ae476405733', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'b2b711b30c424982bc87fb813cf77908', 100.00, 1, 8, 2, '您取消了一个悬赏案件，系统返还案件打赏。', NULL, NULL, '2018-01-16 15:51:59', '2018-01-16 15:51:37', '2018-01-16 15:51:37', NULL);

-- ----------------------------
-- Table structure for za_user_lawyer
-- ----------------------------
DROP TABLE IF EXISTS `za_user_lawyer`;
CREATE TABLE `za_user_lawyer`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id或者企业com_id',
  `law_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '律师id',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户或企业邀请绑定的律师信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_user_lawyer
-- ----------------------------
INSERT INTO `za_user_lawyer` VALUES ('asdjkasdhjksdfnhjkdsf', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '2018-01-08 09:47:21', '2018-01-08 09:47:22');

-- ----------------------------
-- Table structure for za_user_profit
-- ----------------------------
DROP TABLE IF EXISTS `za_user_profit`;
CREATE TABLE `za_user_profit`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户、律师或企业id',
  `coin_consum_total` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '用户累计总消费的货币',
  `coin_surplus` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '用户剩余货币值(可充值可以提现)',
  `coin_income` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '货币收益值',
  `coin_in_total` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '货币总收益值',
  `status` int(1) NULL DEFAULT 1 COMMENT '用户的货币 1默认可以使用 0 货币冻结',
  `user_type` int(1) NULL DEFAULT 1 COMMENT '该货币所属用户类1 普通用户 2 律师 用户3企业用户',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `last_use_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近使用法币的时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户收益法币信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_user_profit
-- ----------------------------
INSERT INTO `za_user_profit` VALUES ('asdbjlasdhjdsfjhfsdjlbnjkf', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 800.00, 400.00, 0.00, 0.00, 1, 1, '2018-01-16 15:51:37', '2018-01-16 15:51:37', '2018-01-16 15:51:37', NULL);
INSERT INTO `za_user_profit` VALUES ('baff261294544ecba9227aca25c59580', 'bt5sdsdsd9083423jhdf234', 0.00, 0.00, 600.00, 600.00, 1, 2, '2018-01-09 15:11:02', '2018-01-09 15:11:02', '2018-01-09 15:11:02', NULL);

-- ----------------------------
-- Table structure for za_user_publish_case
-- ----------------------------
DROP TABLE IF EXISTS `za_user_publish_case`;
CREATE TABLE `za_user_publish_case`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布案件委托id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `law_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '该发布委托信息由哪一位律师解决',
  `award_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '悬赏记录id',
  `case_id` int(6) NULL DEFAULT NULL COMMENT '发布委托类型id',
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布委托的标题',
  `content_desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布委托案件的详细描述',
  `province` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `case_address` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发布委托案件信息物理地址位置',
  `case_lon` double(9, 6) NULL DEFAULT NULL COMMENT '案件发布地址经度',
  `case_lat` double(9, 6) NULL DEFAULT NULL,
  `geo_code` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'geohash编码',
  `status` int(2) NULL DEFAULT 0 COMMENT '该发布委托是否已经解决 默认0 付款成功未解决等待帮助中 1 正在解决中 2 案件已解决 3求助者放弃解决 4求助者删除隐藏发布委托信息 5律师放弃解决(law_id清空)',
  `type` int(2) NULL DEFAULT 1 COMMENT '发布类型：1 发布委托 2 代写文书',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `end_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '截止时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户发布的委托信息内容详情表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_user_publish_case
-- ----------------------------
INSERT INTO `za_user_publish_case` VALUES ('41dd78f549e7489b9024350d7a31746c', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '93f452dc6dbc48db825f411ee61cb3ec', 3, NULL, '这是一件债务债务违约债务违约按债务违约按案件。。。。。。。', '四川省', '成都市', '四川省成都市天府三街', 104.052938, 30.552952, 'uxvxzpzzzrup', 0, 2, '2018-01-16 15:14:20', '2018-01-16 15:14:20', '2018-08-16 15:11:12');
INSERT INTO `za_user_publish_case` VALUES ('657bc4e17a7442bd98e749c02f70cdef', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bt5sdsdsd9083423jhdf234', '21258a6d5db240b8a4d203bca66df2c5', 3, NULL, '这是一件债务债务违约债务违约按债务违约按案件。。。。。。。', '四川省', '成都市', '四川省成都市天府三街', 104.052938, 30.552952, 'uxvxzpzzzrup', 0, 1, '2018-01-16 16:07:14', '2018-01-16 16:07:14', '2018-08-16 16:07:06');
INSERT INTO `za_user_publish_case` VALUES ('asob2q99ad22akuobac7e7ya3ce3xra7', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', '', 'b2b711b30c424982bc87fb813cf77908', 2, '民事纠纷案件发布', '这个一个关于打架斗殴的民事案件，时间起源与很多年前一村庄村民之间争夺财产，双方都多次请求法院协商和解无效。。。。。', '四川省', '成都市', '四川省成都市双流区', 104.032830, 30.450175, 'uxvxuzvpuxbp', 0, 1, '2018-01-16 16:46:41', '2018-01-16 16:46:41', '2018-01-16 16:46:41');

-- ----------------------------
-- Table structure for za_user_purchase_record
-- ----------------------------
DROP TABLE IF EXISTS `za_user_purchase_record`;
CREATE TABLE `za_user_purchase_record`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `deal_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易订单号id或者打赏悬赏id',
  `pay_cash` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '消费或者提取消费金额(现金或者充值货币支付单位元)',
  `pay_type` int(2) NULL DEFAULT 0 COMMENT '消费支付类型 0.微信支付 1.支付宝支付 2.充值货币消费',
  `pay_status` int(2) NULL DEFAULT 0 COMMENT '消费支付状态 0消费付款处理中 1消费付款成功 2消费付款失败 ',
  `consum_type` int(2) NULL DEFAULT 1 COMMENT '事件类型：0购物消费 1打赏消费 2 案件悬赏消费 3提现消费',
  `in_out` int(1) NULL DEFAULT 1 COMMENT '该记录属于支出或收入 0 收入 +    1消费支出 -   2其他',
  `event_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户消费记录描述',
  `user_account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户提取消费使用账户',
  `payee_real_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户真实姓名',
  `consum_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '消费或者提取到账时间',
  `created_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '消费时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户消费(购物、打赏悬赏)交易处理记录信息列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of za_user_purchase_record
-- ----------------------------
INSERT INTO `za_user_purchase_record` VALUES ('0949da0bedf54942bbe0d77eef4ef811', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', '315a0e37106a44918c4f32ea4f7ac387', 100.00, 2, 1, 1, 1, '向张律师进行一次打赏，送了1个称赞', NULL, NULL, '2018-01-09 15:09:32', '2018-01-09 15:09:35', '2018-01-09 15:09:35', NULL);
INSERT INTO `za_user_purchase_record` VALUES ('1064f16f24ce4c879c921c5c0956bda7', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', '93f452dc6dbc48db825f411ee61cb3ec', 100.00, 2, 1, 1, 1, '向张律师进行一次打赏，送了1个礼盒', NULL, NULL, '2018-01-09 15:08:18', '2018-01-09 15:08:21', '2018-01-09 15:08:21', NULL);
INSERT INTO `za_user_purchase_record` VALUES ('1afed3b1ceb04310a9d82904fdf9b466', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', '2cffef68d203409ab293f618408207c3', 100.00, 2, 1, 1, 1, '向张律师进行一次打赏，送了1个称赞', NULL, NULL, '2018-01-09 15:09:34', '2018-01-09 15:09:37', '2018-01-09 15:09:37', NULL);
INSERT INTO `za_user_purchase_record` VALUES ('3b286b0923dc4ca79c86e8858beb63b9', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'bfc2d6f75cb840d5b2c17443f4eb26b4', 100.00, 2, 1, 1, 1, '向张律师进行一次打赏，送了1个称赞', NULL, NULL, '2018-01-09 15:09:36', '2018-01-09 15:09:39', '2018-01-09 15:09:39', NULL);
INSERT INTO `za_user_purchase_record` VALUES ('4d56685b9bbc4e6a8bbce3b40942fd99', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', 'b2b711b30c424982bc87fb813cf77908', 100.00, 2, 1, 2, 1, '发布了一个案件委托,悬赏了1个锦旗', NULL, NULL, '2018-01-09 15:11:03', '2018-01-09 15:11:06', '2018-01-09 15:11:06', NULL);
INSERT INTO `za_user_purchase_record` VALUES ('7be390f96cc24261b36be7e468751681', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', '21258a6d5db240b8a4d203bca66df2c5', 100.00, 2, 1, 2, 1, '发布了一个案件委托,悬赏了1个锦旗', NULL, NULL, '2018-01-11 09:40:57', '2018-01-11 09:40:57', '2018-01-11 09:40:57', NULL);
INSERT INTO `za_user_purchase_record` VALUES ('d38c1acfe3074b59a66b79e202e415a0', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', '071d2d7ab1fd4c9395c5f12871250295', 100.00, 2, 1, 1, 1, '向张律师进行一次打赏，送了1个称赞', NULL, NULL, '2018-01-09 15:10:59', '2018-01-09 15:11:02', '2018-01-09 15:11:02', NULL);
INSERT INTO `za_user_purchase_record` VALUES ('f0fbff77c8224790becc27e7d931367f', 'v4umc48dpqiy5gz9u919zusqnvit1mhf', '8f5156e19e76464589ab72050ebe7a51', 100.00, 2, 1, 1, 1, '向张律师进行一次打赏，送了1个称赞', NULL, NULL, '2018-01-09 15:09:48', '2018-01-09 15:09:51', '2018-01-09 15:09:51', NULL);

SET FOREIGN_KEY_CHECKS = 1;

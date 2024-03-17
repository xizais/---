/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : team_assistant

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 17/03/2024 23:54:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ci_16_20240317
-- ----------------------------
DROP TABLE IF EXISTS `ci_16_20240317`;
CREATE TABLE `ci_16_20240317`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员名称',
  `dCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0：未完成；1：已完成；2：待审批）',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  `input-3` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '123' COMMENT '输入框啦',
  `input-4` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输入框2',
  `textarea-5` varchar(1004) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本框',
  `radio-6` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单选框',
  `checkbox-7` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多选框',
  `time-8` datetime NULL DEFAULT '2024-03-17 00:00:00' COMMENT '时间选择器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集数据表-测试表数据生成' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ci_16_20240317
-- ----------------------------
INSERT INTO `ci_16_20240317` VALUES (1, '111111', '小七', '2024-03-17 23:16:45', '2024-03-17 23:16:45', '0', '0', '123', NULL, NULL, NULL, NULL, '2024-03-17 00:00:00');

-- ----------------------------
-- Table structure for fileinfo
-- ----------------------------
DROP TABLE IF EXISTS `fileinfo`;
CREATE TABLE `fileinfo`  (
  `cFICode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'uuid()' COMMENT '文件编码',
  `cFIFileName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名',
  `cFIMD5Code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'MD5编码',
  `cFIFilePath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
  `dFICreateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cFI_cPICode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `cFIDescription` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '暂无描述' COMMENT '文件描述',
  `iFIFileSize` bigint NOT NULL DEFAULT 0 COMMENT '文件大小（字节）',
  `cFIMimeType` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件类型',
  PRIMARY KEY (`cFICode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fileinfo
-- ----------------------------

-- ----------------------------
-- Table structure for infoform
-- ----------------------------
DROP TABLE IF EXISTS `infoform`;
CREATE TABLE `infoform`  (
  `iIFId` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cIF_cPICode_insert` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cIFTitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表单标题',
  `cIFPuber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表单发布者（本人、组织等）',
  `cIFTableName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建表名称',
  `cIFCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cIF_cPICode_update` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人编号',
  `cIFUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `cIFPubTime` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `cIFState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '草稿' COMMENT '表单状态（草稿、新增、发布、停止）',
  `cIFManager` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据管理员',
  `cIFPubStaff` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据发布人员',
  `cIFPubOrg` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据发布组织',
  `cIFPubFlag` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据发布对象标签',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  PRIMARY KEY (`iIFId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集表单配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of infoform
-- ----------------------------
INSERT INTO `infoform` VALUES (4, '201549221', '测试01', '小希', NULL, '2024-03-07 23:34:08', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '0');
INSERT INTO `infoform` VALUES (5, '201549221', '测试02', '小希', NULL, '2024-03-09 16:02:08', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '0');
INSERT INTO `infoform` VALUES (6, '201549221', '测试03', '小希', NULL, '2024-03-09 16:18:26', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '0');
INSERT INTO `infoform` VALUES (7, '201549221', '测试04', '小希', NULL, '2024-03-10 13:57:27', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '1');
INSERT INTO `infoform` VALUES (8, '201549221', '测试04', '小希', NULL, '2024-03-10 13:57:32', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '1');
INSERT INTO `infoform` VALUES (9, '201549221', '测试04', '小希', NULL, '2024-03-10 13:59:30', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '1');
INSERT INTO `infoform` VALUES (10, '201549221', '5', '小希', NULL, '2024-03-10 18:49:59', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '1');
INSERT INTO `infoform` VALUES (11, '201549221', '6', '小希', NULL, '2024-03-10 18:50:16', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '1');
INSERT INTO `infoform` VALUES (12, '201549221', '123', '小希', NULL, '2024-03-10 18:57:20', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '1');
INSERT INTO `infoform` VALUES (13, '201549221', '测试04', '小希', NULL, '2024-03-10 18:58:17', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '0');
INSERT INTO `infoform` VALUES (14, '201549221', '2131', '小希', NULL, '2024-03-10 18:58:29', '201549221', '2024-03-11 22:46:41', NULL, '草稿', '小希', NULL, NULL, NULL, '1');
INSERT INTO `infoform` VALUES (15, '201549221', '测试05', '希仔', NULL, '2024-03-12 22:25:24', '希仔', '2024-03-12 22:25:24', NULL, '草稿', '希仔', NULL, NULL, NULL, '0');
INSERT INTO `infoform` VALUES (16, '201549221', '测试表数据生成', '黄晓斌', 'ci_16_20240317', '2024-03-17 15:32:54', '201549221', '2024-03-17 23:16:45', '2024-03-18 00:00:00', '发布', '希仔', '小七', '', '', '0');

-- ----------------------------
-- Table structure for infoformcreate
-- ----------------------------
DROP TABLE IF EXISTS `infoformcreate`;
CREATE TABLE `infoformcreate`  (
  `iIFCId` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `iIFC_iIFId` int NOT NULL COMMENT '表单配置表主键',
  `id` int NOT NULL COMMENT '页面容器|元素主键',
  `containerId` int NULL DEFAULT 0 COMMENT '父容器ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据名称',
  `width` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '宽：百分比显示',
  `height` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '高：px显示',
  `showBorder` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '显示边框',
  `borderWidth` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '边框粗度',
  `showRadius` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '显示圆角',
  `borderRadius` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '圆角度数',
  `marginTop` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盒子顶部',
  `marginBottom` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盒子底部',
  `marginLeft` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盒子左边',
  `marginRight` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盒子右边',
  `textType` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本类型',
  `fontSize` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字体大小',
  `fontWeight` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字体粗细',
  `fontFamily` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字体类型',
  `defaultText` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '默认文本',
  `textAlign` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本位置',
  `textColor` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文字颜色',
  `placeholder` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本提示',
  `maxLength` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最大输入字数个数',
  `options` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项值（建议直接存json数组）',
  `defaultTime` datetime NULL DEFAULT NULL COMMENT '默认时间',
  `isNeed` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否必填',
  `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型（text、input、select、textarea、radio、checkbox、time）',
  `minOption` int NULL DEFAULT NULL COMMENT '最小选择',
  `maxOption` int NULL DEFAULT NULL COMMENT '最大可选',
  `showInnerBorder` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否显示内边框',
  PRIMARY KEY (`iIFCId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 157 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集表单创建信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of infoformcreate
-- ----------------------------
INSERT INTO `infoformcreate` VALUES (29, 6, 0, 0, NULL, '60', '500', 'true', '1', 'false', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (30, 6, 1, 29, NULL, '100', '30', 'false', '1', 'false', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '这是一个文本', 'left', NULL, NULL, NULL, NULL, NULL, 'false', 'text', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (31, 6, 2, 29, NULL, '200', '30', 'false', '1', 'false', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '2', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (32, 6, 3, 29, NULL, '100', '30', 'false', '1', 'false', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '选项一', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=11, content=222}]', NULL, 'false', 'select', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (33, 6, 4, 29, NULL, '300', '100', 'false', '1', 'false', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '3', 'left', 'black', '请输入内容', '100', NULL, NULL, 'false', 'textarea', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (34, 6, 5, 29, NULL, '200', '30', 'true', '1', 'false', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '22', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=10, content=22}]', NULL, 'false', 'radio', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (35, 6, 7, 29, NULL, '200', '30', 'false', '1', 'false', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '[]', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=9, content=2}]', NULL, 'false', 'checkbox', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (36, 6, 8, 29, NULL, '200', '30', 'false', '1', 'false', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-09 16:17:54', 'false', 'time', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (55, 5, 0, 0, NULL, '60', '500', 'true', '1', 'true', '4', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (56, 5, 1, 55, NULL, '650', '30', 'false', '1', 'false', '1', '20', '20', '20', '20', 'info', '20', '900', 'Kaiti', '这是一个文本', 'center', NULL, NULL, NULL, NULL, NULL, 'false', 'text', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (57, 5, 2, 55, NULL, '100', '30', 'false', '1', 'false', '1', '10', '10', '10', '', 'none', '14', '400', 'Microsoft YaHei', '这是一个文本', 'left', NULL, NULL, NULL, NULL, NULL, 'false', 'text', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (58, 5, 3, 55, NULL, '200', '30', 'false', '1', 'false', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (59, 5, 0, 55, NULL, '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '选项一', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=1, content=2}]', NULL, 'false', 'select', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (60, 5, 4, 55, NULL, '200', '100', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '100', NULL, NULL, 'false', 'textarea', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (61, 5, 10, 55, NULL, '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'false', 'time', NULL, NULL, NULL);
INSERT INTO `infoformcreate` VALUES (87, 7, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (88, 8, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (89, 9, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (90, 9, 1, 89, NULL, '400', '30', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '[]', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=2, content=2}, {id=3, content=3}, {id=4, content=4}]', NULL, 'false', 'checkbox', 1, 3, 'true');
INSERT INTO `infoformcreate` VALUES (119, 4, 0, 0, NULL, '60', '500', 'false', '1', 'true', '1', '20', '20', '20', '20', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (120, 4, 1, 119, NULL, '100', '30', 'true', '1', 'true', '1', '20', '20', '20', '20', NULL, NULL, NULL, NULL, '选项一', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=3, content=2}, {id=4, content=3}]', NULL, 'true', 'select', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (121, 4, 2, 119, '文本框哪', '300', '100', 'true', '1', 'true', '1', '20', '20', '20', '20', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '100', NULL, NULL, 'true', 'textarea', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (122, 4, 3, 119, NULL, '100', '30', 'true', '1', 'true', '1', '20', '20', '20', '20', 'none', '14', '400', 'Microsoft YaHei', '这是一个文本', 'left', NULL, NULL, NULL, NULL, NULL, 'false', 'text', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (123, 4, 4, 119, NULL, '200', '30', 'true', '1', 'true', '1', '20', '20', '20', '20', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'true', 'input', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (124, 4, 5, 119, NULL, '200', '30', 'true', '1', 'true', '1', '20', '20', '20', '20', NULL, NULL, NULL, NULL, '选项一', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}]', NULL, 'true', 'radio', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (125, 4, 6, 119, NULL, '200', '30', 'true', '1', 'true', '1', '20', '20', '20', '20', NULL, NULL, NULL, NULL, '[]', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}]', NULL, 'true', 'checkbox', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (126, 4, 7, 119, NULL, '200', '30', 'false', '1', 'false', '10', '20', '20', '20', '20', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'false', 'time', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (127, 4, 8, 119, NULL, '200', '30', 'false', '1', 'true', '1', '20', '20', '20', '20', NULL, NULL, NULL, NULL, '[]', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=9, content=2}, {id=10, content=3}, {id=11, content=4}]', NULL, 'false', 'checkbox', 0, 3, 'false');
INSERT INTO `infoformcreate` VALUES (128, 10, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (129, 11, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (130, 11, 1, 129, '', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (131, 12, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (132, 13, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (133, 13, 14, 132, '', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (134, 13, 15, 132, '', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (135, 13, 16, 132, '', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (136, 13, 17, 132, '', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'false', 'time', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (137, 13, 18, 132, '', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'false', 'time', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (138, 13, 19, 132, '', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'false', 'time', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (139, 14, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (140, 15, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (141, 15, 1, 140, '', '700', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '20', '400', 'Kaiti', '测试05', 'center', NULL, NULL, NULL, NULL, NULL, NULL, 'text', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (142, 15, 2, 140, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '姓名：', 'right', NULL, NULL, NULL, NULL, NULL, NULL, 'text', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (143, 15, 3, 140, '姓名', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (144, 15, 4, 140, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '学号：', 'right', NULL, NULL, NULL, NULL, NULL, NULL, 'text', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (145, 15, 5, 140, '学号', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (149, 16, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (150, 16, 2, 149, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '这是一个文本', 'left', NULL, NULL, NULL, NULL, NULL, 'false', 'text', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (151, 16, 3, 149, '输入框啦', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '123', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (152, 16, 4, 149, '输入框2', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (153, 16, 5, 149, '文本框', '300', '100', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '100', NULL, NULL, 'false', 'textarea', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (154, 16, 6, 149, '单选框', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '选项一', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}]', NULL, 'false', 'radio', 0, 0, 'true');
INSERT INTO `infoformcreate` VALUES (155, 16, 7, 149, '多选框', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '[]', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}]', NULL, 'false', 'checkbox', 1, 1, 'true');
INSERT INTO `infoformcreate` VALUES (156, 16, 8, 149, '时间选择器', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-17 00:00:00', 'false', 'time', 0, 0, NULL);

-- ----------------------------
-- Table structure for organizationinfo
-- ----------------------------
DROP TABLE IF EXISTS `organizationinfo`;
CREATE TABLE `organizationinfo`  (
  `cOICode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组织ID（显示层级关系）',
  `cOIOrgCode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组织编码（用户定义）',
  `cOIOrgName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组织名称',
  `cOIOrgDescription` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组织描述',
  `cOIOrgType` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '通用' COMMENT '组织性质',
  `bOIIsPublicVisible` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否公示（是否只有本组织的人才可见）',
  `iOISubLevelSequence` int NULL DEFAULT 0 COMMENT '组织子级序号（每增加一个子级+1）',
  `dOICreationTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cOI_cPICode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人编码',
  `cOIManagerCode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '负责人编码（负责人有全权限，最多5个）',
  `bOIIsUserVerified` bit(1) NOT NULL DEFAULT b'0' COMMENT '用户是否经审核加入',
  `cOIOrgManagementPerm` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'uuid()' COMMENT '管理组织权限',
  `cOIAddUserPerm` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'uuid()' COMMENT '添加(审核)用户权限',
  `cOIDeleteUserPerm` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'uuid()' COMMENT '删除用户权限',
  `cOIAssignPermPerm` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'uuid()' COMMENT '分配自己权限的权限',
  `cOIManageFilePerm` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'uuid()' COMMENT '管理文件权限',
  `bOIIsPublicOrgFile` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否公开组织文件',
  PRIMARY KEY (`cOICode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '组织信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of organizationinfo
-- ----------------------------
INSERT INTO `organizationinfo` VALUES ('1-1-1-1', '2015492', '软件工程2班', NULL, '通用', b'0', 0, '2024-03-17 21:18:24', '201549221', '201549221', b'0', 'uuid()', 'uuid()', 'uuid()', 'uuid()', 'uuid()', b'0');

-- ----------------------------
-- Table structure for orgpersonlink
-- ----------------------------
DROP TABLE IF EXISTS `orgpersonlink`;
CREATE TABLE `orgpersonlink`  (
  `cOPL_cOICode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组织ID',
  `cOPL_cPICode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人员编码',
  `dOPLCreationTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cOPLCreatedBy` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `cOPLPermissionSet` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限集合',
  `iOPLPersonStatus` int NOT NULL DEFAULT 0 COMMENT '人员状态',
  PRIMARY KEY (`cOPL_cOICode`, `cOPL_cPICode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '组织-人员关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orgpersonlink
-- ----------------------------
INSERT INTO `orgpersonlink` VALUES ('2015492', '201549221', '2024-03-17 21:19:07', '201549221', NULL, 1);

-- ----------------------------
-- Table structure for orgpersontag
-- ----------------------------
DROP TABLE IF EXISTS `orgpersontag`;
CREATE TABLE `orgpersontag`  (
  `cOPTTagId` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签ID',
  `cOPTTagName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '未定义标签' COMMENT '标签名称',
  `cOPT_cPICode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人编码',
  `dOPTCreationTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cOPTIncludedOrgId` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '包含的组织ID',
  `cOPTIncludedPersonCode` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '包含的人员编号',
  `bOPTIsEnabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
  `bOPTIsSystemShared` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否系统公用',
  `bOPTIsOrgShared` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否组织公用',
  PRIMARY KEY (`cOPTTagId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '组织-人员标签表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orgpersontag
-- ----------------------------
INSERT INTO `orgpersontag` VALUES ('1', '测试01', '201549221', '2024-03-17 21:21:28', '2015492', '111111', b'1', b'0', b'0');

-- ----------------------------
-- Table structure for personinfo
-- ----------------------------
DROP TABLE IF EXISTS `personinfo`;
CREATE TABLE `personinfo`  (
  `cPICode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人员编码',
  `cPIName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人员名称',
  `cPIGender` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '男' COMMENT '性别',
  `dPIBirthDate` date NULL DEFAULT NULL COMMENT '出生年月',
  `cPIHometown` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '户籍地',
  `cPIContactNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `cPIEmail` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `cPIIDType` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证类型',
  `cPIIDNumber` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `cPIIdentity` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '学生' COMMENT '身份',
  `cPI_cOICode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属组织代号',
  `dPICreateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cPIPassword` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '加密后的123456' COMMENT '密码',
  `bPIIsEnabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
  PRIMARY KEY (`cPICode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人员信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of personinfo
-- ----------------------------
INSERT INTO `personinfo` VALUES ('111111', '小七', '男', NULL, '', '13829377437', '', '', '', '学生', '1-1-1-2', '2024-03-11 20:31:19', '158520161', b'1');
INSERT INTO `personinfo` VALUES ('201549221', '希仔', '男', NULL, '', '13829377437', '', '', '', '学生', '1-1-1-2', '2024-03-11 20:31:19', '158520161', b'1');

-- ----------------------------
-- Table structure for pubconfig
-- ----------------------------
DROP TABLE IF EXISTS `pubconfig`;
CREATE TABLE `pubconfig`  (
  `iPCId` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `iTypeId` int NOT NULL COMMENT '关联表ID',
  `cType` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关联表类型',
  `dPubStartTime` datetime NULL DEFAULT NULL COMMENT '发布开始时间',
  `dPubEndTime` datetime NULL DEFAULT NULL COMMENT '发布停止时间',
  `cIsApproval` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '是' COMMENT '是否进行审核',
  `cIsOrgManger` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '是' COMMENT '是否启用组织管理',
  `cPuber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布者',
  `cPubToPerson` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布人员',
  `cPubToOrg` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布组织',
  `cPubToFlag` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布标识',
  `cPubToPersonCode` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布人员code',
  `cPubToOrgCode` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布组织code',
  `cPubToFlagCode` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发布标识（标签ID）',
  `dCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cCreator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `cUpdater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  PRIMARY KEY (`iPCId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '发布配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pubconfig
-- ----------------------------
INSERT INTO `pubconfig` VALUES (2, 4, 'CollectInfo', '2024-03-16 00:00:00', '2024-03-16 00:00:00', 'true', 'false', '123', '小七', '小七, 希仔', '希仔', '', '111111, 201549221', '201549221', '2024-03-16 19:52:27', '2024-03-17 13:21:05', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (8, 5, 'CollectInfo', '2024-03-16 00:00:00', '2024-03-16 00:00:00', 'false', 'true', '123', '小七', '', '', '[]', '[]', '[]', '2024-03-16 20:11:37', '2024-03-17 13:26:56', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (9, 16, 'CollectInfo', '2024-03-18 00:00:00', '2024-03-24 00:00:00', 'false', 'true', '黄晓斌', '小七,  希仔', '', '', '111111,  201549221', '', '', '2024-03-17 15:33:25', '2024-03-17 23:25:31', '希仔', NULL, '0');

-- ----------------------------
-- Table structure for ruleinformation
-- ----------------------------
DROP TABLE IF EXISTS `ruleinformation`;
CREATE TABLE `ruleinformation`  (
  `cRIRuleId` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规则ID',
  `cRIRule` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规则',
  `cRIRuleCategory` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规则类别',
  `iRICurrentSequence` int NULL DEFAULT 1 COMMENT '当前序号',
  PRIMARY KEY (`cRIRuleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ruleinformation
-- ----------------------------

-- ----------------------------
-- Table structure for worktask
-- ----------------------------
DROP TABLE IF EXISTS `worktask`;
CREATE TABLE `worktask`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作类型',
  `typeId` int NULL DEFAULT NULL COMMENT '工作ID',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编码',
  `updateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据更新时间',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '草稿' COMMENT '状态',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '工作待办表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of worktask
-- ----------------------------
INSERT INTO `worktask` VALUES (1, 'CollectInfo', 16, '111111', '2024-03-17 22:39:20', '草稿', '1');
INSERT INTO `worktask` VALUES (2, 'CollectInfo', 16, '201549221', '2024-03-17 22:39:20', '草稿', '1');
INSERT INTO `worktask` VALUES (3, 'CollectInfo', 16, '111111', '2024-03-17 22:51:20', '草稿', '1');
INSERT INTO `worktask` VALUES (4, 'CollectInfo', 16, '201549221', '2024-03-17 22:51:20', '草稿', '1');
INSERT INTO `worktask` VALUES (5, 'CollectInfo', 16, '111111', '2024-03-17 23:15:07', '草稿', '0');
INSERT INTO `worktask` VALUES (6, 'CollectInfo', 16, '111111', '2024-03-17 23:16:46', '草稿', '0');

SET FOREIGN_KEY_CHECKS = 1;

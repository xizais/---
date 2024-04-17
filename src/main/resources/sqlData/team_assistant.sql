/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : team_assistant

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 15/04/2024 18:06:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for areabook
-- ----------------------------
DROP TABLE IF EXISTS `areabook`;
CREATE TABLE `areabook`  (
  `iABId` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `iAB_iAIId` int NOT NULL COMMENT '场地表ID',
  `cABBookerCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预约人员',
  `dABBookStartTime` datetime NOT NULL COMMENT '预约开始时间',
  `dABBookEndTime` datetime NOT NULL COMMENT '预约结束时间',
  `cABContent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请内容（255字以内）',
  `cABState` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '已通过' COMMENT '申请状态（草稿、待审批、未通过、已通过）',
  `dABCreateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dABUpdateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  PRIMARY KEY (`iABId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of areabook
-- ----------------------------
INSERT INTO `areabook` VALUES (1, 1, '希仔', '2024-03-29 00:00:00', '2024-03-30 00:00:00', NULL, '待审批', '2024-03-29 09:54:14', '2024-03-29 09:54:14', '0');

-- ----------------------------
-- Table structure for areainfo
-- ----------------------------
DROP TABLE IF EXISTS `areainfo`;
CREATE TABLE `areainfo`  (
  `iAIId` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `cAIManagerCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理人编号',
  `cAIManagerName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理人名称',
  `cAIManagerPhone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理人联系方式',
  `cAIName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '场地名称',
  `cAIContent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '场地地点',
  `bAIIsEnable` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用场地',
  `bAIIsApprove` bit(1) NOT NULL DEFAULT b'0' COMMENT '预约是否需要审批',
  `dAICreateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dAIUpdateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cAIHandleCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '处理人ID',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  PRIMARY KEY (`iAIId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of areainfo
-- ----------------------------
INSERT INTO `areainfo` VALUES (1, NULL, '138123456789', '138123456789', '场地名称测试', '场地地点测试', b'1', b'1', '2024-03-29 09:52:46', '2024-03-29 09:53:28', '201549221', '0');

-- ----------------------------
-- Table structure for ci_15_20240326
-- ----------------------------
DROP TABLE IF EXISTS `ci_15_20240326`;
CREATE TABLE `ci_15_20240326`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员名称',
  `org` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `dCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0：未完成；1：已完成；2：待审批）',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  `input-3` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `input-5` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '12' COMMENT '学号',
  `select-6` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项',
  `radio-7` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单选',
  `checkbox-8` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多选',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集数据表-测试05' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ci_15_20240326
-- ----------------------------
INSERT INTO `ci_15_20240326` VALUES (1, '111111', '小七', NULL, '2024-03-26 14:32:15', '2024-03-26 14:32:15', '0', '0', NULL, '12', NULL, NULL, NULL);
INSERT INTO `ci_15_20240326` VALUES (2, '201549221', '希仔', NULL, '2024-03-26 14:32:15', '2024-03-26 14:32:15', '0', '0', NULL, '12', NULL, NULL, NULL);

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
  `org` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `cState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0：未完成；1：已完成；2：待审批）',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  `input-3` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '123' COMMENT '输入框啦',
  `input-4` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输入框2',
  `textarea-5` varchar(1004) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本框',
  `radio-6` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单选框',
  `checkbox-7` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多选框',
  `time-8` datetime NULL DEFAULT '2024-03-17 00:00:00' COMMENT '时间选择器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集数据表-测试表数据生成' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ci_16_20240317
-- ----------------------------
INSERT INTO `ci_16_20240317` VALUES (1, '111111', '小七', '2024-03-17 23:16:45', '2024-03-25 12:33:50', '20软工二班', '0', '0', '123', NULL, NULL, NULL, NULL, '2024-03-17 00:00:00');
INSERT INTO `ci_16_20240317` VALUES (3, '201549221', '希仔', '2024-03-25 15:27:38', '2024-03-25 15:48:37', '暂定', '1', '1', '123', NULL, NULL, NULL, NULL, '2024-03-17 00:00:00');
INSERT INTO `ci_16_20240317` VALUES (4, '201549221', '希仔', '2024-03-25 15:29:19', '2024-03-25 15:29:29', '暂定', '0', '1', '123', NULL, NULL, NULL, NULL, '2024-03-17 00:00:00');
INSERT INTO `ci_16_20240317` VALUES (5, '201549221', '希仔', '2024-03-25 15:30:36', '2024-03-25 15:30:59', '暂定', '0', '1', '123', NULL, NULL, NULL, NULL, '2024-03-17 00:00:00');
INSERT INTO `ci_16_20240317` VALUES (6, '201549221', '希仔', '2024-03-25 15:31:02', '2024-03-25 15:31:27', '暂定', '0', '1', '123', NULL, NULL, NULL, NULL, '2024-03-17 00:00:00');
INSERT INTO `ci_16_20240317` VALUES (7, '201549221', '希仔', '2024-03-25 15:31:31', '2024-03-25 15:32:42', '暂定', '0', '1', '123', NULL, NULL, NULL, NULL, '2024-03-17 00:00:00');
INSERT INTO `ci_16_20240317` VALUES (8, '201549221', '希仔', '2024-03-25 15:32:47', '2024-03-25 15:34:13', '暂定', '0', '1', '123', NULL, NULL, NULL, NULL, '2024-03-17 00:00:00');
INSERT INTO `ci_16_20240317` VALUES (9, '201549221', '希仔', '2024-03-25 15:34:20', '2024-03-25 16:51:12', '暂定', '1', '0', '123', '4444', NULL, NULL, NULL, '2024-03-17 00:00:00');

-- ----------------------------
-- Table structure for ci_17_20240325
-- ----------------------------
DROP TABLE IF EXISTS `ci_17_20240325`;
CREATE TABLE `ci_17_20240325`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员名称',
  `org` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `dCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0：未完成；1：已完成；2：待审批）',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  `input-2` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '123' COMMENT '输入框',
  `select-3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '下拉框',
  `textarea-4` varchar(1004) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本域',
  `radio-5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单选框',
  `checkbox-6` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多选框',
  `time-7` datetime NULL DEFAULT NULL COMMENT '时间器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集数据表-测试表单数据生成、填写与审批' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ci_17_20240325
-- ----------------------------
INSERT INTO `ci_17_20240325` VALUES (1, '111111', '小七', NULL, '2024-03-25 17:07:27', '2024-03-25 17:07:27', '0', '0', '123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ci_17_20240325` VALUES (2, '201549221', '希仔', NULL, '2024-03-25 17:07:27', '2024-03-25 22:23:49', '1', '0', '123', '选项一,3', '文本框内容', '选项2', '2,3', '2024-03-26 17:19:44');

-- ----------------------------
-- Table structure for ci_18_20240326
-- ----------------------------
DROP TABLE IF EXISTS `ci_18_20240326`;
CREATE TABLE `ci_18_20240326`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员名称',
  `org` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `dCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0：未完成；1：已完成；2：待审批）',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  `input-2` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输入',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集数据表-测试工作信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ci_18_20240326
-- ----------------------------
INSERT INTO `ci_18_20240326` VALUES (1, '111111', '小七', NULL, '2024-03-26 09:39:16', '2024-03-26 09:39:16', '0', '0', NULL);
INSERT INTO `ci_18_20240326` VALUES (2, '201549221', '希仔', NULL, '2024-03-26 09:39:16', '2024-03-26 09:57:22', '0', '0', '111');

-- ----------------------------
-- Table structure for ci_19_20240326
-- ----------------------------
DROP TABLE IF EXISTS `ci_19_20240326`;
CREATE TABLE `ci_19_20240326`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员名称',
  `org` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `dCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0：未完成；1：已完成；2：待审批）',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  `input-2` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输入框1',
  `select-3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '下拉框1',
  `textarea-5` varchar(1004) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文本域三',
  `radio-6` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单选框1',
  `checkbox-8` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '多选框1',
  `time-11` datetime NULL DEFAULT NULL COMMENT '时间器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集数据表-测试表单填写' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ci_19_20240326
-- ----------------------------
INSERT INTO `ci_19_20240326` VALUES (1, '111111', '小七', NULL, '2024-03-26 11:50:34', '2024-03-26 11:50:34', '0', '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ci_19_20240326` VALUES (2, '201549221', '希仔', NULL, '2024-03-26 11:50:34', '2024-03-26 13:29:45', '0', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ci_19_20240326` VALUES (3, '201549221', '希仔', 'null', '2024-03-26 13:23:51', '2024-03-26 13:40:41', '2', '1', '1111111111', '选项二', '2222222222', '选项二', '选项一, 选项三', NULL);
INSERT INTO `ci_19_20240326` VALUES (4, '201549221', '希仔', 'null', '2024-03-26 13:29:45', '2024-03-26 13:55:05', '0', '1', '1111111111', '选项二', '2222222222', '选项二', '选项一, 选项三', '2024-03-26 12:15:52');
INSERT INTO `ci_19_20240326` VALUES (5, '201549221', '希仔', 'null', '2024-03-26 13:55:05', '2024-03-26 13:55:11', '1', '0', '1111111111', '选项二', '2222222222', '选项二', '选项一,  选项三', '2024-03-26 12:15:52');

-- ----------------------------
-- Table structure for ci_20_20240326
-- ----------------------------
DROP TABLE IF EXISTS `ci_20_20240326`;
CREATE TABLE `ci_20_20240326`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员名称',
  `org` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `dCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态（0：未完成；1：已完成；2：待审批）',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  `input-2` varchar(104) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '输入框',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集数据表-测试数据显示' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ci_20_20240326
-- ----------------------------
INSERT INTO `ci_20_20240326` VALUES (1, '111111', '小七', NULL, '2024-03-26 15:10:25', '2024-03-26 15:10:25', '0', '0', NULL);
INSERT INTO `ci_20_20240326` VALUES (2, '201549221', '希仔', NULL, '2024-03-26 15:10:25', '2024-03-26 15:10:25', '0', '0', NULL);

-- ----------------------------
-- Table structure for fileinfo
-- ----------------------------
DROP TABLE IF EXISTS `fileinfo`;
CREATE TABLE `fileinfo`  (
  `iFICode` int NOT NULL AUTO_INCREMENT COMMENT '文件编码',
  `cFIFileName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件名',
  `cFIMD5Code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'MD5编码',
  `cFIFilePath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
  `dFICreateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cFI_cPICode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `cFIDescription` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '暂无描述' COMMENT '文件描述',
  `iFIFileSize` bigint NOT NULL DEFAULT 0 COMMENT '文件大小（字节）',
  `cFIMimeType` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件类型',
  PRIMARY KEY (`iFICode`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fileinfo
-- ----------------------------
INSERT INTO `fileinfo` VALUES (17, '201549221黄晓斌互联网学院毕业论文-初稿.docx', 'afd93ee8dfba5af8d698f147dc07a1b2', 'notify\\希仔\\afd93ee8dfba5af8d698f147dc07a1b2.docx', '2024-04-12 20:15:05', '201549221', '暂无描述', 2642721, 'docx');
INSERT INTO `fileinfo` VALUES (18, '201549221黄晓斌互联网学院毕业论文-改1.docx', 'be5145daec920dedcb5d306e264af506', 'notify\\希仔\\be5145daec920dedcb5d306e264af506.docx', '2024-04-12 20:15:30', '201549221', '暂无描述', 2646620, 'docx');
INSERT INTO `fileinfo` VALUES (19, '201549221黄晓斌论文指导记录.docx', 'e14e7442e10aea43e5d0cb6b336f2e0e', 'notify\\15\\希仔\\e14e7442e10aea43e5d0cb6b336f2e0e.docx', '2024-04-12 20:22:41', '201549221', '暂无描述', 16191, 'docx');
INSERT INTO `fileinfo` VALUES (20, '201549221黄晓斌互联网学院毕业论文-修复一.docx', '8b13d2d36ac031150904ae42178ac8ee', 'notify\\15\\希仔\\8b13d2d36ac031150904ae42178ac8ee.docx', '2024-04-12 20:22:44', '201549221', '暂无描述', 2647703, 'docx');
INSERT INTO `fileinfo` VALUES (21, '201549221黄晓斌论文指导记录.docx', 'e14e7442e10aea43e5d0cb6b336f2e0e', 'notify\\16\\希仔\\e14e7442e10aea43e5d0cb6b336f2e0e.docx', '2024-04-12 20:28:55', '201549221', '暂无描述', 16191, 'docx');
INSERT INTO `fileinfo` VALUES (22, '201549221黄晓斌互联网学院毕业论文-修复一.docx', '8b13d2d36ac031150904ae42178ac8ee', 'notify\\16\\希仔\\8b13d2d36ac031150904ae42178ac8ee.docx', '2024-04-12 20:28:59', '201549221', '暂无描述', 2647703, 'docx');
INSERT INTO `fileinfo` VALUES (23, '201549221黄晓斌互联网学院毕业论文-修复一.docx', '8b13d2d36ac031150904ae42178ac8ee', 'notify\\17\\8b13d2d36ac031150904ae42178ac8ee.docx', '2024-04-12 20:29:57', '201549221', '暂无描述', 2647703, 'docx');
INSERT INTO `fileinfo` VALUES (24, '201549221黄晓斌论文指导记录.docx', 'e14e7442e10aea43e5d0cb6b336f2e0e', 'notify\\17\\e14e7442e10aea43e5d0cb6b336f2e0e.docx', '2024-04-12 20:30:00', '201549221', '暂无描述', 16191, 'docx');
INSERT INTO `fileinfo` VALUES (25, '201549221黄晓斌论文指导记录.docx', 'e14e7442e10aea43e5d0cb6b336f2e0e', 'notify\\18\\e14e7442e10aea43e5d0cb6b336f2e0e.docx', '2024-04-12 20:33:33', '201549221', '暂无描述', 16191, 'docx');
INSERT INTO `fileinfo` VALUES (26, '201549221黄晓斌论文指导记录.docx', 'e14e7442e10aea43e5d0cb6b336f2e0e', 'notify\\希仔\\e14e7442e10aea43e5d0cb6b336f2e0e.docx', '2024-04-12 23:32:11', '201549221', '暂无描述', 16191, 'docx');
INSERT INTO `fileinfo` VALUES (27, '201549221黄晓斌论文指导记录.docx', 'e14e7442e10aea43e5d0cb6b336f2e0e', 'task\\17\\e14e7442e10aea43e5d0cb6b336f2e0e.docx', '2024-04-12 23:34:04', '201549221', '暂无描述', 16191, 'docx');
INSERT INTO `fileinfo` VALUES (28, '201549221黄晓斌互联网学院毕业论文-初稿.docx', 'afd93ee8dfba5af8d698f147dc07a1b2', 'task\\15\\afd93ee8dfba5af8d698f147dc07a1b2.docx', '2024-04-13 13:28:52', '201549221', '暂无描述', 2642721, 'docx');
INSERT INTO `fileinfo` VALUES (29, '201549221黄晓斌互联网学院毕业论文-修复一.docx', '8b13d2d36ac031150904ae42178ac8ee', 'task\\17\\8b13d2d36ac031150904ae42178ac8ee.docx', '2024-04-13 13:31:36', '201549221', '暂无描述', 2647703, 'docx');
INSERT INTO `fileinfo` VALUES (30, '201549221黄晓斌互联网学院毕业论文-初稿.docx', 'afd93ee8dfba5af8d698f147dc07a1b2', 'task\\17\\afd93ee8dfba5af8d698f147dc07a1b2.docx', '2024-04-13 19:51:19', '201549221', '暂无描述', 2642721, 'docx');
INSERT INTO `fileinfo` VALUES (31, '201549221黄晓斌论文指导记录.docx', 'e14e7442e10aea43e5d0cb6b336f2e0e', 'doTask\\希仔\\e14e7442e10aea43e5d0cb6b336f2e0e.docx', '2024-04-14 17:18:30', '201549221', '暂无描述', 16191, 'docx');

-- ----------------------------
-- Table structure for filemanager
-- ----------------------------
DROP TABLE IF EXISTS `filemanager`;
CREATE TABLE `filemanager`  (
  `iFMId` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `iFM_iFICode` int NULL DEFAULT NULL COMMENT '文件信息表ID',
  `cFMType` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件所属类型，例如：任务、通知、组织文件',
  `cFMTypeId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件所属类型表ID',
  `cFMManagerCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件所属人员code',
  `dFMHandleTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '处理时间，默认为当前修改时间',
  `cFMHandleCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理人员code',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除），默认为0',
  PRIMARY KEY (`iFMId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件管理表，用于存储文件相关信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of filemanager
-- ----------------------------
INSERT INTO `filemanager` VALUES (51, 17, 'notify', NULL, '201549221', '2024-04-12 20:15:06', '201549221', '0');
INSERT INTO `filemanager` VALUES (52, 18, 'notify', NULL, '201549221', '2024-04-12 20:15:30', '201549221', '0');
INSERT INTO `filemanager` VALUES (53, 19, 'notify', '15', '201549221', '2024-04-12 20:24:44', '201549221', '0');
INSERT INTO `filemanager` VALUES (54, 20, 'notify', '15', '201549221', '2024-04-12 20:24:44', '201549221', '0');
INSERT INTO `filemanager` VALUES (55, 21, 'notify', '16', '201549221', '2024-04-12 20:29:01', '201549221', '0');
INSERT INTO `filemanager` VALUES (56, 22, 'notify', '16', '201549221', '2024-04-12 20:29:01', '201549221', '0');
INSERT INTO `filemanager` VALUES (57, 23, 'notify', '17', '201549221', '2024-04-12 20:31:13', '201549221', '0');
INSERT INTO `filemanager` VALUES (58, 24, 'notify', '17', '201549221', '2024-04-12 20:31:13', '201549221', '0');
INSERT INTO `filemanager` VALUES (59, 25, 'notify', '18', '201549221', '2024-04-12 20:33:35', '201549221', '0');
INSERT INTO `filemanager` VALUES (60, 26, 'notify', NULL, '201549221', '2024-04-12 23:32:11', '201549221', '1');
INSERT INTO `filemanager` VALUES (61, 27, 'task', NULL, '201549221', '2024-04-12 23:34:04', '201549221', '0');
INSERT INTO `filemanager` VALUES (62, 28, 'task', '15', '201549221', '2024-04-13 13:29:02', '201549221', '1');
INSERT INTO `filemanager` VALUES (63, 29, 'task', NULL, '201549221', '2024-04-13 13:31:36', '201549221', '0');
INSERT INTO `filemanager` VALUES (64, 30, 'task', NULL, '201549221', '2024-04-13 19:51:19', '201549221', '1');
INSERT INTO `filemanager` VALUES (65, 27, 'task', '17', '201549221', '2024-04-13 19:57:41', '201549221', '0');
INSERT INTO `filemanager` VALUES (66, 29, 'task', '17', '201549221', '2024-04-13 19:57:41', '201549221', '0');
INSERT INTO `filemanager` VALUES (67, 30, 'task', '17', '201549221', '2024-04-13 19:57:41', '201549221', '0');
INSERT INTO `filemanager` VALUES (68, 31, 'doTask', NULL, '201549221', '2024-04-14 17:18:31', '201549221', '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集表单配置表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `infoform` VALUES (15, '201549221', '测试05', '测试数据显示', 'ci_15_20240326', '2024-03-12 22:25:24', '201549221', '2024-03-26 14:32:15', '2024-03-27 00:00:00', '发布', '希仔', '小七,  希仔', '', '', '0');
INSERT INTO `infoform` VALUES (16, '201549221', '测试表数据生成', '黄晓斌', 'ci_16_20240317', '2024-03-17 15:32:54', '201549221', '2024-03-25 15:47:59', '2024-03-18 00:00:00', '发布', '希仔', '小七', '', '', '0');
INSERT INTO `infoform` VALUES (17, '201549221', '测试表单数据生成、填写与审批', '黄晓斌', 'ci_17_20240325', '2024-03-25 17:05:35', '201549221', '2024-03-25 17:07:28', '2024-03-26 00:00:00', '发布', '希仔', '小七, 希仔', '', '', '0');
INSERT INTO `infoform` VALUES (18, '201549221', '测试工作信息', '黄晓斌', 'ci_18_20240326', '2024-03-26 09:38:31', '201549221', '2024-03-26 09:39:16', '2024-03-27 00:00:00', '发布', '希仔', '小七, 希仔', '', '', '0');
INSERT INTO `infoform` VALUES (19, '201549221', '测试表单填写', '测试发布呐', 'ci_19_20240326', '2024-03-26 11:49:07', '201549221', '2024-03-26 11:50:35', '2024-03-27 00:00:00', '发布', '希仔', '小七, 希仔', '', '', '0');
INSERT INTO `infoform` VALUES (20, '201549221', '测试数据显示', '黄晓斌', 'ci_20_20240326', '2024-03-26 15:09:33', '201549221', '2024-03-26 15:10:26', '2024-03-27 00:00:00', '发布', '希仔', '小七,  希仔', '', '', '0');
INSERT INTO `infoform` VALUES (21, '201549221', '表单创建测试', '希仔', NULL, '2024-03-29 09:51:46', '希仔', '2024-03-29 09:51:46', NULL, '草稿', '希仔', NULL, NULL, NULL, '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 207 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '信息收集表单创建信息表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `infoformcreate` VALUES (149, 16, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (150, 16, 2, 149, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '这是一个文本', 'left', NULL, NULL, NULL, NULL, NULL, 'false', 'text', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (151, 16, 3, 149, '输入框啦', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '123', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (152, 16, 4, 149, '输入框2', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (153, 16, 5, 149, '文本框', '300', '100', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '100', NULL, NULL, 'false', 'textarea', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (154, 16, 6, 149, '单选框', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '选项一', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}]', NULL, 'false', 'radio', 0, 0, 'true');
INSERT INTO `infoformcreate` VALUES (155, 16, 7, 149, '多选框', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '[]', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}]', NULL, 'false', 'checkbox', 1, 1, 'true');
INSERT INTO `infoformcreate` VALUES (156, 16, 8, 149, '时间选择器', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-03-17 00:00:00', 'false', 'time', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (157, 17, 0, 0, NULL, '800', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (158, 17, 1, 157, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '这是一个文本', 'left', NULL, NULL, NULL, NULL, NULL, NULL, 'text', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (159, 17, 2, 157, '输入框', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '123', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (160, 17, 3, 157, '下拉框', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '选项2', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=8, content=选项2}]', NULL, 'false', 'select', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (161, 17, 4, 157, '文本域', '300', '100', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '文本域', 'left', 'black', '请输入内容', '100', NULL, NULL, 'false', 'textarea', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (162, 17, 5, 157, '单选框', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '选项2', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=9, content=选项2}]', NULL, 'false', 'radio', 0, 0, 'true');
INSERT INTO `infoformcreate` VALUES (163, 17, 6, 157, '多选框', '200', '30', 'false', '1', 'true', '1', '100', '10', '10', '10', NULL, NULL, NULL, NULL, '[选项一]', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=10, content=2}, {id=11, content=3}]', NULL, 'false', 'checkbox', 1, 2, 'true');
INSERT INTO `infoformcreate` VALUES (164, 17, 7, 157, '时间器', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'false', 'time', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (180, 15, 0, 0, NULL, '60', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (181, 15, 1, 180, '', '700', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '20', '400', 'Kaiti', '测试05', 'center', NULL, NULL, NULL, NULL, NULL, 'false', 'text', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (182, 15, 2, 180, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '姓名：', 'right', NULL, NULL, NULL, NULL, NULL, 'false', 'text', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (183, 15, 3, 180, '姓名', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (184, 15, 4, 180, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '学号：', 'right', NULL, NULL, NULL, NULL, NULL, 'false', 'text', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (185, 15, 5, 180, '学号', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '12', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (186, 15, 6, 180, '选项', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '2222', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=7, content=2222}]', NULL, 'false', 'select', 0, 0, 'false');
INSERT INTO `infoformcreate` VALUES (187, 15, 7, 180, '单选', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '222', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=8, content=222}]', NULL, 'false', 'radio', 0, 0, 'true');
INSERT INTO `infoformcreate` VALUES (188, 15, 8, 180, '多选', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '[]', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=9, content=222}, {id=10, content=333}]', NULL, 'false', 'checkbox', 1, 2, 'true');
INSERT INTO `infoformcreate` VALUES (189, 18, 0, 0, NULL, '800', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (190, 18, 1, 189, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '这是一个文本', 'left', NULL, NULL, NULL, NULL, NULL, NULL, 'text', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (191, 18, 2, 189, '输入', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (192, 19, 0, 0, NULL, '800', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (193, 19, 1, 192, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '这是一个文本', 'left', NULL, NULL, NULL, NULL, NULL, NULL, 'text', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (194, 19, 2, 192, '输入框1', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (195, 19, 3, 192, '下拉框1', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '选项一', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=4, content=选项二}]', NULL, 'true', 'select', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (196, 19, 5, 192, '文本域三', '300', '100', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '100', NULL, NULL, 'true', 'textarea', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (197, 19, 6, 192, '单选框1', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '选项二', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=7, content=选项二}]', NULL, 'false', 'radio', 0, 0, 'true');
INSERT INTO `infoformcreate` VALUES (198, 19, 8, 192, '多选框1', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, '[]', NULL, NULL, NULL, NULL, '[{id=0, content=选项一}, {id=9, content=选项二}, {id=10, content=选项三}]', NULL, 'true', 'checkbox', 1, 2, 'true');
INSERT INTO `infoformcreate` VALUES (199, 19, 11, 192, '时间器', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'false', 'time', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (200, 20, 0, 0, NULL, '800', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (201, 20, 1, 200, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '这是一个文本', 'left', NULL, NULL, NULL, NULL, NULL, NULL, 'text', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (202, 20, 2, 200, '输入框', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (203, 21, 0, 0, NULL, '800', '500', 'true', '1', 'true', '1', '10', '10', '10', '10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'container', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (204, 21, 1, 203, '', '800', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '20', '400', 'Microsoft YaHei', '测试表单生成', 'center', NULL, NULL, NULL, NULL, NULL, NULL, 'text', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (205, 21, 2, 203, '', '100', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', 'none', '14', '400', 'Microsoft YaHei', '姓名：', 'right', NULL, NULL, NULL, NULL, NULL, NULL, 'text', 0, 0, NULL);
INSERT INTO `infoformcreate` VALUES (206, 21, 3, 203, '姓名', '200', '30', 'false', '1', 'true', '1', '10', '10', '10', '10', NULL, '14', '400', 'Microsoft YaHei', '', 'left', 'black', '请输入内容', '10', NULL, NULL, 'false', 'input', 0, 0, NULL);

-- ----------------------------
-- Table structure for noticemanager
-- ----------------------------
DROP TABLE IF EXISTS `noticemanager`;
CREATE TABLE `noticemanager`  (
  `iNMId` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cNMManagerCodes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知管理人员code',
  `cNMPubName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通知发布者名称',
  `cNMTitle` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知标题',
  `cNMContent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知内容',
  `cNMState` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '草稿' COMMENT '通知状态',
  `dNMCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '通知创建时间',
  `dNMUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '通知修改时间',
  `cNMCreaterCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知创建人员',
  `cNMUpdaterCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知修改人员',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  PRIMARY KEY (`iNMId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of noticemanager
-- ----------------------------
INSERT INTO `noticemanager` VALUES (1, '201549221', '校团委', '1213', '212312', '发布', '2024-03-26 16:57:34', '2024-03-26 23:52:44', '201549221', '201549221', '1');
INSERT INTO `noticemanager` VALUES (2, '201549221', NULL, '测试新增', '测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增测试新增', '草稿', '2024-03-26 16:58:20', '2024-03-26 23:38:51', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (3, '201549221', NULL, '测试修改', '测试修改', '草稿', '2024-03-26 17:06:31', '2024-03-26 18:28:12', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (4, '201549221', NULL, '测试删除', '测试删除测试删除测试删除测试删除', '草稿', '2024-03-26 17:21:31', '2024-03-26 18:28:11', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (5, '201549221', '黄晓斌', '1123', '113123', '发布', '2024-03-26 17:45:31', '2024-03-26 19:23:29', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (6, '201549221', '校团委', '测试通知待办处理', '测试通知待办处理测试通知待办处理测试通知待办处理测试通知待办处理测试通知待办处理测试通知待办处理测试通知待办处理测试通知待办处理测试通知待办处理', '发布', '2024-03-27 09:29:38', '2024-03-27 09:30:13', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (7, '201549221', '测试', '测试数据', '测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据', '发布', '2024-03-27 11:20:04', '2024-03-27 11:20:25', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (8, '201549221', '校团委', '通知发布测试', '通知发布测试内容信息输入~', '发布', '2024-03-29 09:46:30', '2024-03-29 09:48:17', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (9, '201549221', NULL, '测试附件', '测试附件', '草稿', '2024-04-12 20:15:36', '2024-04-12 20:15:36', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (15, '201549221', NULL, '附件上传测试', '附件上传测试', '草稿', '2024-04-12 20:24:42', '2024-04-12 20:24:42', '201549221', '201549221', '1');
INSERT INTO `noticemanager` VALUES (16, '201549221', '测试发布', '测试附件上传', '测试附件上传', '发布', '2024-04-12 20:29:01', '2024-04-12 23:26:10', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (17, '201549221', NULL, '测试附件上传2', '测试附件上传2', '草稿', '2024-04-12 20:30:03', '2024-04-12 20:31:13', '201549221', '201549221', '0');
INSERT INTO `noticemanager` VALUES (18, '201549221', NULL, '测试通知创建不上传文件', '测试通知创建不上传文件', '草稿', '2024-04-12 20:33:24', '2024-04-12 20:33:35', '201549221', '201549221', '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '发布配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pubconfig
-- ----------------------------
INSERT INTO `pubconfig` VALUES (2, 4, 'CollectInfo', '2024-03-16 00:00:00', '2024-03-16 00:00:00', 'true', 'false', '123', '小七', '小七, 希仔', '希仔', '', '111111, 201549221', '201549221', '2024-03-16 19:52:27', '2024-03-17 13:21:05', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (8, 5, 'CollectInfo', '2024-03-16 00:00:00', '2024-03-16 00:00:00', 'false', 'true', '123', '小七', '', '', '[]', '[]', '[]', '2024-03-16 20:11:37', '2024-03-17 13:26:56', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (9, 16, 'CollectInfo', '2024-03-18 00:00:00', '2024-03-31 00:00:00', 'false', 'true', '黄晓斌', '小七,  希仔', '', '', '111111,  201549221', '', '', '2024-03-17 15:33:25', '2024-03-25 15:23:44', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (10, 17, 'CollectInfo', '2024-03-26 00:00:00', '2024-03-30 00:00:00', 'true', 'false', '黄晓斌', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-03-25 17:06:26', '2024-03-25 17:06:27', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (11, 18, 'CollectInfo', '2024-03-27 00:00:00', '2024-03-31 00:00:00', 'true', 'false', '黄晓斌', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-03-26 09:39:06', '2024-03-26 09:39:06', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (12, 19, 'CollectInfo', '2024-03-27 00:00:00', '2024-03-31 00:00:00', 'true', 'false', '测试发布呐', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-03-26 11:50:24', '2024-03-26 11:50:25', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (13, 15, 'CollectInfo', '2024-03-27 00:00:00', '2024-03-31 00:00:00', 'true', 'false', '测试数据显示', '小七,  希仔', '', '', '111111,  201549221', '', '', '2024-03-26 14:31:46', '2024-03-26 14:32:09', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (14, 20, 'CollectInfo', '2024-03-27 00:00:00', '2024-03-31 00:00:00', 'true', 'false', '黄晓斌', '小七,  希仔', '', '', '111111,  201549221', '', '', '2024-03-26 15:10:01', '2024-03-26 15:10:21', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (15, 2, 'Notify', '2024-03-27 00:00:00', '2024-03-27 00:00:00', 'false', 'true', '测试表单配置', '小七,   希仔', '', '', '111111,   201549221', '', '', '2024-03-26 19:47:36', '2024-03-26 23:38:51', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (16, 1, 'Notify', '2024-03-26 00:00:00', NULL, 'false', 'false', '校团委', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-03-26 23:43:38', '2024-03-26 23:52:39', '希仔', NULL, '1');
INSERT INTO `pubconfig` VALUES (17, 6, 'Notify', '2024-03-27 00:00:00', NULL, 'false', 'false', '校团委', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-03-27 09:29:59', '2024-03-27 09:29:59', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (18, 7, 'Notify', '2024-03-27 00:00:00', NULL, 'false', 'false', '测试', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-03-27 11:20:20', '2024-03-27 11:20:20', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (19, 1, 'Task', '2024-03-27 00:00:00', '2024-03-28 00:00:00', 'false', 'false', '测试任务管理', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-03-27 22:35:02', '2024-03-27 22:35:03', '希仔', NULL, '1');
INSERT INTO `pubconfig` VALUES (20, 12, 'Task', '2024-03-27 00:00:00', '2024-03-28 00:00:00', 'false', 'false', '测试发布', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-03-27 23:21:47', '2024-03-27 23:21:47', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (21, 8, 'Notify', '2024-03-30 00:00:00', NULL, 'false', 'false', '校团委', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-03-29 09:48:11', '2024-03-29 09:48:11', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (22, 16, 'Notify', '2024-04-12 00:00:00', NULL, 'false', 'false', '测试发布', '小七, 希仔', '', '', '111111, 201549221', '', '', '2024-04-12 23:26:00', '2024-04-12 23:26:00', '希仔', NULL, '0');
INSERT INTO `pubconfig` VALUES (23, 17, 'Task', '2024-04-13 00:00:00', '2024-04-19 00:00:00', 'false', 'false', '测试发布', '希仔', '', '', '201549221', '', '', '2024-04-13 19:58:27', '2024-04-13 19:58:28', '希仔', NULL, '0');

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
-- Table structure for taskmanager
-- ----------------------------
DROP TABLE IF EXISTS `taskmanager`;
CREATE TABLE `taskmanager`  (
  `iTMId` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cTMManagerCodes` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务管理人员code',
  `cTMPubName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务发布者名称',
  `cTMTitle` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务标题',
  `cTMContent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务内容',
  `cTMRequest` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务要求',
  `cTMState` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '草稿' COMMENT '任务状态',
  `dTMCreateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务创建时间',
  `dTMUpdateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '任务修改时间',
  `cTMCreaterCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务创建人员',
  `cTMUpdaterCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务修改人员',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  PRIMARY KEY (`iTMId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of taskmanager
-- ----------------------------
INSERT INTO `taskmanager` VALUES (1, '201549221', '黄晓斌', '任务名称', '任务内容', '任务要求', '草稿', '2024-03-27 20:34:31', '2024-03-27 23:01:26', '201549221', '201549221', '1');
INSERT INTO `taskmanager` VALUES (2, '201549221', '小希', '任务名称1', '任务内容1', '任务要求1', '草稿', '2024-03-27 20:37:10', '2024-03-27 23:03:25', '201549221', '201549221', '1');
INSERT INTO `taskmanager` VALUES (3, '201549221', NULL, '1', '2', '3', '草稿', '2024-03-27 21:21:07', '2024-03-27 23:03:29', '201549221', '201549221', '1');
INSERT INTO `taskmanager` VALUES (4, '201549221', NULL, '11', '11', '11', '草稿', '2024-03-27 21:21:15', '2024-03-27 21:21:15', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (5, '201549221', NULL, '22', '22', '22', '草稿', '2024-03-27 21:21:23', '2024-03-27 21:21:23', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (6, '201549221', NULL, '33', '33', '33', '草稿', '2024-03-27 21:21:29', '2024-03-27 21:21:29', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (7, '201549221', NULL, '44', '44', '44', '草稿', '2024-03-27 21:21:37', '2024-03-27 21:21:37', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (8, '201549221', NULL, '55', '55', '555', '草稿', '2024-03-27 21:21:44', '2024-03-27 21:21:44', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (9, '201549221', NULL, '66', '66', '66', '草稿', '2024-03-27 21:21:50', '2024-03-27 21:21:50', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (10, '201549221', NULL, '77', '77', '777', '草稿', '2024-03-27 21:21:56', '2024-03-27 21:21:56', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (11, '201549221', NULL, '88', '88', '88', '发布', '2024-03-28 21:22:03', '2024-03-27 22:59:58', '201549221', '201549221', '1');
INSERT INTO `taskmanager` VALUES (12, '201549221', '测试发布', '任务名称测试修改', '任务内容', '任务要求', '发布', '2024-03-27 22:43:49', '2024-03-27 23:23:03', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (13, '201549221', NULL, '任务名称测试修改', '任务内容', '任务要求', '草稿', '2024-03-27 22:47:31', '2024-03-27 23:10:11', '201549221', '201549221', '1');
INSERT INTO `taskmanager` VALUES (14, '201549221', NULL, '测试任务附件上传001', '测试任务附件上传001', '测试任务附件上传001', '草稿', '2024-04-13 13:28:35', '2024-04-13 19:41:07', '201549221', '201549221', '1');
INSERT INTO `taskmanager` VALUES (15, '201549221', NULL, '测试任务附件上传001', '测试任务附件上传001', '测试任务附件上传001', '草稿', '2024-04-13 13:29:02', '2024-04-13 13:29:02', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (16, '201549221', NULL, '测试任务附件上传002', '  测试任务附件上传002\n测试任务附件上传002', '测试任务附件上传002', '草稿', '2024-04-13 19:42:41', '2024-04-13 19:42:41', '201549221', '201549221', '0');
INSERT INTO `taskmanager` VALUES (17, '201549221', '测试发布', '测试任务附件上传002', '  测试任务附件上传002\n测试任务附件上传002', '测试任务附件上传002', '发布', '2024-04-13 19:57:41', '2024-04-13 19:58:35', '201549221', '201549221', '0');

-- ----------------------------
-- Table structure for workmessage
-- ----------------------------
DROP TABLE IF EXISTS `workmessage`;
CREATE TABLE `workmessage`  (
  `iWMId` int NOT NULL AUTO_INCREMENT,
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作类型',
  `typeId` int NULL DEFAULT NULL COMMENT '工作类型对应表ID',
  `personCode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编码',
  `personName` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员名称',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '消息内容',
  `handler` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '处理人',
  `handleTime` datetime NULL DEFAULT NULL COMMENT '处理时间',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  PRIMARY KEY (`iWMId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '工作信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of workmessage
-- ----------------------------
INSERT INTO `workmessage` VALUES (1, 'collectInfo', 16, '111111', '小七', '管理员通知您尽管完成测试表数据生成信息表单填写！', '希仔', '2024-03-25 13:14:32', '0');
INSERT INTO `workmessage` VALUES (2, 'collectInfo', 16, '111111', '小七', '管理员通知您尽管完成测试表数据生成信息表单填写！', '希仔', '2024-03-25 13:17:31', '0');
INSERT INTO `workmessage` VALUES (3, 'CollectInfo', NULL, '201549221', '希仔', '表单：测试表单数据生成、填写与审批审批通过\n审批描述：ok。', '希仔', '2024-03-25 22:07:45', '0');
INSERT INTO `workmessage` VALUES (4, 'CollectInfo', NULL, '201549221', '希仔', '表单：测试表单数据生成、填写与审批审批不通过\n审批描述：1。', '希仔', '2024-03-25 22:09:47', '0');
INSERT INTO `workmessage` VALUES (5, 'CollectInfo', NULL, '201549221', '希仔', '表单：测试表单数据生成、填写与审批审批通过\n; 审批描述：1。', '希仔', '2024-03-25 22:23:43', '0');
INSERT INTO `workmessage` VALUES (6, 'collectInfo', 17, '111111', '小七', '管理员通知您尽管完成测试表单数据生成、填写与审批信息表单填写！', '希仔', '2024-03-25 22:24:11', '0');
INSERT INTO `workmessage` VALUES (7, 'CollectInfo', NULL, '201549221', '希仔', '表单：测试表单填写审批不通过\n; 审批描述：123。', '希仔', '2024-03-26 13:54:20', '0');
INSERT INTO `workmessage` VALUES (8, 'CollectInfo', NULL, '201549221', '希仔', '表单：测试表单填写审批不通过\n; 审批描述：123。', '希仔', '2024-03-26 13:54:34', '0');
INSERT INTO `workmessage` VALUES (9, 'CollectInfo', NULL, '201549221', '希仔', '表单：测试表单填写审批不通过\n; 审批描述：123。', '希仔', '2024-03-26 13:54:40', '0');
INSERT INTO `workmessage` VALUES (10, 'CollectInfo', NULL, '201549221', '希仔', '表单：测试表单填写审批通过\n; 审批描述：123。', '希仔', '2024-03-26 13:55:11', '0');

-- ----------------------------
-- Table structure for worktask
-- ----------------------------
DROP TABLE IF EXISTS `worktask`;
CREATE TABLE `worktask`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作类型（\r\nCollectInfo\r\nCollectInfoApproval\r\n\r\nTask\r\nTaskApproval\r\n\r\nNotify\r\n）',
  `typeId` int NULL DEFAULT NULL COMMENT '工作ID',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编码',
  `updateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据更新时间',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '草稿' COMMENT '状态(草稿、完成)',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容描述',
  `dataState` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态（0：活动；1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '工作待办表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of worktask
-- ----------------------------
INSERT INTO `worktask` VALUES (1, 'CollectInfo', 16, '111111', '2024-03-17 22:39:20', '草稿', NULL, '1');
INSERT INTO `worktask` VALUES (2, 'CollectInfo', 16, '201549221', '2024-03-17 22:39:20', '草稿', NULL, '1');
INSERT INTO `worktask` VALUES (3, 'CollectInfo', 16, '111111', '2024-03-17 22:51:20', '草稿', NULL, '1');
INSERT INTO `worktask` VALUES (4, 'CollectInfo', 16, '201549221', '2024-03-17 22:51:20', '草稿', NULL, '1');
INSERT INTO `worktask` VALUES (5, 'CollectInfo', 16, '111111', '2024-03-17 23:15:07', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (6, 'CollectInfo', 16, '111111', '2024-03-17 23:16:46', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (7, 'CollectInfo', 17, '111111', '2024-03-25 17:07:28', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (8, 'CollectInfo', 17, '201549221', '2024-03-25 17:07:28', '完成', NULL, '0');
INSERT INTO `worktask` VALUES (9, 'CollectInfoApproval', 17, '201549221', '2024-03-25 22:23:39', '通过', '1', '0');
INSERT INTO `worktask` VALUES (10, 'CollectInfo', 17, '201549221', '2024-03-25 22:09:47', '草稿', '审批未通过，请重新完成测试表单数据生成、填写与审批收集表单的填写！', '0');
INSERT INTO `worktask` VALUES (11, 'CollectInfo', 18, '111111', '2024-03-26 09:39:17', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (12, 'CollectInfo', 18, '201549221', '2024-03-26 09:39:17', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (13, 'CollectInfo', 19, '111111', '2024-03-26 11:50:35', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (14, 'CollectInfo', 19, '201549221', '2024-03-26 11:50:35', '完成', NULL, '0');
INSERT INTO `worktask` VALUES (16, 'CollectInfoApproval', 19, '201549221', '2024-03-26 13:19:50', '草稿', '成员希仔已提交测试表单填写信息表单收集，请审批！', '0');
INSERT INTO `worktask` VALUES (17, 'CollectInfoApproval', 19, '201549221', '2024-03-26 13:29:43', '草稿', '成员希仔已提交测试表单填写信息表单收集，请审批！', '0');
INSERT INTO `worktask` VALUES (19, 'CollectInfo', 19, '201549221', '2024-03-26 13:54:34', '草稿', '审批未通过，请重新完成测试表单填写收集表单的填写！', '0');
INSERT INTO `worktask` VALUES (20, 'CollectInfo', 19, '201549221', '2024-03-26 13:54:40', '草稿', '审批未通过，请重新完成测试表单填写收集表单的填写！', '0');
INSERT INTO `worktask` VALUES (22, 'CollectInfo', 15, '111111', '2024-03-26 14:32:16', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (23, 'CollectInfo', 15, '201549221', '2024-03-26 14:32:16', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (24, 'CollectInfo', 20, '111111', '2024-03-26 15:10:26', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (25, 'CollectInfo', 20, '201549221', '2024-03-26 15:10:26', '草稿', NULL, '0');
INSERT INTO `worktask` VALUES (26, 'Notify', 1, '111111', '2024-03-26 23:52:45', '草稿', '校团委发布了通知《1213》，请确认！', '1');
INSERT INTO `worktask` VALUES (27, 'Notify', 1, '201549221', '2024-03-26 23:52:45', '草稿', '校团委发布了通知《1213》，请确认！', '1');
INSERT INTO `worktask` VALUES (28, 'Notify', 6, '111111', '2024-03-27 09:30:13', '草稿', '校团委发布了通知《测试通知待办处理》，请确认！', '0');
INSERT INTO `worktask` VALUES (30, 'Notify', 7, '111111', '2024-03-27 11:20:25', '待确认', '测试发布了通知《测试数据》，请确认！', '0');
INSERT INTO `worktask` VALUES (31, 'Notify', 7, '201549221', '2024-03-27 11:20:25', '已确认', '测试发布了通知《测试数据》，请确认！', '0');
INSERT INTO `worktask` VALUES (32, 'Task', 12, '111111', '2024-03-27 23:21:53', '草稿', '测试发布发布了任务《任务名称测试修改》，请完成！', '0');
INSERT INTO `worktask` VALUES (33, 'Task', 12, '201549221', '2024-03-27 23:21:53', '草稿', '测试发布发布了任务《任务名称测试修改》，请完成！', '0');
INSERT INTO `worktask` VALUES (34, 'Notify', 8, '111111', '2024-03-29 09:48:17', '待确认', '校团委发布了通知《通知发布测试》，请确认！', '0');
INSERT INTO `worktask` VALUES (35, 'Notify', 8, '201549221', '2024-03-29 09:48:17', '待确认', '校团委发布了通知《通知发布测试》，请确认！', '0');
INSERT INTO `worktask` VALUES (36, 'Notify', 16, '111111', '2024-04-12 23:26:10', '待确认', '测试发布发布了通知《测试附件上传》，请确认！', '0');
INSERT INTO `worktask` VALUES (37, 'Notify', 16, '201549221', '2024-04-12 23:26:10', '已确认', '测试发布发布了通知《测试附件上传》，请确认！', '0');
INSERT INTO `worktask` VALUES (38, 'Task', 17, '201549221', '2024-04-13 19:58:35', '草稿', '测试发布发布了任务《测试任务附件上传002》，请完成！', '0');

SET FOREIGN_KEY_CHECKS = 1;

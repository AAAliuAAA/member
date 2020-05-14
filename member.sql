/*
Navicat MySQL Data Transfer

Source Server         : member
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : member

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-05-14 20:18:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `codename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', 'stu_state', '1', '申请状态');
INSERT INTO `dictionary` VALUES ('2', 'stu_state', '2', '积极分子');
INSERT INTO `dictionary` VALUES ('3', 'stu_state', '3', '发展对象');
INSERT INTO `dictionary` VALUES ('4', 'stu_state', '5', '正式党员');
INSERT INTO `dictionary` VALUES ('5', 'approve_state', '1', '正在申请');
INSERT INTO `dictionary` VALUES ('6', 'approve_state', '2', '申请通过');
INSERT INTO `dictionary` VALUES ('7', 'approve_state', '3', '拒绝');
INSERT INTO `dictionary` VALUES ('8', 'stu_state', '4', '预备党员');
INSERT INTO `dictionary` VALUES ('9', 'approve_state', '4', '未申请');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parentNode` varchar(255) DEFAULT '0' COMMENT '是否为父节点',
  `url` varchar(255) DEFAULT NULL,
  `node` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '学生申请', '0', '', '1');
INSERT INTO `menu` VALUES ('2', '入党审批流程', '0', '', '2');
INSERT INTO `menu` VALUES ('4', '积极分子', '2', 'queryApply.html', '2001');
INSERT INTO `menu` VALUES ('6', '发展对象', '2', 'queryApply.html', '2003');
INSERT INTO `menu` VALUES ('7', '转正党员', '2', 'queryApply.html', '2005');
INSERT INTO `menu` VALUES ('10', '申请人信息', '1', 'queryApply.html', '1002');
INSERT INTO `menu` VALUES ('11', '预备党员', '2', 'queryApply.html', '2004');
INSERT INTO `menu` VALUES ('12', '资料管理', '0', null, '3');
INSERT INTO `menu` VALUES ('13', '文件上传', '3', 'upload.html', '3001');
INSERT INTO `menu` VALUES ('14', '文档模板下载', '3', 'download.html', '3002');
INSERT INTO `menu` VALUES ('15', '上传资料下载', '3', 'download.html', '3003');
INSERT INTO `menu` VALUES ('16', '党校结业证书', '3', 'certificate.html', '3004');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perms` varchar(255) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'roles:list', '1');
INSERT INTO `permission` VALUES ('2', 'roles:add', '2');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'superadmin');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuname` varchar(255) DEFAULT NULL,
  `stuno` varchar(11) DEFAULT NULL,
  `sex` varchar(11) DEFAULT '0' COMMENT '0=女，1=男',
  `nation` varchar(255) DEFAULT '汉',
  `idno` varchar(255) DEFAULT NULL,
  `xueyuan` varchar(255) DEFAULT '信息技术学院',
  `profess` varchar(255) DEFAULT '电子商务' COMMENT '专业',
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `stu_state` varchar(255) DEFAULT NULL,
  `approve_state` varchar(255) DEFAULT NULL,
  `certificate` varchar(255) DEFAULT '0' COMMENT '0=未发放，1=已经发放',
  `pwd` varchar(255) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('86', '小花', '12345', null, '', '', '', '', '', '', '5', '2', '1', '123', '1');
INSERT INTO `student` VALUES ('87', '小绿', '', null, '', '', '', '', '', '', '2', '3', '0', '123', null);
INSERT INTO `student` VALUES ('89', '米米', '55289', null, '', '', '', '', '', '', '5', '2', '1', '123', null);
INSERT INTO `student` VALUES ('93', '小', '68393', '0', '12', '', '', '', '', '', '2', '1', '0', '123', null);
INSERT INTO `student` VALUES ('94', '小燕', '709294', '0', '12', '7', '', '', '', '', '1', '3', '0', '123', null);
INSERT INTO `student` VALUES ('95', '小猪', '123', '0', '', '', '', '', '', '', '2', '4', '0', '123', '2');

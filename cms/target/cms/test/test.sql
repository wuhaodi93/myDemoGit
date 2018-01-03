/*
Navicat MySQL Data Transfer

Source Server         : 116.62.136.249
Source Server Version : 50718
Source Host           : 116.62.136.249:3306
Source Database       : yjdPlatform

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-11-01 10:58:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `app_merchantApplyInfo`
-- ----------------------------
DROP TABLE IF EXISTS `app_merchantApplyInfo`;
CREATE TABLE `app_merchantApplyInfo` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `citycode` int(20) NOT NULL,
  `userId` int(20) NOT NULL,
  `creditCode` varchar(40) DEFAULT NULL,
  `status` int(3) NOT NULL,
  `typeId` int(3) NOT NULL,
  `businessLicense` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `zipBusinessLicense` varchar(300) DEFAULT NULL,
  `failedMessage` varchar(300) DEFAULT NULL,
  `qrPath` varchar(300) DEFAULT NULL,
  `companyName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_merchantApplyInfo
-- ----------------------------

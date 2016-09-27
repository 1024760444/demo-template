/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : docs

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-09-20 19:02:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_docs_attribute`
-- ----------------------------
DROP TABLE IF EXISTS `t_docs_attribute`;
CREATE TABLE `t_docs_attribute` (
  `docKey` varchar(32) NOT NULL COMMENT '系统属性标识',
  `docValue` varchar(64) DEFAULT NULL COMMENT '系统属性值',
  `createDate` datetime DEFAULT NULL COMMENT '系统属性创建日期',
  PRIMARY KEY (`docKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统属性设置';

-- ----------------------------
-- Records of t_docs_attribute
-- ----------------------------
INSERT INTO `t_docs_attribute` VALUES ('domain', 'http://localhost:8080/docs-manager/', '2016-09-18 14:35:55');
INSERT INTO `t_docs_attribute` VALUES ('fileBase', 'F:\\works\\docs-manager\\doc', '2016-09-20 18:51:23');

-- ----------------------------
-- Table structure for `t_docs_filelist`
-- ----------------------------
DROP TABLE IF EXISTS `t_docs_filelist`;
CREATE TABLE `t_docs_filelist` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '文档唯一标识',
  `fileName` varchar(32) DEFAULT NULL COMMENT '文档名称',
  `fileDesc` varchar(64) DEFAULT NULL COMMENT '文档描述',
  `teamId` int(10) DEFAULT NULL COMMENT '文档所属分组',
  `userId` int(10) DEFAULT NULL COMMENT '上传文档的用户',
  `showId` int(1) DEFAULT NULL COMMENT '展示权限。 0仅自己可见，1仅本组可见，2所有人可见',
  `downId` int(1) DEFAULT NULL COMMENT '下载权限。0仅自己可下载，1仅本组可下载，2所有人可下载',
  `downCode` varchar(6) DEFAULT '123456' COMMENT '下载码。 可见而不可下载的文档，可通过上传者设置的下载码下载。',
  `createDate` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档信息列表';

-- ----------------------------
-- Records of t_docs_filelist
-- ----------------------------

-- ----------------------------
-- Table structure for `t_docs_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_docs_role`;
CREATE TABLE `t_docs_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色标识',
  `roleName` varchar(32) DEFAULT NULL COMMENT '角色名称，英文名称',
  `roleByname` varchar(32) DEFAULT NULL COMMENT '角色别名，中文名称',
  `roleDesc` varchar(64) DEFAULT NULL COMMENT '角色描述',
  `createDate` datetime DEFAULT NULL COMMENT '角色创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of t_docs_role
-- ----------------------------
INSERT INTO `t_docs_role` VALUES ('1', 'Root', '网站主', '拥有网站', '2016-09-18 11:33:36');
INSERT INTO `t_docs_role` VALUES ('2', 'Admin', '管理员', '管理网站和用户', '2016-09-18 11:55:39');
INSERT INTO `t_docs_role` VALUES ('3', 'Member', '会员', '发布文章信息，查看部分等权限', '2016-09-18 16:38:07');
INSERT INTO `t_docs_role` VALUES ('4', 'Tourist', '游客', '只能查看共享开放资源', '2016-09-18 16:39:14');

-- ----------------------------
-- Table structure for `t_docs_team`
-- ----------------------------
DROP TABLE IF EXISTS `t_docs_team`;
CREATE TABLE `t_docs_team` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户组唯一标识',
  `teamName` varchar(32) DEFAULT NULL COMMENT '用户组名称',
  `teamByname` varchar(32) DEFAULT NULL COMMENT '用户组别名',
  `teamDesc` varchar(64) DEFAULT NULL COMMENT '用户组描述',
  `createDate` datetime DEFAULT NULL COMMENT '用户组创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户组';

-- ----------------------------
-- Records of t_docs_team
-- ----------------------------
INSERT INTO `t_docs_team` VALUES ('1', 'yunwei', '运维组', '', '2016-09-20 11:54:36');
INSERT INTO `t_docs_team` VALUES ('2', 'yanfa', '研发中心', '研发中心', '2016-09-20 12:21:01');
INSERT INTO `t_docs_team` VALUES ('3', 'tiyan', '体验中心', '体验中心', '2016-09-20 12:23:43');
INSERT INTO `t_docs_team` VALUES ('4', 'yanjiu', '研究院', '研究院：主要职责，研究各种前沿技术；讨论研究技术方案的可行性', '2016-09-20 12:24:45');
INSERT INTO `t_docs_team` VALUES ('5', 'renshi', '人事部', '人事部', '2016-09-20 12:25:27');
INSERT INTO `t_docs_team` VALUES ('6', 'jishu', '技术支持部门', '技术支持部门', '2016-09-20 12:26:01');
INSERT INTO `t_docs_team` VALUES ('8', 'yunying', '运营中心', '运营中心', '2016-09-20 12:26:49');
INSERT INTO `t_docs_team` VALUES ('9', 'kaifang', '开发平台', '开发平台', '2016-09-20 12:27:11');
INSERT INTO `t_docs_team` VALUES ('10', 'xingzheng', '行政部', '行政部', '2016-09-20 12:27:29');
INSERT INTO `t_docs_team` VALUES ('11', 'kehu', '客户部', '客户部', '2016-09-20 12:28:02');
INSERT INTO `t_docs_team` VALUES ('12', 'chejizu', '车机组', '车机组', '2016-09-20 12:28:38');
INSERT INTO `t_docs_team` VALUES ('13', 'ert', 'wert', 'ert', '2016-09-20 17:45:27');

-- ----------------------------
-- Table structure for `t_docs_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_docs_user`;
CREATE TABLE `t_docs_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户标识',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户登录名称',
  `userPasswd` varchar(64) DEFAULT NULL COMMENT '登录密码，MD5加密',
  `teamId` int(10) DEFAULT NULL COMMENT '分组标识',
  `roleId` int(10) DEFAULT '0' COMMENT '角色编号',
  `userEmail` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `userPhone` varchar(15) DEFAULT NULL COMMENT '用户手机',
  `createDate` datetime DEFAULT NULL COMMENT '用户创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='登录信息表';

-- ----------------------------
-- Records of t_docs_user
-- ----------------------------
INSERT INTO `t_docs_user` VALUES ('1', 'yanghaitao', 'd41d8cd98f00b204e9800998ecf8427e', '1', '1', '1024760444@qq.com', '1522306295', '2016-09-20 17:10:02');
INSERT INTO `t_docs_user` VALUES ('4', 'ranjunfeng', 'ceb30b15379cab9bba788a9a8c7c7bca', '1', '2', '1024760444@qq.com', '15223062959', '2016-09-19 17:49:50');
INSERT INTO `t_docs_user` VALUES ('5', 'liuguoming', 'd41d8cd98f00b204e9800998ecf8427e', '5', '4', '1024760444@qq.com', '15223062959', '2016-09-20 17:13:02');
INSERT INTO `t_docs_user` VALUES ('6', 'zhangmingchun', 'c9afdf75f954fd191bb68befde003543', '1', '2', '', '', '2016-09-19 17:46:56');
INSERT INTO `t_docs_user` VALUES ('7', 'yangqian', '7854b30785e383f96f60b93c3c724dcf', '1', '3', '', '', '2016-09-19 17:51:35');
INSERT INTO `t_docs_user` VALUES ('8', 'gongliuping', 'c0861a8908e543488c7698646cffec2b', '1', '3', '', '', '2016-09-19 17:52:05');
INSERT INTO `t_docs_user` VALUES ('9', 'lixuefei', '2db95e8e1a9267b7a1188556b2013b33', '6', '3', '', '', '2016-09-20 16:54:42');
INSERT INTO `t_docs_user` VALUES ('10', 'chengwuyon', '1478b839f29c2849ee7e7cd13331f1cf', '1', '3', '553457542@qq.com', '', '2016-09-19 17:53:27');
INSERT INTO `t_docs_user` VALUES ('11', 'longzhengjiang', 'e5ef623b0cee0fd4185be96f57d4e237', '1', '3', '', '', '2016-09-19 17:53:44');
INSERT INTO `t_docs_user` VALUES ('12', 'liyuan', 'e728b47751c6555942cb60f97d1e4553', '1', '3', '', '', '2016-09-19 17:53:57');
INSERT INTO `t_docs_user` VALUES ('13', 'ranya', '731a58830959369de78aac2501c6021a', '1', '3', '', '', '2016-09-19 17:54:11');
INSERT INTO `t_docs_user` VALUES ('14', 'renxiaoqian', '5a3f119fb2bb34537b9b196c4288ca92', '1', '3', '', '1522659864', '2016-09-19 17:54:31');
INSERT INTO `t_docs_user` VALUES ('15', 'hesha', '789406d01073ca1782d86293dcfc0764', '2', '3', '1024760444@qq.com', '15223078549', '2016-09-20 16:46:59');
INSERT INTO `t_docs_user` VALUES ('16', 'zhangjie', 'b1d8fcdf6d0db7011c71fc30e7aef4a4', '12', '4', '', '', '2016-09-20 16:57:20');

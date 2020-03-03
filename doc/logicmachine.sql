/*
Navicat MySQL Data Transfer

Source Server         : 192.168.103.140
Source Server Version : 50722
Source Host           : 192.168.103.140:3006
Source Database       : logicmachine

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-03-03 18:08:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for atomic_fact
-- ----------------------------
DROP TABLE IF EXISTS `atomic_fact`;
CREATE TABLE `atomic_fact` (
  `atomic_fact_id` int(11) NOT NULL COMMENT '主键',
  `table_name` varchar(255) DEFAULT NULL COMMENT '表名',
  `column_name` varchar(255) DEFAULT NULL COMMENT '字段名',
  `operator` varchar(255) DEFAULT NULL COMMENT '原子事实操作符',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`atomic_fact_id`) USING BTREE,
  KEY `fk_op` (`operator`) USING BTREE,
  CONSTRAINT `fk_op` FOREIGN KEY (`operator`) REFERENCES `operator` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='原子事实表，一行再加上对应的表行代表一个原子事实，其真值取决于表中的数据';

-- ----------------------------
-- Records of atomic_fact
-- ----------------------------
INSERT INTO `atomic_fact` VALUES ('0', '', '', '=', null, '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('1', 'customer', 'age', '<', '10', '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('2', 'customer', 'sex', '=', '女', '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('3', 'customer', 'age', '=', '20', '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('4', 'customer', 'age', '<>', '30', '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('5', 'customer', 'sex', '=', '男', '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('6', 'customer', 'age', '>', '50', '2020-03-02 16:16:28', '2020-03-02 16:16:28');

-- ----------------------------
-- Table structure for connective
-- ----------------------------
DROP TABLE IF EXISTS `connective`;
CREATE TABLE `connective` (
  `code` varchar(255) NOT NULL COMMENT '符号',
  `name` varchar(255) DEFAULT NULL COMMENT '含义',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='真值联结词';

-- ----------------------------
-- Records of connective
-- ----------------------------
INSERT INTO `connective` VALUES ('AND', '析取', '2020-03-02 16:17:09', '2020-03-02 16:17:09');
INSERT INTO `connective` VALUES ('NOT', '取反', '2020-03-02 16:17:09', '2020-03-02 16:17:09');
INSERT INTO `connective` VALUES ('OR', '合取', '2020-03-02 16:17:09', '2020-03-02 16:17:09');
INSERT INTO `connective` VALUES ('→', '蕴含', '2020-03-02 16:17:09', '2020-03-02 16:17:09');
INSERT INTO `connective` VALUES ('↔', '等值', '2020-03-02 16:17:09', '2020-03-02 16:17:09');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '张三', '10', '男', '2020-03-02 16:17:44', '2020-03-02 16:17:44');
INSERT INTO `customer` VALUES ('2', '李四', '20', '女', '2020-03-02 16:17:44', '2020-03-02 16:17:44');
INSERT INTO `customer` VALUES ('3', '王五', '35', '男', '2020-03-02 16:17:44', '2020-03-02 16:17:44');
INSERT INTO `customer` VALUES ('4', '陈六', '30', '女', '2020-03-02 16:17:44', '2020-03-02 16:17:44');
INSERT INTO `customer` VALUES ('5', '周七', '50', '男', '2020-03-02 16:17:44', '2020-03-02 16:17:44');
INSERT INTO `customer` VALUES ('6', '吴八', '60', '女', '2020-03-02 16:17:44', '2020-03-02 16:17:44');

-- ----------------------------
-- Table structure for customer_varible
-- ----------------------------
DROP TABLE IF EXISTS `customer_varible`;
CREATE TABLE `customer_varible` (
  `customer_varible_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `call_time_long_sum_last_week` int(11) DEFAULT NULL COMMENT '上一周通话总时长',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_varible_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer_varible
-- ----------------------------

-- ----------------------------
-- Table structure for fact
-- ----------------------------
DROP TABLE IF EXISTS `fact`;
CREATE TABLE `fact` (
  `fact_id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL,
  `atomic_id` int(11) DEFAULT '0' COMMENT '为0则不为原子事实，非0则对应到原子事实 表的某个记录',
  `type` smallint(1) DEFAULT '0' COMMENT '事实类型，0为默认普通事实（或原子事实），1为中间事实，2为根事实',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`fact_id`) USING BTREE,
  KEY `actomic_id` (`atomic_id`) USING BTREE,
  CONSTRAINT `fact_ibfk_1` FOREIGN KEY (`atomic_id`) REFERENCES `atomic_fact` (`atomic_fact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='事实表';

-- ----------------------------
-- Records of fact
-- ----------------------------
INSERT INTO `fact` VALUES ('1', null, '0', '0', '2020-03-02 16:18:13', '2020-03-02 16:18:13');
INSERT INTO `fact` VALUES ('2', null, '0', '0', '2020-03-02 16:18:13', '2020-03-02 16:18:13');
INSERT INTO `fact` VALUES ('3', '根事实1', '0', '2', '2020-03-02 16:18:13', '2020-03-03 11:45:11');
INSERT INTO `fact` VALUES ('4', null, '1', '0', '2020-03-02 16:18:13', '2020-03-02 16:18:13');
INSERT INTO `fact` VALUES ('5', null, '2', '0', '2020-03-02 16:18:13', '2020-03-02 16:18:13');
INSERT INTO `fact` VALUES ('6', null, '3', '0', '2020-03-02 16:18:13', '2020-03-02 16:18:13');
INSERT INTO `fact` VALUES ('7', null, '4', '0', '2020-03-02 16:18:13', '2020-03-02 16:18:13');
INSERT INTO `fact` VALUES ('8', null, '4', '0', '2020-03-02 16:18:13', '2020-03-02 16:18:13');
INSERT INTO `fact` VALUES ('9', null, '5', '0', '2020-03-02 16:18:13', '2020-03-02 16:18:13');
INSERT INTO `fact` VALUES ('10', '根事实2', '0', '2', '2020-03-02 16:18:13', '2020-03-03 11:45:20');
INSERT INTO `fact` VALUES ('11', null, '0', '0', '2020-03-02 16:18:13', '2020-03-02 16:18:13');
INSERT INTO `fact` VALUES ('12', '中间事实', '0', '1', '2020-03-02 16:18:13', '2020-03-03 11:27:01');

-- ----------------------------
-- Table structure for fact_connective
-- ----------------------------
DROP TABLE IF EXISTS `fact_connective`;
CREATE TABLE `fact_connective` (
  `parent_fact` int(11) NOT NULL COMMENT '父事实',
  `child_fact` int(255) NOT NULL COMMENT '子事实',
  `connective` varchar(255) DEFAULT NULL COMMENT '联结词',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`parent_fact`,`child_fact`) USING BTREE,
  KEY `connective` (`connective`) USING BTREE,
  KEY `child_fact` (`child_fact`) USING BTREE,
  CONSTRAINT `fact_connective_ibfk_1` FOREIGN KEY (`parent_fact`) REFERENCES `fact` (`fact_id`),
  CONSTRAINT `fact_connective_ibfk_2` FOREIGN KEY (`connective`) REFERENCES `connective` (`code`) ON UPDATE CASCADE,
  CONSTRAINT `fact_connective_ibfk_3` FOREIGN KEY (`child_fact`) REFERENCES `fact` (`fact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='事实关联表，记录事实之间的树形联结结构';

-- ----------------------------
-- Records of fact_connective
-- ----------------------------
INSERT INTO `fact_connective` VALUES ('1', '2', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('1', '6', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('1', '7', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('2', '5', 'AND', '2020-03-02 16:18:38', '2020-03-02 17:07:13');
INSERT INTO `fact_connective` VALUES ('2', '6', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('3', '1', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('3', '11', 'AND', '2020-03-02 16:18:38', '2020-03-02 17:07:09');
INSERT INTO `fact_connective` VALUES ('10', '5', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('10', '6', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('11', '10', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('11', '12', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('12', '5', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');
INSERT INTO `fact_connective` VALUES ('12', '6', 'OR', '2020-03-02 16:18:38', '2020-03-02 16:18:38');

-- ----------------------------
-- Table structure for operator
-- ----------------------------
DROP TABLE IF EXISTS `operator`;
CREATE TABLE `operator` (
  `code` varchar(255) NOT NULL COMMENT '符号',
  `name` varchar(255) DEFAULT NULL COMMENT '含义',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='原子事实标操作符';

-- ----------------------------
-- Records of operator
-- ----------------------------
INSERT INTO `operator` VALUES ('!in', '不在', '2020-03-02 16:19:08', '2020-03-02 16:19:08');
INSERT INTO `operator` VALUES ('<', '小于', '2020-03-02 16:19:08', '2020-03-02 16:19:08');
INSERT INTO `operator` VALUES ('<=', '小于或等于', '2020-03-02 16:19:08', '2020-03-02 16:19:08');
INSERT INTO `operator` VALUES ('<>', '不等于', '2020-03-02 16:19:08', '2020-03-02 16:19:08');
INSERT INTO `operator` VALUES ('=', '等于', '2020-03-02 16:19:08', '2020-03-02 16:19:08');
INSERT INTO `operator` VALUES ('>', '大于', '2020-03-02 16:19:08', '2020-03-02 16:19:08');
INSERT INTO `operator` VALUES ('>=', '大于或等于', '2020-03-02 16:19:08', '2020-03-02 16:19:08');
INSERT INTO `operator` VALUES ('in', '存在', '2020-03-02 16:19:08', '2020-03-02 16:19:08');

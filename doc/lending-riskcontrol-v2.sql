/*
Navicat MySQL Data Transfer

Source Server         : 192.168.148.61
Source Server Version : 50722
Source Host           : 192.168.148.61:3306
Source Database       : lending-riskcontrol-v2

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-03-28 15:44:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `action_id` int(11) NOT NULL,
  `method` varchar(255) DEFAULT NULL,
  `param` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES ('1', 'setResult', '0');
INSERT INTO `action` VALUES ('2', 'setResult', '1');
INSERT INTO `action` VALUES ('3', 'setResult', '2');
INSERT INTO `action` VALUES ('4', 'setResult', '3');

-- ----------------------------
-- Table structure for atomic_fact
-- ----------------------------
DROP TABLE IF EXISTS `atomic_fact`;
CREATE TABLE `atomic_fact` (
  `atomic_fact_id` int(11) NOT NULL COMMENT '主键',
  `atomic_fact_function` varchar(255) DEFAULT NULL COMMENT '方法',
  `operator` varchar(255) DEFAULT NULL COMMENT '原子事实操作符',
  `value` varchar(255) DEFAULT NULL COMMENT '右值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`atomic_fact_id`) USING BTREE,
  KEY `fk_op` (`operator`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='原子事实表，一行再加上对应的表行代表一个原子事实，其真值取决于表中的数据';

-- ----------------------------
-- Records of atomic_fact
-- ----------------------------
INSERT INTO `atomic_fact` VALUES ('0', '', '=', null, '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('1', 'InstitutionSore', '<', '551', '2020-03-02 16:16:28', '2020-03-12 11:47:06');
INSERT INTO `atomic_fact` VALUES ('2', 'customer', '=', '女', '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('3', 'customer', '=', '20', '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('4', 'customer', '<>', '30', '2020-03-02 16:16:28', '2020-03-02 16:16:28');
INSERT INTO `atomic_fact` VALUES ('5', 'customer', '=', '男', '2020-03-02 16:16:28', '2020-03-02 16:16:28');

-- ----------------------------
-- Table structure for atomic_fact_param
-- ----------------------------
DROP TABLE IF EXISTS `atomic_fact_param`;
CREATE TABLE `atomic_fact_param` (
  `atomic_fact_id` int(11) NOT NULL COMMENT '原子事实id',
  `index` smallint(10) NOT NULL COMMENT '参数位置',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`atomic_fact_id`,`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of atomic_fact_param
-- ----------------------------

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
  KEY `child_fact` (`child_fact`) USING BTREE
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
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `rule_id` int(11) NOT NULL,
  `fact_id` int(11) DEFAULT NULL,
  `then_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of rule
-- ----------------------------

-- ----------------------------
-- Table structure for rules
-- ----------------------------
DROP TABLE IF EXISTS `rules`;
CREATE TABLE `rules` (
  `rules_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rules_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规则集，若干规则的集合，由若干个rule组成，将作为决策的起点';

-- ----------------------------
-- Records of rules
-- ----------------------------
INSERT INTO `rules` VALUES ('1', '策略1');

-- ----------------------------
-- Table structure for rules_rule
-- ----------------------------
DROP TABLE IF EXISTS `rules_rule`;
CREATE TABLE `rules_rule` (
  `rules_id` int(11) DEFAULT NULL,
  `rule_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规则集-规则关系表，保存指定规则集下有哪些规则';

-- ----------------------------
-- Records of rules_rule
-- ----------------------------

-- ----------------------------
-- Table structure for then
-- ----------------------------
DROP TABLE IF EXISTS `then`;
CREATE TABLE `then` (
  `then_id` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`then_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of then
-- ----------------------------

-- ----------------------------
-- Table structure for t_risk_creditscore
-- ----------------------------
DROP TABLE IF EXISTS `t_risk_creditscore`;
CREATE TABLE `t_risk_creditscore` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户 ID',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `phone` varchar(16) NOT NULL DEFAULT '' COMMENT '手机号',
  `id_number` varchar(32) NOT NULL DEFAULT '' COMMENT '身份证号',
  `institution` varchar(16) NOT NULL DEFAULT '' COMMENT '信用评分机构',
  `score` varchar(8) NOT NULL DEFAULT '' COMMENT '信用评分',
  `rank` varchar(8) NOT NULL DEFAULT '' COMMENT '信用评级',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `expiration_time` datetime NOT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='风控信用评分';

-- ----------------------------
-- Records of t_risk_creditscore
-- ----------------------------
INSERT INTO `t_risk_creditscore` VALUES ('1', '1', '张三', '125226522', '56655555', 'advance', '600', '', '2020-03-11 11:46:14', '2020-03-12 11:46:10');

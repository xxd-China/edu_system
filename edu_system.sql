/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50037
 Source Host           : localhost:3306
 Source Schema         : edu_system

 Target Server Type    : MySQL
 Target Server Version : 50037
 File Encoding         : 65001

 Date: 08/03/2019 21:52:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `collegeID` int(11) NOT NULL DEFAULT '',
  `collegeName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '课程名',
  PRIMARY KEY USING BTREE (`collegeID`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '计算机系');
INSERT INTO `college` VALUES (2, '外贸系');
INSERT INTO `college` VALUES (3, '英语系');
INSERT INTO `college` VALUES (4, '建筑系');
INSERT INTO `college` VALUES (5, '数学系');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `courseID` int(11) NOT NULL DEFAULT '',
  `courseName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '课程名称',
  `teacherID` int(11) NOT NULL DEFAULT '',
  `courseTime` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开课时间',
  `classRoom` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开课地点',
  `courseWeek` int(200) NULL DEFAULT NULL COMMENT '学时',
  `courseType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程类型',
  `collegeID` int(11) NOT NULL DEFAULT '' COMMENT '所属院系',
  `score` int(11) NOT NULL DEFAULT '' COMMENT '学分',
  PRIMARY KEY USING BTREE (`courseID`),
  INDEX `collegeID` USING BTREE(`collegeID`),
  INDEX `teacherID` USING BTREE(`teacherID`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 6144 kB; (`collegeID`) REFER `edu_system/college`(`collegeID`); (`t' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'Java程序设计', 1001, '周三', '科401', 19, '必修课', 1, 5);
INSERT INTO `course` VALUES (2, 'C++', 1001, '周四', 'X402', 18, '必修课', 1, 3);
INSERT INTO `course` VALUES (3, '算法', 1001, '周四', '科401', 18, '必修课', 1, 2);
INSERT INTO `course` VALUES (5, '英语', 1002, '周四', 'X302', 18, '必修课', 2, 2);
INSERT INTO `course` VALUES (6, '高数', 1001, '周五', 'X306', 12, '必修课', 1, 1);
INSERT INTO `course` VALUES (7, '语文', 1002, '周一', 'x101', 12, '选修课', 1, 1);
INSERT INTO `course` VALUES (13, '毛概', 1001, '周六', '是是是', 1, '选修', 1, 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleID` int(11) NOT NULL DEFAULT '',
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `permissions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY USING BTREE (`roleID`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (0, 'admin', NULL);
INSERT INTO `role` VALUES (1, 'teacher', NULL);
INSERT INTO `role` VALUES (2, 'student', NULL);

-- ----------------------------
-- Table structure for selectedcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectedcourse`;
CREATE TABLE `selectedcourse`  (
  `courseID` int(11) NOT NULL DEFAULT '',
  `studentID` int(11) NOT NULL DEFAULT '',
  `mark` int(11) NULL DEFAULT NULL COMMENT '成绩',
  INDEX `courseID` USING BTREE(`courseID`),
  INDEX `studentID` USING BTREE(`studentID`),
  CONSTRAINT `selectedcourse_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `selectedcourse_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 6144 kB; (`courseID`) REFER `edu_system/course`(`courseID`); (`stud' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of selectedcourse
-- ----------------------------
INSERT INTO `selectedcourse` VALUES (1, 10002, 66);
INSERT INTO `selectedcourse` VALUES (1, 10003, 88);
INSERT INTO `selectedcourse` VALUES (2, 10003, 99);
INSERT INTO `selectedcourse` VALUES (5, 10001, 88);
INSERT INTO `selectedcourse` VALUES (3, 10001, 79);
INSERT INTO `selectedcourse` VALUES (1, 10001, 77);
INSERT INTO `selectedcourse` VALUES (1, 10006, 99);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthYear` date NULL DEFAULT NULL COMMENT '出生日期',
  `grade` date NULL DEFAULT NULL COMMENT '入学时间',
  `collegeID` int(11) NOT NULL DEFAULT '' COMMENT '院系id',
  PRIMARY KEY USING BTREE (`userID`),
  INDEX `collegeID` USING BTREE(`collegeID`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 111112 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 6144 kB; (`collegeID`) REFER `edu_system/college`(`collegeID`)' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (123, '11', '男', '1996-09-02', '2015-09-02', 1);
INSERT INTO `student` VALUES (1111, '111', '男', '1996-09-02', '2015-09-02', 1);
INSERT INTO `student` VALUES (10001, '陈钢', '男', '1996-09-02', '2015-09-02', 3);
INSERT INTO `student` VALUES (10002, '王伟', '男', '1995-09-14', '2015-09-02', 3);
INSERT INTO `student` VALUES (10003, '刘晓千', '女', '1996-09-02', '2015-09-02', 2);
INSERT INTO `student` VALUES (10005, '韩雪', '女', '1996-09-02', '2015-09-02', 2);
INSERT INTO `student` VALUES (10006, '王涵', '女', '1996-09-02', '2015-09-02', 1);
INSERT INTO `student` VALUES (10007, '林风', '男', '1996-09-02', '2015-09-02', 1);
INSERT INTO `student` VALUES (10008, '谢菲', '女', '1996-09-02', '2015-09-02', 1);
INSERT INTO `student` VALUES (111111, '111', '男', '1996-09-02', '2015-09-02', 1);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthYear` date NOT NULL DEFAULT '',
  `degree` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `grade` date NULL DEFAULT NULL COMMENT '入职时间',
  `collegeID` int(11) NOT NULL DEFAULT '' COMMENT '院系',
  PRIMARY KEY USING BTREE (`userID`),
  INDEX `collegeID` USING BTREE(`collegeID`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1006 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 6144 kB; (`collegeID`) REFER `edu_system/college`(`collegeID`)' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1001, '刘老师', '女', '1990-03-08', '硕士', '副教授', '2015-09-02', 2);
INSERT INTO `teacher` VALUES (1002, '张老师', '男', '1996-09-02', '本科', '普通教师', '2015-09-02', 1);
INSERT INTO `teacher` VALUES (1003, '谢老师', '男', '1996-09-02', '硕士', '助教', '2017-07-07', 1);
INSERT INTO `teacher` VALUES (1004, '黄老师', '男', '1996-09-02', '本科', '普通教师', '2015-09-02', 1);
INSERT INTO `teacher` VALUES (1005, '李老师', '男', '1995-06-06', '硕士', '副教授', '2013-06-05', 3);

-- ----------------------------
-- Table structure for userlogin
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `role` int(11) NOT NULL DEFAULT 2 COMMENT '角色权限',
  PRIMARY KEY USING BTREE (`userID`),
  INDEX `role` USING BTREE(`role`),
  CONSTRAINT `userlogin_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 6144 kB; (`role`) REFER `edu_system/role`(`roleID`)' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES (1, 'admin', '202cb962ac59075b964b07152d234b70', 0);
INSERT INTO `userlogin` VALUES (8, '10001', '202cb962ac59075b964b07152d234b70', 2);
INSERT INTO `userlogin` VALUES (9, '10002', '202cb962ac59075b964b07152d234b70', 2);
INSERT INTO `userlogin` VALUES (10, '10003', '202cb962ac59075b964b07152d234b70', 2);
INSERT INTO `userlogin` VALUES (11, '10005', '202cb962ac59075b964b07152d234b70', 2);
INSERT INTO `userlogin` VALUES (12, '10004', '202cb962ac59075b964b07152d234b70', 2);
INSERT INTO `userlogin` VALUES (13, '10006', '202cb962ac59075b964b07152d234b70', 2);
INSERT INTO `userlogin` VALUES (14, '1001', '202cb962ac59075b964b07152d234b70', 1);
INSERT INTO `userlogin` VALUES (15, '1002', '202cb962ac59075b964b07152d234b70', 1);
INSERT INTO `userlogin` VALUES (16, '1003', '202cb962ac59075b964b07152d234b70', 1);

SET FOREIGN_KEY_CHECKS = 1;

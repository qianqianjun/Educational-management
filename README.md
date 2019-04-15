# 通知公告

- 现在已经成功导入了css 和 js 资源，有了简单界面，各组负责后端的同学clone 项目到自己电脑试着运行一下。注意： JDK 使用 1.8 数据库使用 mysql 8.0，运行成功之后输入 http://localhost:8080/index 即可看到登录界面，更多界面接口请看 IndexControler 中的定义

- 各组组长注意，在domain 中建立自己子系统对应的表，更新到github 中。

- 排课子系统的数据库还有些问题，尽快找我重新设计一下。

- 各组某些负责移动端网页开发的同学，你们当我是傻子么？ 让你们练习的代码直接用现成的？ 好了，我暂时不追究了，只希望开发的时候别给我掉链子，钻空子的同学你们尽快补上遗漏的技能。还有，别把我当傻子，傻子当不了你们团长！以后老实点 \[捂脸]。

# 作业发布：

作业详情请看下面的地址：[作业地址](https://github.com/qianqianjun/Educational-management/blob/master/worksheet1.md)

## 软件工程项目

---

### 各个分支名称

- 排课子系统： ClassScheduling

- 选课子系统：SelectCourse

- 毕业设计子系统： GraduationProject

- 成绩管理子系统： GradeManagement

- 后台管理系统：BackstageManagement

- #### 使用git branch 可以查看自己所在组的分支

### 初始任务

- 所有成员完成 Jetbrains Intelij IDEA的安装，完成Java 开发环境的搭建，编写运行第一个Java Hello world 程序

- 所有成员完成 git 的安装，注册github 账号。

- 确保安装好了mysql 数据库。

- 确保安装好了 Google Chrome 浏览器

- 简单学习一下 [vue.js](http://www.runoob.com/vue2/vue-tutorial.html) （相信大家之前都已经完成了Html ，css ,js 的基础语法的学习，不熟悉的再看一下）

### 数据库设计（暂定）

- 排课子系统：

  课程表：

  | cno | cname | description | college | status       |     |     |     |
  | --- | ----- | ----------- | ------- | ------------ | --- | --- | --- |
  | 课程号 | 课程名   | 课程描述        | 开课学院    | 课程状态（未审核/审核) |     |     |     |

  排课表：

  | semester | cno | tno  | capacity | classroom | start_week | end_week | teach_time |
  | -------- | --- | ---- | -------- | --------- | ---------- | -------- | ---------- |
  | 学期       | 课程号 | 教师工号 | 课程容量     | 教室编号      | 教学起始周      | 教学结束周    | 上课时间（1-3）  |

- 选课子系统：

  学生表：

  | sno | sname | sex | major | klass | come_year | grade |     |
  | --- | ----- | --- | ----- | ----- | --------- | ----- | --- |
  | 学号  | 姓名    | 性别  | 专业    | 班级    | 入学年份      | 年级*   |     |

  选课表：

  | semester | sno | cno | tno    | grade |     |     |     |
  | -------- | --- | --- | ------ | ----- | --- | --- | --- |
  | 学期       | 学号  | 课程号 | 任课教师工号 | 课程成绩  |     |     |     |

- 成绩管理子系统：

  主要操作选课表

- 后台管理系统：

  学期表：

  | start_year | end_year | semester |     |     |     |     |     |
  | ---------- | -------- | -------- | --- | --- | --- | --- | --- |
  | 起始学年       | 终止学年     | 学期号      |     |     |     |     |     |

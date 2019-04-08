实时传送最新的API

论题表(service层)
	1.根据学生号查找论题	
		public Question getSingleQuestionBySno(int sno)
		
	2.根据专业获取全部论题表信息	
		public List<Question> getQuestionByMajorid(int majorid)
		
	3.根据专业获取论题表部分信息（标题 难度 老师工号 是否已选择）
		public List<QuestionStudentInquiry> getPartQuestionByMajorid(int majorid)
		
	4.根据老师工号查询论题表的全部信息
		 public List<Question> getQuestionByTno(int tno)
		 
	5.根据论题号查询单个题目的全部信息
		public Question getSingleQuestionByQuestionid(int questionid)
		
	6.添加论题#添加成功则返回true#否则返回false
		public Boolean addQuestion(Question question)
		
	7.根据论题号删除论题#必须有该论题且未被选中才能删除#已删除返回true#未删除返回false
		public Boolean deleteQuestionByQuestionid(int questionid)
	
	8.确认学生和选题之间的连接#必须有该论题且该论题未被选中#并且学生也没有被任何其他论题选中#成功返回true#失败返回false
		public Boolean sureQuestionStudent(int questionId,int sno)
		
	
选题表(service层)
	1.往选题表中增加记录#学生不能被论题绑定 论题也不能被任何学生选中#成功返回true 失败返回false
		public Boolean chooseQuestion(int questionid,int sno)
	2.通过论题号和学生号查询某个选题表记录
		public QuestionStudentChoose getChoiceByQidSno(int questionid,int sno)
	3.删除某个选题表的记录#该记录不能是在论题表中绑定过的记录
		public Boolean deleteQuestionStudent(int questionid,int sno)
	4.获取某个学生所有的选题信息
		public List<Question> getQuestionOfStudent(int sno)
		
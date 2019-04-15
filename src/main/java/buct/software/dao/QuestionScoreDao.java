package buct.software.dao;


import buct.software.domain.QuestionScore;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuzhongrui
 */

@Repository
public interface QuestionScoreDao {
    //添加成绩
    public void addQuestionScore(QuestionScore questionScore);

    public List<QuestionScore> getQuestionScoreBySno(int sno);

    public List<QuestionScore> getQuestionScoreByTno(int tno);

}

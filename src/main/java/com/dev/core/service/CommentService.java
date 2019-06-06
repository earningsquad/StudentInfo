package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Comment;
import com.dev.core.pageModel.CommentBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
public class CommentService {

    @Autowired
    @Qualifier("baseDao")
    IBaseDao<Comment> dao;

    //添加留言
    public void postComment(Comment comment){
        dao.save(comment);
    }

    //删除留言
    public boolean deleteComment(Comment comment){
        if(findById(comment.getId())){
            return false;
        }

        if(comment.getParentId() == 0){
            String hql = "delete Comment where pid = " + comment.getId();
            dao.executeHql(hql);
        }
        dao.delete(comment);
        return true;
    }

    public boolean findById(int id){
        String hql = "from Comment where id" + id;
        return dao.find(hql).size() == 0;
    }

    //查看自己的留言
    //动态HQL 根据条件查询
    public List<Comment> findSelfComment(Comment comment){
        Map<String, Object> param = new HashMap();
        StringBuffer hql = new StringBuffer();
        hql.append("From Comment where user.id = :userId");
        param.put("userId",comment.getUser().getId());
        if(comment.getStartTime() != null){
            hql.append(" and createTime >= :startTime ");
            param.put("startTime",comment.getStartTime());
        }
        if(comment.getEndTime() != null){
            hql.append(" and createTime <= :endTime ");
            param.put("endTime",comment.getEndTime());
        }
        return dao.find(hql.toString(),param,comment.getPage(),comment.getLimit());
    }

    //查看所有人的留言
    //动态HQL 根据条件查询
    public List<CommentBlock> findComment(Comment comment){
        Map<String, Object> param = new HashMap();
        StringBuffer hql = new StringBuffer();
        hql.append("From Comment where parentId = 0");
        if(comment.getType() != 0){
            hql.append(" and type = :type");
            param.put("type",comment.getType());
        }
        if(comment.getStartTime() != null){
            hql.append(" and createTime >= :startTime ");
            param.put("startTime",comment.getStartTime());
        }
        if(comment.getEndTime() != null){
            hql.append(" and createTime <= :endTime ");
            param.put("endTime",comment.getEndTime());
        }
        hql.append(" order by createTime desc ");
        List<CommentBlock> resultList = new ArrayList<>();
        List<Comment> commentList = dao.find(hql.toString(),param,comment.getPage(),comment.getLimit());
        for(Comment mes: commentList){
            List<Comment> respComment = findAnswer(mes.getId());
            CommentBlock commentBlock = new CommentBlock(mes,respComment);
            resultList.add(commentBlock);
        }
        return resultList;
    }

    //查询每个留言下的回复
    public List<Comment> findAnswer(int id){
        String hql = "from Comment where pid = " + id;
        return dao.find(hql);
    }

    //回复留言
    public void answerComment(Comment comment){
        dao.save(comment);
    }

    //查询公告
    public List<Comment> findNotice(){
        return dao.find("from Comment where type = 1");
    }

    //添加公告
    public void addNotice(Comment comment){
        dao.save(comment);
    }

    //添加公告
    public void deleteNotice(Comment comment){
        dao.delete(comment);
    }

}

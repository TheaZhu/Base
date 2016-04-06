package com.thea.base.model;

import com.thea.base.bean.User;
import com.thea.base.dao.UserDao;

import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * @author Thea (theazhu0321@gmail.com)
 */
public class UserModel {
    private static final String TAG = UserModel.class.getSimpleName();

    private UserDao mDao;

    public UserModel(UserDao dao) {
        mDao = dao;
    }

    public void add(User user) {
        mDao.insert(user);
    }

    public void add(String phone, String password) {
        add(new User(null, null, phone, password));
    }

    public void delete(User user) {
        mDao.delete(user);
    }

    public void delete(long id) {
        mDao.deleteByKey(id);
    }

    public List<User> searchAll() {
        Query<User> query = mDao.queryBuilder().build();
        return query.list();
    }
}

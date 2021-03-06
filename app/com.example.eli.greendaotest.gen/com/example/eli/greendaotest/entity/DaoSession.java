package com.example.eli.greendaotest.entity;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.eli.greendaotest.gen.Car;
import com.example.eli.greendaotest.gen.People;

import com.example.eli.greendaotest.entity.CarDao;
import com.example.eli.greendaotest.entity.PeopleDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig carDaoConfig;
    private final DaoConfig peopleDaoConfig;

    private final CarDao carDao;
    private final PeopleDao peopleDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        carDaoConfig = daoConfigMap.get(CarDao.class).clone();
        carDaoConfig.initIdentityScope(type);

        peopleDaoConfig = daoConfigMap.get(PeopleDao.class).clone();
        peopleDaoConfig.initIdentityScope(type);

        carDao = new CarDao(carDaoConfig, this);
        peopleDao = new PeopleDao(peopleDaoConfig, this);

        registerDao(Car.class, carDao);
        registerDao(People.class, peopleDao);
    }
    
    public void clear() {
        carDaoConfig.clearIdentityScope();
        peopleDaoConfig.clearIdentityScope();
    }

    public CarDao getCarDao() {
        return carDao;
    }

    public PeopleDao getPeopleDao() {
        return peopleDao;
    }

}

package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.domain.Gender;
import com.infoshareacademy.searchengine.domain.Phone;
import com.infoshareacademy.searchengine.domain.User;
import com.infoshareacademy.searchengine.interceptors.AddUserInterceptor;
import com.infoshareacademy.searchengine.interceptors.AddUserSetGenderInterceptor;
import com.infoshareacademy.searchengine.repository.StatisticsRepository;
import com.infoshareacademy.searchengine.repository.UsersRepository;
import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;


import javax.ejb.EJB;
import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.assertj.core.api.AbstractListAssert.*;
import org.junit.runners.JUnit4;

@RunWith(Arquillian.class)
public class UsersRepositoryDaoBeanTest {

    @EJB
    private UsersRepositoryDao usersRepositoryDao;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addClasses(
                        UsersRepositoryDao.class,
                        UsersRepositoryDaoBean.class,
                        UsersRepositoryDaoRemote.class,
                        UsersRepository.class,
                        AddUserInterceptor.class,
                        AddUserSetGenderInterceptor.class,
                        User.class,
                        Gender.class,
                        Phone.class,
                        StatisticsRepositoryDao.class,
                        StatisticsRepositoryDaoBean.class,
                        StatisticsRepositoryDaoBeanTest.class,
                        StatisticsRepository.class,
                        Assertions.class,
                        AbstractListAssert.class,
                        Assert.class,
                        List.class
                );
    }

    @Test
    @InSequence(2)
    public void addUser() throws Exception {
        User user = new User();
        user.setName("Tomasz");
        usersRepositoryDao.addUser(user);
        assertEquals(Gender.MAN, usersRepositoryDao.getUsersList().get(1).getGender());
    }

    @Test
    @InSequence(1)
    public void intercept() throws Exception {
        User user = new User();
        user.setName("Anna");
        usersRepositoryDao.addUser(user);
        assertEquals(Gender.WOMAN, usersRepositoryDao.getUsersList().get(0).getGender());
    }

    @Test
    public void getUsersList() throws Exception {

    }
}
package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.domain.Gender;
import com.infoshareacademy.searchengine.domain.Phone;
import com.infoshareacademy.searchengine.domain.User;
import com.infoshareacademy.searchengine.interceptors.AddUserInterceptor;
import com.infoshareacademy.searchengine.interceptors.AddUserSetGenderInterceptor;
import com.infoshareacademy.searchengine.repository.UsersRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.*;


import javax.ejb.EJB;

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
                        UsersRepositoryDaoRemote.class,
                        UsersRepository.class,
                        AddUserInterceptor.class,
                        AddUserSetGenderInterceptor.class,
                        User.class,
                        Gender.class,
                        Phone.class
                );
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        usersRepositoryDao.addUser(user);
    }
/*
    @Test
    public void intercept() throws Exception {
        User user = new User();
        user.setName("Anna");
        usersRepositoryDao.addUser(user);
        assertThat(usersRepositoryDao.getUsersList().get(0).getGender()).isEqualTo(Gender.WOMAN);
    }*/
}
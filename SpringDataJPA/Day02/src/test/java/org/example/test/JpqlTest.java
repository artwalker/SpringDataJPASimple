package org.example.test;

import org.example.dao.ICustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HackerStar
 * @create 2020-05-16 20:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {
    @Autowired
    private ICustomerDao customerDao;

    @Test
    public void testFindJpql() {
        System.out.println(customerDao.findJpql("Apple"));
    }

    @Test
    public void testFindAll() {
        customerDao.findAllCustomer().forEach(customer -> System.out.println(customer));
    }

    @Test
    public void testFindCustNameAndId() {
        System.out.println(customerDao.findCustNameAndId(3l, "Apple"));
    }

    /**
     * 测试jpql的更新操作
     *  * springDataJpa中使用jpql完成 更新/删除操作
     *         * 需要手动添加事务的支持
     *         * 默认会执行结束之后，回滚事务
     *   @Rollback : 设置是否自动回滚
     *          false | true
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer() {
        customerDao.updataCustomer("Google", 3l);
    }

    /**
     * 测试sql查询
     */
    @Test
    public void testFindSql() {
        customerDao.findSql("Goo%").forEach(customer-> System.out.println(customer));
    }

    /**
     * 测试方法命名规则的查询
     */
    @Test
    public void testNaming() {
        System.out.println(customerDao.findByCustName("Google"));
    }

    /**
     * 测试方法命名规则的查询
     */
    @Test
    public void testFindByCustNameLike() {
        customerDao.findByCustNameLike("Goo%").forEach(customer -> System.out.println(customer));
    }

    /**
     * 测试方法命名规则的查询
     */
    @Test
    public void findByCustNameLikeAndcusAndCustIndustry() {
        System.out.println(customerDao.findByCustNameLikeAndCustIndustry("Goo%", "software"));
    }
}

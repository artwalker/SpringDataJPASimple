package org.example.test;

import org.example.dao.ICustomerDao;
import org.example.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HackerStar
 * @create 2020-05-16 19:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    private ICustomerDao customerDao;

    /**
     * 保存客户：调用save(obj)方法
     *      save：保存或者更新
     *          根据传递的对象是否存在主键id，
     *              如果没有id主键属性：保存
     *              如果存在id主键属性，先根据id查询数据，之后更新数据
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("Google");
        customerDao.save(customer);
    }

    /**
     * 修改客户：调用save(obj)方法
     */
    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustId(3l);
        customer.setCustName("Apple");
        customer.setCustIndustry("software");
        customerDao.save(customer);
    }

    /**
     * 删除客户：调用delete(id)方法
     */
    @Test
    public void testDelete() {
        customerDao.delete(5l);
    }

    /**
     * 根据id查询：调用findOne(id)方法
     */
    @Test
    public void testFindOne() {
        System.out.println(customerDao.findOne(3l));
    }

    /**
     * 根据id从数据库查询
     *      @Transactional : 保证getOne正常运行
     *
     *  findOne：
     *      em.find()           :立即加载
     *  getOne：
     *      em.getReference     :延迟加载
     *      * 返回的是一个客户的动态代理对象
     *      * 什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void testGetOne() {
        System.out.println(customerDao.getOne(3l));
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        customerDao.findAll().forEach(customer-> System.out.println(customer));
    }

    /**
     * 统计查询：查询客户的总数量
     *      count：统计总条数
     */
    @Test
    public void testCount() {
        long count = customerDao.count();//查询全部的客户数量
        System.out.println(count);
    }

    /**
     * 测试：判断id为4的客户是否存在
     *      1. 可以查询以下id为4的客户
     *          如果值为空，代表不存在，如果不为空，代表存在
     *      2. 判断数据库中id为4的客户的数量
     *          如果数量为0，代表不存在，如果大于0，代表存在
     */
    @Test
    public void testExists() {
        boolean exists = customerDao.exists(3l);
        System.out.println("id为3的用户是否存在" + exists);
    }
}

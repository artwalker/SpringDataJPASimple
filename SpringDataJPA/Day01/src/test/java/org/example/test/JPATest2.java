package org.example.test;

import org.example.utils.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 * @author HackerStar
 * @create 2020-05-15 19:46
 */
public class JPATest2 {

    /**
     * 查询全部
     *      jqpl：from org.example.domain.Customer （包名可以省略）
     *      sql：SELECT * FROM cst_customer
     */
    @Test
    public void findAll() {
        //1.获取EntityManager对象
        EntityManager entityManager = JPAUtil.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3. 查询全部
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        resultList.forEach(customer -> System.out.println(customer));
        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        entityManager.close();
    }

    /**
     * 排序查询： 倒序查询全部客户（根据id倒序）
     *      sql：SELECT * FROM cst_customer ORDER BY cust_id DESC
     *      jpql：from Customer order by custId desc
     *
     * 进行jpql查询
     *      1.创建query查询对象
     *      2.对参数进行赋值
     *      3.查询，并得到返回结果
     */
    @Test
    public void orderQuery() {
        //1.获取EntityManager对象
        EntityManager entityManager = JPAUtil.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3. 查询全部
        String jpql = "from Customer order by custId desc ";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        resultList.forEach(customer -> System.out.println(customer));
        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        entityManager.close();
    }

    /**
     * 使用jpql查询，统计客户的总数
     *      sql：SELECT COUNT(cust_id) FROM cst_customer
     *      jpql：select count(custId) from Customer
     */
    @Test
    public void count() {
        //1.获取EntityManager对象
        EntityManager entityManager = JPAUtil.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3. 查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql = "select count(custId) from Customer ";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        //ii.对参数赋值
        //iii.发送查询，并封装结果
        //发送查询，并封装结果集
        Object result = query.getSingleResult();
        System.out.println(result);
        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        entityManager.close();
    }

    /**
     * 分页查询
     *      sql：select * from cst_customer limit 0,2
     *      jqpl : from Customer
     */
    @Test
    public void page() {
        //1.获取EntityManager对象
        EntityManager entityManager = JPAUtil.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3. 查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql = "from Customer ";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        //ii.对参数赋值
        //起始索引
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);
        //iii.发送查询，并封装结果
        /**
         * getResultList ： 直接将查询结果封装为list集合
         * getSingleResult : 得到唯一的结果集
         */
        query.getResultList().forEach(customer -> System.out.println(customer));
        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        entityManager.close();
    }

    /**
     * 条件查询
     *     案例：查询客户名称以‘传智播客’开头的客户
     *          sql：SELECT * FROM cst_customer WHERE cust_name LIKE  ?
     *          jpql : from Customer where custName like ?
     */
    /**
     * 分页查询
     *      sql：select * from cst_customer limit 0,2
     *      jqpl : from Customer
     */
    @Test
    public void queryByCondition() {
        //1.获取EntityManager对象
        EntityManager entityManager = JPAUtil.getEntityManager();
        //2. 开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3. 查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql = "from Customer where custName like ? ";
        Query query = entityManager.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        //ii.对参数赋值
        //第一个参数：占位符的索引位置（从1开始），第二个参数：取值
        query.setParameter(1, "Apple%");
        //iii.发送查询，并封装结果
        /**
         * getResultList ： 直接将查询结果封装为list集合
         * getSingleResult : 得到唯一的结果集
         */
        query.getResultList().forEach(customer -> System.out.println(customer));
        //4. 提交事务
        transaction.commit();
        //5. 释放资源
        entityManager.close();
    }
}

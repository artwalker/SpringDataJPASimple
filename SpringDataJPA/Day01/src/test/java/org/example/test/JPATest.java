package org.example.test;

import org.example.domain.Customer;
import org.example.utils.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author HackerStar
 * @create 2020-05-15 16:55
 */
public class JPATest {
    /**
     * 测试jpa的保存
     * 案例：保存一个客户到数据库中
     * Jpa的操作步骤
     * 1.加载配置文件创建工厂（实体管理器工厂）对象
     * 2.通过实体管理器工厂获取实体管理器
     * 3.获取事务对象，开启事务
     * 4.完成增删改查操作
     * 5.提交事务（回滚事务）
     * 6.释放资源
     */
    @Test
    public void testSave() {
        //创建实体管理类工厂，借助Persistence的静态方法获取
        //其中传递的参数为持久化单元名称，需要jpa配置文件中指定
        //1.加载配置文件从而创建实体管理器工厂对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理器工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
        //3.获取事物对象，开启事物
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //4.完成增删改查操作-----保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("Github");
        customer.setCustIndustry("代码管理");
        //保存
        em.persist(customer);
        //5.提交事物
        tx.commit();
        //6.释放资源
        em.close();
        factory.close();
    }

    /**
     * 保存
     */
    @Test
    public void add() {
        //定义对象
        Customer customer = new Customer();
        customer.setCustName("Linux");
        customer.setCustLevel("普通用户");
        customer.setCustSource("网络");
        customer.setCustIndustry("操作系统");
        customer.setCustAddress("USA");
        customer.setCustPhone("666666");

        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事物对象
            tx = em.getTransaction();
            //开启事物
            tx.begin();
            //执行操作
            em.persist(customer);
            //提交事务
            tx.commit();
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //释放资源
            em.close();
        }
    }

    /**
     * 根据id查询客户
     *  使用find方法查询：
     *      1.查询的对象就是当前客户对象本身
     *      2.在调用find方法的时候，就会发送sql语句查询数据库
     *
     *  立即加载
     */
    @Test
    public void queryById() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事物对象
            tx = em.getTransaction();
            //开启事务
            tx.begin();
            //执行操作
            /**
             * find : 根据id查询数据
             *      class：查询数据的结果需要包装的实体类类型的字节码
             *      id：查询的主键的取值
             */
            Customer customer = em.find(Customer.class, 4l);
            //提交事务
            tx.commit();
            System.out.println(customer);
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //释放资源
            em.close();
        }
    }

    /**
     * 根据id查询客户
     *      getReference方法
     *          1.获取的对象是一个动态代理对象
     *          2.调用getReference方法不会立即发送sql语句查询数据库
     *              * 当调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询数据库
     *
     * 延迟加载（懒加载）
     *      * 得到的是一个动态代理对象
     *      * 什么时候用，什么使用才会查询
     */
    @Test
    public void queryById2() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事物对象
            tx = em.getTransaction();
            //开启事务
            tx.begin();
            //执行操作
            /**
             * getReference : 根据id查询数据
             *      class：查询数据的结果需要包装的实体类类型的字节码
             *      id：查询的主键的取值
             */
            Customer customer = em.getReference(Customer.class, 4l);
            //提交事务
            tx.commit();
            System.out.println(customer);
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //释放资源
            em.close();
        }
    }

    /**
     * 删除
     */
    @Test
    public void remove() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事物对象
            tx = em.getTransaction();
            //开启事物
            tx.begin();
            //执行删除操作
            //i 根据id查询客户
            Customer customer = em.find(Customer.class, 2l);
            //ii 调用remove方法完成删除操作
            em.remove(customer);
            //提交事务
            tx.commit();
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //释放资源
            em.close();
        }
    }

    /**
     * 更新
     *    merge(Object)
     */
    @Test
    public void update() {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事物对象
            tx = em.getTransaction();
            //开启事物
            tx.begin();
            //执行删除操作
            //i 根据id查询客户
            Customer customer = em.find(Customer.class, 3l);
            //ii 更新客户
            customer.setCustName("Apple");
            em.merge(customer);
            //提交事务
            tx.commit();
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //释放资源
            em.close();
        }
    }
}
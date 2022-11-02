package test;


import com.southwind.dao.CustomerDao;
import com.southwind.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao dao;
    @Test
    public void testJpqlFind(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custId = root.get("custId");
                Predicate equal = criteriaBuilder.equal(custId, 5);
                return equal;
            }
        };
        Customer one = dao.findOne(specification);
            System.out.println(one);
    }
    @Test
    public void testJpqlFindAll(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate equal = criteriaBuilder.equal(custName, "wuxicai");
                return equal;
            }
        };
        List<Customer> all = dao.findAll(specification);
        for(Customer one:all){
            System.out.println(one);
        }

    }
    @Test
    public void specand(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate equal = criteriaBuilder.equal(custName, "wuxicai");
                Path<Object> custId = root.get("custId");
                Predicate equal2 = criteriaBuilder.equal(custId, "4");
                Predicate and = criteriaBuilder.and(equal, equal2);
                return and;
            }
        };
        List<Customer> all = dao.findAll(specification);
        for(Customer one:all){
            System.out.println(one);
        }

    }
    @Test
    public void speclike(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate equal = criteriaBuilder.like(custName.as(String.class), "wuxi%");
                Path<Object> custId = root.get("custId");
                Predicate equal2 = criteriaBuilder.equal(custId, "4");
                Predicate and = criteriaBuilder.and(equal, equal2);
                return and;
            }
        };
        List<Customer> all = dao.findAll(specification);
        for(Customer one:all){
            System.out.println(one);
        }

    }
    @Test
    public void specsort(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate equal = criteriaBuilder.like(custName.as(String.class), "wuxi%");
                return equal;
            }
        };
        Sort orders = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> all = dao.findAll(specification,orders);
        for(Customer one:all){
            System.out.println(one);
        }

    }
    @Test
    public void specpage(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Predicate equal = criteriaBuilder.like(custName.as(String.class), "wuxi%");
                return equal;
            }
        };
        PageRequest pageRequest = new PageRequest(0,4);
        Page<Customer> all = dao.findAll(specification, pageRequest);
        System.out.println(all.getTotalElements());
        System.out.println(all.getTotalPages());
        for(Customer one:all){
            System.out.println(one);
        }

    }
}

package com.example.jnu_tijian.config;

import com.example.jnu_tijian.entity.Setmeal;
import com.example.jnu_tijian.mapper.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    private final SqlSessionFactory sqlSessionFactory;

    public MapperConfig(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Bean
    public UsersMapper usersMapper() {
        return sqlSessionFactory.openSession().getMapper(UsersMapper.class);
    }

    @Bean
    public HospitalMapper hospitalMapper() {
        return sqlSessionFactory.openSession().getMapper(HospitalMapper.class);
    }

    @Bean
    public SetmealMapper setmealMapper() {return sqlSessionFactory.openSession().getMapper(SetmealMapper.class);}

    @Bean
    public SetmealdetailedMapper setmealdetailedMapper() {return sqlSessionFactory.openSession().getMapper(SetmealdetailedMapper.class);}

    @Bean
    public CheckitemMapper checkitemMapper() {return sqlSessionFactory.openSession().getMapper(CheckitemMapper.class);}

    @Bean
    public OrdersMapper ordersMapper() {return sqlSessionFactory.openSession().getMapper(OrdersMapper.class);}

    @Bean
    public DoctorMapper doctorMapper() {return sqlSessionFactory.openSession().getMapper(DoctorMapper.class);}
}
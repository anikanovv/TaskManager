package ru.anikanov.tm.utils;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.repository.ProjectMapper;
import ru.anikanov.tm.repository.SessionMapper;
import ru.anikanov.tm.repository.TaskMapper;
import ru.anikanov.tm.repository.UserMapper;

import javax.sql.DataSource;

public class SqlSessionFactory {
    public org.apache.ibatis.session.SqlSessionFactory getSqlSessionFactory() {
        @Nullable final String user = "root";
        @Nullable final String password = "root";
        @Nullable final String url = "jdbc:mysql://localhost:3306/taskmanager";
        @Nullable final String driver = "com.mysql.jdbc.Driver";
        final DataSource dataSource =
                new PooledDataSource(driver, url, user, password);
        final TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        final Environment environment =
                new Environment("development", transactionFactory, dataSource);
        final Configuration configuration = new Configuration(environment);
        configuration.addMapper(TaskMapper.class);
        configuration.addMapper(SessionMapper.class);
        configuration.addMapper(ProjectMapper.class);
        configuration.addMapper(UserMapper.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}

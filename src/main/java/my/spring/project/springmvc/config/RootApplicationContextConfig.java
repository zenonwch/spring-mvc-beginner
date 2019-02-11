package my.spring.project.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("my.spring.project.springmvc")
public class RootApplicationContextConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/sql/create-table.sql")
                .addScript("db/sql/insert-data.sql")
                .build();
    }

    @Bean
    public NamedParameterJdbcTemplate getJdbcTemplate() {
        final DataSource dataSource = dataSource();
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
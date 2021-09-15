package com.thesis.visualizationserverthesis.service;

import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.junit4.SpringRunner;

import javax.script.ScriptException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ImpactServiceImplTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private ImpactService impactService;

    private static final String SCHEMA_SCRIPT = "sql/schema.sql";
    private static final String DATA_SCRIPT = "sql/data.sql";

    @Before
    public void before() throws ScriptException, SQLException {
        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(),new ClassPathResource(SCHEMA_SCRIPT));
        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(),new ClassPathResource(DATA_SCRIPT));
    }

    @Test
    public void searchPreventiveActionsTestForAFilterWithNoStateRequested(){
        //given
        PreventiveActionsFilter filter = new PreventiveActionsFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,1,30));
        filter.setDistrict(0L);
        filter.setProvince(0L);
        filter.setState(0L);

        //when
        val response = impactService.searchPreventiveActions(filter);

        //then
        log.info("el response {}",response.getPreventiveActions().size());
        assertEquals(3L, response.getPreventiveActions().size());
    }
}
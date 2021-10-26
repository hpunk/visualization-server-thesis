package com.thesis.visualizationserverthesis.service;

import com.thesis.visualizationserverthesis.model.api.ImpactViolenceCasesFilter;
import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;
import lombok.SneakyThrows;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.script.ScriptException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ActiveProfiles("test")
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

    @Test
    public void searchPreventiveActionsTestForAFilterWithStateRequested(){
        //given
        PreventiveActionsFilter filter = new PreventiveActionsFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,12,30));
        filter.setDistrict(0L);
        filter.setProvince(0L);
        filter.setState(4L);

        //when
        val response = impactService.searchPreventiveActions(filter);

        //then
        log.info("el response {}",response.getPreventiveActions().size());
        assertEquals(1L, response.getPreventiveActions().size());
    }

    @Test
    public void searchPreventiveActionsTestForAFilterWithStateAndProvinceRequested(){
        //given
        PreventiveActionsFilter filter = new PreventiveActionsFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,12,30));
        filter.setDistrict(0L);
        filter.setProvince(302L);
        filter.setState(3L);

        //when
        val response = impactService.searchPreventiveActions(filter);

        //then
        log.info("el response {}",response.getPreventiveActions().size());
        assertEquals(1L, response.getPreventiveActions().size());
    }

    @Test
    public void searchPreventiveActionsTestForAFilterWithStateAndProvinceAndDistrictRequested(){
        //given
        PreventiveActionsFilter filter = new PreventiveActionsFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,12,30));
        filter.setDistrict(30109L);
        filter.setProvince(301L);
        filter.setState(3L);

        //when
        val response = impactService.searchPreventiveActions(filter);

        //then
        log.info("el response {}",response.getPreventiveActions().size());
        assertEquals(1L, response.getPreventiveActions().size());
    }

    @Test
    @SneakyThrows
    public void getViolenceCasesForImpactTestForAFilterWithNoStateRequested(){
        //given
        ImpactViolenceCasesFilter filter = new ImpactViolenceCasesFilter();
        filter.setAppDateStart(LocalDate.of(2017,1,1));
        filter.setAppDateEnd(LocalDate.of(2017,1,30));
        filter.setDaysAfter(2);
        filter.setDaysBefore(2);
        filter.setDistrict(0L);
        filter.setProvince(0L);
        filter.setState(0L);

        //when
        val response = impactService.getViolenceCasesForImpact(filter);

        //then
        log.info("el response {}",response.getPhysicalV().size());
        assertEquals(34L, response.getPhysicalV().size());
        assertEquals(Optional.of(2L).get(),response.getPsychologicalV().get(3));
    }

    @Test
    @SneakyThrows
    public void getViolenceCasesForImpactTestForAFilterWithStateRequested(){
        //given
        ImpactViolenceCasesFilter filter = new ImpactViolenceCasesFilter();
        filter.setAppDateStart(LocalDate.of(2017,2,3));
        filter.setAppDateEnd(LocalDate.of(2017,2,5));
        filter.setDaysAfter(2);
        filter.setDaysBefore(2);
        filter.setDistrict(0L);
        filter.setProvince(0L);
        filter.setState(4L);

        //when
        val response = impactService.getViolenceCasesForImpact(filter);

        //then
        log.info("el response {}",response.getPhysicalV().size());
        assertEquals(Optional.of(2L).get(), response.getPsychologicalV().get(6));
        assertEquals(7L, response.getPsychologicalV().size());
    }

    @Test
    @SneakyThrows
    public void getViolenceCasesForImpactTestForAFilterWithStateAndProvinceRequested(){
        //given
        ImpactViolenceCasesFilter filter = new ImpactViolenceCasesFilter();
        filter.setAppDateStart(LocalDate.of(2017,3,3));
        filter.setAppDateEnd(LocalDate.of(2017,3,7));
        filter.setDaysAfter(2);
        filter.setDaysBefore(2);
        filter.setDistrict(0L);
        filter.setProvince(303L);
        filter.setState(3L);

        //when
        val response = impactService.getViolenceCasesForImpact(filter);

        //then
        log.info("el response {}",response.getPhysicalV().size());
        assertEquals(9L, response.getPsychologicalV().size());
        assertEquals(Optional.of(2L).get(), response.getPsychologicalV().get(8));
    }

    @Test
    @SneakyThrows
    public void getViolenceCasesForImpactTestForAFilterWithStateAndProvinceAndDistrictRequested(){
        //given
        ImpactViolenceCasesFilter filter = new ImpactViolenceCasesFilter();
        filter.setAppDateStart(LocalDate.of(2017,2,3));
        filter.setAppDateEnd(LocalDate.of(2017,2,7));
        filter.setDaysAfter(2);
        filter.setDaysBefore(2);
        filter.setDistrict(60201L);
        filter.setProvince(602L);
        filter.setState(6L);

        //when
        val response = impactService.getViolenceCasesForImpact(filter);

        //then
        log.info("el response {}",response.getPhysicalV().size());
        assertEquals(9L, response.getPsychologicalV().size());
        assertEquals(Optional.of(1L).get(), response.getSexualV().get(2));
    }

    @Test
    public void getAppPerDayTestForAFilterWithNoStateRequested(){
        //given
        PreventiveActionsFilter filter = new PreventiveActionsFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,1,30));
        filter.setDistrict(0L);
        filter.setProvince(0L);
        filter.setState(0L);

        //when
        val response = impactService.getAppPerDay(filter);

        //then
        log.info("el response {}",response);
        assertEquals(Optional.of(2).get(), response.get(19).getCount());
    }

    @Test
    public void getAppPerDayTestForAFilterWithStateRequested(){
        //given
        PreventiveActionsFilter filter = new PreventiveActionsFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,12,30));
        filter.setDistrict(0L);
        filter.setProvince(0L);
        filter.setState(4L);

        //when
        val response = impactService.getAppPerDay(filter);

        //then
        log.info("el response {}",response);
        assertEquals(Optional.of(1).get(), response.get(29).getCount());
    }

    @Test
    public void getAppPerDayTestForAFilterWithStateAndProvinceRequested(){
        //given
        PreventiveActionsFilter filter = new PreventiveActionsFilter();
        filter.setStartDate(LocalDate.of(2017,11,1));
        filter.setEndDate(LocalDate.of(2017,12,30));
        filter.setDistrict(0L);
        filter.setProvince(302L);
        filter.setState(3L);

        //when
        val response = impactService.getAppPerDay(filter);

        //then
        assertEquals(Optional.of(1).get(), response.get(19).getCount());
    }

    @Test
    public void getAppPerDayTestForAFilterWithStateAndProvinceAndDistrictRequested(){
        //given
        PreventiveActionsFilter filter = new PreventiveActionsFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,12,30));
        filter.setDistrict(30109L);
        filter.setProvince(301L);
        filter.setState(3L);

        //when
        val response = impactService.getAppPerDay(filter);

        //then
        assertEquals(Optional.of(1).get(), response.get(19).getCount());
    }
}
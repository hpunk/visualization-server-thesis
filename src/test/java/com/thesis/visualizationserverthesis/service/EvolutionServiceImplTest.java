package com.thesis.visualizationserverthesis.service;

import com.thesis.visualizationserverthesis.model.api.EvolutionCasesByMonthFilter;
import com.thesis.visualizationserverthesis.model.api.EvolutionDetailedCasesFilter;
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
public class EvolutionServiceImplTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private EvolutionService evolutionService;

    private static final String SCHEMA_SCRIPT = "sql/schema.sql";
    private static final String DATA_SCRIPT = "sql/data.sql";

    @Before
    public void before() throws ScriptException, SQLException {
        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(),new ClassPathResource(SCHEMA_SCRIPT));
        ScriptUtils.executeSqlScript(jdbc.getDataSource().getConnection(),new ClassPathResource(DATA_SCRIPT));
    }

    @Test
    public void getCasesByUbigeoForMonthTestWithNoCasesInDateRangeAtFilter(){
        //given
        EvolutionCasesByMonthFilter filter = new EvolutionCasesByMonthFilter();
        filter.setStartDate(LocalDate.of(2019,1,1));
        filter.setEndDate(LocalDate.of(2019,1,15));
        filter.setState(1L);
        filter.setFilterBy("STATE");

        //when
        val response = evolutionService.getCasesByUbigeoForMonth(filter);

        //then
        log.info("el response {}",response);
        assertEquals(0,response.get(0).getCasesByUbigeo().size());
    }

    @Test
    public void getCasesByUbigeoForMonthTestWithCasesInDateRangeAtFilter(){
        //given
        EvolutionCasesByMonthFilter filter = new EvolutionCasesByMonthFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,3,30));
        filter.setFilterBy("STATE");

        //when
        val response = evolutionService.getCasesByUbigeoForMonth(filter);

        //then
        log.info("el response {}",response);
        assertEquals(1, response.get(0).getCasesByUbigeo().size());
        val femaleCases = response.get(0).getCasesByUbigeo().get(0).getCasesFemale();
        val expectedFemaleCases = 16L;
        assertEquals(Optional.ofNullable(expectedFemaleCases).get(), femaleCases);
        val maleCases = response.get(0).getCasesByUbigeo().get(0).getCasesMale();
        val expectedMaleCases = 2L;
        assertEquals(Optional.ofNullable(expectedMaleCases).get(), maleCases);
    }

    @Test
    public void getCasesByUbigeoForMonthTestWithCasesInDateRangeAtFilterAndUsingProvinceDelimiter(){
        //given
        EvolutionCasesByMonthFilter filter = new EvolutionCasesByMonthFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,1,30));
        filter.setState(3L);
        filter.setFilterBy("PROVINCE");

        //when
        val response = evolutionService.getCasesByUbigeoForMonth(filter);

        //then
        assertEquals(1, response.get(0).getCasesByUbigeo().size());
        val femaleCases = response.get(0).getCasesByUbigeo().get(0).getCasesFemale();
        val expectedFemaleCases = 16L;
        assertEquals(Optional.ofNullable(expectedFemaleCases).get(), femaleCases);
        val maleCases = response.get(0).getCasesByUbigeo().get(0).getCasesMale();
        val expectedMaleCases = 2L;
        assertEquals(Optional.ofNullable(expectedMaleCases).get(), maleCases);
    }

    @Test
    public void getCasesByUbigeoForMonthTestWithCasesInDateRangeOnlyForFemaleVictims(){
        //given
        EvolutionCasesByMonthFilter filter = new EvolutionCasesByMonthFilter();
        filter.setStartDate(LocalDate.of(2017,3,1));
        filter.setEndDate(LocalDate.of(2017,3,30));
        filter.setState(3L);
        filter.setFilterBy("PROVINCE");

        //when
        val response = evolutionService.getCasesByUbigeoForMonth(filter);

        //then
        assertEquals(1, response.get(0).getCasesByUbigeo().size());
        val femaleCases = response.get(0).getCasesByUbigeo().get(0).getCasesFemale();
        val expectedFemaleCases = 0L;
        assertEquals(Optional.ofNullable(expectedFemaleCases).get(), femaleCases);
        val maleCases = response.get(0).getCasesByUbigeo().get(0).getCasesMale();
        val expectedMaleCases = 2L;
        assertEquals(Optional.ofNullable(expectedMaleCases).get(), maleCases);
    }

    @Test
    public void getCasesByUbigeoForMonthTestWithCasesInDateRangeOnlyForMaleVictims(){
        //given
        EvolutionCasesByMonthFilter filter = new EvolutionCasesByMonthFilter();
        filter.setStartDate(LocalDate.of(2017,2,1));
        filter.setEndDate(LocalDate.of(2017,2,28));
        filter.setState(3L);
        filter.setFilterBy("PROVINCE");

        //when
        val response = evolutionService.getCasesByUbigeoForMonth(filter);

        //then
        assertEquals(1, response.get(0).getCasesByUbigeo().size());
        val femaleCases = response.get(0).getCasesByUbigeo().get(0).getCasesFemale();
        val expectedFemaleCases = 1L;
        assertEquals(Optional.ofNullable(expectedFemaleCases).get(), femaleCases);
        val maleCases = response.get(0).getCasesByUbigeo().get(0).getCasesMale();
        val expectedMaleCases = 0L;
        assertEquals(Optional.ofNullable(expectedMaleCases).get(), maleCases);
    }

    @Test
    public void getDetailedCasesByWeekTestWithCasesInDateRangeAndFilteringByState(){
        //given
        EvolutionDetailedCasesFilter filter = new EvolutionDetailedCasesFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,3,30));
        filter.setState(3L);
        filter.setFilterBy("STATE");

        //when
        val response = evolutionService.getDetailedCasesByWeek(filter);

        //then
        log.info("el response {}",response);
        assertEquals(Optional.of(1L).get(),response.get(0).getManPsychological());
        assertEquals(Optional.of(1L).get(),response.get(1).getManPsychological());
    }

    @Test
    public void getDetailedCasesByWeekTestWithCasesInDateRangeAndFilteringByProvince(){
        //given
        EvolutionDetailedCasesFilter filter = new EvolutionDetailedCasesFilter();
        filter.setStartDate(LocalDate.of(2017,1,1));
        filter.setEndDate(LocalDate.of(2017,3,30));
        filter.setState(3L);
        filter.setProvince(302L);
        filter.setFilterBy("PROVINCE");

        //when
        val response = evolutionService.getDetailedCasesByWeek(filter);

        //then
        log.info("el response {}",response);
        assertEquals(Optional.of(5L).get(),response.get(0).getWomanPsychological());
        assertEquals(Optional.of(1L).get(),response.get(0).getWomanSexual());
    }
}

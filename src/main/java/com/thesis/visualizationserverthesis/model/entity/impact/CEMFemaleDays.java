package com.thesis.visualizationserverthesis.model.entity.impact;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name="cem_female_victim_days")
public class CEMFemaleDays {
    @Id
    @Column(name="case_id")
    private Long caseId;
    @Column(name="state_peru")
    private Long stateCode;
    @Column(name="province_peru")
    private Long provinceCode;
    @Column(name="district_peru")
    private Long districtCode;
    @Column(name="case_date")
    private LocalDate caseDate;
    @Column(name="physical_v_count")
    private Long physicalVCount;
    @Column(name="sexual_v_count")
    private Long sexualVCount;
    @Column(name="economical_v_count")
    private Long economicalVCount;
    @Column(name="psychological_v_count")
    private Long psychologicalVCount;
    @Column(name="first_time_count")
    private Long firstTimeCount;
    @Column(name="inf_count")
    private Long infCount;
    @Column(name="nin_count")
    private Long ninCount;
    @Column(name="ado_count")
    private Long adoCount;
    @Column(name="ado_t_count")
    private Long adoTCount;
    @Column(name="jov_count")
    private Long jovCount;
    @Column(name="adu_count")
    private Long aduCount;
    @Column(name="m60_count")
    private Long mayCount;
    @Column(name="family_count")
    private Long familyCount;
    @Column(name="love_count")
    private Long loveCount;
    @Column(name="no_rel_count")
    private Long noRelationCount;
    @Column(name="aggr_alcohol_count")
    private Long aggrAlcohCount;
    @Column(name="aggr_drug_count")
    private Long aggrDrugCount;
    @Column(name="vict_disab_count")
    private Long victDisabCount;
    @Column(name="vict_alco_count")
    private Long victAlcohCount;
    @Column(name="vict_drug_count")
    private Long victDrugCount;
    @Column(name="vict_lgtbi_count")
    private Long victLGTBICount;
}

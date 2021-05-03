package com.thesis.visualizationserverthesis.model.entity.impact;

import com.thesis.visualizationserverthesis.model.entity.base.PreventiveAction;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name="app_2020")
public class PreventiveActionTwenty {
    @Id
    @Column(name="app_id")
    private Long appId;
    @Column(name="cem_code")
    private String CEMCode;
    @Column(name="start_date")
    private LocalDate startDate;
    @Column(name="state_code")
    private Long stateCode;
    @Column(name="province_code")
    private Long provinceCode;
    @Column(name="district_code")
    private Long districtCode;
    @Column(name="area")
    private Integer area;
    @Column(name="action_code")
    private String actionCode;
    @Column(name="campaign")
    private Integer campaign;
    @Column(name="oj_m")
    private Integer ojM;
    @Column(name="oj_v")
    private Integer ojV;
    @Column(name="op_m")
    private Integer opM;
    @Column(name="op_v")
    private Integer opV;
    @Column(name="os_m")
    private Integer osM;
    @Column(name="os_v")
    private Integer osV;
    @Column(name="aufr_m")
    private Integer aufrM;
    @Column(name="aufr_v")
    private Integer aufrV;
    @Column(name="aufl_m")
    private Integer auflM;
    @Column(name="aufl_v")
    private Integer auflV;
    @Column(name="auc_m")
    private Integer aucM;
    @Column(name="auc_v")
    private Integer aucV;
    @Column(name="ed_m")
    private Integer edM;
    @Column(name="ed_v")
    private Integer edV;
    @Column(name="es_m")
    private Integer esM;
    @Column(name="es_v")
    private Integer esV;
    @Column(name="esu_m")
    private Integer esuM;
    @Column(name="esu_v")
    private Integer esuV;
    @Column(name="pa_m")
    private Integer paM;
    @Column(name="pa_v")
    private Integer paV;
    @Column(name="lico_m")
    private Integer licoM;
    @Column(name="lico_v")
    private Integer licoV;
    @Column(name="fa_m")
    private Integer faM;
    @Column(name="fa_v")
    private Integer faV;
    @Column(name="pro_m")
    private Integer proM;
    @Column(name="pro_v")
    private Integer proV;
    @Column(name="col_m")
    private Integer colM;
    @Column(name="col_v")
    private Integer colV;
    @Column(name="sere_m")
    private Integer sereM;
    @Column(name="sere_v")
    private Integer sereV;
    @Column(name="fupu_m")
    private Integer fupuM;
    @Column(name="fupu_v")
    private Integer fupuV;
    @Column(name="fupri_m")
    private Integer fupriM;
    @Column(name="fupri_v")
    private Integer fupriV;
    @Column(name="emp_m")
    private Integer empM;
    @Column(name="emp_v")
    private Integer empV;
    @Column(name="gemp_m")
    private Integer gempM;
    @Column(name="gemp_v")
    private Integer gempV;
    @Column(name="trab_m")
    private Integer trabM;
    @Column(name="trab_v")
    private Integer trabV;
    @Column(name="cont_m")
    private Integer contM;
    @Column(name="cont_v")
    private Integer contV;
    @Column(name="per_m")
    private Integer perM;
    @Column(name="per_v")
    private Integer perV;
    @Column(name="osb_m")
    private Integer osbM;
    @Column(name="osb_v")
    private Integer osbV;
    @Column(name="mrc_m")
    private Integer mrcM;
    @Column(name="mrc_v")
    private Integer mrcV;
    @Column(name="rong_m")
    private Integer rongM;
    @Column(name="rong_v")
    private Integer rongV;
    @Column(name="mmcr_m")
    private Integer mmcrM;
    @Column(name="mmcr_v")
    private Integer mmcrV;
    @Column(name="ac_m")
    private Integer acM;
    @Column(name="ac_v")
    private Integer acV;
    @Column(name="po_m")
    private Integer poM;
    @Column(name="po_v")
    private Integer poV;
    @Column(name="ot_m")
    private Integer otM;
    @Column(name="ot_v")
    private Integer otV;
    @Column(name="inf_m")
    private Integer infM;
    @Column(name="inf_v")
    private Integer infV;
    @Column(name="nin_m")
    private Integer ninM;
    @Column(name="nin_v")
    private Integer ninV;
    @Column(name="adol_m")
    private Integer adoM;
    @Column(name="adol_v")
    private Integer adoV;
    @Column(name="adol_t_m")
    private Integer adoTM;
    @Column(name="adol_t_v")
    private Integer adoTV;
    @Column(name="jov_m")
    private Integer jovM;
    @Column(name="jov_v")
    private Integer jovV;
    @Column(name="adu_m")
    private Integer aduM;
    @Column(name="adu_v")
    private Integer aduV;
    @Column(name="may_m")
    private Integer mayM;
    @Column(name="may_v")
    private Integer mayV;

    public PreventiveAction toPreventiveAction(){
        PreventiveAction instance = new PreventiveAction();
        BeanUtils.copyProperties(this,instance);
        return instance;
    }
}

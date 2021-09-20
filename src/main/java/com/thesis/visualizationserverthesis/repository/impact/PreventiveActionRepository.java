package com.thesis.visualizationserverthesis.repository.impact;

import com.thesis.visualizationserverthesis.model.api.PreventiveActionsFilter;
import com.thesis.visualizationserverthesis.model.entity.impact.PreventiveAction;
import com.thesis.visualizationserverthesis.utils.IAggregatedAppAssistants;
import com.thesis.visualizationserverthesis.utils.IPreventiveActionCounterProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreventiveActionRepository extends JpaRepository<PreventiveAction,Long> {

    @Query(value ="select distinct p.action_code as actionType, p.state_code as state, p.province_code as province, p.district_code as district, COUNT(p.*) as count, " +
            " SUM(p.oj_m) as ojM, SUM(p.oj_v) as ojV, SUM(p.op_m) as opM, SUM(p.op_v) as opV, SUM(p.os_m) as osM, SUM(p.os_v) as osV, SUM(p.aufr_m) as aufrM, SUM(p.aufr_v) as aufrV " +
            ", SUM(p.aufl_m) as auflM, SUM(p.aufl_v) as auflV, SUM(p.auc_m) as aucM, SUM(p.auc_v) as aucV, SUM(p.ed_m) as edM, SUM(p.ed_v) as edV, SUM(p.es_m) as esM, SUM(p.es_v) as esV, SUM(p.esu_m) as esuM," +
            " SUM(p.esu_v) as esuV, SUM(p.pa_m) as paM, SUM(p.pa_v) as paV, SUM(p.lico_m) as licoM, SUM(p.lico_v) as licoV, SUM(p.fa_m) as faM, SUM(p.fa_v) as faV, SUM(p.pro_m) as proM, SUM(p.pro_v) as proV, SUM(p.col_m) as colM," +
            " SUM(p.col_v) as colV, SUM(p.sere_m) as sereM, SUM(p.sere_v) as sereV, SUM(p.fupu_m) as fupuM, SUM(p.fupu_v) as fupuV, SUM(p.fupri_m) as fupriM, SUM(p.fupri_v) as fupriV, SUM(p.emp_m) as empM, SUM(p.emp_v) as empV," +
            " SUM(p.gemp_m) as gempM, SUM(p.gemp_v) as gempV, SUM(p.trab_m) as trabM, SUM(p.trab_v) as trabV, SUM(p.cont_m) as contM, SUM(p.cont_v) as contV, SUM(p.per_m) as perM, SUM(p.per_v) as perV, SUM(p.osb_m) as osbM," +
            " SUM(p.osb_v) as osbV, SUM(p.mrc_m) as mrcM, SUM(p.mrc_v) as mrcV, SUM(p.rong_m) as rongM, SUM(p.rong_v) as rongV, SUM(p.mmcr_m) as mmcrM, SUM(p.mmcr_v) as mmcrV, SUM(p.ac_m) as acM, SUM(p.ac_v) as acV," +
            " SUM(p.po_m) as poM, SUM(p.po_v) as poV, SUM(p.ot_m) as otM,  SUM(p.ot_v) as otV, SUM(p.inf_m) as infM, SUM(p.inf_v) as infV, SUM(p.nin_m) as ninM, SUM(p.nin_v) as ninV, SUM(p.adol_m) as adoM, " +
            " SUM(p.adol_v) as adoV, SUM(p.adol_t_m) as adoTM, SUM(p.adol_t_v) as adoTV, SUM(p.jov_m) as jovM, SUM(p.jov_v) as jovV, SUM(p.adu_m) as aduM, SUM(p.adu_v) as aduV, SUM(p.may_m) as mayM, SUM(p.may_v) as mayV " +
            "from app p " +
            "where p.start_date >= :#{#filter.startDate} AND p.start_date <= :#{#filter.endDate} " +
            "and (:#{#filter.state} = 0 or (:#{#filter.state} = p.state_code) ) " +
            "and (:#{#filter.province} = 0  or (:#{#filter.province} = p.province_code )) " +
            "and (:#{#filter.district} = 0 or (:#{#filter.district} = p.district_code )) " +
            "group by p.action_code, p.state_code, p.province_code, p.district_code " +
            "order by count desc" , nativeQuery = true)
    List<IPreventiveActionCounterProjection> countPreventiveActionsForTimeAndUbigeo(@Param("filter") PreventiveActionsFilter filter);

    @Query(value ="select distinct p.action_code as actionType, p.state_code as state, p.province_code as province, COUNT(p.*) as count, " +
            " SUM(p.oj_m) as ojM, SUM(p.oj_v) as ojV, SUM(p.op_m) as opM, SUM(p.op_v) as opV, SUM(p.os_m) as osM, SUM(p.os_v) as osV, SUM(p.aufr_m) as aufrM, SUM(p.aufr_v) as aufrV " +
            ", SUM(p.aufl_m) as auflM, SUM(p.aufl_v) as auflV, SUM(p.auc_m) as aucM, SUM(p.auc_v) as aucV, SUM(p.ed_m) as edM, SUM(p.ed_v) as edV, SUM(p.es_m) as esM, SUM(p.es_v) as esV, SUM(p.esu_m) as esuM," +
            " SUM(p.esu_v) as esuV, SUM(p.pa_m) as paM, SUM(p.pa_v) as paV, SUM(p.lico_m) as licoM, SUM(p.lico_v) as licoV, SUM(p.fa_m) as faM, SUM(p.fa_v) as faV, SUM(p.pro_m) as proM, SUM(p.pro_v) as proV, SUM(p.col_m) as colM," +
            " SUM(p.col_v) as colV, SUM(p.sere_m) as sereM, SUM(p.sere_v) as sereV, SUM(p.fupu_m) as fupuM, SUM(p.fupu_v) as fupuV, SUM(p.fupri_m) as fupriM, SUM(p.fupri_v) as fupriV, SUM(p.emp_m) as empM, SUM(p.emp_v) as empV," +
            " SUM(p.gemp_m) as gempM, SUM(p.gemp_v) as gempV, SUM(p.trab_m) as trabM, SUM(p.trab_v) as trabV, SUM(p.cont_m) as contM, SUM(p.cont_v) as contV, SUM(p.per_m) as perM, SUM(p.per_v) as perV, SUM(p.osb_m) as osbM," +
            " SUM(p.osb_v) as osbV, SUM(p.mrc_m) as mrcM, SUM(p.mrc_v) as mrcV, SUM(p.rong_m) as rongM, SUM(p.rong_v) as rongV, SUM(p.mmcr_m) as mmcrM, SUM(p.mmcr_v) as mmcrV, SUM(p.ac_m) as acM, SUM(p.ac_v) as acV," +
            " SUM(p.po_m) as poM, SUM(p.po_v) as poV, SUM(p.ot_m) as otM,  SUM(p.ot_v) as otV, SUM(p.inf_m) as infM, SUM(p.inf_v) as infV, SUM(p.nin_m) as ninM, SUM(p.nin_v) as ninV, SUM(p.adol_m) as adoM, " +
            " SUM(p.adol_v) as adoV, SUM(p.adol_t_m) as adoTM, SUM(p.adol_t_v) as adoTV, SUM(p.jov_m) as jovM, SUM(p.jov_v) as jovV, SUM(p.adu_m) as aduM, SUM(p.adu_v) as aduV, SUM(p.may_m) as mayM, SUM(p.may_v) as mayV " +
            "from app p " +
            "where p.start_date >= :#{#filter.startDate} AND p.start_date <= :#{#filter.endDate} " +
            "and (:#{#filter.state} = 0 or (:#{#filter.state} = p.state_code) ) " +
            "and (:#{#filter.province} = 0  or (:#{#filter.province} = p.province_code )) " +
            "group by p.action_code, p.state_code, p.province_code " +
            "order by count desc" , nativeQuery = true)
    List<IPreventiveActionCounterProjection> countPreventiveActionsForTimeAndProvince(@Param("filter") PreventiveActionsFilter filter);

    @Query(value ="select distinct p.action_code as actionType, p.state_code as state, COUNT(p.*) as count, " +
            " SUM(p.oj_m) as ojM, SUM(p.oj_v) as ojV, SUM(p.op_m) as opM, SUM(p.op_v) as opV, SUM(p.os_m) as osM, SUM(p.os_v) as osV, SUM(p.aufr_m) as aufrM, SUM(p.aufr_v) as aufrV " +
            ", SUM(p.aufl_m) as auflM, SUM(p.aufl_v) as auflV, SUM(p.auc_m) as aucM, SUM(p.auc_v) as aucV, SUM(p.ed_m) as edM, SUM(p.ed_v) as edV, SUM(p.es_m) as esM, SUM(p.es_v) as esV, SUM(p.esu_m) as esuM," +
            " SUM(p.esu_v) as esuV, SUM(p.pa_m) as paM, SUM(p.pa_v) as paV, SUM(p.lico_m) as licoM, SUM(p.lico_v) as licoV, SUM(p.fa_m) as faM, SUM(p.fa_v) as faV, SUM(p.pro_m) as proM, SUM(p.pro_v) as proV, SUM(p.col_m) as colM," +
            " SUM(p.col_v) as colV, SUM(p.sere_m) as sereM, SUM(p.sere_v) as sereV, SUM(p.fupu_m) as fupuM, SUM(p.fupu_v) as fupuV, SUM(p.fupri_m) as fupriM, SUM(p.fupri_v) as fupriV, SUM(p.emp_m) as empM, SUM(p.emp_v) as empV," +
            " SUM(p.gemp_m) as gempM, SUM(p.gemp_v) as gempV, SUM(p.trab_m) as trabM, SUM(p.trab_v) as trabV, SUM(p.cont_m) as contM, SUM(p.cont_v) as contV, SUM(p.per_m) as perM, SUM(p.per_v) as perV, SUM(p.osb_m) as osbM," +
            " SUM(p.osb_v) as osbV, SUM(p.mrc_m) as mrcM, SUM(p.mrc_v) as mrcV, SUM(p.rong_m) as rongM, SUM(p.rong_v) as rongV, SUM(p.mmcr_m) as mmcrM, SUM(p.mmcr_v) as mmcrV, SUM(p.ac_m) as acM, SUM(p.ac_v) as acV," +
            " SUM(p.po_m) as poM, SUM(p.po_v) as poV, SUM(p.ot_m) as otM,  SUM(p.ot_v) as otV, SUM(p.inf_m) as infM, SUM(p.inf_v) as infV, SUM(p.nin_m) as ninM, SUM(p.nin_v) as ninV, SUM(p.adol_m) as adoM, " +
            " SUM(p.adol_v) as adoV, SUM(p.adol_t_m) as adoTM, SUM(p.adol_t_v) as adoTV, SUM(p.jov_m) as jovM, SUM(p.jov_v) as jovV, SUM(p.adu_m) as aduM, SUM(p.adu_v) as aduV, SUM(p.may_m) as mayM, SUM(p.may_v) as mayV " +
            "from app p " +
            "where p.start_date >= :#{#filter.startDate} AND p.start_date <= :#{#filter.endDate} " +
            "and (:#{#filter.state} = 0 or (:#{#filter.state} = p.state_code) ) " +
            "group by p.action_code, p.state_code " +
            "order by count desc" , nativeQuery = true)
    List<IPreventiveActionCounterProjection> countPreventiveActionsForTimeAndState(@Param("filter") PreventiveActionsFilter filter);

    @Query(value ="select distinct p.action_code as actionType, COUNT(p.*) as count, " +
            " SUM(p.oj_m) as ojM, SUM(p.oj_v) as ojV, SUM(p.op_m) as opM, SUM(p.op_v) as opV, SUM(p.os_m) as osM, SUM(p.os_v) as osV, SUM(p.aufr_m) as aufrM, SUM(p.aufr_v) as aufrV " +
            ", SUM(p.aufl_m) as auflM, SUM(p.aufl_v) as auflV, SUM(p.auc_m) as aucM, SUM(p.auc_v) as aucV, SUM(p.ed_m) as edM, SUM(p.ed_v) as edV, SUM(p.es_m) as esM, SUM(p.es_v) as esV, SUM(p.esu_m) as esuM," +
            " SUM(p.esu_v) as esuV, SUM(p.pa_m) as paM, SUM(p.pa_v) as paV, SUM(p.lico_m) as licoM, SUM(p.lico_v) as licoV, SUM(p.fa_m) as faM, SUM(p.fa_v) as faV, SUM(p.pro_m) as proM, SUM(p.pro_v) as proV, SUM(p.col_m) as colM," +
            " SUM(p.col_v) as colV, SUM(p.sere_m) as sereM, SUM(p.sere_v) as sereV, SUM(p.fupu_m) as fupuM, SUM(p.fupu_v) as fupuV, SUM(p.fupri_m) as fupriM, SUM(p.fupri_v) as fupriV, SUM(p.emp_m) as empM, SUM(p.emp_v) as empV," +
            " SUM(p.gemp_m) as gempM, SUM(p.gemp_v) as gempV, SUM(p.trab_m) as trabM, SUM(p.trab_v) as trabV, SUM(p.cont_m) as contM, SUM(p.cont_v) as contV, SUM(p.per_m) as perM, SUM(p.per_v) as perV, SUM(p.osb_m) as osbM," +
            " SUM(p.osb_v) as osbV, SUM(p.mrc_m) as mrcM, SUM(p.mrc_v) as mrcV, SUM(p.rong_m) as rongM, SUM(p.rong_v) as rongV, SUM(p.mmcr_m) as mmcrM, SUM(p.mmcr_v) as mmcrV, SUM(p.ac_m) as acM, SUM(p.ac_v) as acV," +
            " SUM(p.po_m) as poM, SUM(p.po_v) as poV, SUM(p.ot_m) as otM,  SUM(p.ot_v) as otV, SUM(p.inf_m) as infM, SUM(p.inf_v) as infV, SUM(p.nin_m) as ninM, SUM(p.nin_v) as ninV, SUM(p.adol_m) as adoM, " +
            " SUM(p.adol_v) as adoV, SUM(p.adol_t_m) as adoTM, SUM(p.adol_t_v) as adoTV, SUM(p.jov_m) as jovM, SUM(p.jov_v) as jovV, SUM(p.adu_m) as aduM, SUM(p.adu_v) as aduV, SUM(p.may_m) as mayM, SUM(p.may_v) as mayV " +
            "from app p " +
            "where p.start_date >= :#{#filter.startDate} AND p.start_date <= :#{#filter.endDate} " +
            "group by p.action_code " +
            "order by count desc" , nativeQuery = true)
    List<IPreventiveActionCounterProjection> countPreventiveActionsForTime(@Param("filter") PreventiveActionsFilter filter);

    @Query(value ="select SUM(p.oj_m) as ojM, SUM(p.oj_v) as ojV, SUM(p.op_m) as opM, SUM(p.op_v) as opV, SUM(p.os_m) as osM, SUM(p.os_v) as osV, SUM(p.aufr_m) as aufrM, SUM(p.aufr_v) as aufrV " +
            ", SUM(p.aufl_m) as auflM, SUM(p.aufl_v) as auflV, SUM(p.auc_m) as aucM, SUM(p.auc_v) as aucV, SUM(p.ed_m) as edM, SUM(p.ed_v) as edV, SUM(p.es_m) as esM, SUM(p.es_v) as esV, SUM(p.esu_m) as esuM, " +
            " SUM(p.esu_v) as esuV, SUM(p.pa_m) as paM, SUM(p.pa_v) as paV, SUM(p.lico_m) as licoM, SUM(p.lico_v) as licoV, SUM(p.fa_m) as faM, SUM(p.fa_v) as faV, SUM(p.pro_m) as proM, SUM(p.pro_v) as proV, SUM(p.col_m) as colM " +
            ", SUM(p.col_v) as colV, SUM(p.sere_m) as sereM, SUM(p.sere_v) as sereV, SUM(p.fupu_m) as fupuM, SUM(p.fupu_v) as fupuV, SUM(p.fupri_m) as fupriM, SUM(p.fupri_v) as fupriV, SUM(p.emp_m) as empM, SUM(p.emp_v) as empV," +
            " SUM(p.gemp_m) as gempM, SUM(p.gemp_v) as gempV, SUM(p.trab_m) as trabM, SUM(p.trab_v) as trabV, SUM(p.cont_m) as contM, SUM(p.cont_v) as contV, SUM(p.per_m) as perM, SUM(p.per_v) as perV, SUM(p.osb_m) as osbM, " +
            " SUM(p.osb_v) as osbV, SUM(p.mrc_m) as mrcM, SUM(p.mrc_v) as mrcV, SUM(p.rong_m) as rongM, SUM(p.rong_v) as rongV, SUM(p.mmcr_m) as mmcrM, SUM(p.mmcr_v) as mmcrV, SUM(p.ac_m) as acM, SUM(p.ac_v) as acV," +
            " SUM(p.po_m) as poM, SUM(p.po_v) as poV, SUM(p.ot_m) as otM,  SUM(p.ot_v) as otV, SUM(p.inf_m) as infM, SUM(p.inf_v) as infV, SUM(p.nin_m) as ninM, SUM(p.nin_v) as ninV, SUM(p.adol_m) as adoM, " +
            " SUM(p.adol_v) as adoV, SUM(p.adol_t_m) as adoTM, SUM(p.adol_t_v) as adoTV, SUM(p.jov_m) as jovM, SUM(p.jov_v) as jovV, SUM(p.adu_m) as aduM, SUM(p.adu_v) as aduV, SUM(p.may_m) as mayM, SUM(p.may_v) as mayV " +
            "from app p " +
            "where p.start_date >= :#{#filter.startDate} AND p.start_date <= :#{#filter.endDate} ", nativeQuery = true)
    IAggregatedAppAssistants getAggregatedAssistantsForTime(@Param("filter") PreventiveActionsFilter filter);

    @Query(value ="select SUM(p.oj_m) as ojM, SUM(p.oj_v) as ojV, SUM(p.op_m) as opM, SUM(p.op_v) as opV, SUM(p.os_m) as osM, SUM(p.os_v) as osV, SUM(p.aufr_m) as aufrM, SUM(p.aufr_v) as aufrV " +
            ", SUM(p.aufl_m) as auflM, SUM(p.aufl_v) as auflV, SUM(p.auc_m) as aucM, SUM(p.auc_v) as aucV, SUM(p.ed_m) as edM, SUM(p.ed_v) as edV, SUM(p.es_m) as esM, SUM(p.es_v) as esV, SUM(p.esu_m) as esuM, " +
            " SUM(p.esu_v) as esuV, SUM(p.pa_m) as paM, SUM(p.pa_v) as paV, SUM(p.lico_m) as licoM, SUM(p.lico_v) as licoV, SUM(p.fa_m) as faM, SUM(p.fa_v) as faV, SUM(p.pro_m) as proM, SUM(p.pro_v) as proV, SUM(p.col_m) as colM " +
            ", SUM(p.col_v) as colV, SUM(p.sere_m) as sereM, SUM(p.sere_v) as sereV, SUM(p.fupu_m) as fupuM, SUM(p.fupu_v) as fupuV, SUM(p.fupri_m) as fupriM, SUM(p.fupri_v) as fupriV, SUM(p.emp_m) as empM, SUM(p.emp_v) as empV," +
            " SUM(p.gemp_m) as gempM, SUM(p.gemp_v) as gempV, SUM(p.trab_m) as trabM, SUM(p.trab_v) as trabV, SUM(p.cont_m) as contM, SUM(p.cont_v) as contV, SUM(p.per_m) as perM, SUM(p.per_v) as perV, SUM(p.osb_m) as osbM, " +
            " SUM(p.osb_v) as osbV, SUM(p.mrc_m) as mrcM, SUM(p.mrc_v) as mrcV, SUM(p.rong_m) as rongM, SUM(p.rong_v) as rongV, SUM(p.mmcr_m) as mmcrM, SUM(p.mmcr_v) as mmcrV, SUM(p.ac_m) as acM, SUM(p.ac_v) as acV," +
            " SUM(p.po_m) as poM, SUM(p.po_v) as poV, SUM(p.ot_m) as otM,  SUM(p.ot_v) as otV, SUM(p.inf_m) as infM, SUM(p.inf_v) as infV, SUM(p.nin_m) as ninM, SUM(p.nin_v) as ninV, SUM(p.adol_m) as adoM, " +
            " SUM(p.adol_v) as adoV, SUM(p.adol_t_m) as adoTM, SUM(p.adol_t_v) as adoTV, SUM(p.jov_m) as jovM, SUM(p.jov_v) as jovV, SUM(p.adu_m) as aduM, SUM(p.adu_v) as aduV, SUM(p.may_m) as mayM, SUM(p.may_v) as mayV " +
            "from app p " +
            "where p.start_date >= :#{#filter.startDate} AND p.start_date <= :#{#filter.endDate} " +
            "and (:#{#filter.state} = 0 or (:#{#filter.state} = p.state_code) ) ", nativeQuery = true)
    IAggregatedAppAssistants getAggregatedAssistantsForTimeAndState(@Param("filter") PreventiveActionsFilter filter);

    @Query(value ="select SUM(p.oj_m) as ojM, SUM(p.oj_v) as ojV, SUM(p.op_m) as opM, SUM(p.op_v) as opV, SUM(p.os_m) as osM, SUM(p.os_v) as osV, SUM(p.aufr_m) as aufrM, SUM(p.aufr_v) as aufrV " +
            ", SUM(p.aufl_m) as auflM, SUM(p.aufl_v) as auflV, SUM(p.auc_m) as aucM, SUM(p.auc_v) as aucV, SUM(p.ed_m) as edM, SUM(p.ed_v) as edV, SUM(p.es_m) as esM, SUM(p.es_v) as esV, SUM(p.esu_m) as esuM, " +
            " SUM(p.esu_v) as esuV, SUM(p.pa_m) as paM, SUM(p.pa_v) as paV, SUM(p.lico_m) as licoM, SUM(p.lico_v) as licoV, SUM(p.fa_m) as faM, SUM(p.fa_v) as faV, SUM(p.pro_m) as proM, SUM(p.pro_v) as proV, SUM(p.col_m) as colM " +
            ", SUM(p.col_v) as colV, SUM(p.sere_m) as sereM, SUM(p.sere_v) as sereV, SUM(p.fupu_m) as fupuM, SUM(p.fupu_v) as fupuV, SUM(p.fupri_m) as fupriM, SUM(p.fupri_v) as fupriV, SUM(p.emp_m) as empM, SUM(p.emp_v) as empV," +
            " SUM(p.gemp_m) as gempM, SUM(p.gemp_v) as gempV, SUM(p.trab_m) as trabM, SUM(p.trab_v) as trabV, SUM(p.cont_m) as contM, SUM(p.cont_v) as contV, SUM(p.per_m) as perM, SUM(p.per_v) as perV, SUM(p.osb_m) as osbM, " +
            " SUM(p.osb_v) as osbV, SUM(p.mrc_m) as mrcM, SUM(p.mrc_v) as mrcV, SUM(p.rong_m) as rongM, SUM(p.rong_v) as rongV, SUM(p.mmcr_m) as mmcrM, SUM(p.mmcr_v) as mmcrV, SUM(p.ac_m) as acM, SUM(p.ac_v) as acV," +
            " SUM(p.po_m) as poM, SUM(p.po_v) as poV, SUM(p.ot_m) as otM,  SUM(p.ot_v) as otV, SUM(p.inf_m) as infM, SUM(p.inf_v) as infV, SUM(p.nin_m) as ninM, SUM(p.nin_v) as ninV, SUM(p.adol_m) as adoM, " +
            " SUM(p.adol_v) as adoV, SUM(p.adol_t_m) as adoTM, SUM(p.adol_t_v) as adoTV, SUM(p.jov_m) as jovM, SUM(p.jov_v) as jovV, SUM(p.adu_m) as aduM, SUM(p.adu_v) as aduV, SUM(p.may_m) as mayM, SUM(p.may_v) as mayV " +
            "from app p " +
            "where p.start_date >= :#{#filter.startDate} AND p.start_date <= :#{#filter.endDate} " +
            "and (:#{#filter.state} = 0 or (:#{#filter.state} = p.state_code) ) " +
            "and (:#{#filter.province} = 0  or (:#{#filter.province} = p.province_code )) ", nativeQuery = true)
    IAggregatedAppAssistants getAggregatedAssistantsForTimeAndStateAndProvince(@Param("filter") PreventiveActionsFilter filter);

    @Query(value ="select SUM(p.oj_m) as ojM, SUM(p.oj_v) as ojV, SUM(p.op_m) as opM, SUM(p.op_v) as opV, SUM(p.os_m) as osM, SUM(p.os_v) as osV, SUM(p.aufr_m) as aufrM, SUM(p.aufr_v) as aufrV " +
            ", SUM(p.aufl_m) as auflM, SUM(p.aufl_v) as auflV, SUM(p.auc_m) as aucM, SUM(p.auc_v) as aucV, SUM(p.ed_m) as edM, SUM(p.ed_v) as edV, SUM(p.es_m) as esM, SUM(p.es_v) as esV, SUM(p.esu_m) as esuM, " +
            " SUM(p.esu_v) as esuV, SUM(p.pa_m) as paM, SUM(p.pa_v) as paV, SUM(p.lico_m) as licoM, SUM(p.lico_v) as licoV, SUM(p.fa_m) as faM, SUM(p.fa_v) as faV, SUM(p.pro_m) as proM, SUM(p.pro_v) as proV, SUM(p.col_m) as colM " +
            ", SUM(p.col_v) as colV, SUM(p.sere_m) as sereM, SUM(p.sere_v) as sereV, SUM(p.fupu_m) as fupuM, SUM(p.fupu_v) as fupuV, SUM(p.fupri_m) as fupriM, SUM(p.fupri_v) as fupriV, SUM(p.emp_m) as empM, SUM(p.emp_v) as empV," +
            " SUM(p.gemp_m) as gempM, SUM(p.gemp_v) as gempV, SUM(p.trab_m) as trabM, SUM(p.trab_v) as trabV, SUM(p.cont_m) as contM, SUM(p.cont_v) as contV, SUM(p.per_m) as perM, SUM(p.per_v) as perV, SUM(p.osb_m) as osbM, " +
            " SUM(p.osb_v) as osbV, SUM(p.mrc_m) as mrcM, SUM(p.mrc_v) as mrcV, SUM(p.rong_m) as rongM, SUM(p.rong_v) as rongV, SUM(p.mmcr_m) as mmcrM, SUM(p.mmcr_v) as mmcrV, SUM(p.ac_m) as acM, SUM(p.ac_v) as acV," +
            " SUM(p.po_m) as poM, SUM(p.po_v) as poV, SUM(p.ot_m) as otM,  SUM(p.ot_v) as otV, SUM(p.inf_m) as infM, SUM(p.inf_v) as infV, SUM(p.nin_m) as ninM, SUM(p.nin_v) as ninV, SUM(p.adol_m) as adoM, " +
            " SUM(p.adol_v) as adoV, SUM(p.adol_t_m) as adoTM, SUM(p.adol_t_v) as adoTV, SUM(p.jov_m) as jovM, SUM(p.jov_v) as jovV, SUM(p.adu_m) as aduM, SUM(p.adu_v) as aduV, SUM(p.may_m) as mayM, SUM(p.may_v) as mayV " +
            "from app p " +
            "where p.start_date >= :#{#filter.startDate} AND p.start_date <= :#{#filter.endDate} " +
            "and (:#{#filter.state} = 0 or (:#{#filter.state} = p.state_code) ) " +
            "and (:#{#filter.province} = 0  or (:#{#filter.province} = p.province_code )) " +
            "and (:#{#filter.district} = 0 or (:#{#filter.district} = p.district_code )) ", nativeQuery = true)
    IAggregatedAppAssistants getAggregatedAssistantsForTimeAndStateAndProvinceAndDistrict(@Param("filter") PreventiveActionsFilter filter);
}

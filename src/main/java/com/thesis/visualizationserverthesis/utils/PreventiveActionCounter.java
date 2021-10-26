package com.thesis.visualizationserverthesis.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PreventiveActionCounter {
    private Integer count;
    private String actionType;
    private Long state;
    private Long province;
    private Long district;
    private Integer ojM;
    private Integer ojV;
    private Integer opM;
    private Integer opV;
    private Integer osM;
    private Integer osV;
    private Integer aufrM;
    private Integer aufrV;
    private Integer auflM;
    private Integer auflV;
    private Integer aucM;
    private Integer aucV;
    private Integer edM;
    private Integer edV;
    private Integer esM;
    private Integer esV;
    private Integer esuM;
    private Integer esuV;
    private Integer paM;
    private Integer paV;
    private Integer licoM;
    private Integer licoV;
    private Integer faM;
    private Integer faV;
    private Integer proM;
    private Integer proV;
    private Integer colM;
    private Integer colV;
    private Integer sereM;
    private Integer sereV;
    private Integer fupuM;
    private Integer fupuV;
    private Integer fupriM;
    private Integer fupriV;
    private Integer empM;
    private Integer empV;
    private Integer gempM;
    private Integer gempV;
    private Integer trabM;
    private Integer trabV;
    private Integer contM;
    private Integer contV;
    private Integer perM;
    private Integer perV;
    private Integer osbM;
    private Integer osbV;
    private Integer mrcM;
    private Integer mrcV;
    private Integer rongM;
    private Integer rongV;
    private Integer mmcrM;
    private Integer mmcrV;
    private Integer acM;
    private Integer acV;
    private Integer poM;
    private Integer poV;
    private Integer otM;
    private Integer otV;
    private Integer infM;
    private Integer infV;
    private Integer ninM;
    private Integer ninV;
    private Integer adoM;
    private Integer adoV;
    private Integer adoTM;
    private Integer adoTV;
    private Integer jovM;
    private Integer jovV;
    private Integer aduM;
    private Integer aduV;
    private Integer mayM;
    private Integer mayV;

    public PreventiveActionCounter(IPreventiveActionCounterProjection projection){
        this.count = projection.getCount();
        this.actionType = projection.getActionType();
        this.state = projection.getState();
        this.province = projection.getProvince();
        this.district = projection.getDistrict();
        this.ojM = projection.getOjM();
        this.ojV = projection.getOjV();
        this.opM = projection.getOpM();
        this.opV = projection.getOpV();
        this.osM = projection.getOsM();
        this.osV = projection.getOsV();
        this.aufrM = projection.getAufrM();
        this.aufrV = projection.getAufrV();
        this.auflM = projection.getAuflM();
        this.auflV = projection.getAuflV();
        this.aucM = projection.getAucM();
        this.aucV = projection.getAucV();
        this.edM = projection.getEdM();
        this.edV = projection.getEdV();
        this.esM = projection.getEsM();
        this.esV = projection.getEsV();
        this.esuM = projection.getEsuM();
        this.esuV = projection.getEsuV();
        this.paM = projection.getPaM();
        this.paV = projection.getPaV();
        this.licoM = projection.getLicoM();
        this.licoV = projection.getLicoV();
        this.faM = projection.getFaM();
        this.faV = projection.getFaV();
        this.proM = projection.getProM();
        this.proV = projection.getProV();
        this.colM = projection.getColM();
        this.colV = projection.getColV();
        this.sereM = projection.getSereM();
        this.sereV = projection.getSereV();
        this.fupuM = projection.getFupuM();
        this.fupuV = projection.getFupuV();
        this.fupriM = projection.getFupriM();
        this.fupriV = projection.getFupriV();
        this.empM = projection.getEmpM();
        this.empV = projection.getEmpV();
        this.gempM = projection.getGempM();
        this.gempV = projection.getGempV();
        this.trabM = projection.getTrabM();
        this.trabV = projection.getTrabV();
        this.contM = projection.getContM();
        this.contV = projection.getContV();
        this.perM = projection.getPerM();
        this.perV = projection.getPerV();
        this.osbM = projection.getOsbM();
        this.osbV = projection.getOsbV();
        this.mrcM = projection.getMrcM();
        this.mrcV = projection.getMrcV();
        this.rongM = projection.getRongM();
        this.rongV = projection.getRongV();
        this.mmcrM = projection.getMmcrM();
        this.mmcrV = projection.getMmcrV();
        this.acM = projection.getAcM();
        this.acV = projection.getAcV();
        this.poM = projection.getPoM();
        this.poV = projection.getPoV();
        this.otM = projection.getOtM();
        this.otV = projection.getOtV();
        this.infM = projection.getInfM();
        this.infV = projection.getInfV();
        this.ninM = projection.getNinM();
        this.ninV = projection.getNinV();
        this.adoM = projection.getAdoM();
        this.adoV = projection.getAdoV();
        this.adoTM = projection.getAdoTM();
        this.adoTV = projection.getAdoTV();
        this.aduM = projection.getAduM();
        this.aduV = projection.getAduV();
        this.mayM = projection.getMayM();
        this.mayV = projection.getMayV();
        this.jovM = projection.getJovM();
        this.jovV = projection.getJovV();
    }
}

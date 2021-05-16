package com.thesis.visualizationserverthesis.model.api;

import com.thesis.visualizationserverthesis.utils.CemCountImpact;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ImpactViolenceCasesDTO {
    private List<LocalDate> dates;
    private List<Long> physicalV;
    private List<Long> economicalV;
    private List<Long> sexualV;
    private List<Long> psychologicalV;
    private LocalDate appDate;

    public ImpactViolenceCasesDTO(List<LocalDate> dates, List<CemCountImpact> maleCases, List<CemCountImpact> femaleCases, LocalDate appDate){
        this.setDates(dates);

        val physicalList = new ArrayList<Long>();
        val economicalList = new ArrayList<Long>();
        val sexualList = new ArrayList<Long>();
        val psychoList = new ArrayList<Long>();

        int maleIndex = 0;
        int femaleIndex = 0;
        for(int i=0; i< dates.size();i++){
            Long physical = 0L;
            Long sexual = 0L;
            Long psycho = 0L;
            Long economical = 0L;
            if(maleIndex < maleCases.size() && maleCases.get(maleIndex).getCaseDate().compareTo(dates.get(i)) == 0){
                physical+=maleCases.get(maleIndex).getPhysicalVCount();
                sexual+=maleCases.get(maleIndex).getSexualVCount();
                psycho+=maleCases.get(maleIndex).getPsychologicalVCount();
                economical+=maleCases.get(maleIndex).getEconomicalVCount();
                maleIndex++;
            }
            if(femaleIndex < femaleCases.size() && femaleCases.get(femaleIndex).getCaseDate().compareTo(dates.get(i)) == 0){
                physical+=femaleCases.get(femaleIndex).getPhysicalVCount();
                sexual+=femaleCases.get(femaleIndex).getSexualVCount();
                psycho+=femaleCases.get(femaleIndex).getPsychologicalVCount();
                economical+=femaleCases.get(femaleIndex).getEconomicalVCount();
                femaleIndex++;
            }
            physicalList.add(physical);
            economicalList.add(economical);
            sexualList.add(sexual);
            psychoList.add(psycho);
        }
        this.setEconomicalV(economicalList);
        this.setPhysicalV(physicalList);
        this.setSexualV(sexualList);
        this.setPsychologicalV(psychoList);
        this.setAppDate(appDate);
    }
}

package org.opencb.biodata.models.variant.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.opencb.biodata.models.feature.AllelesCode;
import org.opencb.biodata.models.feature.Genotype;
import org.opencb.biodata.models.pedigree.Condition;
import org.opencb.biodata.models.pedigree.Individual;
import org.opencb.biodata.models.pedigree.Pedigree;
import org.opencb.biodata.models.variant.ArchivedVariantFile;
import org.opencb.biodata.models.variant.Variant;
import org.opencb.biodata.models.variant.Variant.VariantType;

/**
 * @author Alejandro Aleman Ramos &lt;aaleman@cipf.es&gt;
 * @author Cristina Yenyxe Gonzalez Garcia &lt;cyenyxe@ebi.ac.uk&gt;
 * 
 * TODO Mendelian errors must be calculated
 */
public class VariantStats {

    private Variant variant;
    
    private String chromosome;
    private long position;
    private String refAllele;
    private String altAllele;
    
    private int refAlleleCount;
    private int altAlleleCount;
    private Map<Genotype, Integer> genotypesCount;
    
    private int missingAlleles;
    private int missingGenotypes;
    
    private float refAlleleFreq;
    private float altAlleleFreq;
    private Map<Genotype, Float> genotypesFreq;
    private float maf;
    private float mgf;
    private String mafAllele;
    private String mgfGenotype;
    
    private boolean passedFilters;
    
    private boolean pedigreeStatsAvailable;
    
    private int mendelianErrors;
    
    private float casesPercentDominant;
    private float controlsPercentDominant;
    private float casesPercentRecessive;
    private float controlsPercentRecessive;
    
    private int transitionsCount;
    private int transversionsCount;
    
    private float quality;
    private int numSamples;
    private VariantHardyWeinbergStats hw;

    
    public VariantStats() {
        this.chromosome = null;
        this.position = -1;
        this.refAllele = null;
        this.altAllele = null;
        
        this.maf = -1;
        this.mgf = -1;
        this.mafAllele = null;
        this.mgfGenotype = null;
        this.refAlleleCount = this.altAlleleCount = -1;
        this.refAlleleFreq = this.altAlleleFreq = -1;
        this.genotypesCount = new HashMap<>();
        this.genotypesFreq = new HashMap<>();
        
        this.missingAlleles = -1;
        this.missingGenotypes = -1;
        this.mendelianErrors = -1;
        this.casesPercentDominant = -1;
        this.controlsPercentDominant = -1;
        this.casesPercentRecessive = -1;
        this.controlsPercentRecessive = -1;
        this.transitionsCount = -1;
        this.transversionsCount = -1;
        
        this.hw = new VariantHardyWeinbergStats();
    }

    public VariantStats(Variant variant) {
        this();
        
        if (variant != null) {
            this.variant = variant;
            this.chromosome = variant.getChromosome();
            this.position = variant.getStart();
            this.refAllele = variant.getReference();
            this.altAllele = variant.getAlternate();
        }
    }

    public VariantStats(String chromosome, int position, String referenceAllele, String alternateAlleles, double maf,
            double mgf, String mafAllele, String mgfGenotype, int numMissingAlleles, int numMissingGenotypes,
            int numMendelErrors, boolean isIndel, double percentCasesDominant, double percentControlsDominant,
            double percentCasesRecessive, double percentControlsRecessive) {
        this();
        
        this.chromosome = chromosome;
        this.position = position;
        this.refAllele = referenceAllele;
        this.altAllele = alternateAlleles;
        
        this.maf = (float) maf;
        this.mgf = (float) mgf;
        this.mafAllele = mafAllele;
        this.mgfGenotype = mgfGenotype;

        this.missingAlleles = numMissingAlleles;
        this.missingGenotypes = numMissingGenotypes;
        this.mendelianErrors = numMendelErrors;

        this.casesPercentDominant = (float) percentCasesDominant;
        this.controlsPercentDominant = (float) percentControlsDominant;
        this.casesPercentRecessive = (float) percentCasesRecessive;
        this.controlsPercentRecessive = (float) percentControlsRecessive;

    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getRefAllele() {
        return refAllele;
    }

    public String getAltAllele() {
        return altAllele;
    }

    public void setAltAllele(String altAllele) {
        this.altAllele = altAllele;
    }

    public int getRefAlleleCount() {
        return refAlleleCount;
    }

    public void setRefAlleleCount(int refAlleleCount) {
        this.refAlleleCount = refAlleleCount;
    }

    public int getAltAlleleCount() {
        return altAlleleCount;
    }

    public void setAltAlleleCount(int altAlleleCount) {
        this.altAlleleCount = altAlleleCount;
    }

    public float getRefAlleleFreq() {
        return refAlleleFreq;
    }

    public void setRefAlleleFreq(float refAlleleFreq) {
        this.refAlleleFreq = refAlleleFreq;
    }

    public float getAltAlleleFreq() {
        return altAlleleFreq;
    }

    public void setAltAlleleFreq(float altAlleleFreq) {
        this.altAlleleFreq = altAlleleFreq;
    }
    
    public String getMafAllele() {
        return mafAllele;
    }

    public void setMafAllele(String mafAllele) {
        this.mafAllele = mafAllele;
    }

    public String getMgfGenotype() {
        return mgfGenotype;
    }

    public void setMgfGenotype(String mgfGenotype) {
        this.mgfGenotype = mgfGenotype;
    }

    public Map<Genotype, Integer> getGenotypesCount() {
        return genotypesCount;
    }

    public void addGenotype(Genotype g) {
        this.addGenotype(g, 1);
    }
    
    public void addGenotype(Genotype g, int addedCount) {
        Integer count;
        if (genotypesCount.containsKey(g)) {
            count = genotypesCount.get(g) + addedCount;
        } else {
            count = addedCount;
        }
        genotypesCount.put(g, count);
    }
    
    public void setGenotypesCount(Map<Genotype, Integer> genotypesCount) {
        this.genotypesCount = genotypesCount;
    }

    public Map<Genotype, Float> getGenotypesFreq() {
        return genotypesFreq;
    }

    public void setGenotypesFreq(Map<Genotype, Float> genotypesFreq) {
        this.genotypesFreq = genotypesFreq;
    }

    public float getMaf() {
        return maf;
    }

    public void setMaf(float maf) {
        this.maf = maf;
    }

    public float getMgf() {
        return mgf;
    }

    public void setMgf(float mgf) {
        this.mgf = mgf;
    }

    public int getMissingAlleles() {
        return missingAlleles;
    }

    public void setMissingAlleles(int missingAlleles) {
        this.missingAlleles = missingAlleles;
    }

    public int getMissingGenotypes() {
        return missingGenotypes;
    }

    public void setMissingGenotypes(int missingGenotypes) {
        this.missingGenotypes = missingGenotypes;
    }

    public int getMendelianErrors() {
        return mendelianErrors;
    }

    public void setMendelianErrors(int mendelianErrors) {
        this.mendelianErrors = mendelianErrors;
    }

    public float getCasesPercentDominant() {
        return casesPercentDominant;
    }

    public void setCasesPercentDominant(float casesPercentDominant) {
        this.casesPercentDominant = casesPercentDominant;
    }

    public float getControlsPercentDominant() {
        return controlsPercentDominant;
    }

    public void setControlsPercentDominant(float controlsPercentDominant) {
        this.controlsPercentDominant = controlsPercentDominant;
    }

    public float getCasesPercentRecessive() {
        return casesPercentRecessive;
    }

    public void setCasesPercentRecessive(float casesPercentRecessive) {
        this.casesPercentRecessive = casesPercentRecessive;
    }

    public float getControlsPercentRecessive() {
        return controlsPercentRecessive;
    }

    public void setControlsPercentRecessive(float controlsPercentRecessive) {
        this.controlsPercentRecessive = controlsPercentRecessive;
    }

    public void setRefAllele(String refAllele) {
        this.refAllele = refAllele;
    }

    public int getTransitionsCount() {
        return transitionsCount;
    }

    public void setTransitionsCount(int transitionsCount) {
        this.transitionsCount = transitionsCount;
    }

    public int getTransversionsCount() {
        return transversionsCount;
    }

    public void setTransversionsCount(int transversionsCount) {
        this.transversionsCount = transversionsCount;
    }

    public VariantHardyWeinbergStats getHw() {
        return hw;
    }

    public boolean isIndel() {
        return variant.getType() == VariantType.INDEL;
    }

    public boolean isSNP() {
        return variant.getType() == VariantType.SNV;
    }

    public boolean hasPassedFilters() {
        return passedFilters;
    }

    public void setPassedFilters(boolean passedFilters) {
        this.passedFilters = passedFilters;
    }

    public boolean isPedigreeStatsAvailable() {
        return pedigreeStatsAvailable;
    }

    public void setPedigreeStatsAvailable(boolean pedigreeStatsAvailable) {
        this.pedigreeStatsAvailable = pedigreeStatsAvailable;
    }

    public float getQuality() {
        return quality;
    }

    public void setQuality(float quality) {
        this.quality = quality;
    }

    public int getNumSamples() {
        return numSamples;
    }

    public void setNumSamples(int numSamples) {
        this.numSamples = numSamples;
    }

    @Override
    public String toString() {
        return "VariantStats{"
                + "chromosome='" + chromosome + '\''
                + ", position=" + position
                + ", refAllele='" + refAllele + '\''
                + ", altAllele='" + altAllele + '\''
                + ", mafAllele='" + mafAllele + '\''
                + ", mgfAllele='" + mgfGenotype + '\''
                + ", maf=" + maf
                + ", mgf=" + mgf
                + ", missingAlleles=" + missingAlleles
                + ", missingGenotypes=" + missingGenotypes
                + ", mendelinanErrors=" + mendelianErrors
                + ", casesPercentDominant=" + casesPercentDominant
                + ", controlsPercentDominant=" + controlsPercentDominant
                + ", casesPercentRecessive=" + casesPercentRecessive
                + ", controlsPercentRecessive=" + controlsPercentRecessive
                + ", transitionsCount=" + transitionsCount
                + ", transversionsCount=" + transversionsCount
                + '}';
    }

    public VariantStats calculate(Map<String, Map<String, String>> samplesData, Map<String, String> attributes, Pedigree pedigree) {
        int[] allelesCount = new int[2];
        int totalAllelesCount = 0, totalGenotypesCount = 0;

        float controlsDominant = 0, casesDominant = 0;
        float controlsRecessive = 0, casesRecessive = 0;

        this.setNumSamples(samplesData.size());
        this.setPedigreeStatsAvailable(pedigree != null);

        for (Map.Entry<String, Map<String, String>> sample : samplesData.entrySet()) {
            String sampleName = sample.getKey();
            Genotype g = new Genotype(sample.getValue().get("GT"), this.getRefAllele(), this.getAltAllele());
            this.addGenotype(g);

            // Check missing alleles and genotypes
            switch (g.getCode()) {
                case ALLELES_OK:
                    // Both alleles set
                    allelesCount[g.getAllele(0)]++;
                    allelesCount[g.getAllele(1)]++;

                    totalAllelesCount += 2;
                    totalGenotypesCount++;

                    // Counting genotypes for Hardy-Weinberg (all phenotypes)
                    if (g.isAlleleRef(0) && g.isAlleleRef(1)) { // 0|0
                        this.getHw().incN_AA();
                    } else if ((g.isAlleleRef(0) && g.getAllele(1) == 1) || (g.getAllele(0) == 1 && g.isAlleleRef(1))) {  // 0|1, 1|0
                        this.getHw().incN_Aa();

                    } else if (g.getAllele(0) == 1 && g.getAllele(1) == 1) {
                        this.getHw().incN_aa();
                    }

                    break;
                case HAPLOID:
                    // Haploid (chromosome X/Y)
                    try {
                        allelesCount[g.getAllele(0)]++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("vcfRecord = " + variant);
                        System.out.println("g = " + g);
                    }
                    totalAllelesCount++;
                    break;
                case MULTIPLE_ALTERNATES:
                    // Alternate with different "index" than the one that is being handled
                    break;
                default:
                    // Missing genotype (one or both alleles missing)
                    this.setMissingGenotypes(this.getMissingGenotypes() + 1);
                    if (g.getAllele(0) < 0) {
                        this.setMissingAlleles(this.getMissingAlleles() + 1);
                    } else {
                        allelesCount[g.getAllele(0)]++;
                        totalAllelesCount++;
                    }

                    if (g.getAllele(1) < 0) {
                        this.setMissingAlleles(this.getMissingAlleles() + 1);
                    } else {
                        allelesCount[g.getAllele(1)]++;
                        totalAllelesCount++;
                    }
                    break;

            }

            // Include statistics that depend on pedigree information
            if (pedigreeStatsAvailable) {
                if (g.getCode() == AllelesCode.ALLELES_OK || g.getCode() == AllelesCode.HAPLOID) {
                    Individual ind = pedigree.getIndividual(sampleName);
//                    if (MendelChecker.isMendelianError(ind, g, variant.getChromosome(), file.getSamplesData())) {
//                        this.setMendelianErrors(this.getMendelianErrors() + 1);
//                    }
                    if (g.getCode() == AllelesCode.ALLELES_OK) {
                        // Check inheritance models
                        if (ind.getCondition() == Condition.UNAFFECTED) {
                            if (g.isAlleleRef(0) && g.isAlleleRef(1)) { // 0|0
                                controlsDominant++;
                                controlsRecessive++;

                            } else if ((g.isAlleleRef(0) && !g.isAlleleRef(1)) || (!g.isAlleleRef(0) || g.isAlleleRef(1))) { // 0|1 or 1|0
                                controlsRecessive++;

                            }
                        } else if (ind.getCondition() == Condition.AFFECTED) {
                            if (!g.isAlleleRef(0) && !g.isAlleleRef(1) && g.getAllele(0) == g.getAllele(1)) {// 1|1, 2|2, and so on
                                casesRecessive++;
                                casesDominant++;
                            } else if (!g.isAlleleRef(0) || !g.isAlleleRef(1)) { // 0|1, 1|0, 1|2, 2|1, 1|3, and so on
                                casesDominant++;

                            }
                        }

                    }
                }
            } 

        }  // Finish all samples loop
        
        // Set counts for each allele
        this.setRefAlleleCount(allelesCount[0]);
        this.setAltAlleleCount(allelesCount[1]);

        // Calculate MAF and MGF
        this.calculateAlleleAndGenotypeFrequencies(totalAllelesCount, totalGenotypesCount);

        // Calculate Hardy-Weinberg statistic
        this.getHw().calculate();

        // Transitions and transversions
        this.calculateTransitionsAndTransversions(variant.getReference(), variant.getAlternate());

        // Update variables finally used to update file_stats_t structure
        if ("PASS".equalsIgnoreCase(attributes.get("FILTER"))) {
            this.setPassedFilters(true);
        }

        if (attributes.containsKey("QUAL") && !(".").equals(attributes.get("QUAL"))) {
            float qualAux = Float.valueOf(attributes.get("QUAL"));
            if (qualAux >= 0) {
                this.setQuality(qualAux);
            }
        }

        // Once all samples have been traversed, calculate % that follow inheritance model
        controlsDominant = controlsDominant * 100 / (this.getNumSamples() - this.getMissingGenotypes());
        casesDominant = casesDominant * 100 / (this.getNumSamples() - this.getMissingGenotypes());
        controlsRecessive = controlsRecessive * 100 / (this.getNumSamples() - this.getMissingGenotypes());
        casesRecessive = casesRecessive * 100 / (this.getNumSamples() - this.getMissingGenotypes());

        this.setCasesPercentDominant(casesDominant);
        this.setControlsPercentDominant(controlsDominant);
        this.setCasesPercentRecessive(casesRecessive);
        this.setControlsPercentRecessive(controlsRecessive);

        return this;
    }

    /**
     * Calculates the statistics for some variants read from a set of files, and 
     * optionally given pedigree information. Some statistics like inheritance 
     * patterns can only be calculated if pedigree information is provided.
     * 
     * @param variants The variants whose statistics will be calculated
     * @param ped Optional pedigree information to calculate some statistics
     */
    public static void calculateStatsForVariantsList(List<Variant> variants, Pedigree ped) {
        for (Variant variant : variants) {
            for (ArchivedVariantFile file : variant.getFiles().values()) {
                VariantStats stats = new VariantStats(variant).calculate(file.getSamplesData(), file.getAttributes(), ped);
                file.setStats(stats); // TODO Correct?
            }
        }
    }

    private void calculateAlleleAndGenotypeFrequencies(int totalAllelesCount, int totalGenotypesCount) {
        // MAF
        refAlleleFreq = (totalAllelesCount > 0) ? refAlleleCount / (float) totalAllelesCount : 0;
        altAlleleFreq = (totalAllelesCount > 0) ? altAlleleCount / (float) totalAllelesCount : 0;
        if (refAlleleFreq <= altAlleleFreq) {
            this.setMaf(refAlleleFreq);
            this.setMafAllele(refAllele);
        } else {
            this.setMaf(altAlleleFreq);
            this.setMafAllele(altAllele);
        }

        // MGF
        float currMgf = Float.MAX_VALUE;
        Genotype currMgfGenotype = null;
        for (Map.Entry<Genotype, Integer> gtCount : genotypesCount.entrySet()) {
            float freq = (totalGenotypesCount > 0) ? gtCount.getValue() / (float) totalGenotypesCount : 0;
            genotypesFreq.put(gtCount.getKey(), freq);
            if (freq < currMgf) {
                currMgf = freq;
                currMgfGenotype = gtCount.getKey();
            }
        }
        
        if (currMgfGenotype != null) {
            this.setMgf(currMgf);
            this.setMgfGenotype(currMgfGenotype.toString());
        }
        
    }

    private void calculateTransitionsAndTransversions(String reference, String alternate) {
        int numTransitions = 0, numTranversions = 0;

        if (reference.length() == 1 && alternate.length() == 1) {
            switch (reference.toUpperCase()) {
                case "C":
                    if (alternate.equalsIgnoreCase("T")) {
                        numTransitions++;
                    } else {
                        numTranversions++;
                    }
                    break;
                case "T":
                    if (alternate.equalsIgnoreCase("C")) {
                        numTransitions++;
                    } else {
                        numTranversions++;
                    }
                    break;
                case "A":
                    if (alternate.equalsIgnoreCase("G")) {
                        numTransitions++;
                    } else {
                        numTranversions++;
                    }
                    break;
                case "G":
                    if (alternate.equalsIgnoreCase("A")) {
                        numTransitions++;
                    } else {
                        numTranversions++;
                    }
                    break;
            }
        }

        this.setTransitionsCount(numTransitions);
        this.setTransversionsCount(numTranversions);
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.variant);
        hash = 13 * hash + Objects.hashCode(this.chromosome);
        hash = 13 * hash + (int) (this.position ^ (this.position >>> 32));
        hash = 13 * hash + Objects.hashCode(this.refAllele);
        hash = 13 * hash + Objects.hashCode(this.altAllele);
        hash = 13 * hash + this.refAlleleCount;
        hash = 13 * hash + this.altAlleleCount;
        hash = 13 * hash + Objects.hashCode(this.genotypesCount);
        hash = 13 * hash + this.missingAlleles;
        hash = 13 * hash + this.missingGenotypes;
        hash = 13 * hash + Float.floatToIntBits(this.refAlleleFreq);
        hash = 13 * hash + Float.floatToIntBits(this.altAlleleFreq);
        hash = 13 * hash + Objects.hashCode(this.genotypesFreq);
        hash = 13 * hash + Float.floatToIntBits(this.maf);
        hash = 13 * hash + Float.floatToIntBits(this.mgf);
        hash = 13 * hash + Objects.hashCode(this.mafAllele);
        hash = 13 * hash + Objects.hashCode(this.mgfGenotype);
        hash = 13 * hash + (this.passedFilters ? 1 : 0);
        hash = 13 * hash + (this.pedigreeStatsAvailable ? 1 : 0);
        hash = 13 * hash + this.mendelianErrors;
        hash = 13 * hash + Float.floatToIntBits(this.casesPercentDominant);
        hash = 13 * hash + Float.floatToIntBits(this.controlsPercentDominant);
        hash = 13 * hash + Float.floatToIntBits(this.casesPercentRecessive);
        hash = 13 * hash + Float.floatToIntBits(this.controlsPercentRecessive);
        hash = 13 * hash + this.transitionsCount;
        hash = 13 * hash + this.transversionsCount;
        hash = 13 * hash + Float.floatToIntBits(this.quality);
        hash = 13 * hash + this.numSamples;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VariantStats other = (VariantStats) obj;
        if (!Objects.equals(this.chromosome, other.chromosome)) {
            return false;
        }
        if (this.position != other.position) {
            return false;
        }
        if (!Objects.equals(this.refAllele, other.refAllele)) {
            return false;
        }
        if (!Objects.equals(this.altAllele, other.altAllele)) {
            return false;
        }
        if (this.refAlleleCount != other.refAlleleCount) {
            return false;
        }
        if (this.altAlleleCount != other.altAlleleCount) {
            return false;
        }
        if (!Objects.equals(this.genotypesCount, other.genotypesCount)) {
            return false;
        }
        if (this.missingAlleles != other.missingAlleles) {
            return false;
        }
        if (this.missingGenotypes != other.missingGenotypes) {
            return false;
        }
        if (Float.floatToIntBits(this.refAlleleFreq) != Float.floatToIntBits(other.refAlleleFreq)) {
            return false;
        }
        if (Float.floatToIntBits(this.altAlleleFreq) != Float.floatToIntBits(other.altAlleleFreq)) {
            return false;
        }
        if (!Objects.equals(this.genotypesFreq, other.genotypesFreq)) {
            return false;
        }
        if (Float.floatToIntBits(this.maf) != Float.floatToIntBits(other.maf)) {
            return false;
        }
        if (Float.floatToIntBits(this.mgf) != Float.floatToIntBits(other.mgf)) {
            return false;
        }
        if (!Objects.equals(this.mafAllele, other.mafAllele)) {
            return false;
        }
        if (!Objects.equals(this.mgfGenotype, other.mgfGenotype)) {
            return false;
        }
        if (this.passedFilters != other.passedFilters) {
            return false;
        }
        if (this.pedigreeStatsAvailable != other.pedigreeStatsAvailable) {
            return false;
        }
        if (this.mendelianErrors != other.mendelianErrors) {
            return false;
        }
        if (Float.floatToIntBits(this.casesPercentDominant) != Float.floatToIntBits(other.casesPercentDominant)) {
            return false;
        }
        if (Float.floatToIntBits(this.controlsPercentDominant) != Float.floatToIntBits(other.controlsPercentDominant)) {
            return false;
        }
        if (Float.floatToIntBits(this.casesPercentRecessive) != Float.floatToIntBits(other.casesPercentRecessive)) {
            return false;
        }
        if (Float.floatToIntBits(this.controlsPercentRecessive) != Float.floatToIntBits(other.controlsPercentRecessive)) {
            return false;
        }
        if (this.transitionsCount != other.transitionsCount) {
            return false;
        }
        if (this.transversionsCount != other.transversionsCount) {
            return false;
        }
        if (Float.floatToIntBits(this.quality) != Float.floatToIntBits(other.quality)) {
            return false;
        }
        if (this.numSamples != other.numSamples) {
            return false;
        }
        return true;
    }
    

}

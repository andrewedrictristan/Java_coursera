import edu.duke.*;
/**
 * Beschreiben Sie hier die Klasse Part1.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Part1 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex +3);
        while(currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }
            else{
                currIndex= dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    
    
    public void testFindStopCOdon(){
        String dna= "AATG34567890123TAA567TAA";
        int dex = findStopCodon(dna,0,"TAA");
        if(dex != 15) System.out.println("error on 15");
        
        dex = findStopCodon(dna,15,"TAA");
        if(dex != 21) System.out.println("error on 21");
        
        
        System.out.println("test finished");
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1){
            return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //int minIndex = Math.min(taaIndex, Math.min(tagIndex,tgaIndex));
        int minIndex=0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else{
            minIndex= taaIndex;
        }
        
       
        
        
        if(minIndex == -1 || (tagIndex != 1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        
        
         if(minIndex == -1 ){
            return "";
        }
        return dna.substring(startIndex, minIndex +3);
    }
    
    public void testFindGene(){
        int startIndex=0;
        String dna= "ATGCCCGGGAAATACGCCTAA";
        String gene= findGene(dna,startIndex);
        System.out.println("first dna " + dna);
        
        System.out.println("the found gene is: "+ gene);
       
        
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna,startIndex);
            
            if(currentGene.isEmpty() ){
                break;
            }
            
            System.out.println(currentGene);
           
            startIndex= dna.indexOf(currentGene , startIndex) + currentGene.length();
            
           
            
            
        }
        
    }
    
     public StorageResource getAllGenes(String dna){
         StorageResource geneList= new StorageResource();
         
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna,startIndex);
            
            if(currentGene.isEmpty() ){
                break;
            }
            
            //System.out.println(currentGene);
            geneList.add(currentGene);
            startIndex= dna.indexOf(currentGene , startIndex) + currentGene.length();
            
           
            
            
        }
        return geneList;
    }
    
    public int charCounter(String dna, String character){
        int times=0;
        int startIndex = dna.indexOf(character);
        while(true){
            if(startIndex == -1){
             break;   
            }
            times= times+1;
            startIndex= dna.indexOf(character,startIndex+1);
        }
        return times;
    }
    
    public float cgRatio(String dna){
        float totalCharacter = charCounter(dna,"C") + charCounter(dna,"G");
        float ratio = totalCharacter / dna.length();
        return ratio;
    }
    
    public void testOn(String dna){
        // System.out.println(dna);
        // printAllGenes(dna);
        System.out.println("Testing getAllGennes on " + dna);
        StorageResource genes= getAllGenes(dna);
        for(String g : genes.data()){
           System.out.println(g);
        }
    }
    
    public int countCTG(String dna){
        int times=0;
        int startIndex= dna.indexOf("CTG");
        while(true){
            if(startIndex == -1){
                break;
            }
            times +=1;
            startIndex= dna.indexOf("CTG",startIndex+3);
        }
        return times;
    }
    
    public void processGene(StorageResource sr){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        
        
    }
    
    public void test(){
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        
        
       
       
    }
    
    
}

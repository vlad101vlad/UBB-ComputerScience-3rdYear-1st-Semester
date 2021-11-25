package ro.ubb.cluj.service;

import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.domain.Production;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GrammarReader {
    private final String filePath;

    public GrammarReader(String filePath) {
        this.filePath = filePath;
    }

    public GrammarModel readGrammar() throws Exception {
        GrammarModel grammarModel = new GrammarModel();
        List<Production> productionList = new ArrayList<>();
        grammarModel.setProductions(productionList);

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        int lineCounter = 0;
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(lineCounter == 0){
                grammarModel.setInitialNonTerminal(line);
            }
            if(lineCounter == 1 || lineCounter == 2){
                String[] strings = line.split("\\s+");
                List<String> list = new ArrayList<>(Arrays.asList(strings));

                if(lineCounter == 1)
                    grammarModel.setNonTerminals(list);
                if(lineCounter == 2)
                    grammarModel.setTerminals(list);
            }
            if(lineCounter > 2){
                Production production = this.readProductions(line);
                productionList.add(production);
            }

            lineCounter++;
        }

        return grammarModel;
    }

    private Production readProductions(String line) throws Exception {
        Production production = new Production();

        String[] nonTerminalWithProduction = line.split("=");

        String[] productionRule = nonTerminalWithProduction[1].split("\\s+");
        List<String> productionRuleList =  this.removeEmptyStrings(new ArrayList<>(Arrays.asList(productionRule)));

        if(!isCFG(nonTerminalWithProduction[0].strip()))
            throw new Exception("GRAMMAR IS NOT CFG!");

        production.setNonTerminal(nonTerminalWithProduction[0].strip());
        production.setProductionRule(productionRuleList);

        return production;
    }

    private boolean isCFG(String leftSideNonTerminals){
        String[] nonTerminals = leftSideNonTerminals.split("\\s+");
        return nonTerminals.length <= 1;
    }

    private List<String> removeEmptyStrings(List<String> list){
        return list.stream().filter(string -> !string.equals(""))
                .collect(Collectors.toList());
    }


}

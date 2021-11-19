package ro.ubb.cluj.service;

import ro.ubb.cluj.domain.GrammarModel;
import ro.ubb.cluj.domain.Production;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GrammarReader {
    private final String filePath;

    public GrammarReader(String filePath) {
        this.filePath = filePath;
    }

    public GrammarModel readGrammar() throws FileNotFoundException {
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

    private Production readProductions(String line){
        Production production = new Production();

        String[] nonTerminalWithProduction = line.split("=");

        String[] productionRule = nonTerminalWithProduction[1].split("\\s+");
        List<String> productionRuleList = new ArrayList<>(Arrays.asList(productionRule));

        production.setNonTerminal(nonTerminalWithProduction[0]);
        production.setProductionRule(productionRuleList);

        return production;
    }


}
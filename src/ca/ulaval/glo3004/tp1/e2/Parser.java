package ca.ulaval.glo3004.tp1.e2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {
	public List<List<String>> parse(File fileToParse, String type) throws FileNotFoundException {
        List<List<String>> simulations = new ArrayList<List<String>>();
        Scanner input = new Scanner(fileToParse);
        String actionSplitter = type.equals("txt") ?  ",": ";";
        while (input.hasNext()) {
            String nextLine = input.nextLine().trim();
            String[] actions = nextLine.split(actionSplitter);
            simulations.add(Arrays.asList(actions));
        }
        input.close();
        return simulations;
    }
}

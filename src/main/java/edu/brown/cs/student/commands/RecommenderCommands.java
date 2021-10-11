package edu.brown.cs.student.commands;

import edu.brown.cs.student.recommender.RecommendationGenerator;

public class RecommenderCommands implements REPLCommand {

    @Override
    public void handle(String[] args) {
        if(args[0].equals("recsys_rec")) {
            RecommendationGenerator gen = new RecommendationGenerator(args);
            gen.recsys_rc();
        }
    }

}

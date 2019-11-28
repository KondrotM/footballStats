package uk.ac.glos.ct5025.s1804317.footballStats;

import java.util.ArrayList;

public class Item {
    private int goals;
    private String name;

    public Item(String tempName){
        name = tempName;
        goals = 0;
    }

    public void scoreGoal() {
        goals++;
    }

    public String getName() { return name; }

    public int getGoals() { return goals; }

    public void init(){
        goals = 0;
    }

    public static Item getItemFromList(ArrayList itemList){
        System.out.println(".. Back");
        Item currItem;
        for (int i = 0; i < itemList.size(); i++) {
            currItem = (Item) itemList.get(i);
            System.out.println(i+1 + ". " + currItem.getName());
        }
        String choice = main.getInput(null);
        if (choice.equals("..")){
            return null;
        } else {
            int itemNo = Integer.parseInt(choice);
            currItem = (Item) itemList.get(itemNo-1);
            return currItem;
        }
    }

}
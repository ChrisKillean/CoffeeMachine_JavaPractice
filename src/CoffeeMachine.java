import java.util.Scanner;

public class CoffeeMachine {
    // inventory inside machine
    private static int waterLevel = 1200;
    private static int milkLevel = 540;
    private static int coffeeBeans = 120;
    private static int disposableCups = 9;
    private static int moneyTotal = 550;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean keepGoing = true;

        String userCommand;

        inventoryPrintout();

        // match user command to relevant function
        do {

            inventoryPrintout();
            // take user command
            System.out.println("Write action (buy, fill, take): ");
            userCommand = scan.next().toLowerCase();

            switch(userCommand) {
                case "buy":
                    buyDrink();
                    continue;

                case "fill":
                    fillMachine();
                    continue;

                case "take":
                    emptyMoney();
                    continue;

                default:
                    System.out.println("Command not recognised, try again");
            }
        } while(true);
    }

    private static void inventoryPrintout() {
        // Prints the inventory of the coffee machine
        System.out.println("The coffee machine has:");
        System.out.println(getWater() + "ml of water");
        System.out.println(getMilk() + "ml of milk");
        System.out.println(getBeans() + "g of coffee beans");
        System.out.println(getCups() + " disposable cups");
        System.out.println("£" + getMoney() + " of money");
        System.out.println();
    }

    private static void buyDrink() {
        /*
        Takes the order when the user wishes to buy a drink. Calls a function to check the machine has enough
        ingredients to make the drink, before updating inventory if it does
         */


        // Ingredient requirements and cost of each drink
        // expresso
        final int expressoWater = 250;
        final int expressoBeans = 16;
        final int expressoCost = 4;
        // latte
        final int latteWater = 350;
        final int latteMilk = 75;
        final int latteBeans = 20;
        final int latteCost = 7;
        // cappuccino
        final int cappuccinoWater = 200;
        final int cappuccinoMilk = 100;
        final int cappuccinoBeans = 12;
        final int cappuccinoCost = 6;

        boolean orderTaken = false;
        Scanner scan = new Scanner(System.in);

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");

        // loops until user enters and order the machine can fill.
        do {
            int userOrder = scan.nextInt();

            switch(userOrder) {
                case 1:
                    orderTaken = checkPlaceOrder(expressoWater, expressoBeans, expressoCost);
                    continue;

                case 2:
                    orderTaken = checkPlaceOrder(latteWater, latteMilk, latteBeans, latteCost);
                    continue;

                case 3:
                    orderTaken = checkPlaceOrder(cappuccinoWater, cappuccinoMilk, cappuccinoBeans, cappuccinoCost);
                    continue;

                default:
                    System.out.println("Order not recognised");
            }
        } while(!orderTaken);
    }

    private static boolean checkPlaceOrder(int water, int milk, int beans, int cost) {
        // Assesses ability to make beverage for drinks which need water, milk and beans

        // checking there are enough ingredients and updates stock levels if there is
        if(getWater() >= water && getMilk() >= milk && getBeans() >= beans && getCups() > 0) {
            setWater(getWater() - water);
            setMilk(getMilk() - milk);
            setBeans(getBeans() - beans);
            setCups(getCups() - 1);
            setMoney(getMoney() + cost);

            System.out.println();
            return true;
        }

        // else print saying drink cannot be made
        System.out.println("This option is not available");
        System.out.println();
        return false;
    }

    private static boolean checkPlaceOrder(int water, int beans, int cost) {
        // Assesses ability to make beverage for drinks which need water and beans

        // checking there are enough ingredients and updates stock levels if there is
        if(getWater() >= water && getBeans() >= beans && getCups() > 0) {
            setWater(getWater() - water);
            setBeans(getBeans() - beans);
            setCups(getCups() - 1);
            setMoney(getMoney() + cost);

            System.out.println();
            return true;

        }

        // else print saying drink cannot be made
        System.out.println("This option is not available");
        System.out.println();
        return false;

    }

    private static void fillMachine() {
        // Asks user how much water, milk, coffee and cups they are adding then updates these values
        Scanner scan = new Scanner(System.in);

        System.out.println("Write how many ml of water do you want to add:");
        setWater(getWater() + scan.nextInt());

        System.out.println("Write how many ml of milk do you want to add:");
        setMilk(getMilk() + scan.nextInt());

        System.out.println("Write how many grams of coffee beans do you want to add:");
        setBeans(getBeans() + scan.nextInt());

        System.out.println("Write how many disposable cups of coffee do you want to add:");
        setCups(getCups() + scan.nextInt());
    }

    private static void emptyMoney() {
        // remove all money from machine, update value and tell user how much money they have been given
        int allMoney = getMoney();
        setMoney(0);
        System.out.println("I game you £" + allMoney);
        System.out.println();
    }

    private static void setWater(int water) {
        waterLevel = water;
    }

    private static int getWater() {
        return waterLevel;
    }

    private static void setMilk(int milk) {
        milkLevel = milk;
    }

    private static int getMilk() {
        return milkLevel;
    }

    private static void setBeans(int beans) {
        coffeeBeans = beans;
    }

    private static int getBeans() {
        return coffeeBeans;
    }

    private static void setCups(int cups) {
        disposableCups = cups;
    }

    private static int getCups() {
        return disposableCups;
    }

    private static void setMoney(int money) {
        moneyTotal = money;
    }

    private static int getMoney() {
        return moneyTotal;
    }
}
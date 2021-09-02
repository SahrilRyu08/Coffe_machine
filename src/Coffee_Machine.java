import java.util.Scanner;

class Coffee_Machine {
        int totalWater = 400;
        int totalMilk = 540;
        int totalBeans = 120;
        int disposableCups = 9;
        int totalMoney = 550;

        public void printSupplies() {
            System.out.println("The coffee machine has:\n"
                    + totalWater + " ml of water\n"
                    + totalMilk + " ml of milk\n"
                    + totalBeans + " g of beans\n"
                    + disposableCups + " disposable cups\n$"
                    + totalMoney + " of money");
        }

        public void addSupplies(int water, int milk, int beans, int cups) {
            totalWater += water;
            totalMilk += milk;
            totalBeans += beans;
            disposableCups += cups;
        }

        public void withdrawMoney() {
            System.out.println("I gave you $" + totalMoney);
            totalMoney = 0;
        }

        public void buyCoffee(int choice) {
            Coffee chosenCoffee = null;
            switch (choice) {
                case 1:
                    chosenCoffee = new Espresso(250, 0, 16, 4);
                    break;
                case 2:
                    chosenCoffee = new Latte(350, 75, 20, 7);
                    break;
                case 3:
                    chosenCoffee = new Cappuccino(200, 100, 12, 6);
                    break;
                default:
                    break;
            }
            makeCoffee(chosenCoffee);
        }

        public void makeCoffee(Coffee coffee) {
            boolean enoughWater = totalWater >= coffee.water;
            boolean enoughMilk = totalMilk >= coffee.milk;
            boolean enoughBeans = totalBeans >= coffee.beans;
            boolean enoughCups = disposableCups >= 1;

            if (enoughWater && enoughMilk && enoughBeans && enoughCups) {
                System.out.println("I have enough resources, making you a coffee!");
                totalWater -= coffee.water;
                totalMilk -= coffee.milk;
                totalBeans -= coffee.beans;
                totalMoney += coffee.price;
                disposableCups -= 1;
            } else {
                if (!enoughWater) {
                    System.out.println("Sorry, not enough water!");
                } else if (!enoughMilk) {
                    System.out.println("Sorry, not enough milk!");
                } else if (!enoughBeans) {
                    System.out.println("Sorry, not enough beans!");
                } else if (!enoughCups) {
                    System.out.println("Sorry, not enough cups!");
                }
            }
        }

        public static void main(String[] args) {
            Coffee_Machine machine = new Coffee_Machine();
            Scanner scanner = new Scanner(System.in);
            String action = "";
            boolean isRunning = true;

            while (isRunning) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");

                action = scanner.next();


                switch (action) {
                    case "buy":
                        String input = "";
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                        input = scanner.next();
                        if (input.equals("back")) {
                            break;
                        } else {
                            machine.buyCoffee(Integer.parseInt(input));
                        }
                        break;
                    case "fill":
                        int addWater, addMilk, addBeans, addCups;
                        System.out.println("Write how many ml of water you want to add: ");
                        addWater = scanner.nextInt();
                        System.out.println("Write how many ml of milk you want to add: ");
                        addMilk = scanner.nextInt();
                        System.out.println("Write how many grams of coffee beans you want to add: ");
                        addBeans = scanner.nextInt();
                        System.out.println("Write how many disposable cups of coffee you want to add: ");
                        addCups = scanner.nextInt();
                        machine.addSupplies(addWater, addMilk, addBeans, addCups);
                        break;
                    case "take":
                        machine.withdrawMoney();
                        break;
                    case "remaining":
                        machine.printSupplies();
                        break;
                    case "exit":
                        isRunning = false;
                        break;
                }
            }
        }
    }

    class Coffee {
        int water;
        int milk;
        int beans;
        int price;

        public Coffee(int water, int milk, int beans, int price) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.price = price;
        }
    }

    class Espresso extends Coffee {
        public Espresso(int water, int milk, int beans, int price) {
            super(water, milk, beans, price);
        }
    }

    class Latte extends Coffee {
        public Latte(int water, int milk, int beans, int price) {
            super(water, milk, beans, price);
        }
    }

    class Cappuccino extends Coffee {
        public Cappuccino(int water, int milk, int beans, int price) {
            super(water, milk, beans, price);
        }

    }